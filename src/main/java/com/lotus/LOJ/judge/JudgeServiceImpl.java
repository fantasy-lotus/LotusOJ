package com.lotus.LOJ.judge;

import cn.hutool.json.JSONUtil;
import com.lotus.LOJ.common.ErrorCode;
import com.lotus.LOJ.exception.BusinessException;
import com.lotus.LOJ.judge.sandBox.CodeSandBox;
import com.lotus.LOJ.judge.sandBox.CodeSandBoxFactory;
import com.lotus.LOJ.judge.sandBox.model.ExecuteCodeRequest;
import com.lotus.LOJ.judge.sandBox.model.ExecuteCodeResponse;
import com.lotus.LOJ.judge.strategy.JudgeContext;
import com.lotus.LOJ.model.dto.question.JudgeCase;
import com.lotus.LOJ.model.dto.question.JudgeConfig;
import com.lotus.LOJ.model.dto.submit.JudgeInfo;
import com.lotus.LOJ.model.entity.Question;
import com.lotus.LOJ.model.entity.Submit;
import com.lotus.LOJ.service.QuestionService;
import com.lotus.LOJ.service.SubmitService;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 判题服务实现类
 */
@Service
@Slf4j
public class JudgeServiceImpl implements JudgeService{

    @Autowired
    @Lazy
    private SubmitService submitService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private JudgeMannger judgeMannger;

    //配置文件获取代码沙箱类型
    @Value("${codeSandBox.type}")
    private String type;

    @Override
    public JudgeInfo doJudge(long submitId) {
        if(submitId<=0)
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数错误");
        Submit submit = submitService.getById(submitId);
        if(submit==null)
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数错误");
        String language = submit.getLanguage();
        String code = submit.getCode();
        Integer status = submit.getStatus();
        if(status != 0){
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"该提交已判题");
        }

        //更新提交记录状态为判题中
        submit.setStatus(1);
        boolean b = submitService.updateById(submit);
        if(!b)
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"更新提交记录失败");
        Long questionId = submit.getQuestionId();
        Question question = questionService.getById(questionId);
        String judgeCase = question.getJudgeCase();
        List<String> input = new ArrayList<>();
        List<String> output = new ArrayList<>();
        for (JudgeCase aCase : JSONUtil.toList(judgeCase, JudgeCase.class)) {
            input.add(aCase.getInput());
            output.add(aCase.getOutput());
        }
        String judgeConfig = question.getJudgeConfig();
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                        .code(code)
                        .language(language)
                        .input(input)
                        .build();

        //工厂模式获取代码沙箱
        CodeSandBox codeSandBox = CodeSandBoxFactory.getCodeSandBox(type);
        if(codeSandBox == null){
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"请检查判题配置");
        }

        //代码沙箱执行代码
        ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCode(executeCodeRequest);
        if(executeCodeResponse == null){
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"代码运行失败");
        }
        JudgeContext judgeContext = JudgeContext.builder()
                        .judgeConfig(JSONUtil.toBean(judgeConfig, JudgeConfig.class))
                        .output(executeCodeResponse.getOutput())
                        .target(output)
                        .time(executeCodeResponse.getTime())
                        .memory(executeCodeResponse.getMemory())
                        .submit(submit)
                        .build();

        //根据运行结果判题
        JudgeInfo judgeInfo = judgeMannger.doJudge(judgeContext);
        if(judgeInfo == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"不支持的答题语言");
        }
        submit.setStatus(judgeInfo.getResult().equals("AC")?2:-1);
        submit.setInfo(JSONUtil.toJsonStr(judgeInfo));
        boolean res = submitService.updateById(submit);
        if(!res)
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"更新提交记录失败");
        return judgeInfo;
    }
}

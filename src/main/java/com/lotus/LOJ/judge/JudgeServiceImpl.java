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
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class JudgeServiceImpl implements JudgeService{

    @Autowired
    @Lazy
    private SubmitService submitService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CodeSandBox codeSandBox;

    @Autowired
    private JudgeMannger judgeMannger;

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
        ExecuteCodeRequest executeCodeRequest = new ExecuteCodeRequest();
        executeCodeRequest.setCode(code);
        executeCodeRequest.setLanguage(language);
        executeCodeRequest.setInput(input);
        CodeSandBox codeSandBox = CodeSandBoxFactory.getCodeSandBox(type);
        ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCode(executeCodeRequest);
        if(executeCodeResponse == null){
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"判题失败");
        }
        JudgeContext judgeContext = JudgeContext.builder()
                        .judgeConfig(JSONUtil.toBean(judgeConfig, JudgeConfig.class))
                        .output(output)
                        .target(output)
                        .time(executeCodeResponse.getTime())
                        .memory(executeCodeResponse.getMemory())
                        .submit(submit)
                        .build();
        JudgeInfo judgeInfo = judgeMannger.doJudge(judgeContext);
        submit.setStatus(1);
        submit.setInfo(JSONUtil.toJsonStr(judgeInfo));
        boolean b = submitService.updateById(submit);
        if(!b)
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"更新提交记录失败");
        return judgeInfo;
    }
}

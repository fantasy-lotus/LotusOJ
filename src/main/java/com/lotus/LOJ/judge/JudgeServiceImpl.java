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
import com.lotus.LOJ.model.entity.Question;
import com.lotus.LOJ.model.entity.Submit;
import com.lotus.LOJ.service.QuestionService;
import com.lotus.LOJ.service.SubmitService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class JudgeServiceImpl implements JudgeService{

    @Autowired
    private SubmitService submitService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CodeSandBox codeSandBox;

    @Autowired
    private JudgeMannger judgeMannger;

    @Value("${codeSandBox.type")
    private String type;
    @Override
    public Long doJudge(long submitId) {
        if(submitId<=0)
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数错误");
        Submit submit = submitService.getById(submitId);
        if(submit==null)
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数错误");
        String language = submit.getLanguage();
        String code = submit.getCode();
        String info = submit.getInfo();
        Integer status = submit.getStatus();
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
        JudgeContext judgeContext = JudgeContext.builder()
                        .judgeConfig(judgeConfig)

        judgeMannger.doJudge()
        return null;
    }
}

package com.lotus.LOJ.judge.strategy;

import com.lotus.LOJ.model.dto.question.JudgeConfig;
import com.lotus.LOJ.model.dto.submit.JudgeInfo;

import java.util.List;

public class MyDefaultStrategy {
    public JudgeInfo doInnerJudge(JudgeContext judgeContext) {
        List<String> target = judgeContext.getTarget();
        List<String> output = judgeContext.getOutput();
        String time = judgeContext.getTime();
        String memory = judgeContext.getMemory();
        JudgeConfig judgeConfig = judgeContext.getJudgeConfig();
        Integer submitId = judgeContext.getSubmitId();

    }
}

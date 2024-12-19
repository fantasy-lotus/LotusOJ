package com.lotus.LOJ.judge;

import com.lotus.LOJ.judge.strategy.JudgeContext;
import com.lotus.LOJ.judge.strategy.MyDefaultStrategy;
import com.lotus.LOJ.model.dto.submit.JudgeInfo;
import org.springframework.stereotype.Service;

@Service
public class JudgeMannger {
    JudgeInfo doJudge(JudgeContext judgeContext) {
        //策略模式, 根据不同的语言选择不同的策略
        if(("java").equals(judgeContext.getSubmit().getLanguage())) {
            return new MyDefaultStrategy().doInnerJudge(judgeContext);
        }
        return null;
    }
}

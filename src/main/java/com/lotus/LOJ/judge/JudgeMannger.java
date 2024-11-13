package com.lotus.LOJ.judge;

import com.lotus.LOJ.judge.strategy.JudgeContext;
import com.lotus.LOJ.judge.strategy.MyDefaultStrategy;
import com.lotus.LOJ.model.dto.submit.JudgeInfo;
import org.springframework.stereotype.Service;

@Service
public class JudgeMannger {
    JudgeInfo doJudge(JudgeContext judgeContext) {
        if(("java").equals(judgeContext.getSubmit().getLanguage())) {
            return new MyDefaultStrategy().doInnerJudge(judgeContext);
        }
        return null;
    }
}

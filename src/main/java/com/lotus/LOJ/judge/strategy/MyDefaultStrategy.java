package com.lotus.LOJ.judge.strategy;

import com.lotus.LOJ.model.dto.question.JudgeConfig;
import com.lotus.LOJ.model.dto.submit.JudgeInfo;
import com.lotus.LOJ.model.entity.Submit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
public class MyDefaultStrategy {
    public JudgeInfo doInnerJudge(JudgeContext judgeContext) {
        log.info(judgeContext.toString());
        List<String> target = judgeContext.getTarget();
        List<String> output = judgeContext.getOutput();
        String time = judgeContext.getTime();
        String memory = judgeContext.getMemory();
        JudgeConfig judgeConfig = judgeContext.getJudgeConfig();
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setTime(time);
        judgeInfo.setMemory(memory);
        if(target.size()!=output.size()){
            judgeInfo.setResult("WA");
            return judgeInfo;
        }
        for(int i = 0;i<target.size();i++){
            if(!target.get(i).equals(output.get(i)))
                judgeInfo.setResult("WA");
        }
        if(time.compareTo(judgeConfig.getTimeLimit())>0){
            judgeInfo.setResult("TLE");
            return judgeInfo;
        }
        if(memory.compareTo(judgeConfig.getMemoryLimit())>0){
            judgeInfo.setResult("MLE");
            return judgeInfo;
        }
        judgeInfo.setResult("AC");
        return judgeInfo;
    }
}

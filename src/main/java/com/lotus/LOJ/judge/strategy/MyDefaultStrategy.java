package com.lotus.LOJ.judge.strategy;

import com.lotus.LOJ.model.dto.question.JudgeConfig;
import com.lotus.LOJ.model.dto.submit.JudgeInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 默认的判题策略
 * 获取由代码执行器返回的结果，与目标结果进行比对, 进行判题逻辑
 */
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
        if(output==null){
            judgeInfo.setResult("RE");
            return judgeInfo;
        }
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

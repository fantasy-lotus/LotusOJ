package com.lotus.LOJ.judge.strategy;

import com.lotus.LOJ.model.dto.question.JudgeConfig;
import com.lotus.LOJ.model.entity.Submit;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class JudgeContext {
    private List<String> target;

    private List<String> output;

    private String time;

    private String memory;

    private JudgeConfig judgeConfig;

    private Submit submit;
}

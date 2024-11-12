package com.lotus.LOJ.model.dto.question;

import lombok.Data;

import java.io.Serializable;

@Data
public class JudgeConfig implements Serializable {
    /**
     * 内存限制
     */
    private String memoryLimit;
    /**
     * 时间限制
     */
    private String timeLimit;

    private static final long serialVersionUID = -2737577828339250347L;
}

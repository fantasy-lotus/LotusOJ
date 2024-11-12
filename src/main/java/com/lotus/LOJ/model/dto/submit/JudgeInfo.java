package com.lotus.LOJ.model.dto.submit;

import lombok.Data;

import java.io.Serializable;

@Data
public class JudgeInfo implements Serializable {
    /**
     * 判题结果
     */
    private String result;

    /**
     * 运行时间
     */
    private String time;

    /**
     * 运行内存
     */
    private String memory;

    private static final long serialVersionUID = 7893775684046728555L;
}

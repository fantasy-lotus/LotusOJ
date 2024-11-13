package com.lotus.LOJ.model.dto.submit;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class JudgeInfo implements Serializable {
    /**
     * 判题结果
     */
    private List<String> output;

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

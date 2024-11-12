package com.lotus.LOJ.model.dto.submit;

import lombok.Data;

@Data
public class SubmitRequest {
    /**
     * 编程语言
     */
    private String language;

    /**
     * 提交代码
     */
    private String code;

    /**
     * 题目id
     */
    private Long questionId;

    /**
     * 提交用户id
     */
    private Long userid;

    /**
     * 提交时间
     */
    private String submitTime;
}

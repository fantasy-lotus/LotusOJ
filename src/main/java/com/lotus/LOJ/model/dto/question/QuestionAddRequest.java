package com.lotus.LOJ.model.dto.question;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 创建请求
 *

 */
@Data
public class QuestionAddRequest implements Serializable {

    /**
     * 标题
     */
    private String title;

    /**
     * 题干
     */
    private String content;

    /**
     * 测试点 - json数组
     */
    private List<JudgeCase> judgeCase;

    /**
     * 判题限制 - json对象
     */
    private JudgeConfig judgeConfig;

    private static final long serialVersionUID = 1L;
}
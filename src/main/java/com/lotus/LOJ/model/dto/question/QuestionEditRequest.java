package com.lotus.LOJ.model.dto.question;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 编辑请求
 *

 */
@Data
public class QuestionEditRequest implements Serializable {

    /**
     * id
     */
    private Long id;

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
    private String judgeCase;

    /**
     * 判题限制 - json对象
     */
    private String judgeConfig;

    private static final long serialVersionUID = 1L;
}
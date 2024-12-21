package com.lotus.LOJ.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 题目
 * @TableName question
 */
@TableName(value ="question")
@Data
public class Question implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 标题
     */
    @NotBlank(message = "标题不能为空")
    @Size(max = 16,message = "标题长度应小于16")
    private String title;

    /**
     * 题干
     */
    @NotBlank(message = "题干不能为空")
    private String content;

    /**
     * 难度 0 - noob 1 - normal 2 - hard -1 - undefined
     */
    private Integer level;

    /**
     * 测试点 - json数组
     */
    @NotBlank(message = "测试点不能为空")
    private String judgeCase;

    /**
     * 判题限制 - json对象
     */
    @NotBlank(message = "判题限制不能为空")
    private String judgeConfig;

    /**
     * 通过人数
     */
    private Integer acceptNum;

    /**
     * 提交人数
     */
    private Integer submitNum;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
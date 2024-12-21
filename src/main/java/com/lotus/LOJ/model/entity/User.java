package com.lotus.LOJ.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


/**
 * 用户
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 账号
     */
    @NotBlank(message = "账号不能为空")
    @Size(min = 11,max = 11,message = "请输入正确的电话号码格式")
    @Pattern(regexp = "^[0-9]+$", message = "请输入正确的电话号码格式")
    private String tel;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Size(min = 6,max = 16,message = "密码长度应在6-16位之间")
    private String pwd;

    /**
     * 用户昵称
     */
    @NotBlank(message = "昵称不能为空")
    @Size(min = 2,max = 16,message = "昵称长度应在1-16位之间")
    private String name;

    /**
     * 用户简介
     */
    @Size(max = 255,message = "简介长度应在0-255位之间")
    private String profile;

    /**
     * 用户角色：user - 0/admin - 1/ban - -1
     */
    private Integer role;

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
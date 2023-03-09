package com.tp.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user")
public class User implements Serializable {
    private static final long serialVersionUID = 1;
    @TableId
    private Long id;

    private String userName;

    private String nickName;
    @JSONField(serialize = false)
    private String password;
    private String status;
    private String email;
    private String phoneNumber;
    /**
     * 性别 1 男 2 女 0 未知生物
     */
    private String gender;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 用户类型 0管理员 1普通用户
     */
    private String userType;
    /**
     * 创建人ID
     */
    private Long createBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private String createTime;
    /**
     * 更新人ID
     */
    private Long updateBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.UPDATE)
    private String updateTime;
    /**
     * 删除标志
     */
    private Integer delFlag;
}

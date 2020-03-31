package com.zigar.api.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author zigar
 * @since 2020-01-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("user_id_")
    private Long userId;

    @TableField("username_")
    private String username;

    @TableField("password_")
    private String password;

    @TableField("display_name_")
    private String displayName;

    @TableField(value = "create_time_", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField("last_login_time_")
    private LocalDateTime lastLoginTime;

    @TableField("pwd_reset_time_")
    private LocalDateTime pwdResetTime;

    @TableField("phone_")
    private String phone;

    @TableField("sex_")
    private Integer sex;

    @TableField("is_enabled_")
    private Boolean enabled;

    @TableField("sys_role_")
    private String sysRole;

}

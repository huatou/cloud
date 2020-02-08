package com.zigar.user.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zigar.user.system.Request.RequestInsertAction;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
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
public class User implements Serializable {


    private static final long serialVersionUID = 1L;

    @TableId("user_id_")
    private Long userId;

    @NotBlank(groups = RequestInsertAction.class, message = "插入用户时username不能为空")
    @TableField("username_")
    private String username;

    @NotBlank(groups = RequestInsertAction.class, message = "插入用户时password不能为空")
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

}

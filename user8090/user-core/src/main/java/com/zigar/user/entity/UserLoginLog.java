package com.zigar.user.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author zigar
 * @since 2020-01-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_login_log")
public class UserLoginLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("log_id_")
    private Long logId;

    @TableField("user_id_")
    private Long userId;

    @TableField(value = "login_time_", fill = FieldFill.INSERT)
    private LocalDateTime loginTime;

    @TableField("user_agent_")
    private String userAgent;


}

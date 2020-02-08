package entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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

    private static final long serialVersionUID=1L;

    @TableId("log_id_")
    private Long logId;

    @TableField("user_id_")
    private Long userId;

    @TableField("login_time_")
    private LocalDateTime loginTime;

    @TableField("user_agent_")
    private String userAgent;


}

package entity;

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
 * @since 2020-01-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class File implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 文件id
     */
    @TableId("file_id_")
    private Long fileId;

    /**
     * 存储在服务器上的文件名，为文件的md5值
     */
    @TableField("file_name_")
    private String fileName;

    /**
     * 创建时间
     */
    @TableField("create_time_")
    private LocalDateTime createTime;

    /**
     * 文件大小
     */
    @TableField("size_")
    private Long size;

    /**
     * 存放在服务器上的物理地址，为绝对路径地址
     */
    @TableField("location_")
    private String location;

    /**
     * 文件的真正名称
     */
    @TableField("real_name_")
    private String realName;

    /**
     * 排序
     */
    @TableField("sort_")
    private Integer sort;

    /**
     * 父级目录id
     */
    @TableField("parent_id_")
    private Long parentId;


}

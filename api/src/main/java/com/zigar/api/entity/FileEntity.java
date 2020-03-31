package com.zigar.api.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zigar.core.action.RequestInsertAction;
import com.zigar.core.action.RequestUpdateAction;
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
 * @since 2020-01-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("file")
public class FileEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文件id
     */
    @TableId("file_id_")
    @NotBlank(groups = RequestUpdateAction.class)
    private Long fileId;

    /**
     * 文件的md5值
     */
    @JsonIgnore
    @TableField("md5_")
    private String md5;

    /**
     * 创建时间
     */
    @TableField(value = "create_time_", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 文件大小
     */
    @TableField("size_")
    private Long size;

    /**
     * 存放在服务器上的物理地址，为绝对路径地址
     */
    @JsonIgnore
    @TableField("location_")
    private String location;

    /**
     * 文件的真正名称
     */
    @NotBlank(groups = RequestInsertAction.class)
    @TableField("original_name_")
    private String originalName;

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

    /**
     * 文件后缀
     */
    @TableField("suffix_")
    private String suffix;

    /**
     * 上传用户id
     */
    @TableField("user_id_")
    private Long userId;

    @TableField(exist = false)
    private String username;

}

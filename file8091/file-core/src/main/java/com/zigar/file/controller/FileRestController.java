package com.zigar.file.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zigar.file.config.JdbcConfigBean;
import com.zigar.file.entity.FileEntity;
import com.zigar.file.entity.User;
import com.zigar.file.feign.UserFeignClient;
import com.zigar.file.model.Results;
import com.zigar.file.service.FileService;
import com.zigar.file.system.Request.RequestInsertAction;
import com.zigar.file.system.properties.FileProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Zigar
 * @createTime 2020-01-24 19:41:41
 * @description
 */

@RestController
public class FileRestController {


    @Autowired
    private FileProperties fileProperties;

    @Autowired
    private FileService fileService;

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private JdbcConfigBean jdbcConfigBean;

    @GetMapping("/test")
    Object test() {
        return jdbcConfigBean.toString();
    }


    @PostMapping("/zigar/file/upload")
    Object fileUpload(MultipartFile file, HttpServletRequest httpServletRequest) {


        /**
         *
         * 判断文件是否为空
         * 判断文件是否已经上传过，已上传的则直接返回数据库内容
         * 检查本服务器是否配置了文件存储位置，文件存储以时间为文件夹存储当天文件
         * 插入数据库文件记录
         * 返回文件id给前端
         *
         */

        //前端没有选择文件，srcFile为空
        if (file.isEmpty()) {
            return Results.error("请选择文件");
        }
        try {

            String parentIdStr = httpServletRequest.getParameter("parentId");
            Long parentId = null;
            if (!StringUtils.isEmpty(parentIdStr)) {
                try {
                    parentId = Long.valueOf(parentIdStr);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    return Results.error("此目录不存在");
                }
                LambdaQueryWrapper<FileEntity> fileEntityLambdaQueryWrapper = Wrappers.<FileEntity>lambdaQuery().eq(FileEntity::getFileId, parentIdStr);
                FileEntity parentFileEntity = fileService.getOne(fileEntityLambdaQueryWrapper);
                if (parentFileEntity == null) {
                    return Results.error("此目录不存在");
                }
            } else {
                //指定的目录为空则表示该文件是放到根目录上

            }

            //插入文件记录

            //先检查文件是否已经上传

            String md5FileName = DigestUtils.md5DigestAsHex(file.getBytes());

            LambdaQueryWrapper<FileEntity> fileEntityLambdaQueryWrapper = Wrappers.<FileEntity>lambdaQuery().eq(FileEntity::getMd5, md5FileName);

            FileEntity localFileEntity = fileService.getOne(fileEntityLambdaQueryWrapper);

            if (localFileEntity != null) {
                return Results.succeed(localFileEntity);
            }


            FileEntity newFileEntity = new FileEntity();
            newFileEntity.setMd5(md5FileName);
            newFileEntity.setParentId(parentId);

            //构建上传目标路径
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            String nowDateStr = simpleDateFormat.format(new Date());
            String destFilePath = fileProperties.getLocalFilePath() + fileProperties.getImageFilePath() + "/" + nowDateStr + "/";
            newFileEntity.setLocation(destFilePath);

            String originalFilename = file.getOriginalFilename();

            newFileEntity.setOriginalName(originalFilename);
            newFileEntity.setSize(file.getSize());

            String suffixName = StringUtils.lowerCase(StringUtils.substring(originalFilename, StringUtils.lastIndexOf(originalFilename, ".") + 1));
            newFileEntity.setSuffix(suffixName);

            fileService.save(newFileEntity);


            //开始将源文件写入目标地址
            //如果构建目录不能存在，则需要在本地服务器新建多级目录
            File destFile = new File(destFilePath);
            if (!destFile.exists()) {
                destFile.mkdirs();
            }
            byte[] bytes = file.getBytes();
            //目录 + md5文件名称
            Path path = Paths.get(destFilePath + "/" + newFileEntity.getFileId());
            Files.write(path, bytes);

            return Results.succeed(newFileEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Results.error("文件上传失败");
    }

    //获取目录列表
    @GetMapping("/zigar/file")
    public Object fileList(FileEntity fileEntity) {
        LambdaQueryWrapper<FileEntity> fileEntityLambdaQueryWrapper = Wrappers.lambdaQuery(fileEntity);
        List<FileEntity> list = fileService.list(fileEntityLambdaQueryWrapper);
        list.forEach(file -> {
            User user = userFeignClient.queryUserById(file.getUserId()).getData();
            file.setUsername(user == null ? "" : user.getUsername());
        });
        return list;
    }

    /**
     * 请求失败执行的方法
     * fallbackMethod的方法参数个数类型要和原方法一致
     *
     * @param userId
     * @return
     */
    public FileEntity queryItemByIdFallbackMethod(Long userId) {
        return null;
    }

    //添加目录
    @PostMapping("/zigar/file")
    public Object fileAdd(@RequestBody @Validated(RequestInsertAction.class) FileEntity fileEntity) {
        fileService.save(fileEntity);
        return Results.succeed("添加目录成功");
    }

    //修改目录
    @PutMapping("/zigar/file/{id}")
    public Object fileUpdate(@RequestBody FileEntity fileEntity, @PathVariable Long id) {
        fileEntity.setFileId(id);
        fileService.saveOrUpdate(fileEntity);
        return Results.succeed("修改目录成功");
    }

    //删除目录
    @DeleteMapping("/zigar/file/{id}")
    public Object fileDelete(@PathVariable Long id) {
        fileService.removeById(id);
        return Results.succeed("删除目录成功");
    }


}

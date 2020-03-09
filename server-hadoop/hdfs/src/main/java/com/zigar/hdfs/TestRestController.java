package com.zigar.hdfs;

import com.zigar.api.entity.model.Results;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Zigar
 * @createTime 2020-02-28 12:38:23
 * @description
 */

@RestController
public class TestRestController {


    private String defaultHdfsUri = "hdfs://localhost:9000";

    @Autowired
    private HdfsService hdfsService;

    @GetMapping("/test")
    Object test() {
        return "test HDFS";
    }


    @PostMapping("/file/upload")
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
            //插入文件记录

            //先检查文件是否已经上传

            byte[] fileBytes = file.getBytes();
            String md5FileName = DigestUtils.md5DigestAsHex(fileBytes);

            //构建上传目标路径
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            String nowDateStr = simpleDateFormat.format(new Date());

            String originalFilename = file.getOriginalFilename();

            String suffixName = StringUtils.lowerCase(StringUtils.substring(originalFilename, StringUtils.lastIndexOf(originalFilename, ".") + 1));

            //上传文件到HDFS
            Configuration configuration = new Configuration();
            FileSystem fs = FileSystem.get(new URI(defaultHdfsUri), configuration);
            Path src = new Path(originalFilename);
            FSDataOutputStream fsDataOutputStream = fs.create(src);
            fsDataOutputStream.write(fileBytes);
            fsDataOutputStream.close();
            fs.close();

            return Results.succeed();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return Results.error("文件上传失败");
    }


}

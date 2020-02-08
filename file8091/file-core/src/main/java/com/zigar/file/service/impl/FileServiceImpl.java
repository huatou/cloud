package com.zigar.file.service.impl;

import com.zigar.file.entity.FileEntity;
import com.zigar.file.mapper.FileMapper;
import com.zigar.file.service.FileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zigar
 * @since 2020-01-27
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, FileEntity> implements FileService {

}

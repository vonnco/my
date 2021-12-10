package com.vonco.fastdfs.service;

import com.github.tobato.fastdfs.domain.FileInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ke feng
 * @title: FdfsService
 * @projectName my
 * @description: TODO
 * @date 2021/12/6 17:56
 */
public interface FdfsService {
    //上传文件
    String uploadFile(MultipartFile file);
    //查询文件
    FileInfo queryFile(String path);
    //下载文件
    String downloadFile(String filePath);
    //删除文件
    void deleteFile(String filePath);
}

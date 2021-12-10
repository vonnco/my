package com.vonco.fastdfs.service.impl;

import com.github.tobato.fastdfs.domain.FileInfo;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.proto.storage.DownloadFileWriter;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.vonco.fastdfs.service.FdfsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

/**
 * @author ke feng
 * @title: FdfsServiceImpl
 * @projectName my
 * @description: TODO
 * @date 2021/12/6 17:57
 */
@Service
public class FdfsServiceImpl implements FdfsService {
    @Autowired
    private FastFileStorageClient storageClient;

    /**
     * 上传文件
     * @param file
     * @return
     */
    @Override
    public String uploadFile(MultipartFile file) {
        String path = null;
        try {
            File file1 = new File("D:\\eclipse_update_120.jpg");
            StorePath storePath = storageClient.uploadFile(new FileInputStream(file1), file1.length(), "jpg", null);
            path = storePath.getFullPath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }

    /**
     * 查询文件
     * @param filePath 文件全路径
     * @return
     */
    @Override
    public FileInfo queryFile(String filePath) {
        String groupName = filePath.substring(0, filePath.indexOf("/"));
        String path = filePath.substring(filePath.indexOf("/")+1);
        FileInfo fileInfo = storageClient.queryFileInfo(groupName, path);
        return fileInfo;
    }

    /**
     * 下载文件
     * @param filePath 文件全路径
     * @return
     */
    @Override
    public String downloadFile(String filePath) {
        String groupName = filePath.substring(0, filePath.indexOf("/"));
        String path = filePath.substring(filePath.indexOf("/")+1);
        String s = storageClient.downloadFile(groupName, path, new DownloadFileWriter("D://test2.jpg"));
        return s;
    }

    /**
     * 删除文件
     * @param filePath 文件全路径
     */
    @Override
    public void deleteFile(String filePath) {
        storageClient.deleteFile(filePath);
    }
}

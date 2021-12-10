package com.vonco.fastdfs;

import com.vonco.fastdfs.service.FdfsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FastdfsApplicationTests {
    @Autowired
    private FdfsService fdfsService;

    @Test
    void contextLoads() {
        String s = fdfsService.downloadFile("group1/M00/00/00/rBv1ymGvKz-ADsRbAAMuiGK_tF8740.jpg");
        System.out.println(s);
    }

}

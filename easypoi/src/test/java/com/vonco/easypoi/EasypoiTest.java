package com.vonco.easypoi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.vonco.easypoi.domain.ComponentEntity;
import com.vonco.easypoi.domain.SubjectEntity;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ke feng
 * @title: EasypoiTest
 * @projectName my
 * @description: TODO
 * @date 2022/10/29 10:21
 */
@SpringBootTest
public class EasypoiTest {

    @Test
    public void export(){
        List<SubjectEntity> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            SubjectEntity subjectEntity = new SubjectEntity();
            subjectEntity.setId("1");
            subjectEntity.setCode("000001");
            subjectEntity.setName("单位1");
            subjectEntity.setAttribute("公");
            subjectEntity.setGrade(1);
            subjectEntity.setSectionCode("111111");
            subjectEntity.setSectionName("街巷1");
            subjectEntity.setHouseNum("一栋一单元");
            subjectEntity.setLongitude(new BigDecimal("100.123"));
            subjectEntity.setLatitude(new BigDecimal("23.123"));
            subjectEntity.setPhoto("http://test.jpg");
            subjectEntity.setResponsible("负责人1");
            subjectEntity.setPhone("18200555182");
            subjectEntity.setInsiderNum(5);
            subjectEntity.setOutsiderNum(10);
            subjectEntity.setBedNum(20);
            subjectEntity.setArea1(new BigDecimal(6));
            subjectEntity.setArea2(new BigDecimal(7));
            subjectEntity.setBucketNum(12);
            List<ComponentEntity> componentEntityList = new ArrayList<>();
            ComponentEntity componentEntity1 = new ComponentEntity();
            componentEntity1.setCode("00000101");
            componentEntity1.setName("收集点1");
            componentEntity1.setBoxNum(11);
            componentEntity1.setPhoto("http://test1.jpg");
            ComponentEntity componentEntity2 = new ComponentEntity();
            componentEntity2.setCode("00000102");
            componentEntity2.setName("收集点2");
            componentEntity2.setBoxNum(22);
            componentEntity2.setPhoto("http://test3.jpg");
            componentEntityList.add(componentEntity1);
            componentEntityList.add(componentEntity2);
            subjectEntity.setComponentEntityList(componentEntityList);
            list.add(subjectEntity);
        }
        try {
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), SubjectEntity.class, list);
            FileOutputStream fos = new FileOutputStream("D:/test.xls");
            workbook.write(fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

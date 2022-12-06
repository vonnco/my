package com.vonco.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.google.common.collect.Lists;
import com.vonco.easyexcel.dao.UserMapper;
import com.vonco.easyexcel.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;

@SpringBootTest
class EasyexcelApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 3100; i++) {
            User user = User.builder()
                    .name("测试名称" + i)
                    .sex(1)
                    .age(18)
                    .birthday(new Date())
                    .address("测试地址" + i)
                    .build();
            userList.add(user);
        }
       /* int i = userMapper.insertBatch(userList);
        System.out.println(i);*/
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("D:\\test.xlsx");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Map<String,Map<Class, List<User>>> map1 = new HashMap();
        Map<Class,List<User>> map2 = new HashMap();
        map2.put(User.class, userList);
        map1.put("测试",map2);
        List<List<User>> partition = Lists.partition(userList, 3000);
        ExcelWriter excelWriter = EasyExcel.write(outputStream).build();
        for (int i = 0; i < partition.size(); i++) {
            WriteSheet test1 = EasyExcel.writerSheet("user"+i).head(User.class).build();
            excelWriter.write(partition.get(i),test1);
        }
        excelWriter.finish();
        /*List<Student> studentList = new ArrayList<>();
        studentList.add(Student.builder().name("张三").sex(0).subject("语文").score(90).build());
        studentList.add(Student.builder().name("张三").sex(0).subject("数学").score(80).build());
        studentList.add(Student.builder().name("李四").sex(0).subject("语文").score(90).build());
        studentList.add(Student.builder().name("李四").sex(0).subject("数学").score(85).build());
        studentList.add(Student.builder().name("王五").sex(1).subject("语文").score(60).build());
        studentList.add(Student.builder().name("王五").sex(1).subject("数学").score(70).build());
        Set<WriteHandler> handlers = new HashSet<>();
        handlers.add(new ExcelFillCellMergeHandler(0, new int[]{0, 1}));
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("D:\\test.xlsx");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Map<String,Map<Class, List<Student>>> map1 = new HashMap();
        Map<Class,List<Student>> map2 = new HashMap();
        map2.put(Student.class, studentList);
        map1.put("测试",map2);
        EasyExcelUtil.write(outputStream,handlers,map1);
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //.read("D:\\test.xlsx",User.class, new UserReadListener(userMapper));
    }

}

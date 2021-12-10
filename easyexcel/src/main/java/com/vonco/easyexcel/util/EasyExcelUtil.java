package com.vonco.easyexcel.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.handler.WriteHandler;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.google.common.collect.Lists;
import com.vonco.easyexcel.domain.User;
import com.vonco.easyexcel.listener.UserReadListener;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author ke feng
 * @title: EasyExcelUtil
 * @projectName my
 * @description: TODO
 * @date 2021/12/9 14:38
 */
public class EasyExcelUtil {
    /**
     * 导出（一个对象）
     * @param filePath 输出路径
     * @param sheetName 表名
     * @param isSpiltSheet 是否分表
     * @param handlers 策略
     * @param head 头
     * @param data 数据集合
     */
    public static<T> void write(String filePath, String sheetName, Boolean isSpiltSheet, Set<WriteHandler> handlers, Class head, List<T> data) {
        ExcelWriter excelWriter = null;
        WriteSheet writeSheet = null;
        try {
            //构建ExcelWriter
            excelWriter = EasyExcel.write(filePath, head).build();
            if (!isSpiltSheet) {
                //构建ExcelWriterSheetBuilder
                ExcelWriterSheetBuilder excelWriterSheetBuilder = EasyExcel.writerSheet(sheetName);
                //添加策略
                if (handlers != null) {
                    for (WriteHandler handler : handlers) {
                        excelWriterSheetBuilder.registerWriteHandler(handler);
                    }
                }
                //构建WriteSheet
                writeSheet = excelWriterSheetBuilder.build();
            }
            //把数据集合分割成3000条每段
            List<List<T>> partition = Lists.partition(data, 3000);
            for (int i = 0; i < partition.size(); i++) {
                if (isSpiltSheet) {
                    //构建ExcelWriterSheetBuilder
                    ExcelWriterSheetBuilder excelWriterSheetBuilder = EasyExcel.writerSheet(sheetName+i);
                    //添加策略
                    if (handlers != null) {
                        for (WriteHandler handler : handlers) {
                            excelWriterSheetBuilder.registerWriteHandler(handler);
                        }
                    }
                    //构建WriteSheet
                    writeSheet = excelWriterSheetBuilder.build();
                }
                //把分片数据写入WriteSheet
                excelWriter.write(partition.get(i),writeSheet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭ExcelWriter流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }

    /**
     * 导出（一个对象）
     * @param outputStream 输出流
     * @param sheetName 表名
     * @param isSpiltSheet 是否分表
     * @param handlers 策略
     * @param head 头
     * @param data 数据集合
     */
    public static<T> void write(OutputStream outputStream, String sheetName, Boolean isSpiltSheet, Set<WriteHandler> handlers, Class head, List<T> data) {
        ExcelWriter excelWriter = null;
        WriteSheet writeSheet = null;
        try {
            //构建ExcelWriter
            excelWriter = EasyExcel.write(outputStream, head).build();
            if (!isSpiltSheet) {
                //构建ExcelWriterSheetBuilder
                ExcelWriterSheetBuilder excelWriterSheetBuilder = EasyExcel.writerSheet(sheetName);
                //添加策略
                if (handlers != null) {
                    for (WriteHandler handler : handlers) {
                        excelWriterSheetBuilder.registerWriteHandler(handler);
                    }
                }
                //构建WriteSheet
                writeSheet = excelWriterSheetBuilder.build();
            }
            //把数据集合分割成3000条每段
            List<List<T>> partition = Lists.partition(data, 3000);
            for (int i = 0; i < partition.size(); i++) {
                if (isSpiltSheet) {
                    //构建ExcelWriterSheetBuilder
                    ExcelWriterSheetBuilder excelWriterSheetBuilder = EasyExcel.writerSheet(sheetName+i);
                    //添加策略
                    if (handlers != null) {
                        for (WriteHandler handler : handlers) {
                            excelWriterSheetBuilder.registerWriteHandler(handler);
                        }
                    }
                    //构建WriteSheet
                    writeSheet = excelWriterSheetBuilder.build();
                }
                //把分片数据写入WriteSheet
                excelWriter.write(partition.get(i),writeSheet);
            }
        } finally {
            //关闭ExcelWriter流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }

    /**
     * 导出（多个对象）
     * @param filePath 输出路径
     * @param handlers 策略
     * @param map
     */
    public static<T> void write(String filePath, Set<WriteHandler> handlers, Map<String,Map<Class,List<T>>> map) {
        ExcelWriter excelWriter = null;
        try {
            //构建ExcelWriter
            excelWriter = EasyExcel.write(filePath).build();
            for (Map.Entry<String, Map<Class, List<T>>> stringMapEntry : map.entrySet()) {
                //获取表名
                String sheetName = stringMapEntry.getKey();
                for (Map.Entry<Class, List<T>> classListEntry : stringMapEntry.getValue().entrySet()) {
                    //获取头
                    Class head = classListEntry.getKey();
                    //构建ExcelWriterSheetBuilder
                    ExcelWriterSheetBuilder excelWriterSheetBuilder = EasyExcel.writerSheet(sheetName).head(head);
                    //添加策略
                    if (handlers != null) {
                        for (WriteHandler handler : handlers) {
                            excelWriterSheetBuilder.registerWriteHandler(handler);
                        }
                    }
                    //构建WriteSheet
                    WriteSheet writeSheet = excelWriterSheetBuilder.build();
                    List<T> objects = classListEntry.getValue();
                    //把数据集合分割成3000条每段
                    List<List<T>> partition = Lists.partition(objects, 3000);
                    for (int i = 0; i < partition.size(); i++) {
                        excelWriter.write(partition.get(i),writeSheet);
                    }
                }
            }
        } finally {
            //关闭ExcelWriter流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }

    /**
     * 导出（多个对象）
     * @param outputStream 输出流
     * @param handlers 策略
     * @param map
     */
    public static<T> void write(OutputStream outputStream, Set<WriteHandler> handlers, Map<String,Map<Class,List<T>>> map) {
        ExcelWriter excelWriter = null;
        try {
            //构建ExcelWriter
            excelWriter = EasyExcel.write(outputStream).build();
            for (Map.Entry<String, Map<Class, List<T>>> stringMapEntry : map.entrySet()) {
                //获取表名
                String sheetName = stringMapEntry.getKey();
                for (Map.Entry<Class, List<T>> classListEntry : stringMapEntry.getValue().entrySet()) {
                    //获取头
                    Class head = classListEntry.getKey();
                    //构建ExcelWriterSheetBuilder
                    ExcelWriterSheetBuilder excelWriterSheetBuilder = EasyExcel.writerSheet(sheetName).head(head);
                    //添加策略
                    if (handlers != null) {
                        for (WriteHandler handler : handlers) {
                            excelWriterSheetBuilder.registerWriteHandler(handler);
                        }
                    }
                    //构建WriteSheet
                    WriteSheet writeSheet = excelWriterSheetBuilder.build();
                    List<T> objects = classListEntry.getValue();
                    //把数据集合分割成3000条每段
                    List<List<T>> partition = Lists.partition(objects, 3000);
                    for (int i = 0; i < partition.size(); i++) {
                        excelWriter.write(partition.get(i),writeSheet);
                    }
                }
            }
        } finally {
            //关闭ExcelWriter流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }

    /**
     * 导入
     * @param filePath 输入路径
     * @param head 头
     * @param readListener 监听
     */
    public static void read(String filePath, Class head, ReadListener readListener){
        EasyExcel.read(filePath, head,readListener).doReadAll();
    }

    /**
     * 导入
     * @param inputStream 输入流
     * @param head 头
     * @param readListener 监听
     */
    public static void read(InputStream inputStream, Class head, ReadListener readListener){
        EasyExcel.read(inputStream, head,readListener).doReadAll();
    }
}

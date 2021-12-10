package com.vonco.easyexcel.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.*;
import com.alibaba.excel.enums.BooleanEnum;
import com.alibaba.excel.enums.poi.BorderStyleEnum;
import com.alibaba.excel.enums.poi.FillPatternTypeEnum;
import com.alibaba.excel.enums.poi.HorizontalAlignmentEnum;
import com.alibaba.excel.enums.poi.VerticalAlignmentEnum;
import com.vonco.easyexcel.converter.SexConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * @author ke feng
 * @title: User
 * @projectName my
 * @description: TODO
 * @date 2021/12/8 18:00
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//标题样式(日期格式,隐藏,锁定,在单元格前面增加`符号,水平居中,是否应换行,垂直居中,旋转角度,缩进的空格数,左边框的样式,右边框样式,上边框样式,下边框样式,左边框颜色,右边框颜色,上边框颜色,下边框颜色,填充类型,背景色,前景色,自动单元格自动大小)
//颜色(IndexedColors.PINK.getIndex())
@HeadStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER,verticalAlignment = VerticalAlignmentEnum.CENTER)
//标题字体样式(字体名称,字体高度,是否斜体,是否设置删除水平线,字体颜色,偏移量,下划线,编码格式,是否加粗)
@HeadFontStyle(fontName = "",fontHeightInPoints = -1,italic = BooleanEnum.DEFAULT,strikeout = BooleanEnum.DEFAULT,color = -1,typeOffset = -1,underline = -1,charset = -1,bold = BooleanEnum.DEFAULT)
//标题高度
@HeadRowHeight(value = -1)
//内容样式
@ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER,verticalAlignment = VerticalAlignmentEnum.CENTER)
//内容字体样式
@ContentFontStyle(fontName = "",fontHeightInPoints = -1,italic = BooleanEnum.DEFAULT,strikeout = BooleanEnum.DEFAULT,color = -1,typeOffset = -1,underline = -1,charset = -1,bold = BooleanEnum.DEFAULT)
//内容高度
@ContentRowHeight(-1)
public class User {
    @ExcelIgnore//忽略项
    private Integer id;
    @ColumnWidth(value = 10)//列宽
    @ExcelProperty(value = "姓名",index = 0)//字段配置
    private String name;
    @ColumnWidth(value = 10)
    @ExcelProperty(value = "性别",index = 1,converter = SexConverter.class)
    private Integer sex;
    @ColumnWidth(value = 10)
    @NumberFormat(value = "#岁")
    @ExcelProperty(value = "年龄",index = 2)
    private Integer age;
    @ColumnWidth(value = 20)
    @DateTimeFormat("yyyy年MM月dd日 HH:mm:ss")
    @ExcelProperty(value = "生日",index = 3)
    private Date birthday;
    @ColumnWidth(value = 20)
    @ExcelProperty(value = "住址",index = 4)
    private String address;
}

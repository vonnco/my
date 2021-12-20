package com.vonco.mybatisplus;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;

import java.util.Collections;

/**
 * @author ke feng
 * @title: CodeGenerator
 * @projectName mybatisplus
 * @description: TODO
 * @date 2021/10/29 14:28
 */
public class CodeGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create(
                //数据库配置
                new DataSourceConfig.Builder("jdbc:mysql://localhost:3306/base_data","root","123456")
                        //.dbQuery(new MySqlQuery()) // 数据库查询
                        //.schema("mybatis-plus") // 数据库schema(部分数据库适用)
                        //.typeConvert(new MySqlTypeConvert()) // 数据库类型转换器
                        //.keyWordsHandler(new MySqlKeyWordsHandler()) // 数据库关键字处理器
                )
                //全局配置
                .globalConfig(builder -> {
                    builder.author("ke feng") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .disableOpenDir() // 禁止打开输出目录
                            //.enableKotlin() // 开启 kotlin 模式
                            .dateType(DateType.ONLY_DATE) // 时间策略
                            .commentDate("yyyy-MM-dd") // 注释日期
                            .outputDir("D:\\projects\\city_management_platform\\citymanagementplatform\\web-project\\base-data\\src\\main\\java"); // 指定输出目录
                })
                //模板配置
                /*.templateConfig(builder -> {
                    builder.disable(TemplateType.ENTITY) // 禁用模板
                            .entity("/templates/entity.java") // 设置实体模板路径(JAVA)
                            .entityKt("/templates/entity.java") // 设置实体模板路径(kotlin)
                            .service("/templates/service.java") // 设置 service 模板路径
                            .serviceImpl("/templates/serviceImpl.java") // 设置 serviceImpl 模板路径
                            .mapper("/templates/mapper.java") // 设置 mapper 模板路径
                            .mapperXml("/templates/mapper.xml") // 设置 mapperXml 模板路径
                            .controller("/templates/controller.java"); // 设置 controller 模板路径
                })*/
                //注入配置
                /*.injectionConfig(builder -> {
                    builder.beforeOutputFile((tableInfo, objectMap) -> {
                        System.out.println("tableInfo: " + tableInfo.getEntityName() + " objectMap: " + objectMap.size());
                    }) // 输出文件之前消费者
                            .customMap(Collections.singletonMap("test", "baomidou")) // 自定义配置 Map 对象
                            .customFile(Collections.singletonMap("test.txt", "/templates/test.vm")); // 自定义配置模板文件
                })*/
                //包配置
                .packageConfig(builder -> {
                    builder.parent("com.xt.cloud") // 设置父包名
                            .moduleName("basedata") // 设置父包模块名
                            .entity("entity.po") // Entity 包名
                            .service("service") // Service 包名
                            .serviceImpl("service.impl") // Service Impl 包名
                            .mapper("dao") // Mapper 包名
                            .controller("controller") // Controller 包名
                            //.xml("mapper.xml") // Mapper XML 包名
                            //.other("other") // 自定义文件包名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:/projects/city_management_platform/citymanagementplatform/garbage-project/do-garbage/src/main/resources/mapper")); // 路径配置信息
                })
                // 策略配置
                .strategyConfig(builder -> {
                    builder.addInclude("garbage_weight_report_detail") // 设置需要生成的表名
                            //.addExclude("t_simple") // 增加表排除匹配(内存过滤)
                            //.addTablePrefix("t_") // 增加过滤表前缀
                            //.addTableSuffix("_t") // 增加过滤表后缀
                            //.addFieldPrefix("t_") // 增加过滤字段前缀
                            //.addFieldSuffix("_2021") // 增加过滤字段后缀
                            //.enableCapitalMode() // 开启大写命名
                            //.enableSkipView() // 开启跳过视图
                            //.disableSqlFilter() // 禁用 sql 过滤
                            //.enableSchema() // 启用 schema
                            //.likeTable(new LikeTable("USER")) // 模糊表匹配(sql 过滤)
                            //.notLikeTable(new LikeTable("USER")) // 模糊表排除(sql 过滤)

                            //.entityBuilder() // 实体策略配置
                            //.superClass(BaseEntity.class) // 设置父类
                            //.disableSerialVersionUID() // 禁用生成 serialVersionUID
                            //.enableColumnConstant() // 开启生成字段常量
                            //.enableChainModel() // 开启链式模型
                            //.enableLombok() // 开启 lombok 模型
                            //.enableRemoveIsPrefix() // 开启 Boolean 类型字段移除 is 前缀
                            //.enableTableFieldAnnotation() // 开启生成实体时生成字段注解
                            //.enableActiveRecord() // 开启 ActiveRecord 模型
                            //.versionColumnName("version") // 乐观锁字段名(数据库)
                            //.versionPropertyName("version") // 乐观锁属性名(实体)
                            //.logicDeleteColumnName("deleted") // 逻辑删除字段名(数据库)
                            //.logicDeletePropertyName("deleted") // 逻辑删除属性名(实体)
                            //.naming(NamingStrategy.no_change) // 数据库表映射到实体的命名策略
                            //.columnNaming(NamingStrategy.underline_to_camel) // 数据库表字段映射到实体的命名策略
                            //.addSuperEntityColumns("id", "created_by", "created_time", "updated_by", "updated_time") // 添加父类公共字段
                            //.addIgnoreColumns("age") // 添加忽略字段
                            //.addTableFills(new Column("created_time", FieldFill.INSERT)) // 添加表字段填充
                            //.addTableFills(new Property("createdBy", FieldFill.INSERT),new Property("updatedBy", FieldFill.INSERT_UPDATE),new Property("createdTime", FieldFill.INSERT),new Property("updatedTime", FieldFill.INSERT_UPDATE)) // 添加表字段填充
                            //.idType(IdType.AUTO) // 全局主键类型
                            //.convertFileName(ConverterFileName) // 转换文件名称
                            //.formatFileName("%sEntity") // 格式化文件名称

                            .controllerBuilder() // controller 策略配置
                            //.superClass(BaseController.class) // 设置父类
                            //.enableHyphenStyle() // 开启驼峰转连字符
                            .enableRestStyle() // 开启生成@RestController 控制器
                            //.convertFileName(ConverterFileName) // 转换文件名称
                            //.formatFileName("%sAction") // 格式化文件名称

                            /*.serviceBuilder() // service 策略配置
                            .superServiceClass(BaseService.class) // 设置 service 接口父类
                            .superServiceImplClass(BaseServiceImpl.class) // 设置 service 实现类父类
                            .convertServiceFileName(ConverterFileName) // 转换 service 接口文件名称
                            .convertServiceImplFileName(ConverterFileName) // 转换 service 实现类文件名称
                            .formatServiceFileName("%sService") // 格式化 service 接口文件名称
                            .formatServiceImplFileName("%sServiceImp") // 格式化 service 实现类文件名称*/

                            .mapperBuilder() // mapper 策略配置
                            //.superClass(BaseMapper.class) // 设置父类
                            //.enableMapperAnnotation() // 开启 @Mapper 注解
                            .enableBaseResultMap() // 启用 BaseResultMap 生成
                            .enableBaseColumnList() // 启用 BaseColumnList
                            //.cache(MyMapperCache.class) // 设置缓存实现类
                            //.convertMapperFileName(ConverterFileName) // 转换 mapper 类文件名称
                            //.convertXmlFileName(ConverterFileName) // 转换 xml 文件名称
                            //.formatMapperFileName("%sDao") // 格式化 mapper 文件名称
                            //.formatXmlFileName("%sXml") // 格式化 xml 实现类文件名称
                    ;
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}

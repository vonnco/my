package com.vonco.mybatisplus;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Property;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Collections;

public class MyBatisPlusGenerator extends JFrame {

    private JTextField urlField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField outputDirField;
    private JTextField xmlPathField;
    private JTextField authorField;
    private JTextField packageNameField;
    private JTextField moduleNameField;
    private JTextField controllerNameField;
    private JTextField serviceNameField;
    private JTextField serviceImplNameField;
    private JTextField mapperNameField;
    private JTextField entityNameField;
    private JTextField tableNameField;
    private JButton generateButton;

    public MyBatisPlusGenerator() {
        setTitle("MyBatis-Plus代码生成器");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding

        JLabel urlLabel = new JLabel("数据库URL(必填):");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(urlLabel, gbc);
        urlField = new JTextField(50);
        urlField.addFocusListener(new RequiredFieldFocusListener("数据库URL为空"));
        urlField.setText("jdbc:mysql://192.168.0.249:3306/xt_market");
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(urlField, gbc);

        JLabel usernameLabel = new JLabel("用户名(必填):");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(usernameLabel, gbc);
        usernameField = new JTextField(50);
        usernameField.addFocusListener(new RequiredFieldFocusListener("用户名为空"));
        usernameField.setText("root");
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("密码(必填):");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(passwordLabel, gbc);
        passwordField = new JPasswordField(50);
        passwordField.addFocusListener(new RequiredFieldFocusListener("密码为空"));
        passwordField.setText("mysql#2020");
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(passwordField, gbc);

        JLabel outputDirLabel = new JLabel("输出路径(必填):");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(outputDirLabel, gbc);
        outputDirField = new JTextField(50);
        outputDirField.addFocusListener(new RequiredFieldFocusListener("输出路径为空"));
        outputDirField.setText("D:\\projects\\my\\mybatisplus");
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(outputDirField, gbc);

        JLabel xmlLabel = new JLabel("xml路径(必填):");
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(xmlLabel, gbc);
        xmlPathField = new JTextField(50);
        xmlPathField.addFocusListener(new RequiredFieldFocusListener("xml路径为空"));
        xmlPathField.setText("D:\\projects\\my\\mybatisplus\\src\\main\\resources\\mapper");
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(xmlPathField, gbc);

        JLabel authorLabel = new JLabel("作者(必填):");
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(authorLabel, gbc);
        authorField = new JTextField(50);
        authorField.addFocusListener(new RequiredFieldFocusListener("作者为空"));
        authorField.setText("ke feng");
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(authorField, gbc);

        JLabel packageLabel = new JLabel("父包名(必填):");
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(packageLabel, gbc);
        packageNameField = new JTextField(50);
        packageNameField.addFocusListener(new RequiredFieldFocusListener("父包名为空"));
        packageNameField.setText("com.vonco");
        gbc.gridx = 1;
        gbc.gridy = 6;
        panel.add(packageNameField, gbc);

        JLabel moduleLabel = new JLabel("父包模块名(必填):");
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(moduleLabel, gbc);
        moduleNameField = new JTextField(50);
        moduleNameField.addFocusListener(new RequiredFieldFocusListener("父包模块名为空"));
        moduleNameField.setText("mybatisplus");
        gbc.gridx = 1;
        gbc.gridy = 7;
        panel.add(moduleNameField, gbc);

        JLabel controllerLabel = new JLabel("controller子包名:");
        gbc.gridx = 0;
        gbc.gridy = 8;
        panel.add(controllerLabel, gbc);
        controllerNameField = new JTextField(50);
        controllerNameField.setText("controller");
        gbc.gridx = 1;
        gbc.gridy = 8;
        panel.add(controllerNameField, gbc);

        JLabel serviceLabel = new JLabel("Service接口子包名:");
        gbc.gridx = 0;
        gbc.gridy = 9;
        panel.add(serviceLabel, gbc);
        serviceNameField = new JTextField(50);
        serviceNameField.setText("service");
        gbc.gridx = 1;
        gbc.gridy = 9;
        panel.add(serviceNameField, gbc);

        JLabel serviceImplLabel = new JLabel("Service实现类子包名:");
        gbc.gridx = 0;
        gbc.gridy = 10;
        panel.add(serviceImplLabel, gbc);
        serviceImplNameField = new JTextField(50);
        serviceImplNameField.setText("service.impl");
        gbc.gridx = 1;
        gbc.gridy = 10;
        panel.add(serviceImplNameField, gbc);

        JLabel mapperLabel = new JLabel("Mapper接口子包名:");
        gbc.gridx = 0;
        gbc.gridy = 11;
        panel.add(mapperLabel, gbc);
        mapperNameField = new JTextField(50);
        mapperNameField.setText("dao");
        gbc.gridx = 1;
        gbc.gridy = 11;
        panel.add(mapperNameField, gbc);

        JLabel entityLabel = new JLabel("实体类子包名:");
        gbc.gridx = 0;
        gbc.gridy = 12;
        panel.add(entityLabel, gbc);
        entityNameField = new JTextField(50);
        entityNameField.setText("entity.po");
        gbc.gridx = 1;
        gbc.gridy = 12;
        panel.add(entityNameField, gbc);

        JLabel tableLabel = new JLabel("表名(多个,隔开):");
        gbc.gridx = 0;
        gbc.gridy = 13;
        panel.add(tableLabel, gbc);
        tableNameField = new JTextField(50);
        gbc.gridx = 1;
        gbc.gridy = 13;
        panel.add(tableNameField, gbc);

        generateButton = new JButton("生成");
        generateButton.addActionListener(e ->  {
            if (urlField.getText().trim().isEmpty()
                    || usernameField.getText().trim().isEmpty()
                    || passwordField.getText().trim().isEmpty()
                    || outputDirField.getText().trim().isEmpty()
                    || xmlPathField.getText().trim().isEmpty()
                    || authorField.getText().trim().isEmpty()
                    || packageNameField.getText().trim().isEmpty()
                    || moduleNameField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "必填项为空！", "校验错误", JOptionPane.ERROR_MESSAGE);
            } else {
                generateCode();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 14;
        gbc.gridwidth = 2;
        panel.add(generateButton, gbc);

        add(panel);
        pack();
        setVisible(true);
    }

    private void generateCode() {
        String url = urlField.getText();
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String outputDir = outputDirField.getText();
        String mapperXmlPath = xmlPathField.getText();
        String author = authorField.getText();
        String packageName = packageNameField.getText();
        String moduleName = moduleNameField.getText();
        String controllerName = controllerNameField.getText();
        String serviceName = serviceNameField.getText();
        String serviceImplName = serviceImplNameField.getText();
        String mapperName = mapperNameField.getText();
        String entityName = entityNameField.getText();
        String tableName = tableNameField.getText();

        FastAutoGenerator.create(
                        //数据库配置
                        new DataSourceConfig.Builder(url,username,password)
                        /*.dbQuery(new MySqlQuery()) // 数据库查询
                        .schema("mybatis-plus") // 数据库schema(部分数据库适用)
                        .typeConvert(new MySqlTypeConvert()) // 数据库类型转换器
                        .keyWordsHandler(new MySqlKeyWordsHandler()) // 数据库关键字处理器
                        .typeConvertHandler((GlobalConfig globalConfig, TypeRegistry typeRegistry, TableField.MetaInfo metaInfo) -> { return typeRegistry.getColumnType(metaInfo); }) // 类型转换器
                        .databaseQueryClass(DefaultQuery.class) // 数据库查询方式*/
                )
                //全局配置
                .globalConfig(builder -> {
                    builder.disableOpenDir() // 禁止自动打开输出目录
                            .outputDir(outputDir+"/src/main/java") // 指定代码生成的输出目录
                            .author(author) // 设置作者名
                            //.enableKotlin() // 开启 kotlin 模式
                            .enableSwagger() // 开启 swagger 模式
                            .dateType(DateType.ONLY_DATE) // 设置时间类型策略
                            .commentDate("yyyy-MM-dd"); // 设置注释日期格式
                })
                //包配置
                .packageConfig(builder -> {
                    builder.parent(packageName) // 设置父包名
                            .moduleName(moduleName) // 设置父包模块名
                            .entity(StringUtils.isNotBlank(entityName) ? entityName:"entity") // 设置 Entity 包名
                            .service(StringUtils.isNotBlank(serviceName) ? serviceName:"service") // 设置 Service 包名
                            .serviceImpl(StringUtils.isNotBlank(serviceImplName) ? serviceImplName:"service.impl") // 设置 Service Impl 包名
                            .mapper(StringUtils.isNotBlank(mapperName) ? mapperName:"mapper") // 设置 Mapper 包名
                            //.xml("mapper.xml") // 设置 Mapper XML 包名
                            .controller(StringUtils.isNotBlank(controllerName) ? controllerName:"controller") // 设置 Controller 包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, mapperXmlPath)); // 设置路径配置信息
                })
                //模板配置
                /*.strategyConfig(builder -> {
                    builder.entityBuilder() // 设置实体类模板
                            .javaTemplate("/templates/entity.java.vm") // 设置 Java 实体模板
                            .disable() // 禁用实体类生成
                            .serviceBuilder() // 设置 Service 层模板
                            .disableService() // 禁用 Service 层生成
                            .serviceTemplate("/templates/service.java.vm") // 设置 Service 模板
                            .serviceImplTemplate("/templates/serviceImpl.java.vm"); // 设置 mapper 模板路径
                })*/
                //注入配置
                /*.injectionConfig(builder -> {
                    builder.beforeOutputFile((tableInfo, objectMap) -> {
                                System.out.println("准备生成文件: " + tableInfo.getEntityName());
                                // 可以在这里添加自定义逻辑，如修改 objectMap 中的配置
                    }) // 输出文件之前执行的逻辑
                            .customMap(Collections.singletonMap("projectName", "MyBatis-Plus Generator")) // 自定义配置 Map 对象
                            .customFile(Collections.singletonMap("custom.txt", "/templates/custom.vm")); // 自定义配置模板文件
                })*/
                // 策略配置
                .strategyConfig(builder -> {
                    builder.addInclude(tableName) // 增加表匹配(内存过滤)(与 addExclude 互斥，只能配置一项，支持正则匹配，如 ^t_.* 匹配所有以 t_ 开头的表名)
                            //.enableCapitalMode() // 开启大写命名
                            //.enableSkipView() // 开启跳过视图
                            //.disableSqlFilter() // 禁用 SQL 过滤
                            //.enableSchema() // 启用 schema
                            //.likeTable(new LikeTable("USER")) // 模糊表匹配(SQL 过滤)(与 notLikeTable 互斥，只能配置一项)
                            //.notLikeTable(new LikeTable("USER")) // 模糊表排除(SQL 过滤)
                            //.addExclude("t_simple") // 增加表排除匹配(内存过滤)(支持正则匹配，如 .*st$ 匹配所有以 st 结尾的表名)
                            //.addTablePrefix("t_", "c_") // 增加过滤表前缀
                            //.addTableSuffix("_relation") // 增加过滤表后缀
                            //.addFieldPrefix("user_") // 增加过滤字段前缀
                            //.addFieldSuffix("_id") // 增加过滤字段后缀
                            //.outputFile(IOutputFile) // 内置模板输出文件处理

                            .entityBuilder() // 实体策略配置
                            .javaTemplate("/templates/entity.java.vm")
                            //.nameConvert(INameConvert) // 名称转换实现
                            //.superClass(BaseEntity.class) // 设置父类
                            //.superClass("com.xt.cloud.common.entity.po.BasePo") // 设置父类
                            //.disableSerialVersionUID() // 禁用生成 serialVersionUID
                            .enableFileOverride() // 覆盖已生成文件
                            //.enableColumnConstant() // 开启生成字段常量
                            //.enableChainModel() // 开启链式模型
                            .enableLombok() // 开启 lombok 模型
                            //.enableRemoveIsPrefix() // 开启 Boolean 类型字段移除 is 前缀
                            //.enableTableFieldAnnotation() // 开启生成实体时生成字段注解
                            //.enableActiveRecord() // 开启 ActiveRecord 模型
                            //.versionColumnName("version") // 乐观锁字段名(数据库字段)(versionColumnName 与 versionPropertyName 二选一即可)
                            //.versionPropertyName("version") // 乐观锁属性名(实体)
                            .logicDeleteColumnName("deleted") // 逻辑删除字段名(数据库字段)(logicDeleteColumnName 与 logicDeletePropertyName 二选一即可)
                            //.logicDeletePropertyName("deleted") // 逻辑删除属性名(实体)
                            .naming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略
                            .columnNaming(NamingStrategy.underline_to_camel) // 数据库表字段映射到实体的命名策略
                            //.addSuperEntityColumns("id", "created_by", "created_time", "updated_by", "updated_time") // 添加父类公共字段
                            //.addIgnoreColumns("age") // 添加忽略字段
                            //.addTableFills(new Column("created_time", FieldFill.INSERT)) // 添加表字段填充
                            .addTableFills(new Property("createdBy", FieldFill.INSERT),new Property("updatedBy", FieldFill.INSERT_UPDATE),new Property("createdTime", FieldFill.INSERT),new Property("updatedTime", FieldFill.INSERT_UPDATE)) // 添加表字段填充
                            .idType(IdType.ASSIGN_ID) // 全局主键类型
                            //.convertFileName(ConverterFileName) // 转换文件名称
                            //.formatFileName("%sEntity") // 格式化文件名称

                            .controllerBuilder() // controller 策略配置
                            .template("/templates/controller.java.vm")
                            //.superClass(BaseController.class) // 设置父类
                            //.superClass("com.baomidou.global.BaseController")
                            .enableFileOverride() // 覆盖已生成文件
                            //.enableHyphenStyle() // 开启驼峰转连字符
                            .enableRestStyle() // 开启生成@RestController 控制器
                            //.convertFileName(ConverterFileName) // 转换文件名称
                            //.formatFileName("%sAction") // 格式化文件名称

                            .serviceBuilder() // service 策略配置
                            .serviceTemplate("/templates/service.java.vm")
                            .serviceImplTemplate("/templates/serviceImpl.java.vm")
                            //.superServiceClass(BaseService.class) // 设置 service 接口父类
                            //.superServiceClass("com.baomidou.global.BaseService")
                            //.superServiceImplClass(BaseServiceImpl.class) // 设置 service 实现类父类
                            //.superServiceImplClass("com.baomidou.global.BaseServiceImpl") // 设置 service 实现类父类
                            .enableFileOverride() // 覆盖已生成文件
                            //.convertServiceFileName(ConverterFileName) // 转换 service 接口文件名称
                            //.convertServiceImplFileName(ConverterFileName) // 转换 service 实现类文件名称
                            //.formatServiceFileName("%sService") // 格式化 service 接口文件名称
                            //.formatServiceImplFileName("%sServiceImp") // 格式化 service 实现类文件名称

                            .mapperBuilder() // mapper 策略配置
                            .mapperTemplate("/templates/mapper.java.vm")
                            //.mapperXmlTemplate("/templates/mapper.xml")
                            //.superClass(BaseMapper.class) // 设置父类
                            //.superClass("com.baomidou.global.BaseMapper") // 设置父类
                            .enableFileOverride() // 覆盖已生成文件
                            .enableMapperAnnotation() // 开启 @Mapper 注解
                            .enableBaseResultMap() // 启用 BaseResultMap 生成
                            .enableBaseColumnList() // 启用 BaseColumnList
                            //.cache(MyMapperCache.class) // 设置缓存实现类
                            //.convertMapperFileName(ConverterFileName) // 转换 mapper 类文件名称
                            //.convertXmlFileName(ConverterFileName) // 转换 xml 文件名称
                            //.formatMapperFileName("%sDao") // 格式化 mapper 文件名称
                            //.formatXmlFileName("%sXml") // 格式化 xml 实现类文件名称
                    ;
                })
                .templateEngine(new VelocityTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
        JOptionPane.showMessageDialog(this, "代码生成成功!");
    }

    // 自定义焦点监听器，用于必填字段的验证
    static class RequiredFieldFocusListener implements FocusListener {
        private String errorMessage;

        public RequiredFieldFocusListener(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        @Override
        public void focusGained(FocusEvent e) {
            // 当获得焦点时，清除错误提示（可选）
            JTextField textField = (JTextField) e.getComponent();
            textField.setForeground(null); // 或者 textField.setToolTipText(null);
        }

        @Override
        public void focusLost(FocusEvent e) {
            // 当失去焦点时进行验证
            JTextField textField = (JTextField) e.getComponent();
            if (textField.getText().trim().isEmpty()) {
                textField.setForeground(Color.RED); // 或者 textField.setToolTipText(errorMessage);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MyBatisPlusGenerator();
            }
        });
    }
}

package com.lzq.robot.user.userprovider;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class CodeGeneration {

    /**
     *
     * @Title: main
     * @Description: 生成
     * @param args
     */
    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        String projectPath = System.getProperty("user.dir");//项目路径;
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(projectPath+"/user/user-provider/src/main/java");//输出文件路径
        gc.setFileOverride(true); // 是否文件覆盖
        gc.setActiveRecord(false);// 不需要ActiveRecord(实体类继承Model)特性的请改为false
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML ColumnList
        gc.setAuthor("liuzhaoqing5@jd.com");// 作者

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setControllerName("%sController");
        // 默认service接口名IXXXService 自定义指定之后就不会用I开头了
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUrl("jdbc:mysql://localhost:3306/hive_cloud");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // strategy.setTablePrefix(new String[] { "sys_" });// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略(下划线转驼峰)
        strategy.setInclude("sys_user", "sys_role", "sys_tenant", "sys_user_role_ref", "interactive_screen", "interactive_apk"); // 需要生成的表名
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.lzq.robot.user.userprovider");
//        pc.setController("controller");
//        pc.setService("service");
//        pc.setServiceImpl("service.impl");
//        pc.setMapper("mapper");
//        pc.setEntity("entity");
//        pc.setXml("xml");
        mpg.setPackageInfo(pc);

        // 执行生成
        mpg.execute();

    }

}
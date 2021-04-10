package com.github.ulwx.aka.dbutils.database.spring.boot.starter.test;

import com.github.ulwx.aka.dbutils.database.spring.AkaMpperScannerConfigurer;
import com.github.ulwx.aka.dbutils.database.spring.MDataBaseFactory;
import com.github.ulwx.aka.dbutils.database.spring.MDataBaseTemplate;
import com.github.ulwx.aka.dbutils.database.utils.DbConst;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement(proxyTargetClass = true)
@EnableAspectJAutoProxy(exposeProxy = true)
@Configuration
@ComponentScan
public class MyConfiguration implements ApplicationContextAware {

    private ApplicationContext ctx;
    @Bean(destroyMethod = "close")
    public BasicDataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/test?x=1&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8&useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("abcd");
        dataSource.setMaxWaitMillis(10000);
        dataSource.setInitialSize(1);
        dataSource.setMaxTotal(10);
        dataSource.setMinEvictableIdleTimeMillis(6000);
        return dataSource;

    }
    @Bean
    public DataSourceTransactionManager transactionManager() {
        DataSourceTransactionManager dt = new DataSourceTransactionManager();
        dt.setDataSource(dataSource());//①
        return dt;
    }
    //配置MDataBaseTemplate类型Bean，如果不配置，aka-dbutils-spring-boot-starter会自动配置一个
    @Bean
    public MDataBaseFactory mDataBaseFactory() {
        MDataBaseFactory mDataBaseFactory = new MDataBaseFactory(dataSource());//② 和①处的必须注入同一个连接池
        mDataBaseFactory.setTableColumRule(DbConst.TableNameRules.underline_to_camel);
        mDataBaseFactory.setTableNameRule(DbConst.TableColumRules.underline_to_camel);
        return mDataBaseFactory;

    }
    @Bean
    //配置MDataBaseTemplate Bean用于数据库操作，如果不配置，aka-dbutils-spring-boot-starter会自动配置一个
    public MDataBaseTemplate mDataBaseTemplate() {
        MDataBaseFactory mDataBaseFactory=mDataBaseFactory();
        return new MDataBaseTemplate(mDataBaseFactory);
    }
    @Bean
    //配置AkaMpperScannerConfigurer Bean用于用于扫描Mapper（继承自AkaMapper）从而生成代理Bean注册到Spring容器
    //如果不配置,aka-dbutils-spring-boot-starter会自动配置一个
    public static  AkaMpperScannerConfigurer akaMpperScannerConfigurer(){
        AkaMpperScannerConfigurer akaMpperScannerConfigurer=
                new AkaMpperScannerConfigurer();
        akaMpperScannerConfigurer.setBasePackage(MyConfiguration.class.getPackage().getName());
        //指定MDataBaseTemplate Bean的名称，如果不指定，默认使用"mDataBaseTemplate"名称
        akaMpperScannerConfigurer.setMdDataBaseTemplateBeanName("mDataBaseTemplate");
        return akaMpperScannerConfigurer;
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx=applicationContext;
    }
}

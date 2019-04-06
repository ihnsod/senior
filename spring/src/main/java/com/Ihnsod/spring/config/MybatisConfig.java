//package com.Ihnsod.spring.config;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.core.io.support.ResourcePatternResolver;
//
//import javax.sql.DataSource;
//import java.io.IOException;
//
///**
// * @author: Ihnsod
// * @create: 2019/03/23 13:23
// **/
//@Configuration
//public class MybatisConfig {
//
//    private Logger logger = LoggerFactory.getLogger(MybatisConfig.class);
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Bean(name = "sqlSessionFactory")
//    public SqlSessionFactory sqlSessionFactoryBean(@Value("${mybatis.mapperLocations}") String mapperPath,
//                                                   @Value("${mybatis.configLocations}") String configPath) {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
//        //添加xml目录
//        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        try {
//            bean.setMapperLocations(resolver.getResources(mapperPath));
//            bean.setConfigLocation(new ClassPathResource(mapperPath));
//            return bean.getObject();
//        } catch (IOException e) {
//            logger.error("mybatis配置错误！", e);
//        } catch (Exception e) {
//            logger.error("mybatis配置错误！", e);
//            throw new RuntimeException(e);
//        }
//        return null;
//    }
//
////    //配置mybatis的分页插件pageHelper
////    @Bean
////    public PageHelper pageHelper() {
////        PageHelper pageHelper = new PageHelper();
////        Properties properties = new Properties();
////        properties.setProperty("offsetAsPageNum", "true");
////        properties.setProperty("rowBoundsWithCount", "true");
////        properties.setProperty("reasonable", "true");
//////        properties.setProperty("dialect", "mysql");    //配置mysql数据库的方言
////        pageHelper.setProperties(properties);
////        return pageHelper;
////    }
//}

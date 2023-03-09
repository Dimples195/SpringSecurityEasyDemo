package com.tp.config;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.tp.handler.BeanEntityMetaObjectHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class MybatisPlusConfig {
    /**
     * 字段自动填充
     */
//    @Bean
//    public MybatisSqlSessionFactoryBean sessionFactory(DataSource dataSource) {
//        MybatisSqlSessionFactoryBean mybatisPlus = new MybatisSqlSessionFactoryBean();
//        //加载数据源
//        mybatisPlus.setDataSource(dataSource);
//        //全局配置
//        GlobalConfig globalConfig = new GlobalConfig();
//        //配置填充器
//        globalConfig.setMetaObjectHandler(new BeanEntityMetaObjectHandler());
//        globalConfig.setBanner(false);
//        mybatisPlus.setMapperLocations(resolveMapperLocations());
//        mybatisPlus.setGlobalConfig(globalConfig);
//        return mybatisPlus;
//    }
//
//    public Resource[] resolveMapperLocations() {
//        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
//        List<String> mapperLocations = new ArrayList<>();
//        mapperLocations.add("classpath*:/mapper/**/*.xml");
//        List<Resource> resources = new ArrayList<>();
//        for (String mapperLocation : mapperLocations) {
//            try {
//                Resource[] mappers = resourceResolver.getResources(mapperLocation);
//                resources.addAll(Arrays.asList(mappers));
//            } catch (IOException e) {
//                // ignore
//            }
//        }
//        return resources.toArray(new Resource[0]);
//    }
}

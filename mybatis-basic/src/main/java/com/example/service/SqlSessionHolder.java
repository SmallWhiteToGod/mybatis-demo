package com.example.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SqlSessionHolder implements InitializingBean, ApplicationContextAware {

    //sqlSession 可以直接执行已经映射的sql
    private static SqlSession sqlSession;

    private ApplicationContext applicationContext;

    @Override
    public void afterPropertiesSet() throws Exception {
        openSession();
    }

    public void openSession() throws IOException {
        //通过配置文件方式 获取SqlSessionFactory
        sqlSession = (SqlSession)applicationContext.getBean("sqlSession");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public SqlSession getSqlSession(){
        return sqlSession;
    }
}

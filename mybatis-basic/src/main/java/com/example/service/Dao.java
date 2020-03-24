package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Dao<T> {

    @Autowired
    private SqlSessionHolder sqlSessionHolder;

    /**
     *  @param sqlId sql的唯一标识(命名空间+sqlId)
     * @param param 要执行的参数
     */
    public T selectOne(String sqlId, Object param) {
        return sqlSessionHolder.getSqlSession().selectOne(sqlId,param);
    }


}

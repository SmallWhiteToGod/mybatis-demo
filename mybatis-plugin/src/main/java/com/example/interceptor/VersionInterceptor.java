package com.example.interceptor;

import com.example.util.ReflectUtil;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;
import java.util.Properties;

@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class VersionInterceptor implements Interceptor {

    private static final Logger logger = Logger.getLogger(VersionInterceptor.class);

    private static final String BY_VERSION = "byversion";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        Object param = invocation.getArgs()[1];
        Class<?> clazz = param.getClass();

        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        SqlCommandType commandType = mappedStatement.getSqlCommandType();

        Method setVersion = ReflectUtil.getMethod(clazz, "setVersion", Long.class);
        Method getVersion = ReflectUtil.getMethod(clazz, "getVersion");

        //新增类型  初始化version为0
        if (commandType == SqlCommandType.INSERT) {
            logger.info("versionInterceptor init version");
            if (setVersion != null) {
                setVersion.invoke(param, 0L);
            }
        }
        //修改类型
        if (commandType == SqlCommandType.UPDATE) {
            String sqlId = mappedStatement.getId();
            if (sqlId.toLowerCase().contains(BY_VERSION)) {
                logger.info("versionInterceptor update");
                Integer effectCnt = (Integer) invocation.proceed();
                if (effectCnt != null && effectCnt == 0) {
                    throw new RuntimeException("存在版本冲突，更新失败!!");
                } else {
                    //更新成功 将版本号加一（不推荐使用）
                    if (setVersion != null && getVersion != null) {
                        Long nowVersion = (Long) getVersion.invoke(param, new Object[]{});
                        if (nowVersion != null) {
                            setVersion.invoke(param, nowVersion + 1L);
                        }
                    }
                }
                return effectCnt;
            }
        }

        //执行原方法并返回（记得执行 否则拦截链会断）
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            logger.info("注册乐观锁拦截器");
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {
    }
}

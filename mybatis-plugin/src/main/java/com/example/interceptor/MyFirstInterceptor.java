package com.example.interceptor;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;
import java.util.Properties;

//插件签名：指定拦截的对象和方法(数组)
@Intercepts({@Signature(type = StatementHandler.class,method = "parameterize",args = java.sql.Statement.class)})
public class MyFirstInterceptor implements Interceptor {

    private static final Logger logger = Logger.getLogger(MyFirstInterceptor.class);

    /**
     * 动态的改变执行sql的参数 测试是从查找id为1的员工,改查id为3的员工
     * 对目标方法进行拦截:
     *     public void parameterize(Statement statement) throws SQLException {
     *         this.parameterHandler.setParameters((PreparedStatement)statement);
     *     }
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();
        //获取执行参数
        MetaObject metaObject = SystemMetaObject.forObject(target);
        Object param = metaObject.getValue("parameterHandler.parameterObject");
        //打印拦截信息
        logInterceptorInfo(target.getClass(),invocation.getMethod(),param);
        //修改参数的值  从查找id为1的员工,改查id为3的员工
        metaObject.setValue("parameterHandler.parameterObject",3);

        //执行原方法并返回（记得执行 否则拦截链会断）
        return invocation.proceed();
    }

    /**
     * 为目标对象创建一个代理对象
     * @return 返回代理对象
     */
    @Override
    public Object plugin(Object target) {
        //可以借助Plugin的wrap方法(JDK动态代理)
        if (target instanceof StatementHandler) {
            Object wrap = Plugin.wrap(target, this);
            logger.info("MyFirstInterceptor plugin()创建的代理对象："+wrap);
            return wrap;
        } else {
            return target;
        }
    }

    /**
     * 将插件注册时的property，设置进来
     */
    @Override
    public void setProperties(Properties properties) {}

    /**
     * 打印拦截的对象信息
     * @param name
     * @param method
     * @param param
     */
    private void logInterceptorInfo(Class<?> name, Method method, Object param) {
        logger.info("MyFirstInterceptor 拦截的目标对象: "+ name.getName());
        logger.info("MyFirstInterceptor 拦截的目标方法: "+ method.getName());
        logger.info("MyFirstInterceptor 拦截方法的参数: "+ param);
    }
}

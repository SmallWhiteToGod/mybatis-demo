import com.example.dao.EmployeeDao;
import com.example.pojo.Employee;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class MybatisCacheTest {

    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSession sqlSession;

    /**
     * 分布式情况下 缓存与数据库不一致
     */


    /**
     * 一级缓存测试  sqlSession级别的缓存 默认开启
     * @throws IOException
     * SqlSession was not registered for synchronization because synchronization is not active
     */
    @Test
    public void test1() throws IOException{
        EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
        Employee employee1 = employeeDao.selectById(1);
        Employee employee2 = employeeDao.selectById(1);

        logger.info("由于一级缓存的存在,只查询一次数据库，sql语句打印一次");
        logger.info("两次的对象是否相等 employee1==employee2 :" + (employee1 == employee2));
    }

    /**
     * 二级缓存测试  nameSpace级别的缓存 需要在setting配置和映射文件中开启
     * @throws IOException
     * Cache Hit Ratio : 0.5
     */
    @Test
    public void test2() throws IOException, InterruptedException {
        EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
        Employee employee1 = employeeDao.selectById(1);
        System.out.println(employee1.toString());
        Thread.sleep(5000);

        Employee employee2 = employeeDao.selectById(1);

        logger.info("由于二级缓存的存在,只查询一次数据库，sql语句打印一次");
        logger.info("readOnly为true时对象相等 employee1==employee2 :" + (employee1 == employee2));
    }
}

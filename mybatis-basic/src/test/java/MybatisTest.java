import com.example.dao.EmployeeDao;
import com.example.pojo.Employee;
import com.example.service.Dao;
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
public class MybatisTest {

    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    private Dao<Employee> dao;

    @Autowired
    private SqlSession sqlSession;

    /**
     * 通过sqlId的方式查询
     */
    @Test
    public void test1() throws IOException{
        String sqlId = "com.example.dao.EmployeeDao.selectById";
        Employee employee = dao.selectOne(sqlId,1);
        logger.info(employee.toString());
    }

    /**
     * 面向接口 创建一个接口,mybatis会为接口创建一个代理实现类执行sql
     * (命名空间=接口的全类名 sql标签id=方法名)
     */
    @Test
    public void test2() throws IOException{
        EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
        Employee employee = employeeDao.selectById(1);
        logger.info(employee.toString());
    }
}

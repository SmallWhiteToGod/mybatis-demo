import com.example.dao.EmployeeDao;
import com.example.pojo.Employee;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class MybatisInterceptorTest {

    private static final Logger logger = Logger.getLogger(MybatisInterceptorTest.class);

    @Autowired
    private SqlSession sqlSession;

    /**
     * 自定义插件
     */
    @Test
    public void testInterceptor() {
        EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
        Employee employee = employeeDao.selectById(1);
        logger.info(employee.toString());
    }

    /**
     * 分页插件PageHelper
     */
    @Test
    public void testPageHelper() {
        EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
        Page<Object> page = PageHelper.startPage(1, 10);
        List<Employee> list = employeeDao.selectAll(new Employee());
        logger.info("查询出的记录数: " + list.size());
        logPageInfo(page);
        logger.info(Arrays.toString(list.toArray()));
    }

    private void logPageInfo(Page<Object> page) {
        logger.info("当前页码: "+page.getPageNum());
        logger.info("总页码:"+page.getPages());
        logger.info("每页的记录数: "+page.getPageSize());
        logger.info("总记录数: "+page.getTotal());
    }


    /**
     *  乐观锁版本测试
     */
    @Test
    public void test3() throws IOException, InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(4);

        for (int i = 0; i < 4; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Employee entity = new Employee();
                    entity.setLastName("navicat");

                    //多个线程查出,一秒后更新,只有一个线程能更新成功
                    EmployeeDao employeeDao = sqlSession.getMapper(EmployeeDao.class);
                    entity = employeeDao.selectAll(entity).get(0);

                    try {
                        Thread.sleep(1000);

                        entity.setEmail("Navicat@163.com");
                        employeeDao.updateNotNullByVersion(entity);

                        logger.info(Thread.currentThread().getName()+":更新成功 "+entity);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }catch (RuntimeException e){
                        e.printStackTrace();
                        logger.error(Thread.currentThread().getName()+":更新失败");
                    }
                    countDownLatch.countDown();
                }
            }).start();
        }
        countDownLatch.await();
    }

}

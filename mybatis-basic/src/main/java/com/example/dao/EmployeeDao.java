package com.example.dao;

import com.example.pojo.Employee;
import com.example.pojo.PageEmployee;

import java.util.List;

public interface EmployeeDao {

    Employee selectById(Integer id);

    List<Employee> selectAll(Employee template);

    int insert(Employee entity);

    int delete(Employee entity);

    int update(Employee entity);

    int updateNotNull(Employee entity);

    //模拟调用存储过程
    void getPageByProcedure(PageEmployee employee);

    //乐观锁拦截器
    int updateNotNullByVersion(Employee entity);

}

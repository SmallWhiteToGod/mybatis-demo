package com.example.pojo;

import java.util.List;

/**
 * 封装的javaBean 模拟调用存储过程
 */
public class PageEmployee {

    private int start;
    private int end;
    private int count;
    private List<Employee> emps;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Employee> getEmps() {
        return emps;
    }

    public void setEmps(List<Employee> emps) {
        this.emps = emps;
    }
}

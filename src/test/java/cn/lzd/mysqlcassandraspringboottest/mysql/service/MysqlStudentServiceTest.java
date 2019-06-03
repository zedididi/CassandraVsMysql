package cn.lzd.mysqlcassandraspringboottest.mysql.service;

import cn.lzd.mysqlcassandraspringboottest.model.StudentInfo;
import cn.lzd.mysqlcassandraspringboottest.service.MysqlStudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: mysql-cassandra-springboot-test
 * @Author： LiuZedi
 * @Date： 2019/6/1 21:23
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MysqlStudentServiceTest {


    @Autowired
    private MysqlStudentService mysqlStudentService;

    @Test
    public void deleteStudentInfo() {
        mysqlStudentService.deleteStudentInfo(2000 );
    }

    @Test
    public void insert() {
        String[] names = {"苏枫", "大佬", "汉堡", "菜饼", "王者"};
        for (int i = 0; i < 200000  ; i++)
            mysqlStudentService.insertStudent(new StudentInfo(names[i % 5] + i, i, "this is " + i, i + 1));

    }
}
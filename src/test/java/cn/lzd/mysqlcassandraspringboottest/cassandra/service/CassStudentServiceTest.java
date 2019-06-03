package cn.lzd.mysqlcassandraspringboottest.cassandra.service;

import cn.lzd.mysqlcassandraspringboottest.model.StudentInfo;
import cn.lzd.mysqlcassandraspringboottest.service.CassStudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: mysql-cassandra-springboot-test
 * @Author： LiuZedi
 * @Date： 2019/5/24 16:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CassStudentServiceTest {

    @Autowired
    CassandraTemplate cassandraTemplate;

    @Autowired
    CassStudentService cassStudentService;

    @Test
    public void getStudentById() {
       // cassStudentService.getAllStudent();
        cassStudentService.getStudentInfoById(163104);
        System.out.println(cassStudentService.getAllStudent());
    }
    @Test
    public void delete(){
        cassStudentService.dropTable();
    }
    @Test
    public void insertStudentInfo() {
        System.out.println(cassStudentService.insertStudentInfo(new StudentInfo("sufeng",1313382,"广东",18)));
        System.out.println(cassStudentService.getStudentInfoById(1313382));
    }
}
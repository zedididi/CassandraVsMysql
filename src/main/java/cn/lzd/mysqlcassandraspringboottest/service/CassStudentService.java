package cn.lzd.mysqlcassandraspringboottest.service;

import cn.lzd.mysqlcassandraspringboottest.model.StudentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: mysql-cassandra-springboot-test
 * @Author： LiuZedi
 * @Date： 2019/5/24 14:10
 */
@Service
public class CassStudentService {

    @Autowired
    private CassandraTemplate cassandraTemplate;

    public void createKeyspace() {
        String cql = "CREATE KEYSPACE if not exists mydb WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '3'}";
        cassandraTemplate.update(cql);
    }

    public void createTable() {
        String cql = "CREATE TABLE if not exists mydb.student (name text,id int,city text,age int,PRIMARY KEY (id))";
        cassandraTemplate.update(cql);
    }

    public void dropTable() {
        String cql = "DROP TABLE mydb.student";
        cassandraTemplate.update(cql);
    }

    public List<StudentInfo> getStudentInfoById(int id) {
        String cql = "select * from student where id=" + id + ";";
        return cassandraTemplate.select(cql, StudentInfo.class);
    }

    public List<StudentInfo> getAllStudent() {

        String cql = "select * from student";
        return cassandraTemplate.select(cql, StudentInfo.class);
    }


    public StudentInfo insertStudentInfo(StudentInfo studentInfo) {
        String cql = "insert into student (`name`,id,city,age) values(?,?,?,?);";
        return cassandraTemplate.insert(studentInfo);
    }

}


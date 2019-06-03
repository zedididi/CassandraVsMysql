package cn.lzd.mysqlcassandraspringboottest.service;

import cn.lzd.mysqlcassandraspringboottest.model.StudentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: mysql-cassandra-springboot-test
 * @Author： LiuZedi
 * @Date： 2019/5/24 9:38
 */
@Service
public class MysqlStudentService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<StudentInfo> getStudentInfoById(int id) {

        String sql = "select * from student where id=" + id;
        RowMapper<StudentInfo> rowMapper = new BeanPropertyRowMapper<StudentInfo>(StudentInfo.class);
        List<StudentInfo> list = jdbcTemplate.query(sql, rowMapper);
        return list;
    }

    public List<StudentInfo> getStudentInfoByNameAndId(String name, int id) {

        String sql = "select * from student where name=" + name + " id=" + id;
        RowMapper<StudentInfo> rowMapper = new BeanPropertyRowMapper<StudentInfo>(StudentInfo.class);
        List<StudentInfo> list = jdbcTemplate.query(sql, rowMapper);
        return list;
    }

    public List<StudentInfo> getAllStudentInfo() {

        String sql = "select * from student";
        RowMapper<StudentInfo> rowMapper = new BeanPropertyRowMapper<>(StudentInfo.class);
        List<StudentInfo> list = jdbcTemplate.query(sql, rowMapper);
        return list;
    }

    public int insertStudent(StudentInfo studentInfo) {

        String sql = "insert into student (`name`,city,age) values(?,?,?);";
        return jdbcTemplate.update(sql, new Object[]{studentInfo.getName(), studentInfo.getCity(), studentInfo.getAge()});
    }

    public int deleteStudentInfo(int n) {
        String sql = "delete from student where id>=" + n;
        return jdbcTemplate.update(sql);
    }
}

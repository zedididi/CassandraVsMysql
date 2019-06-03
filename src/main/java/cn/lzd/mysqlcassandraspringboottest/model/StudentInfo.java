package cn.lzd.mysqlcassandraspringboottest.model;

import lombok.Data;
import org.springframework.context.annotation.Primary;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.stereotype.Repository;

/**
 * @program: mysql-cassandra-springboot-test
 * @Author： LiuZedi
 * @Date： 2019/5/23 18:36
 */

@Data
@Table(value = "student")
public class StudentInfo {


    @Column("name")
    private String name;
    @PrimaryKey("id")
    private int id;
    @Column("city")
    private String city;
    @Column("age")
    private int age;

    public StudentInfo() {
    }

    public StudentInfo(String name, int id, String city, int age) {
        this.name = name;
        this.id = id;
        this.city = city;
        this.age = age;
    }
}

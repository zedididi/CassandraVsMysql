package cn.lzd.mysqlcassandraspringboottest.model;

import lombok.Data;

/**
 * @program: mysql-cassandra-springboot-test
 * @Author： LiuZedi
 * @Date： 2019/5/25 12:20
 */

@Data
public class ThreadModel {

    private String name;
    private Long time;
    private String type;
    private int runTimes;

    public ThreadModel() {
    }

    public ThreadModel(String name, Long time, String type, int runTimes) {
        this.name = name;
        this.time = time;
        this.type = type;
        this.runTimes = runTimes;
    }
}

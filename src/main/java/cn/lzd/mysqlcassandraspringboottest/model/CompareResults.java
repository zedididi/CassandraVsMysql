package cn.lzd.mysqlcassandraspringboottest.model;

import lombok.Data;

/**
 * @program: mysql-cassandra-springboot-test
 * @Author： LiuZedi
 * @Date： 2019/6/1 17:21
 */

@Data
public class CompareResults {
    private String type;
    private Long[] insert = new Long[5];
    private Long[] query = new Long[5];
    private Long[] result = new Long[5];

    public CompareResults() {
    }

    public CompareResults(String type) {
        this.type = type;
    }

    public void setResult(int index, Long value) {
        result[index] = value;
    }

    public void setInsert(int index, Long value) {
        insert[index] = value;
    }

    public void setQuery(int index, Long value) {
        query[index] = value;
    }
}

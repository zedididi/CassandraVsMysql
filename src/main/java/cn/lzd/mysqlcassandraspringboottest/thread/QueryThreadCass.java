package cn.lzd.mysqlcassandraspringboottest.thread;

import cn.lzd.mysqlcassandraspringboottest.service.CassStudentService;
import cn.lzd.mysqlcassandraspringboottest.model.ThreadModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * @program: mysql-cassandra-springboot-test
 * @Author： LiuZedi
 * @Date： 2019/6/1 20:49
 */
public class QueryThreadCass implements Callable {

    private static final Logger logger = LoggerFactory.getLogger(QueryThreadCass.class);
    private int end;
    private CassStudentService cassStudentService;

    public QueryThreadCass(int end, CassStudentService cassStudentService) {
        this.end = end;
        this.cassStudentService = cassStudentService;
    }

    @Override
    public ThreadModel call() {
        Random random = new Random(System.currentTimeMillis());
        Long startTime = System.currentTimeMillis();
        for (int i = 0; i < end; i++) {
            cassStudentService.getStudentInfoById(random.nextInt(2000));
        }
        Long endTime = System.currentTimeMillis();
        logger.info(Thread.currentThread().getName() + "线程被调用了！！ 此线程花费时间 " + (endTime - startTime) + "     当前查询总耗时： " + (endTime - startTime));
        return new ThreadModel(Thread.currentThread().getName(), endTime - startTime, "queryCass", end);
    }
}

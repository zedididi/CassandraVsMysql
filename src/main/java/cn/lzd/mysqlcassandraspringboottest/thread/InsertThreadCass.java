package cn.lzd.mysqlcassandraspringboottest.thread;

import cn.lzd.mysqlcassandraspringboottest.service.CassStudentService;
import cn.lzd.mysqlcassandraspringboottest.model.StudentInfo;
import cn.lzd.mysqlcassandraspringboottest.model.ThreadModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

/**
 * @program: mysql-cassandra-springboot-test
 * @Author： LiuZedi
 * @Date： 2019/5/25 11:39
 */
public class InsertThreadCass implements Callable {


    private static final Logger logger = LoggerFactory.getLogger(InsertThreadCass.class);
    private int begin, end;
    private CassStudentService cassStudentService;

    public InsertThreadCass(int begin, int end, CassStudentService cassStudentService) {
        this.begin = begin;
        this.end = end;
        this.cassStudentService = cassStudentService;
    }

    @Override
    public ThreadModel call() {
        String[] names = {"苏枫", "大佬", "汉堡", "菜饼", "王者"};
        Long startTime = System.currentTimeMillis();
        for (int i = begin; i < end; i++) {
            cassStudentService.insertStudentInfo(new StudentInfo(names[i % 5] + i, i, "this is " + i, i + 1));
        }
        Long endTime = System.currentTimeMillis();
        logger.info(Thread.currentThread().getName() + "线程被调用了！！ 此线程花费时间 " + (endTime - startTime) + "     当前插入总耗时： " + (endTime - startTime));
        return new ThreadModel(Thread.currentThread().getName(), endTime - startTime, "insertCass", end - begin);
    }
}

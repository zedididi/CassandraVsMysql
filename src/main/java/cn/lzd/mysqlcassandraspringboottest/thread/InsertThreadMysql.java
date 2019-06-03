package cn.lzd.mysqlcassandraspringboottest.thread;

import cn.lzd.mysqlcassandraspringboottest.model.StudentInfo;
import cn.lzd.mysqlcassandraspringboottest.model.ThreadModel;
import cn.lzd.mysqlcassandraspringboottest.service.MysqlStudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

/**
 * @program: mysql-cassandra-springboot-test
 * @Author： LiuZedi
 * @Date： 2019/5/24 12:23
 */
public class InsertThreadMysql implements Callable<ThreadModel> {

    private static final Logger logger = LoggerFactory.getLogger(InsertThreadMysql.class);
    private int begin, end;
    private MysqlStudentService mysqlStudentService;

    public InsertThreadMysql(int begin, int end, MysqlStudentService mysqlStudentService) {
        this.begin = begin;
        this.end = end;
        this.mysqlStudentService = mysqlStudentService;
    }

    @Override
    public ThreadModel call() {
        String[] names = {"苏枫", "大佬", "汉堡", "菜饼", "王者"};
        Long startTime = System.currentTimeMillis();
        for (int i = begin; i < end; i++) {
            mysqlStudentService.insertStudent(new StudentInfo(names[i % 5] + i, i, "this is " + i, i + 1));
        }
        Long endTime = System.currentTimeMillis();
        logger.info(Thread.currentThread().getName() + "线程被调用了！！ 此线程花费时间 " + (endTime - startTime) + "     当前插入总耗时： " + (endTime - startTime));
        return new ThreadModel(Thread.currentThread().getName(), endTime - startTime, "insertMysql", end - begin);
    }
}

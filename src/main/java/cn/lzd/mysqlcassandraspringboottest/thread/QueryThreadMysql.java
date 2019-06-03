package cn.lzd.mysqlcassandraspringboottest.thread;

import cn.lzd.mysqlcassandraspringboottest.model.ThreadModel;
import cn.lzd.mysqlcassandraspringboottest.service.MysqlStudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.Callable;


/**
 * @program: mysql-cassandra-springboot-test
 * @Author： LiuZedi
 * @Date： 2019/5/24 13:51
 */
public class QueryThreadMysql implements Callable<ThreadModel> {

    private static final Logger logger= LoggerFactory.getLogger(QueryThreadMysql.class);
    private int end;
    private MysqlStudentService mysqlStudentService;

    public QueryThreadMysql(int end, MysqlStudentService mysqlStudentService){
        this.end=end;
        this.mysqlStudentService = mysqlStudentService;
    }

    @Override
    public ThreadModel call() {
        Random random=new Random(System.currentTimeMillis());
        Long startTime=System.currentTimeMillis();
        for (int i=0;i<end;i++){
           mysqlStudentService.getStudentInfoById(random.nextInt(2000));
        }
        Long endTime=System.currentTimeMillis();
        logger.info(Thread.currentThread().getName()+"线程被调用了！！ 此线程花费时间 "+(endTime-startTime)+"     当前查询总耗时： "+(endTime-startTime));
        return new ThreadModel(Thread.currentThread().getName(),endTime-startTime,"queryMysql",end);
    }
}

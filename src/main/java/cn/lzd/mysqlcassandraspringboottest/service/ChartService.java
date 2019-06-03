package cn.lzd.mysqlcassandraspringboottest.service;

import cn.lzd.mysqlcassandraspringboottest.model.StudentInfo;
import cn.lzd.mysqlcassandraspringboottest.thread.InsertThreadCass;
import cn.lzd.mysqlcassandraspringboottest.thread.QueryThreadCass;
import cn.lzd.mysqlcassandraspringboottest.model.CompareResults;
import cn.lzd.mysqlcassandraspringboottest.model.ThreadModel;
import cn.lzd.mysqlcassandraspringboottest.thread.InsertThreadMysql;
import cn.lzd.mysqlcassandraspringboottest.thread.QueryThreadMysql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @program: mysql-cassandra-springboot-test
 * @Author： LiuZedi
 * @Date： 2019/6/1 20:07
 */
@Service
public class ChartService {

    @Autowired
    MysqlStudentService mysqlStudentService;
    @Autowired
    CassStudentService cassStudentService;
    int[][] t = {{10, 0}, {8, 2}, {5, 5}, {2, 8}, {0, 10}};
    int n = 1000, line = 20000;
    double n1 = 1.0;

    public CompareResults getResultByOneMysql(int n, double n1, int line) throws ExecutionException, InterruptedException {
        CompareResults compareResults = new CompareResults("oneMysql");

        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int j = 0; j < t.length; j++) {
            List<Future<ThreadModel>> futureList = new ArrayList<>();
            Long time = 0L;
            mysqlStudentService.deleteStudentInfo(line);
            for (int i = 20; i < (20 * (1 + t[j][0] * n1 / 10)); i++)
                futureList.add(executorService.submit(new InsertThreadMysql(i * n, i * n + n, mysqlStudentService)));
            for (int i = 20; i < (20 * (1 + t[j][1] * n1 / 10)); i++)
                futureList.add(executorService.submit(new QueryThreadMysql(n, mysqlStudentService)));
            for (Future<ThreadModel> future : futureList
            ) {
                time += future.get().getTime();
            }
            compareResults.setResult(j, time);
        }
        executorService.shutdown();
        return compareResults;
    }

    public CompareResults getResultByOneCassandra(int n, double n1) throws ExecutionException, InterruptedException {
        CompareResults compareResults = new CompareResults("oneCassandra");

        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int j = 0; j < t.length; j++) {
            List<Future<ThreadModel>> futureList = new ArrayList<>();
            Long time = 0L;
            for (int i = 20; i < (20 * (1 + t[j][0] * n1 / 10)); i++)
                futureList.add(executorService.submit(new InsertThreadCass(i * n, i * n + n, cassStudentService)));
            for (int i = 20; i < (20 * (1 + t[j][1] * n1 / 10)); i++)
                futureList.add(executorService.submit(new QueryThreadCass(n, cassStudentService)));
            for (Future<ThreadModel> future : futureList
            ) {
                time += future.get().getTime();
            }
            compareResults.setResult(j, time);
        }
        executorService.shutdown();
        return compareResults;
    }

    public void InsertInfo(int line) {
        mysqlStudentService.deleteStudentInfo(1);
        int n = line / 20;
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++)
            executorService.submit(new InsertThreadMysql(0, n, mysqlStudentService));
        executorService.shutdown();

    }
}
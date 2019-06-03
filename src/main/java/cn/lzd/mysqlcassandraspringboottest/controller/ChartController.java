package cn.lzd.mysqlcassandraspringboottest.controller;

import cn.lzd.mysqlcassandraspringboottest.model.CompareResults;
import cn.lzd.mysqlcassandraspringboottest.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @program: mysql-cassandra-springboot-test
 * @Author： LiuZedi
 * @Date： 2019/6/1 10:19
 */
@Controller
@RequestMapping("/chart")
public class ChartController {

    @Autowired
    ChartService chartService;

    @RequestMapping(method = RequestMethod.GET)
    public String getChart() {
        return "chart";
    }

    @RequestMapping("/run")
    public String Run() {
        return "chart-1";
    }

    @RequestMapping(value = "/result", method = RequestMethod.POST)
    @ResponseBody
    public List<CompareResults> getResult() throws ExecutionException, InterruptedException {
        List<CompareResults> list = new ArrayList<>();
        //  list.add(chartService.getResultByOneMysql(10000,1.0,200000));
        // list.add(chartService.getResultByOneCassandra(10000,1.0));
        chartService.InsertInfo(20000);
        list.add(chartService.getResultByOneMysql(1000, 1.0, 20000));
        list.add(chartService.getResultByOneCassandra(1000, 1.0));
        list.add(chartService.getResultByOneMysql(100, 1.0, 2000));
        list.add(chartService.getResultByOneCassandra(100, 1.0));
        list.add(chartService.getResultByOneMysql(10, 1.0, 2000));
        list.add(chartService.getResultByOneCassandra(10, 1.0));
        /*chartService.InsertInfo(200);
        list.add(chartService.getResultByOneMysql(10,1.0,200));
        list.add(chartService.getResultByOneCassandra(10,1.0));
        list.add(chartService.getResultByOneMysql(10,1.0,200));
        list.add(chartService.getResultByOneCassandra(10,1.0));
        list.add(chartService.getResultByOneMysql(10,1.0,200));
        list.add(chartService.getResultByOneCassandra(10,1.0));*/

        System.out.print(list);
        return list;
    }

}

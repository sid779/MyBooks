package com.example.mybooks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import javafx.util.Pair;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@RestController
public class MyBooksApplication {

    public PriorityQueue<Pair<Long, Request>> requestsByTime =
            new PriorityQueue<>(Comparator.comparingLong(a -> a.getKey()));
    public Queue<Expert> ExpertsAvailable = new LinkedList<>();
    public Queue<Expert> ExpertsWorking = new LinkedList<>();
    public Map<RequestType,Long> timeToFinish = new HashMap<>();
    public Map<String,Boolean> isCustomerInQueue = new HashMap<>();
    public List <Customer> customers = new ArrayList<>();

    MyBooksApplication()
    {
        MockService.mockData(requestsByTime,ExpertsAvailable,timeToFinish,customers,isCustomerInQueue);
    }



    public static void main(String[] args) {
        SpringApplication.run(MyBooksApplication.class, args);
    }

}

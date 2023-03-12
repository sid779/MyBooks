package com.example.mybooks;

import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class MockService {

    public static void mockData(PriorityQueue<Pair<Long, Request>> requestsByTime,
             Queue<Expert> ExpertsAvailable, Map<RequestType,Long> timeToFinish,
             List <Customer> customers,  Map<String,Boolean> isCustomerInQueue)
    {
        timeToFinish.put(RequestType.REVIEW_SALES, TimeUnit.HOURS.toMillis(2));
        timeToFinish.put(RequestType.REVIEW_PURCHASES, TimeUnit.HOURS.toMillis(2));
        timeToFinish.put(RequestType.COMPUTE_TAX, TimeUnit.HOURS.toMillis(3));
        timeToFinish.put(RequestType.ADD_TAX, TimeUnit.HOURS.toMillis(1));

        Expert expert1 = new Expert("id1","pass1");
        Expert expert2 = new Expert("id2","pass2");
        Expert expert3 = new Expert("id3","pass3");

        ExpertsAvailable.add(expert1);
        ExpertsAvailable.add(expert2);
        ExpertsAvailable.add(expert3);


        Request req1 = new Request("1",RequestType.REVIEW_SALES,System.currentTimeMillis()+timeToFinish.get(RequestType.REVIEW_SALES));
        Request req2 = new Request("1",RequestType.REVIEW_PURCHASES,System.currentTimeMillis()+timeToFinish.get(RequestType.REVIEW_PURCHASES));
        Request req3 = new Request("1",RequestType.COMPUTE_TAX,System.currentTimeMillis()+timeToFinish.get(RequestType.COMPUTE_TAX));
        Request req4 = new Request("2",RequestType.REVIEW_SALES,System.currentTimeMillis()+timeToFinish.get(RequestType.REVIEW_SALES));
        Request req5 = new Request("2",RequestType.ADD_TAX,System.currentTimeMillis()+timeToFinish.get(RequestType.ADD_TAX));
        Request req6 = new Request("2",RequestType.REVIEW_PURCHASES,System.currentTimeMillis()+timeToFinish.get(RequestType.REVIEW_PURCHASES));

        Customer customer1 = new Customer("1","pass1");
        Customer customer2 = new Customer("2","pass2");

        customers.add(customer1);
        customers.add(customer2);

        isCustomerInQueue.put("1",true);
        isCustomerInQueue.put("2",true);

        customer1.addRequest(req1);
        customer1.addRequest(req2);
        customer1.addRequest(req3);
        customer2.addRequest(req4);
        customer2.addRequest(req5);
        customer2.addRequest(req6);


        requestsByTime.add(new Pair<>(req1.getExpiry(),req1));
        requestsByTime.add(new Pair<>(req4.getExpiry(),req4));


    }
}

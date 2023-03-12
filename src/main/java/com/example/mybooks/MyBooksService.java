package com.example.mybooks;

import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Service
public class MyBooksService {
    private final MyBooksApplication myBooksApplication;

    @Autowired
    public MyBooksService(MyBooksApplication myBooksApplication){
        this.myBooksApplication = myBooksApplication;
    }

    private Customer getCustomerById(String id)
    {
        for(Customer customer:myBooksApplication.customers)
        {
            System.out.println("INSIDE loop " + customer.getId());
            if(customer.getId().equals(id)) return customer;
        }

        return new Customer(null,null);
    }

    private Expert getExpertById(String id)
    {
        for(Expert expert:myBooksApplication.ExpertsWorking)
        {
            System.out.println("INSIDE loop " + expert.getId());
            if(expert.getId().equals(id)) return expert;
        }

        return new Expert(null,null);
    }


    private Long getExpiryForRequest(Request request)
    {
        return System.currentTimeMillis()+
                myBooksApplication.timeToFinish.get(request.getRequestType());
    }

    public Queue<Request> getTaskByCustomerId(String id){
        System.out.println("INSIDE getTaskByCustomerId: " + id);
        Customer customer = getCustomerById(id);
        return customer.getRequests();
    }

    public Queue<Request> getTaskByExpertId(String id) {
        System.out.println("INSIDE getTaskByExpertId: " + id);
        return getTaskByCustomerId(getExpertById(id).getCustomerId());
    }

    public Boolean addTask(Request request, String id) {
        Customer customer = getCustomerById(id);
        if(customer.getId()==null) return false;

        request.setExpiry(getExpiryForRequest(request));
        customer.addRequest(request);
        if(!myBooksApplication.isCustomerInQueue.get(id)) {
            myBooksApplication.requestsByTime.add(
                    new Pair<>(getExpiryForRequest(request), request));
            myBooksApplication.isCustomerInQueue.put(id,true);
        }
        return true;
    }


    public List<String> finishTasks(String id) {
        Queue<Request> tasks = getTaskByExpertId(id);
        List<String> response= new ArrayList<String >();
        if(tasks.isEmpty()) return response;

        for(Request task:tasks)
        {
            System.out.println("EXPERT: " + id + " worked with customer "+ task.getCustomerID() + " at " + task.getExpiry());
            response.add("EXPERT: " + id + " worked with customer "+ task.getCustomerID() + " at " + task.getExpiry());
        }
        Customer customer = getCustomerById(tasks.peek().getCustomerID());
        customer.removeRequests();

        // update entities
        myBooksApplication.isCustomerInQueue.put(id,false);
        myBooksApplication.ExpertsAvailable.add(getExpertById(id));

        return response;
    }


    public String assignTasks() {
        if(myBooksApplication.requestsByTime.isEmpty()) return "NO TASKS";
        if(myBooksApplication.ExpertsAvailable.isEmpty()) return "NO Experts";
        while(!myBooksApplication.requestsByTime.isEmpty() && !myBooksApplication.ExpertsAvailable.isEmpty()){
            String customerId = myBooksApplication.requestsByTime.poll().getValue().getCustomerID();
            myBooksApplication.isCustomerInQueue.put(customerId,false);
            Expert expert = myBooksApplication.ExpertsAvailable.poll();
            expert.assignToCustomer(customerId);
            myBooksApplication.ExpertsWorking.add(expert);
        }

        return "SUCCESSFULLY ASSIGNED TASKS";
    }


}

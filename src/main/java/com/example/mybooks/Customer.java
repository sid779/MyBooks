package com.example.mybooks;

import java.util.LinkedList;
import java.util.Queue;

public class Customer {
    private String id;
    private String password;
    private Queue<Request> requests;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Queue<Request> getRequests() {
        return requests;
    }

    public void addRequest(Request request) {
        this.requests.add(request);
    }
    public void removeRequests(){this.requests.clear();}

    public Customer(String id, String password) {
        this.id = id;
        this.password = password;
        this.requests = new LinkedList<>();
    }
}

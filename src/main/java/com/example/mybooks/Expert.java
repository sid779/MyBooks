package com.example.mybooks;

import java.util.LinkedList;
import java.util.Queue;

public class Expert {
    public Expert(String id, String password) {
        this.id = id;
        this.password = password;
        this.customerId = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void assignToCustomer(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId(){return customerId;}

    private String id;
    private String password;

    private String customerId;
}

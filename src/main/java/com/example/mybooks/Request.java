package com.example.mybooks;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.context.annotation.Bean;

public class Request {
    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getExpertID() {
        return expertID;
    }

    public void setExpertID(String expertID) {
        this.expertID = expertID;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public long getExpiry() {
        return expiry;
    }

    public void setExpiry(long expiry) {
        this.expiry = expiry;
    }

    Request(String customerID,RequestType requestType ,Long expiry){
        this.customerID = customerID;
        this.expiry = expiry;
        expertID = null;
        this.requestType = requestType;
    }

    @JsonCreator
    Request(@JsonProperty("customerID") String customerID,@JsonProperty("requestType") RequestType requestType){
        this.customerID = customerID;
        this.expiry = 0;
        expertID = null;
        this.requestType = requestType;
    }

    private String customerID;
    private String expertID;
    private RequestType requestType;
    private long expiry;



}

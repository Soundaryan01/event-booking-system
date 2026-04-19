package com.eventbooking.eventbookingsystem.wrapper;

public class APIRequest<T> {

    private T data;

    public APIRequest() {}

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
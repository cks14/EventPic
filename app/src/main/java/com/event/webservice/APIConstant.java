package com.event.webservice;

public class APIConstant {

    public static APIConstant instance;

    public static final String CHANNEL_ID = "1";
    public static final String CHANNEL_NAME = "Notifications";
    public static final String CHANNEL_DESCRIPTION = "hello";


    public static APIConstant getInstance() {
        if (instance == null) {
            instance = new APIConstant();
        }
        return instance;
    }

    public static final String ifsc_url="https://genuinemark.org/piccollect/";
    public static final String pincode_url="http://postalpincode.in/";



}

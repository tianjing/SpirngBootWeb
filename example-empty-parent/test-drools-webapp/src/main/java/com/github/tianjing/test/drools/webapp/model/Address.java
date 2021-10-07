package com.github.tianjing.test.drools.webapp.model;

/**
 * @author 田径
 * @date 2020-05-02 20:07
 * @desc
 **/
public class Address {
    private String postcode;
    private String street;
    private String state;


    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String pPostcode) {
        postcode = pPostcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String pStreet) {
        street = pStreet;
    }

    public String getState() {
        return state;
    }

    public void setState(String pState) {
        state = pState;
    }
}

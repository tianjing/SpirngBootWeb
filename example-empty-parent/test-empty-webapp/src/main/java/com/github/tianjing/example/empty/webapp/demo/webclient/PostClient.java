package com.github.tianjing.example.empty.webapp.demo.webclient;

import tgtools.exceptions.APPErrorException;
import tgtools.net.WebClient;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 20:03
 */
public class PostClient {
    public static void main(String[] args){
        WebClient client =new WebClient();
        client.setMethod("POST");
        client.setUrl("http://127.0.0.1:8080/tg/index.html");
        try {
            String res=client.doInvokeAsString("a=1&b=2");
        } catch (APPErrorException e) {
            e.printStackTrace();
        }
    }
}

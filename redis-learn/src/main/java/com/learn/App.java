package com.learn;

import com.sun.xml.internal.ws.server.ServerRtException;

public class App {
    public static void main(String[] args){
        App app = new App();
        app.testSet("test","test redis");

        final String val= app.testGet("test");
        System.out.println(val);
    }

    public void testSet(String key, String val){
        final Jedis resource = RedisFactory.getInstance().getResource();
        try{
            resource.set(key,val);
        }finally {
            resource.close();
        }
    }
    public String testGet(String key){
        final Jedis resource = RedisFactory.getInstance().getResource();
        try{
            final String val = resource.get(key);
            return val;
        }finally {
            resource.close();
        }
    }
}

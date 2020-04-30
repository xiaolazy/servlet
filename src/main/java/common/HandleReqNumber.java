package common;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: servlet
 * @description:
 * @author: lyy
 * @generate: 2020-04-21 10:44
 **/
public class HandleReqNumber {
    public static ConcurrentHashMap<String,Integer> reqCount = new ConcurrentHashMap();
    static {
        reqCount.put("topicOne",0);
    }

    public static void handleTopicOne(){
        reqCount.put("topicOne",reqCount.get("topicOne") == 0 ? 1 : 0);
    }
    public static Integer getTopicOne(){
        return reqCount.get("topicOne");
    }
}

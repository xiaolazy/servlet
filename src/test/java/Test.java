import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
import util.IdWorker;
import util.RedisUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @program: servlet
 * @description:
 * @author: lyy
 * @generate: 2020-04-28 17:33
 **/
public class Test {
    static Integer count = 1;

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString().replace("-",""));
        Jedis jedis = RedisUtil.getInstance().getJedis();
        String cursor = "0";

        Set<String> keys = jedis.keys("u:sign:1*");
//		for (int i = 0; i < 221; i++) {
//			System.out.println(IdWorker.createId());
//		}
//        ExecutorService ex = Executors.newScheduledThreadPool(20);
//        RedisUtil.Strings str = RedisUtil.getInstance().STRINGS;
//        ex.execute(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 1; i < 9000; i++) {
//                    if(i % 30 == 0)
//                        count++;
//                    set(count,i%30,i%2);
////                    System.out.println(i%2);
//                }
//            }
//        });
//        ex.shutdown();

    }
    private static synchronized void set(int uid,int i,int val){
        Jedis jedis = RedisUtil.getInstance().getJedis();
        String key = buildSignKey(uid, LocalDate.now());

        String ss = String.valueOf(val);
        try{
            jedis.setbit(buildSignKey(uid,LocalDate.now()),i,ss);
//            System.out.println("offset::"+i);
//            System.out.println("getbit：："+jedis.getbit(key,i));
//            System.out.println("getbit：："+jedis.getbit(key,i));
        }catch (Exception e){

        }
        finally {
            jedis.close();
        }

    }

    private static String buildSignKey(int uid, LocalDate date) {
        return String.format("u:sign:%d:%s", uid, formatDate(date));
    }
    private static String formatDate(LocalDate date) {
        return formatDate(date, "yyyyMM");
    }
    private static String formatDate(LocalDate date, String pattern) {
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }

}

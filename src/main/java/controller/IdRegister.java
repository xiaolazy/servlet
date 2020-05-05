package controller;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.common.utils.DateUtil;
import entity.Result;
import entity.Student;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.http.client.utils.DateUtils;
import util.RedisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public interface IdRegister {
    String ztableName = "zset:student";
    String htableName = "zhash:student";

    default String getId(){
        return UUID.randomUUID().toString().replace("-","");
    }
    default void setId(Student tar,String id){
        tar.setId(id);
    }
    default RedisUtil.Hash getRedisHash(){
        return  RedisUtil.getInstance().HASH;
    }
    default void setDefaultParameter(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
    }

    default  void successData(HttpServletResponse response,Object result) throws IOException {
        PrintWriter out = response.getWriter();
        String json = JSONObject.toJSONString(result);

        out.println(json);
        out.close();
    }

    default  <T> void reflectParameter(T obj, HttpServletRequest request) {
        try {
            //获取对象的Class
            Class<?> clazz = obj.getClass();
            //获取Class中所有已声明的属性集合
            Field[] fileds = clazz.getDeclaredFields();
            //遍历属性结合
            for (Field f : fileds) {
                //过滤被final、static修饰的成员变量
                if ((f.getModifiers() & Modifier.FINAL) > 0
                        || (f.getModifiers() & Modifier.STATIC) > 0) {
                    continue;
                }
                //取消所有私有变量的限制
                f.setAccessible(true);//取消Field的访问检查
                //获取属性的类型,long/int/string....
                Class<?> fieldType = f.getType();
                //获取属性的名字
                String fieldName = f.getName();
                //根据属性的名字从request中获取value
                String paramVal = request.getParameter(fieldName);
                //判断类型,如果是String
                if (String.class == fieldType) {
                    f.set(obj, paramVal);
                    //判断类型,如果是long,则使用NumberUtils.toLong,即使为空,也赋默认值0L
                } else if (long.class == fieldType || Long.class == fieldType) {
                    f.set(obj, NumberUtils.toLong(paramVal));
                    //判断类型,如果是int,则使用NumberUtils.toInt,即使为空,也赋默认值0
                } else if (int.class == fieldType || Integer.class == fieldType) {
                    f.set(obj, NumberUtils.toInt(paramVal));
                    //判断类型,如果是date,则使用DateUtil.parseDateNewFormat格式化日期格式
                } else if (LocalDate.class == fieldType) {
                    f.set(obj, LocalDate.parse(paramVal, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                }
            }
        } catch (IllegalArgumentException e) {
        } catch (IllegalAccessException e) {
        }
    }
}

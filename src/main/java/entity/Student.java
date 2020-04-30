package entity;

import com.alibaba.fastjson.JSONObject;
import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

/**
 * @program: servlet
 * @description:
 * @author: lyy
 * @generate: 2020-04-28 17:37
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student implements Serializable {
    private static final long serialVersionUID = 1l;
    private String id;
    private String name;
    public LocalDate birthday;
    private String description;
    private Integer avgscore;

    public String toJSON(){
        return JSONObject.toJSONString(this);
    }
}

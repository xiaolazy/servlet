package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: servlet
 * @description:
 * @author: lyy
 * @generate: 2020-04-29 15:01
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result implements Serializable {
    private static final Long serialVersionUID = 1L;
    private Integer code;
    private String msg;
    private Object data;

    public static Result success(){
        return new Result(200,"","");
    }
    public static Result successData(Object data){
        return new Result(200,"",data);
    }
}

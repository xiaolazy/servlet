package entity;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @program: servlet
 * @description:
 * @author: lyy
 * @generate: 2020-04-29 14:58
 **/
@Data
@NoArgsConstructor @AllArgsConstructor @Builder
public class PageResult implements Serializable {
    private static final Long serialVersionUID = 1L;

    private List<Student> students;
    private Integer count;
    private Integer pageNum;
    private Object data;

    public PageResult(Object data){
        this.data = data;
    }
    public static PageResult successData(Object data){
        return new PageResult(data);
    }
}

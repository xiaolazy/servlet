package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: servlet
 * @description:
 * @author: lyy
 * @generate: 2020-04-21 10:04
 **/
@Data
@NoArgsConstructor @AllArgsConstructor @Builder
public class Header implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String key;
    protected String name;

}

package pojo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Access {
    private Long time;
    private String domain;
    private Double traffic;
}

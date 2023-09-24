package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessLogDO {
    private String title;
    private String url;
    private String method;
    private Integer httpCode;
    private String body;
    private Date createTime;
    private String userId;
    private String city;
}

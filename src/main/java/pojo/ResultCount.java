package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultCount {
    private String url;
    private Integer code;
    private Long count;
    private String startTime;
    private String endTime;
    private String type;
}
package transformation;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import pojo.Access;

/**
 * datastream -> datastream
 * 过滤掉不满足条件的数据
 */
public class FilterDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<String> source = env.readTextFile("data/access.log");
        SingleOutputStreamOperator<Access> mapResult = source.map(new MapFunction<String, Access>() {
            @Override
            public Access map(String value) throws Exception {
                String[] values = value.split(",");
                Access access = new Access();
                access.setTime(Long.valueOf(values[0]));
                access.setDomain(values[1]);
                access.setTraffic(Double.valueOf(values[2]));
                return access;
            }
        });
        SingleOutputStreamOperator<Access> filterResult = mapResult.filter(new FilterFunction<Access>() {
            @Override
            public boolean filter(Access value) throws Exception {
                return value.getTraffic() > 6000;
            }
        });
        filterResult.print();
        env.execute("filter test");
    }
}

package transformation;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import pojo.Access;

/**
 * datastream -> datastream
 * 一个元素转换成另一个元素
 */
public class MapDemo {
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
        mapResult.print();
        env.execute("map test");
    }
}

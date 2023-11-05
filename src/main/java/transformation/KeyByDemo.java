package transformation;


import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import pojo.Access;

/**
 * datastream -> keyedstream
 *
 * keyBy 将数据根据key分散到不同的分区
 *
 * reduce 将相同的key聚合处理
 */
public class KeyByDemo {
    public static void main(String[] args) throws Exception{
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
        KeyedStream<Access, String> keyedStream = mapResult.keyBy(new KeySelector<Access, String>() {
            @Override
            public String getKey(Access value) throws Exception {
                return value.getDomain();
            }
        });

        SingleOutputStreamOperator<Access> sumResult = keyedStream.sum("traffic");

        sumResult.print();
        env.execute();
    }
}

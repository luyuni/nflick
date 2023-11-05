package transformation;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;
import pojo.Access;

/**
 * datastream -> datastream
 * 一个元素出去可能0个元素1个元素多个元素等
 */
public class FlatMapDemo {
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
        SingleOutputStreamOperator<String> flatMapResult = mapResult.flatMap(new FlatMapFunction<Access, String>() {
            @Override
            public void flatMap(Access value, Collector<String> out) throws Exception {
                if (value.getDomain().equals("nyl1.com")) {

                } else if (value.getDomain().equals("nyl2.com")) {
                    out.collect(value.getDomain());
                } else {
                    out.collect(value.getDomain());
                    out.collect(String.valueOf(value.getTime()));
                }
            }
        });
        flatMapResult.print();
        env.execute("map test");
    }
}

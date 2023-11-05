package transformation;

import org.apache.flink.streaming.api.datastream.ConnectedStreams;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.CoMapFunction;

import java.util.Locale;

/**
 * datastream,datastream -> datastream
 * 两个数据流的合并
 * 数据类型可以不同
 * 可以共享状态
 * 形式上的合并
 */
public class ConnectDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<Integer> stream1 = env.fromElements(11, 22, 33);
        DataStreamSource<String> stream2 = env.fromElements("A", "B", "C");
        ConnectedStreams<Integer, String> connectResult = stream1.connect(stream2);
        SingleOutputStreamOperator<String> mapedResult = connectResult.map(new CoMapFunction<Integer, String, String>() {
            // 共享状态
            private String prefix = "nyl_";

            @Override
            public String map1(Integer value) throws Exception {
                return prefix + value * 10;
            }

            @Override
            public String map2(String value) throws Exception {
                return prefix + value.toLowerCase(Locale.ROOT);
            }
        });

        mapedResult.print();

        env.execute("map test");
    }
}

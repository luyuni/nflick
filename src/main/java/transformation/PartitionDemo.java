package transformation;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.Partitioner;
import org.apache.flink.api.java.functions.IdPartitioner;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import pojo.Access;

/**
 * datastream -> datastream
 * 一个元素转换成另一个元素
 */
public class PartitionDemo {
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

        DataStream<Access> partitionCustomResult = mapResult.partitionCustom(new Partitioner<String>() {
            @Override
            public int partition(String key, int numPartitions) {
                System.out.println(numPartitions);
                if ("nyl1.com".equals(key)) {
                    return 0;
                } else if ("nyl2.com".equals(key)) {
                    return 1;
                } else {
                    return 2;
                }
            }
        }, new KeySelector<Access, String>() {
            @Override
            public String getKey(Access value) throws Exception {
                return value.getDomain();
            }
        });
        // 这个map主要验证一下 相同的partition是不是在一个地方处理
        SingleOutputStreamOperator<String> mapedResult = partitionCustomResult.map(new MapFunction<Access, String>() {
            @Override
            public String map(Access value) throws Exception {
                System.out.println("thread id is " + Thread.currentThread().getId() + " - " + value.getDomain());
                return value.getDomain();
            }
        });
        mapedResult.print();
        env.execute("map test");
    }
}

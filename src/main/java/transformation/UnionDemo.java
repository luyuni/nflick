package transformation;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import pojo.Access;

/**
 * 多个datastream -> datastream
 * 多个dataStream合并成一个datastream，自己合并自己就每个元素double一份
 * 多个流中的数据类型必须相同
 */
public class UnionDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<Integer> stream1 = env.fromElements(11, 22, 33);
        DataStreamSource<Integer> stream2 = env.fromElements(1, 2, 3);
        DataStreamSource<Integer> stream3 = env.fromElements(4);
        DataStream<Integer> unionResult = stream1.union(stream2, stream3);

        unionResult.print();

        env.execute("map test");
    }
}

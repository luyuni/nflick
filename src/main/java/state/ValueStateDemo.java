package state;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用value来进行状态管理
 */
public class ValueStateDemo {

    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        List<Tuple2<String, Long>> list = new ArrayList<>();


        env.execute();
    }
}

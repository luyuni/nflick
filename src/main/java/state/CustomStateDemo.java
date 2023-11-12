package state;


import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CustomStateDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setRuntimeMode(RuntimeExecutionMode.STREAMING);

        DataStream<String> source = env.socketTextStream("localhost", 9999);

        SingleOutputStreamOperator<String> mapedStream = source.map(item -> item.toLowerCase(Locale.ROOT));

        SingleOutputStreamOperator<Tuple2<String, Long>> flatMapedStream = mapedStream.flatMap(new FlatMapFunction<String, Tuple2<String, Long>>() {
            @Override
            public void flatMap(String value, Collector<Tuple2<String, Long>> out) throws Exception {
                String[] words = value.split(",");
                for (String word : words) {
                    out.collect(Tuple2.of(word, 1l));
                }
            }
        });

        KeyedStream<Tuple2<String, Long>, String> keyedStream = flatMapedStream.keyBy(new KeySelector<Tuple2<String, Long>, String>() {
            @Override
            public String getKey(Tuple2<String, Long> value) throws Exception {
                return value.f0;
            }
        });

        SingleOutputStreamOperator<Tuple2<String, Long>> customMap = keyedStream.map(new MapFunction<Tuple2<String, Long>, Tuple2<String, Long>>() {
            Map<String, Long> cache = new ConcurrentHashMap<>();

            @Override
            public Tuple2<String, Long> map(Tuple2<String, Long> value) throws Exception {
                String word = value.f0;
                Long cnt = value.f1;
                Long oldCount = cache.getOrDefault(word, 0l);
                Long curCount = oldCount + cnt;
                cache.put(word, curCount);
                System.out.println("单词" + word + "的个数是" + curCount);
                return Tuple2.of(word, curCount);
            }
        });

        env.execute();
    }
}

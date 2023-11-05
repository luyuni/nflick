package transformation;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;
import pojo.Access;

/**
 * 分流demo
 */
public class SideOutputDemo {
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
        OutputTag<Access> nyl1 = new OutputTag<Access>("nyl1") {};
        OutputTag<Access> nyl2 = new OutputTag<Access>("nyl2") {};

        SingleOutputStreamOperator<Access> mainStream = mapResult.process(new ProcessFunction<Access, Access>() {
            @Override
            public void processElement(Access value, Context ctx, Collector<Access> out) throws Exception {
                if (value.getDomain().equals("nyl1.com")) {
                    ctx.output(nyl1, value);
                } else if (value.getDomain().equals("nyl2.com")) {
                    ctx.output(nyl2, value);
                } else {
                    out.collect(value);
                }
            }
        });
        mainStream.print("main stream:");
        mainStream.getSideOutput(nyl1).print("nyl1 stream:");
        mainStream.getSideOutput(nyl2).print("nyl2 stream:");

        env.execute();
    }
}

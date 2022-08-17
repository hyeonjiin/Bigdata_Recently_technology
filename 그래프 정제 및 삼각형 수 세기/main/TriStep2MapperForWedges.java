package weather;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Mapper;

public class TriStep2MapperForWedges extends Mapper<IntPairWritable, IntWritable, IntPairWritable, IntWritable>{
	
	IntPairWritable ok = new IntPairWritable();
	@Override
	protected void map(IntPairWritable key, IntWritable value, Mapper<IntPairWritable, IntWritable, IntPairWritable, IntWritable>.Context context)
			throws IOException, InterruptedException {
		StringTokenizer st = new StringTokenizer(key.toString());
		int u = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());
		if (u < v) {
			ok.set(u,v);
			context.write(ok, value);
		}
		else if (u > v) {
			ok.set(v,u);
			context.write(ok, value);
		}
	}
}

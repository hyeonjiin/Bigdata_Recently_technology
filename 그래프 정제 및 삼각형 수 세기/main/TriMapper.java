package weather;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TriMapper extends Mapper<Object, Text, IntWritable, IntWritable> {
	
	IntWritable ok = new IntWritable();
	IntWritable ov = new IntWritable();
	
	@Override
	protected void map(Object key, Text value, Mapper<Object, Text, IntWritable, IntWritable>.Context context)
			throws IOException, InterruptedException {
		
		StringTokenizer st = new StringTokenizer(value.toString());
		
		int u = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());
		if (u < v) {
			ok.set(u);
			ov.set(v);
			context.write(ok, ov);
		}
		else if (u > v) {
			ok.set(v);
			ov.set(u);
			context.write(ok, ov);
		}
	}
}

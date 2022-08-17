package weather;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TriMapper1 extends Mapper<Object, Text, IntWritable, Text> {
	
	IntWritable ok = new IntWritable();
	Text ov = new Text();
	
	@Override
	protected void map(Object key, Text value, Mapper<Object, Text, IntWritable, Text>.Context context)
			throws IOException, InterruptedException {
		
		StringTokenizer st = new StringTokenizer(value.toString());
		
		int u =  Integer.parseInt(st.nextToken());
		int v =  Integer.parseInt(st.nextToken());
		ok.set(u);
		ov.set(u+" "+v);
		context.write(ok,ov);
		
		ok.set(v);
		ov.set(u+" "+v);
		context.write(ok,ov);
	}
}

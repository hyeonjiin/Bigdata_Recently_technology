package weather;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TriMapper2 extends Mapper<Object, Text, Text, Text> {
	
	Text ok = new Text();
	Text ov = new Text();
	
	@Override
	protected void map(Object key, Text value, Mapper<Object, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		StringTokenizer st = new StringTokenizer(value.toString());
		String k = st.nextToken();
		String v = st.nextToken();
		ok.set(k);
		ov.set(v);
		context.write(ok,ov);
		
	}
}

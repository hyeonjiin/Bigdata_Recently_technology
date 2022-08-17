package weather;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class TriReducer extends Reducer<IntWritable, IntWritable,IntWritable,IntWritable>{
	IntWritable ok = new IntWritable();
	IntWritable ov = new IntWritable();
	
	@Override
	protected void reduce(IntWritable key, Iterable<IntWritable> values,
			Reducer<IntWritable, IntWritable, IntWritable, IntWritable>.Context context) throws IOException, InterruptedException {
		
		ArrayList<Integer> neighbors = new ArrayList<Integer>();
		for(IntWritable v : values) {
			if(neighbors.contains(v.get()))
			{
				continue;
			}
			neighbors.add(v.get());
		}
		
		ok.set(key.get());
		for(int u : neighbors) 
		{
			if (key.get() < u ) 
			{
				ov.set(u);
				context.write(ok, ov);
			}
		}
	}
}

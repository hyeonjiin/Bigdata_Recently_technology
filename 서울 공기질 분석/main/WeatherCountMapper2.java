package weather;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WeatherCountMapper2 extends Mapper<Object, Text, IntWritable, Text>{
	 
		IntWritable a = new IntWritable();
	    Text b = new Text();
	    int n=0;

	    @Override
	    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
	    	if(n>0)
	    	{
	    		StringTokenizer st = new StringTokenizer(value.toString(),",");
	    		String Measure_date = st.nextToken();	
	    		int Station_code = Integer.parseInt(st.nextToken());
	    		String Item_code = st.nextToken();
	    		double Item_value = Double.parseDouble(st.nextToken());

	    		if(Item_code.compareTo("8")==0 && Item_value<=30)
	    		{
	    			a.set(Station_code);
	    			b.set(Measure_date);
	    			context.write(a,b);
	    		}
	    		else if(Item_code.compareTo("9")==0 && Item_value<=15)
	    		{
	    			a.set(Station_code);
	    			b.set(Measure_date);
	    			context.write(a,b);
	    		}
	    	}
	    	n++;
	    }

}

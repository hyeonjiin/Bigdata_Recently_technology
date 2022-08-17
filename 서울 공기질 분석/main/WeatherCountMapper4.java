package weather;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WeatherCountMapper4 extends Mapper<Object,Text,Text,Text>{
	 
		Text a = new Text();
	    Text b = new Text();
	    int n=0;

	    @Override
	    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
	    	if(n>0)
	    	{
	    		StringTokenizer st = new StringTokenizer(value.toString(),",");
	    		String Measure_date = st.nextToken();	
	    		String Station_code = st.nextToken();
	    		String Item_code = st.nextToken();
	    		String Item_value = st.nextToken();
	        
	    		StringTokenizer t = new StringTokenizer(Measure_date.toString()," ");
	    		String date = t.nextToken();
	    		String tt = t.nextToken();
	        
	    		a.set(tt);
	    		b.set(Item_code+" "+Item_value);
	    		context.write(a,b);
	    	}
	    	n++;
	    }

}

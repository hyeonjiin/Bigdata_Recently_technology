package weather;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class WeatherCountMapper extends Mapper<Object, Text, Text, DoubleWritable> {

    Text word = new Text();
    DoubleWritable one = new DoubleWritable();
    int n=0;
    @Override
    protected void map(Object key, Text value, Context context) 
    		throws IOException, InterruptedException {
        if(n>0)
        {
        	StringTokenizer st = new StringTokenizer(value.toString(),",");
        	String Mueaure_date = st.nextToken();
        	String station_code = st.nextToken();
        	String item_code = st.nextToken();
        	double item_value = Double.parseDouble(st.nextToken());

        	if(item_code.compareTo("8")==0){
        		word.set(station_code);
        		one.set(item_value);
        		context.write(word,one);
        	}
        }
        n++;
    }
}

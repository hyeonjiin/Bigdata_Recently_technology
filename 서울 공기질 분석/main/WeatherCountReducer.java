package weather;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class WeatherCountReducer extends Reducer<Text, DoubleWritable, Text, Text> {

    Text a = new Text();
    @Override
    protected void reduce(Text key, Iterable<DoubleWritable> values,
                          Reducer<Text, DoubleWritable, Text, Text>.Context context) throws IOException, InterruptedException {
    	double max = 0;
    	double min = 100000;
        double sum = 0;
        int n = 0;
        for(DoubleWritable v : values) 
        {
        	if(v.get()<0)
        		continue;
            if(v.get()>max)
            {
                max = v.get();
            }
            if(v.get()<min)
            {
                min = v.get();
            }
            sum += v.get();
            n++;
        }
        double avg = sum/n;
        a.set(avg + "\t" + max + "\t" + min);
        context.write(key, a);
    }
}
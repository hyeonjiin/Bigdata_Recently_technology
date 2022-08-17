package weather;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;

public class WeatherCountReducer2 extends Reducer<IntWritable, Text, IntWritable, IntWritable> {

    IntWritable a = new IntWritable();
    IntWritable b = new IntWritable();

    int k = 0;
    int v = 0;

    @Override
    protected void reduce(IntWritable key, Iterable<Text> values,
                          Context context) throws IOException, InterruptedException {
    	ArrayList<String> d= new ArrayList<String>();
        int num=0;

        for(Text t : values) 
        {
            String date = t.toString();
            boolean check = d.contains(date);
            if(check==false)
            {
                d.add(date);
            }
            else 
            {
                d.remove(date);
                num++;
            }
        }
        if(v<num)
        {
        	k = key.get();
        	v = num;
        }
        if(key.get()==125){
            a.set(k);
            b.set(v);
            context.write(a, b);
        }
    }
}

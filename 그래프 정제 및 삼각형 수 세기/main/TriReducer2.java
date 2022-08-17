package weather;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class TriReducer2 extends Reducer<Text,Text, Text, Text>{
	Text ok = new Text();
	Text ov = new Text();
	
	@Override
	protected void reduce(Text key, Iterable<Text> values,
			Reducer<Text, Text, Text, Text>.Context context) throws IOException, InterruptedException {
		
		
		int u1=-1;
		int v1=-1;
		
		for(Text t : values) 
        {
            StringTokenizer st = new StringTokenizer(t.toString(),",");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if(u==-1)
            {
            	v1 = v;
            }
            else if(v==-1)
            {
            	u1 = u;
            }
        }
		StringTokenizer s = new StringTokenizer(key.toString(),",");
		int k = Integer.parseInt(s.nextToken());
		int v = Integer.parseInt(s.nextToken());
		if(u1<v1 || (u1==v1 && k<v))
		{
			ok.set(""+k);
			ov.set(""+v);
		}
		else
		{
			ok.set(""+v);
			ov.set(""+k);
		}
		context.write(ok,ov);
	}	
}

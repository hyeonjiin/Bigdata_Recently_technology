package weather;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class TriReducer1 extends Reducer<IntWritable,Text, Text, Text>{
	Text ok = new Text();
	Text ov = new Text();
	Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	
	@Override
	protected void reduce(IntWritable key, Iterable<Text> values,
			Reducer<IntWritable, Text, Text, Text>.Context context) throws IOException, InterruptedException {
		
		int k = key.get();
		ArrayList<Integer> neighbors = new ArrayList<Integer>();
		Map<Integer, Integer> check = new HashMap<Integer, Integer>();
		
		for(Text t : values) 
        {
            StringTokenizer st = new StringTokenizer(t.toString()," ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            
            if(k == u)
            {
            	neighbors.add(v);
            	check.put(v,0);
            	if(map.containsKey(u))
            	{
            		int num = map.get(u);
            		map.put(u, num+1);
            	}
            	else
            	{
            		map.put(u,1);
            	}
            }
            else if (k == v)
            {
            	neighbors.add(u);
            	check.put(u,1);
            	if(map.containsKey(v))
            	{
            		int num = map.get(v);
            		map.put(v, num+1);
            	}
            	else
            	{
            		map.put(v,1);
            	}
            }
        }
		for(int u : neighbors) 
		{
			if(check.get(u)==0)
			{
				ok.set(k+","+u);
				ov.set(map.get(k)+","+"-1");
			}
			else if(check.get(u)==1)
			{
				ok.set(u+","+k);
				ov.set("-1"+","+map.get(k));
			}
			context.write(ok,ov);
		}
	}
}

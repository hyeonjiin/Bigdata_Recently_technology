package weather;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.StringTokenizer;

public class WeatherCountReducer3  extends Reducer<Text, Text, Text, Text> {
	
	Text b = new Text();
	
	@Override
    protected void reduce(Text key, Iterable<Text> values,
                          Context context) throws IOException, InterruptedException {
		
		String num1 = new String();
		String num3 = new String();
		String num5 = new String();
		String num6 = new String();
		String num8 = new String();
		String num9 = new String();
		
		for(Text t : values)
		{
			 StringTokenizer st = new StringTokenizer(t.toString()," ");
			 String item_code = st.nextToken();
			 String item_value = st.nextToken();
			 if(item_code.compareTo("1")==0)
			 {
				 num1 = item_value;
			 }
			 if(item_code.compareTo("3")==0)
			 {
				 num3 = item_value;
			 }
			 if(item_code.compareTo("5")==0)
			 {
				 num5 = item_value;
			 }
			 if(item_code.compareTo("6")==0)
			 {
				 num6 = item_value;
			 }
			 if(item_code.compareTo("8")==0)
			 {
				 num8 = item_value;
			 }
			 if(item_code.compareTo("9")==0)
			 {
				 num9 = item_value;
			 }
		}
		b.set("1: "+num1+"\t"+"3: "+num3+"\t"+"5: "+num5+"\t"+"6: "+num6+"\t"+"8: "+num8+"\t"+"9: "+num9);
		context.write(key,b);
	}
}

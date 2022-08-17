package weather;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.DoubleWritable;
import java.io.IOException;
import java.util.StringTokenizer;

public class WeatherCountReducer4  extends Reducer<Text, Text, Text, DoubleWritable> {
	
	Text b = new Text();
	DoubleWritable a = new DoubleWritable();
	@Override
    protected void reduce(Text key, Iterable<Text> values,
                          Context context) throws IOException, InterruptedException {
		
		double num1 = 0.0;
		double num3 = 0.0;
		double num5 = 0.0;
		double num6 = 0.0;
		double num8 = 0.0;
		double num9 = 0.0;
		
		int n1=0;
		int n3=0;
		int n5=0;
		int n6=0;
		int n8=0;
		int n9=0;
		
		for(Text t : values)
		{
			 StringTokenizer st = new StringTokenizer(t.toString()," ");
			 String item_code = st.nextToken();
			 double item_value = Double.parseDouble(st.nextToken());
			 if(item_code.compareTo("1")==0 && item_value>=0)
			 {
				 num1 += item_value;
				 n1++;
			 }
			 if(item_code.compareTo("3")==0 && item_value>=0)
			 {
				 num3 = item_value;
				 n3++;
			 }
			 if(item_code.compareTo("5")==0 && item_value>=0)
			 {
				 num5 = item_value;
				 n5++;
			 }
			 if(item_code.compareTo("6")==0 && item_value>=0)
			 {
				 num6 = item_value;
				 n6++;
			 }
			 if(item_code.compareTo("8")==0 && item_value>=0)
			 {
				 num8 = item_value;
				 n8++;
			 }
			 if(item_code.compareTo("9")==0 && item_value>=0)
			 {
				 num9 = item_value;
				 n9++;
			 }
		}
		b.set(key +"\t"+" 1 : ");
		a.set(num1/n1);
		context.write(b, a);
		
		b.set(key+"\t"+ " 3 : ");
		a.set(num3/n3);
		context.write(b, a);
		
		b.set(key +"\t"+ " 5 : ");
		a.set(num5/n5);
		context.write(b, a);
		
		b.set(key +"\t"+ " 6 : ");
		a.set(num6/n6);
		context.write(b, a);
		
		b.set(key+"\t"+ " 8 : ");
		a.set(num8/n8);
		context.write(b, a);
		
		b.set(key +"\t"+ " 9 : ");
		a.set(num9/n9);
		context.write(b, a);
	}
}

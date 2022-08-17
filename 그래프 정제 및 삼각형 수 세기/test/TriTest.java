package weather;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;

public class TriTest {
	public static void main(String[] args) throws Exception {
		
		Configuration conf = new Configuration();
		
		
		String[] params = {"src/test/resources/com-youtube.ungraph.txt"};
		
		ToolRunner.run( new Tri(), params);
	}
}

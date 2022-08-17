package weather;
import org.apache.hadoop.util.ToolRunner;

public class WeatherTest4 {
	
    public static void main(String[] args) throws Exception {
		
		String[] input_args = {"src/test/resources/Measurement_info.csv"};
		ToolRunner.run(new WeatherCount4(), input_args);
    }

}
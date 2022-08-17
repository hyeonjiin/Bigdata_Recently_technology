package weather;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class WeatherCount3 extends Configured implements Tool{

    public static void main(String[] args) throws Exception {
        ToolRunner.run(new WeatherCount3(), args);
    }

    public int run(String[] args) throws Exception {


        Job job = Job.getInstance(getConf());
        job.setJarByClass(WeatherCount3.class);

        job.setMapperClass(WeatherCountMapper3.class);
        job.setReducerClass(WeatherCountReducer3.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[0]).suffix("3.out"));

        job.waitForCompletion(true);

        return 0;
    }

}
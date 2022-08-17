package weather;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class Tri extends Configured implements Tool {

	public static void main(String[] args) throws Exception {
		ToolRunner.run(new Tri(), args);
	}
	
	public int run(String[] args) throws Exception {
		
		String inputpath = args[0];
		String tmppath = inputpath + ".1out";
		String outpath = inputpath + ".2out";
		String outpath2 = inputpath + ".3out";
		
		runStep1(inputpath, tmppath);
		runStep2(tmppath, outpath);
		runStep3(outpath,outpath2);
		return 0;
	}
	
	private void runStep1(String inputpath, String tmppath) throws Exception{
		
		Job job = Job.getInstance(getConf());
		job.setJarByClass(Tri.class);
		
		job.setMapperClass(TriMapper.class);
		job.setReducerClass(TriReducer.class);
		
		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		FileInputFormat.addInputPath(job, new Path(inputpath));
		FileOutputFormat.setOutputPath(job, new Path(tmppath));
		
		job.waitForCompletion(true);
		
	}
	private void runStep2(String tmppath, String outpath) throws Exception {
		
		Job job = Job.getInstance(getConf());
		job.setJarByClass(Tri.class);
		
		job.setMapperClass(TriMapper1.class);
		job.setReducerClass(TriReducer1.class);
		
		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(Text.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		FileInputFormat.addInputPath(job, new Path(tmppath));
		FileOutputFormat.setOutputPath(job, new Path(outpath));
		
		job.waitForCompletion(true);	
	}
	
	private void runStep3(String outpath, String outpath2) throws Exception {
		
		Job job = Job.getInstance(getConf());
		job.setJarByClass(Tri.class);
		
		job.setMapperClass(TriMapper2.class);
		job.setReducerClass(TriReducer2.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		FileInputFormat.addInputPath(job, new Path(outpath));
		FileOutputFormat.setOutputPath(job, new Path(outpath2));
		
		job.waitForCompletion(true);
		
	}
}

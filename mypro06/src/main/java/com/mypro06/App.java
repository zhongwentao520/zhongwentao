package com.mypro06;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        //1. 创建一个job和任务入口(指定主类)
        Job job = Job.getInstance(new Configuration());
        job.setJarByClass(App.class);

        //2. 指定job的mapper和输出的类型<k2 v2>
        job.setMapperClass(SoGouQMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //3. 指定job的reducer和输出的类型<k4  v4>
        job.setReducerClass(SoGouQReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //4. 指定job的输入和输出路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        //5. 执行job
        job.waitForCompletion(true);
        System.out.println( "Hello World!" );
    }
}

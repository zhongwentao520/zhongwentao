package com.mypro05;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class App 
{
    public static void main( String[] args )throws Exception
    {
        Job job = Job.getInstance(new Configuration());
         job.setJarByClass(App.class);

        //2. 指定job的mapper和输出的类型<k2 v2>
        job.setMapperClass(SoldMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Sold.class);

        //3. 指定job的reducer和输出的类型<k4  v4>
        job.setReducerClass(SoldReduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        //4. 指定job的输入和输出路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        //5. 执行job
        job.waitForCompletion(true);

        System.out.println( "Hello World!" );
    }
}

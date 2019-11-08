package com.mypro04;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class App 
{
    public static void main( String[] args ) throws IOException, ClassNotFoundException, InterruptedException {
        //1. 创建一个job和任务入口(指定主类)
        Job job = Job.getInstance(new Configuration());
        job.setJarByClass(App.class);

        //2. 指定job的mapper和输出的类型<k2 v2>
        job.setMapperClass(SalaryTotalMapper.class);
        job.setMapOutputKeyClass(NullWritable.class);
        job.setMapOutputValueClass(Employee.class);

        //这里有变化：
        //指定任务的分区规则的类
        job.setPartitionerClass(SalaryTotalPartitioner.class);
        //指定建立几个分区
        job.setNumReduceTasks(3);

        //3. 指定job的reducer和输出的类型<k4  v4>
        job.setReducerClass(SalaryTotalReducer.class);
        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(Text.class);

        //4. 指定job的输入和输出路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        //5. 执行job
        job.waitForCompletion(true);

        System.out.println( "序列化 分区完毕！hello world!" );
    }
}

package com.mypro06;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SoGouQMapper extends Mapper<LongWritable, Text,Text, IntWritable> {
    @Override
    protected void map(LongWritable k1, Text v1, Context context) throws IOException, InterruptedException {
        //取出数据
        String data = v1.toString();
        String[] words = data.split("\t");

        //设置偏移量
        context.write(new Text(words[4]),new IntWritable(1));
    }
}

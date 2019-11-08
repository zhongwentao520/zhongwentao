package com.mypro05;


import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class SoldMapper extends Mapper<LongWritable, Text, Text, Sold> {
    @Override
    protected void map(LongWritable k1, Text v1, Context context) throws IOException, InterruptedException {
        String data = v1.toString();
        String[] words = data.split(",");

//        String t1 = StringUtils.substringAfter(data, ",");
//        String t2 = StringUtils.substringAfter(t1, "-");
        //取出时间偏移量
        String t2 = words[2];
        String[] words2 = t2.split("-");

        Sold sold = new Sold();
        sold.setTime(words[0]);
        sold.setQuantity_sold(Integer.parseInt(words[5]));
        sold.setAmount_sold(Float.valueOf(words[6]));
        context.write(new Text(words2[0]),sold);
    }
}

package com.mypro04;


import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SalaryTotalReducer extends Reducer<NullWritable,Employee,NullWritable,Text> {
    @Override
    protected void reduce(NullWritable k3, Iterable<Employee> v3, Context context) throws IOException, InterruptedException {
        String line=null;
        for (Employee v : v3) {
            line = v.toString();
            context.write(k3, new Text(line));
        }
    }
}

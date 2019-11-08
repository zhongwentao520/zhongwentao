package com.mypro04;


import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class SalaryTotalPartitioner extends Partitioner<NullWritable, Employee> {
    @Override
    public int getPartition(NullWritable k2, Employee v2, int i) {
        //如何分区: 每个部门放在一个分区
        if(v2.getSal() < 1500) {
            //放入1号分区中
            return 1%i;// 1%3=1
        }else if(v2.getSal() >=1500 && v2.getSal() < 3000){
            //放入2号分区中
            return 2%i;// 2%3=2
        }else {
            //放入3号分区中
            return 3 % i;// 3%3=0
        }
    }
}

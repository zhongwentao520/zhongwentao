package com.mypro06;

import java.io.IOException;
import java.util.Comparator;
import java.util.TreeMap;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SoGouQReducer extends Reducer<Text, IntWritable,Text, IntWritable> {

    private static final TreeMap<Integer,String> map = new TreeMap<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return -(o1.compareTo(o2));
        }
    });
    @Override
    protected void reduce(Text k3, Iterable<IntWritable> v3, Context context) throws IOException, InterruptedException {
        int total = 0;
        for(IntWritable v:v3){
            total += v.get();
        }
        String url = k3.toString();
        map.put(total,url);
        if(map.size()>10){
            map.remove(map.lastKey());
        }
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {

        for (Integer count :map.keySet()){
            context.write(new Text(map.get(count)),new IntWritable(count));
        }
        //这里将map.entrySet()转换成list
//        List<Map.Entry<String,Integer>> list = new LinkedList<Map.Entry<String, Integer>>(map.entrySet());
//
//        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
//            @Override
//            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
//                return (int)(o2.getValue() - o1.getValue());
//            }
//        });
//
//        for(int i = 0;i<3;i++){
//            context.write(new Text(list.get(i).getKey()),new IntWritable(list.get(i).getValue()));
//        }
    }
}

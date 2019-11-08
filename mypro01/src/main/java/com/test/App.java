package com.test;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )throws Exception
    {
        Configuration conf = new Configuration() ;
        //配置namenode地址
        URI uri = new URI("hdfs://192.168.30.130:8020");
        FileSystem fs = FileSystem.get(uri,conf,"hadoop");
        //指定用户名，获取Filesystem对象

//       Path dfs = new Path("/mydir/test2.txt") ;

        //创建文件
//       FSDataOutputStream os = fs.create(dfs,true);
//       os.writeBytes("hello,hdfs!");
//
//       os.close();
//       fs.close();

        //删除文件
//        fs.delete(dfs,true);



        //上传文件
//       Path dfs = new Path("/mydir/hdfs4.txt");
//       Path in = new Path("/home/hadoop/homework2/test4.txt");
//        try{
//            fs.copyFromLocalFile(in,dfs);
//        }catch (IOException e){
//            e.printStackTrace();
//        }



        //下载文件
//        Path dfs = new Path("/17034460223/test5.txt");
//        Path in = new Path("/home/hadoop/homework2/down.txt");
//        try{
//            fs.copyToLocalFile(false,dfs,in,true);
//            fs.close();
//        }catch(IOException e){
//            e.printStackTrace();
//        }

        //写入一个属性
//        Path dfs = new Path("/17034460223/test5.txt");
//        String user = "user.id";
//        String number = "17034460223";
//        byte[] value = number.getBytes();
//        fs.setXAttr(dfs,user,value);
//
//        byte[] read=fs.getXAttr(dfs,"user.id");
//        String refound = new String(read,"UTF-8");

        //读取文件
        Path dfs = new Path("/17034460223/test5.txt");
        try{

            FSDataInputStream in = fs.open(dfs);
            byte[] line =new byte[1024];
            in.read(line);
            //String lines = new String(line,"UTF-8");


            System.out.println(new String(line).toString());
        }catch(IOException e){
            e.printStackTrace();
        }






         //os.close();
        //D:\大数据\mypro01\target

    }
}

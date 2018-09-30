package com.demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

//一个遍历所有文件路径
//其他线程做操作
//并发队列ConcurrentLinkedQueue和阻塞队列LinkedBlockingQueue用法
//由于LinkedBlockingQueue实现是线程安全的，实现了先进先出等特性，
// 是作为生产者消费者的首选，LinkedBlockingQueue 可以指定容量
// ，也可以不指定，不指定的话，默认最大是Integer.MAX_VALUE，其中主要用到put和take方法，
// put方法在队列满的时候会阻塞直到有队列成员被消费，take方法在队列空的时候会阻塞，直到有队列成员被放进来。
public class Main {
    static LinkedBlockingQueue<String> pathQueue=new LinkedBlockingQueue<String>();
    public static void main(String args[])throws Exception{
       // Files.list(Paths.get(".")).forEach(System.out::println);
        //Files.copy(Paths.get(""))
       final String prefDir="C:\\Users\\dsys\\Desktop\\";
        //Files.createDirectory(Paths.get(prexfDir,"java8"));
       // Files.copy(Paths.get(prexfDir,"java8"),Paths.get(prexfDir,"java8bak"));
        //testPath();
       // putPath(prexfDir+"java8");
       // Files.createDirectory(Paths.get(prefDir,"test\\test\\test"),);
        Files.createFile(Paths.get(prefDir,"\\test\\test\\1.txt"));
        Files.createDirectories(Paths.get(prefDir,"test\\test\\test"));
        Files.createDirectories(Paths.get(prefDir,"test\\test\\test"));
        System.out.println(Paths.get("..\\..\\").toAbsolutePath());
        System.out.println(Paths.get("..\\..\\").normalize().toAbsolutePath());
        System.out.println(Paths.get("..\\..\\").toRealPath());
       // Files.walkFileTree(Paths.get(prefDir,"java8"));
    }
    public static void testPath() throws Exception{
        System.out.println(Paths.get(".").toString());
        System.out.println(Paths.get(".").getRoot());
        System.out.println(Paths.get(".").toAbsolutePath());
        System.out.println(Paths.get(".").toFile().getAbsolutePath());
        System.out.println(Paths.get("").toAbsolutePath());
    }
    public static void putPath(String startDir) throws Exception {
        Files.list(Paths.get(startDir)).forEach(x-> {
            if(x.toFile().isDirectory()){
                try {
                    putPath(x.toFile().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                System.out.println(x.getFileName());
//                try {
//                    pathQueue.put(x.toString());
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }

        });
    }
}

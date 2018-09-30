package com.demo;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 *  Pattern 一个Pattern是一个正则表达式经编译后的表现模式。
 *  Matcher 一个Matcher对象是一个状态机器，它依据Pattern对象做为匹配模式对字符串展开匹配检查。
 *  首先一个Pattern实例订制了一个所用语法与PERL的类似的正则表达式经编译后的模式，
 *  然后一个Matcher实例在这个给定的Pattern实例的模式控制下进行字符串的匹配工作。
 */
public class RegexUtils {

    static String findAll(String regex,String text){
        Pattern p= Pattern.compile(regex);
        System.out.println("regex:"+p.pattern());
        return "";
    }
    static boolean isAllMatcher(String regex,String text){
        Pattern p=Pattern.compile(regex);
        Matcher m=p.matcher(text);
        return m.matches();
       // return Pattern.matches(regex,text);
    }
    static String[] regexSplit(String regex,String text){
        Pattern p=Pattern.compile(regex);
        return p.split(text);
    }
    public static void main(String args[]) throws IOException {
//        BufferedWriter writer=Files.newBufferedWriter(Paths.get(".\\test.txt"));
//        writer.write("hello world");
//        writer.newLine();
//        writer.close();
        //Files.newBufferedWriter()
//        Pattern p=Pattern.compile("\\d+");
//        Matcher m=p.matcher("aa22bb23bb34bb55");
//        String test="aa123aa3433aa";
//        System.out.println(test.replace("a","b"));
//        System.out.println(test.replaceFirst("\\d+","num"));
//        System.out.println(test.replaceAll("\\d+","num"));
//        System.out.println(m.matches());//整体匹配
//        System.out.println(m.lookingAt());//匹配开始
//        //System.out.println(m.find());//任何地方匹配
//        while(m.find()){
//            System.out.println(m.start());
//            System.out.println(m.end());
//            System.out.println(m.group());
//        }


//        String sb=new String(Files.readAllBytes(Paths.get("F:\\workspace\\ideaproject\\dabao\\src\\main\\java\\com\\dsys\\main\\51Keystatistics.html")),"utf-8");
//        Matcher m=scriptP.matcher(sb);
//        while(m.find()){
//            int start=m.start();
//            int end=m.end();
//            String text=m.group();
//            String changeVerText=text.replaceFirst(replaceScriptRegex,".js?ver="+System.currentTimeMillis());
//            sb=sb.replace(text,changeVerText);
//        }
//        Pattern cssP=Pattern.compile(cssRegex);
//        Matcher m1=cssP.matcher(sb);
//        while (m.find()){

//            String text=m.group();
//            String changeVerText=text.replaceFirst(replaceCssRegex,".css?ver="+System.currentTimeMillis());
//            sb=sb.replace(text,changeVerText);
//        }
//        System.out.println(sb);
    }
}

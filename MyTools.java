package com.hzins.travel.tools;

import com.google.common.base.Preconditions;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 这是一个空的类，用于查找携保工具类的位置
 */
public class MyTools {
    public static void main(String[] args) throws Exception {
       /*String url = "http://www.baidu.com";
        if(url.startsWith("http:")){
            url = "https:"+url.substring(5);
        }
        System.out.println(url);*/

    /*    Boolean ss = null;
        System.out.println(!ss);*/

        analyzeLine("张飞\t男\t513023199205061254\t2017/12/20\t15112345678\n");

    }


    private static void analyzeLine(String line){
        //若存在换行符，取第一个换行符之前的内容
        int end = -1;
        if((end = line.indexOf("\n"))>=0){
            line = line.substring(0,end).trim();
        }
        //若单行长度大于500，就去500以前的数据
        if(line.length() > 500){
            line = line.substring(0,500);
        }
        //去除多余空格和tab符号
        List<String> wordList = new ArrayList();
        String[] arrs = line.split(" |\t");
        for(String arr : arrs){
            if(arr.trim().length()>0){
                wordList.add(arr.trim());
            }
        }
        //添加空格便于正则取值
        line = " "+StringUtils.join(wordList," ")+" ";

        /*出生日期提取1980-7-25, 1980/7/25, 1980年7月25,
        19800725,25/JUL/1980, 25-JUL-1980, 25/7/1980, 25/JUL/80,
        25-JUL-80, 25/7/80
        */
        String regex_birth = "([\\d]{4}[-/年][\\w]{1,3}[-/年][\\d]{1,2})|([\\d]{2}[-/][\\w]{1,3}[-/][\\d]{1,4})|( 19[\\d]{2}[01]{,2}[0123][\\d])";
        Pattern p = Pattern.compile(regex_birth);
        Matcher m = p.matcher(line);
        String birth = m.group(1);
        System.out.println(birth);

        //手机号码提取

        //性别提取

        //证件号码提取

        //姓名提取
        // TODO 为空


//
//        String reg" [\u4e00-\u9fa5a-zA-Z ]*? ";
//        //提取性别:
//        int gender_index = line.indexOf("男");
//        if(gender_index == -1 || (gender_index))
//        String regex_gender = "([\da-zA-Z ](男|女)[\da-zA-Z ])|(^(男|女)[\da-zA-Z ])|([\da-zA-Z ](男|女)$)";



        //优先
         //[\u4e00-\u9fa5]
        //1先提取手机号

        System.out.println();
    }


    public void checkHash(){
        Map<Integer,Integer> checkMap = new HashMap<>();
        int hashCode = 0;
        int value = 2043019;
        int count = 1;
        String hashStr = null;
        int hash = 0;
        for(int i = 0; i < 1000; i++ ){
            hashCode = String.valueOf(value++).hashCode();
            hashStr = String.valueOf(Math.abs(hashCode));
            hash = hashCode;

            if(hashStr.length()>8){
                hashCode = Integer.parseInt(hashStr.substring(hashStr.length()-8));
            }else if(hashStr.length()<8){
                hashCode = Integer.parseInt(hashStr)+10000000;
            }
            if(checkMap.containsKey(hashCode)){
                System.out.println((i+1)+"次后开始重复开始：原hashcode:"+hash+"  新hash:"+hashCode+"，原值："+value+"，冲突的值:"+checkMap.get(hashCode));
                if(count++ >= 3){
                    break;
                }
            }
            System.out.println(hashCode);
            if(i % 100000 == 0){
                System.out.println("map的长度:"+checkMap.size());
            }
            checkMap.put(hashCode,value);
        }
        System.out.println("执行完毕");
    }
    public void checkRandom(){
        Integer integer = null;
        Random random = new Random();
        List<Integer> arrlist = new ArrayList<>();
        int n = 1;
        for(int i = 0; i < 100000; i++){
            integer = random.nextInt(80000000);
            if(arrlist.contains(integer)){
                System.out.println((i+1)+"次后开始重复开始："+integer);
                if(n>=20){
                    break;
                }
                n++;
            }
            arrlist.add(integer);
        }
        System.out.println("执行结束");
    }

}
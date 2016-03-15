package common;

import java.io.BufferedReader;  
import java.io.InputStreamReader;  

public class test {  
       public static void main(String[] args){  
               try{  
                       System.out.println("start");  
                       //u+x 权限
                       Process pr = Runtime.getRuntime().exec(new String[]{"/bin/sh","-c", "/home/hadoop/git/smartmonkey/src/common/test.sh"});  
                       //Process pr = Runtime.getRuntime().exec(new String[]{"/usr/bin/python2.7","-c","/home/hadoop/git/smartmonkey/src/common/test.py"});  
                       BufferedReader in = new BufferedReader(new  
                               InputStreamReader(pr.getInputStream()));  
                       String line;  
                       while ((line = in.readLine()) != null) {  
                           System.out.println(line);  
                       }  
                       in.close();  
                       pr.waitFor();  
                       System.out.println("end");  
               } catch (Exception e){  
                           e.printStackTrace();  
                       }  
               }  
}
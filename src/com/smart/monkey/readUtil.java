package com.smart.monkey;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 读取文件工具类
 * 静态方法
 * String ReadFile(String Path)
 * 输入：Path 读入文件路径
 * 输出：读出文件内容String
 */
public class readUtil {
	public static String ReadFile(String Path){
		BufferedReader reader = null;
		String laststr = "";
		try{
			FileInputStream fileInputStream = new FileInputStream(Path);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			reader = new BufferedReader(inputStreamReader);
			String tempString = null;
			while((tempString = reader.readLine()) != null){
				laststr += tempString;
			}
			reader.close();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(reader != null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return laststr;
		}
}

package 网络信息安全;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class MD5算法使用 {

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		// TODO Auto-generated method stub
	    String str="";
		
		File file=new File("D:\\课业\\网络信息安全\\实验2-1（时间_2017-9-27 上午3-4节）\\test-1.txt");
		@SuppressWarnings("resource")
		BufferedReader reader=new BufferedReader(new FileReader(file));			String temp;
		while((temp=reader.readLine())!=null)
		{
			str+=temp;
		}
		
		System.out.println(EncodeerByMD5(str));

	}
	
	//计算信息摘要
	public static String EncodeerByMD5(String str) throws NoSuchAlgorithmException
	{
		MessageDigest md5=MessageDigest.getInstance("MD5");
		return new String(Base64.getEncoder().encodeToString(md5.digest((str.getBytes()))));
	}

}

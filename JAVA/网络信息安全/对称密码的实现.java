package 网络信息安全;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/*
 * 
 * 
 * 1、对称密码技术的实现
（1）熟悉相应的DES加密/解密函数的使用，在单机完成对特定文件（test-1.txt）的单重DES加密/解密；
（2）针对DES密钥特点（8字节，64比特位，其中真正起作用的为56比特位），设计一次一密的密钥产生机制（即每次会话所使用的密钥都是临时随机产生的）；
 */
public class 对称密码的实现 {
	
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String str="";
		
		File file=new File("D:\\课业\\网络信息安全\\实验2-1（时间_2017-9-27 上午3-4节）\\test-1.txt");
		@SuppressWarnings("resource")
		BufferedReader reader=new BufferedReader(new FileReader(file));
		String temp;
		while((temp=reader.readLine())!=null)
		{
			str+=temp;
		}
		
		//密码 长度要是8的倍数
		String password="95880280";
		byte[] result=encrypt(str.getBytes(),password);
		System.out.println("加密后："+new String(result));
		
		byte[] decryResult=decrypt(result,password);
		System.out.println("解密后:"+new String(decryResult));
		
	}
	
	
	public static byte[] encrypt(byte[] datasource,String password) {
		SecureRandom random=new SecureRandom();
		try {
			DESKeySpec desKey=new DESKeySpec(password.getBytes());
			//创建一个密匙工厂
			SecretKeyFactory keyFactory=SecretKeyFactory.getInstance("DES");
			SecretKey secureKey=keyFactory.generateSecret(desKey);
			
			//Cipher对象实际完成加密操作
			Cipher cipher=Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, secureKey,random);
			
			return cipher.doFinal(datasource);
			
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public static byte[] decrypt(byte[] src,String password)
	{
		//DES算法要求有一个可新人的随机数源
		SecureRandom random=new SecureRandom();
		
		try {
			DESKeySpec desKey =new DESKeySpec(password.getBytes());
			
			SecretKeyFactory keyFactory=SecretKeyFactory.getInstance("DES");
			
			SecretKey secureKey=keyFactory.generateSecret(desKey);
			
			Cipher cipher=Cipher.getInstance("DES");
			
			cipher.init(Cipher.DECRYPT_MODE, secureKey,random);
			
			return cipher.doFinal(src);
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

}

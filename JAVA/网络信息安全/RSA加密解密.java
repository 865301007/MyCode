package 网络信息安全;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSA加密解密 {

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
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
		
		KeyPair keyPair=genKeyPair(1024);
		
		//获取公私钥，并以base64格式打印出来
		PublicKey publicKey=keyPair.getPublic();
		System.out.println("公钥："+new String(Base64.getEncoder().encode(publicKey.getEncoded())));
		
	    PrivateKey privateKey=keyPair.getPrivate();
	    System.out.println("私钥："+new String(Base64.getEncoder().encode(privateKey.getEncoded())));
	    
	    //公钥加密
	    byte[] encryptedBytes=encrypt(str.getBytes(),publicKey);
	    System.out.println("公钥加密后:"+new String(encryptedBytes));
	    
	    //私钥解密
	    byte[] decryptedBytes=decrypt(encryptedBytes,privateKey);
	    System.out.println("私钥解密后:"+new String(decryptedBytes));
	    
	    encryptedBytes=encryptByPrivate(str.getBytes(),privateKey);
	    System.out.println("私钥加密后:"+new String(encryptedBytes));
	    
	    
	    decryptedBytes=decryptByPublic(encryptedBytes,publicKey);
	    System.out.println("公钥解密后:"+new String(decryptedBytes));
		
		

	}
	
	//生成密钥对
	public static KeyPair genKeyPair(int KeyLength) throws NoSuchAlgorithmException
	{
		KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(1024);
		return keyPairGenerator.generateKeyPair();
	}
	
	//公钥加密
	public static byte[] encrypt(byte[] content,PublicKey publicKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
	{
		Cipher cipher=Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return cipher.doFinal(content);
		
	}
	
	//私钥解密
	public static byte[] decrypt(byte[] content,PrivateKey privateKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
	{
		Cipher cipher=Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return cipher.doFinal(content);
	}
	
	//私钥加密
	public static byte[] encryptByPrivate(byte[] content,PrivateKey privateKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
	{
		Cipher cipher=Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		return cipher.doFinal(content);
		
	}
	
	//公钥解密
	public static byte[] decryptByPublic(byte[] content,PublicKey publicKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
	{
		Cipher cipher=Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		return cipher.doFinal(content);
	}
	
	

}

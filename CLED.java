package CLED;

/* Author Dalia Khader

AES16Encryption
===============

Assignment for Cloudflare

This is an AES Encryption Scheme for a 16byte sized cipher implemented in Java.

To Encrypt a string the following commandline should be used:

> java Encrypt(HelloHelloHello1;0123456789abcdef)

The plaintext is: HelloHelloHello1
The Key is: 0123456789abcdef

To Decrypt a ciphertext the following command line should be used:

> java Decrypt(-111 -24 -21 -77 -75 85 -49 -46 -110 -9 49 -18 0 -71 23 48;0123456789abcdef)

The ciphertext is: -111 -24 -21 -77 -75 85 -49 -46 -110 -9 49 -18 0 -71 23 48
The Key is: 0123456789abcdef
 * 
 * */

import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
 
import javax.crypto.Cipher;



public class CLED {


	static String IV = "PickABetterOne16";
	
public static void main(String [] args) {
	
	//Parse Command Line to it's values
	 String s = Concatenate(args,' ');
	 String tokens[] = Parse(s);
			
	try {		
		//Encrypt 16 Size Block
	if(tokens[0].equals("Encrypt")) { 
	String plaintext = tokens[1];
	String encryptionKey = tokens[2];
	byte[] cipher = encrypt(plaintext, encryptionKey);
	System.out.println("Ciphertext is: \n");
	for (int i=0; i<cipher.length; i++)
	System.out.print(new Integer(cipher[i])+" ");
	System.out.println("");
	}
	else if(tokens[0].equals("Decrypt")){
		//Decrypt 16 sized block
	byte[] cipher = StringToCipher(tokens);
	String decrypted = decrypt(cipher, tokens[tokens.length-1]);
	System.out.println("Plaintext is:\n" + decrypted);
	} else {
		System.out.println("Unknown Command");
	}} catch (Exception e) {
	e.printStackTrace();
	}
}

/*Function encrypt takes a plaintext of size 16 bytes and 
 * computes its ciphertext in AES block cipher*/
	public static byte[] encrypt(String plainText, String encryptionKey) throws Exception {
	Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
	SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
	cipher.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
	return cipher.doFinal(plainText.getBytes("UTF-8"));
	}

	/*Function decrypts a ciphertext of size 16 bytes and 
	 * computes its original plaintext in AES block cipher*/	
	
	public static String decrypt(byte[] cipherText, String encryptionKey) throws Exception{
	Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
	SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
	cipher.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
	return new String(cipher.doFinal(cipherText),"UTF-8");
	}
	
/* Concatenate, Parse and String to cipher 
 * used to convert and parse the strings to their original format*/	
	
static	String Concatenate(String []A, char a){
	    String result = A[0];
	   for(int i=1;i<A.length;i++)
	    {  
		   if(A[i]!=null){
	        result = result + a + A[i];
		   }
	    }  
	    return result;
	}

static String [] Parse(String s){
	String delims = "[ ( ; ) ]+"; 
	String[] tokens = s.split(delims);
    return tokens;
}

static byte [] StringToCipher(String s[]){
	

	byte [] a = new byte[s.length-2];
	for(int i=0;i<a.length;i++)
		{
		try{	
		a[i] = new Integer(s[i+1]).byteValue();
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
		}
	return a;
}
	
	
	}

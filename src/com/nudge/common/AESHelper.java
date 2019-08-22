package com.nudge.common;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;



//import android.util.Base64;

public class AESHelper {
	public static String CUSTOMER_TYPE = "customer";
	public static String DEALER_TYPE = "dealer";
	public static String SERVER_DB_TYPE = "server";
	public static String CUSTOMER_KEY = "c-F@hT+~noBt}@qk";
	public static String DEALER_KEY =   "<`5]5f4RbW4%~djq";
	public static String SERVER_DB_KEY ="&i|qRK*ztgK9uQ/o";

	private static final String ALGORITHM       = "AES";
	private static final String UNICODE_FORMAT  = "UTF8";

	/*public static String encrypt(String seed, String cleartext) throws Exception {
                byte[] rawKey = getRawKey(seed.getBytes());
                byte[] result = encrypt(rawKey, cleartext.getBytes());
                return toHex(result);
        }*/

	//private static String CUSTOMER_KEY = "c-F@hT+~noBt}@qk#6!R;/.fAMam>JVt";

	/*public static String encrypt(String type,String clearText) {
		byte[] encryptedText = null;
		try {


//			byte[] keyData = getRawKey(CUSTOMER_KEY.getBytes());//seed.getBytes();
			byte[] keyData = getRawKey(type);//seed.getBytes();
			SecretKey ks = new SecretKeySpec(keyData, "AES");
			Cipher c = Cipher.getInstance("AES");
			c.init(Cipher.ENCRYPT_MODE, ks);
			encryptedText = c.doFinal(clearText.getBytes("UTF-8"));
			//return new Base64().encodeToString(encryptedText);

			return Base64.encodeBytes(encryptedText);
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}*/

	/* public static String decrypt(String seed, String encrypted) throws Exception {
                byte[] rawKey = getRawKey(seed.getBytes());
                byte[] enc = toByte(encrypted);
                byte[] result = decrypt(rawKey, enc);
                return new String(result);
        }*/
	/*public static String decrypt (String type,String encryptedText) {
		byte[] clearText = null;
		try {
			//byte[] keyData = seed.getBytes();
			//byte[] keyData = getRawKey(CUSTOMER_KEY.getBytes());//seed.getBytes();
			byte[] keyData = getRawKey(type);//seed.getBytes();
			SecretKey ks = new SecretKeySpec(keyData, "AES");
			Cipher c = Cipher.getInstance("AES");
			c.init(Cipher.DECRYPT_MODE, ks);
//			clearText = c.doFinal(new Base64().decodeBase64(encryptedText));
			//clearText = c.doFinal(Base64.decode(encryptedText));
			clearText = c.doFinal(encryptedText.getBytes("UTF-8"));
			return new String(clearText, "UTF-8");
			//return new String(clearText, ch);
		} catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	} 

	private static byte[] getRawKey(String type) throws Exception {

		byte[] keyByte = null;
		try 
		{
			if(type.equalsIgnoreCase(CUSTOMER_TYPE))
			{
				keyByte = CUSTOMER_KEY.getBytes();
			}
			else if(type.equalsIgnoreCase(DEALER_TYPE))
			{
				keyByte = DEALER_KEY.getBytes();
			}

			else if(type.equalsIgnoreCase(SERVER_DB_TYPE))
			{
				keyByte = SERVER_DB_KEY.getBytes();
			}

		}
		catch (Exception e)
		{
			// TODO: handle exception
		}

		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		sr.setSeed(keyByte);
		kgen.init(128, sr); // 192 and 256 bits may not be available
		//kgen.init(256, sr); // 192 and 256 bits may not be available
		SecretKey skey = kgen.generateKey();
		byte[] raw = skey.getEncoded();
		return raw;
	}


	private static byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] encrypted = cipher.doFinal(clear);
		return encrypted;
	}

	private static byte[] decrypt(byte[] raw, byte[] encrypted) throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);
		byte[] decrypted = cipher.doFinal(encrypted);
		return decrypted;
	}

	public static String toHex(String txt) {
		return toHex(txt.getBytes());
	}
	public static String fromHex(String hex) {
		return new String(toByte(hex));
	}

	public static byte[] toByte(String hexString) {
		int len = hexString.length()/2;
		byte[] result = new byte[len];
		for (int i = 0; i < len; i++)
			result[i] = Integer.valueOf(hexString.substring(2*i, 2*i+2), 16).byteValue();
		return result;
	}

	public static String toHex(byte[] buf) {
		if (buf == null)
			return "";
		StringBuffer result = new StringBuffer(2*buf.length);
		for (int i = 0; i < buf.length; i++) {
			appendHex(result, buf[i]);
		}
		return result.toString();
	}
	private final static String HEX = "0123456789ABCDEF";
	private static void appendHex(StringBuffer sb, byte b) {
		sb.append(HEX.charAt((b>>4)&0x0f)).append(HEX.charAt(b&0x0f));
	}*/



	public static String encrypt(String valueToEnc,String enc_key) throws Exception {
		Key key = generateKey(enc_key);
		Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(Cipher.ENCRYPT_MODE, key); 
		
		byte[] encValue = c.doFinal(valueToEnc.getBytes());
		String encryptedValue = new BASE64Encoder().encode(encValue);
		return encryptedValue;
	}

	public static String decrypt(String encryptedValue,String enc_key) throws Exception {
		Key key = generateKey(enc_key);
		Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(Cipher.DECRYPT_MODE, key);
		byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedValue);
		byte[] decValue = c.doFinal(decordedValue);//////////LINE 50
		String decryptedValue = new String(decValue);
		return decryptedValue;
	}

	private static Key generateKey(String myEncryptionKey) throws Exception {
		byte[] keyAsBytes;
		keyAsBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
		Key key = new SecretKeySpec(keyAsBytes, ALGORITHM);
		return key;
	}
	
	
	public static String GetEncryptValue(String valueToEnc)
	{
		String encryptedValue="";
		try
		{
			String enc_key = SERVER_DB_KEY;
			Key key = generateKey(enc_key);
			Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.ENCRYPT_MODE, key); 
			
			byte[] encValue = c.doFinal(valueToEnc.getBytes());
			encryptedValue = new BASE64Encoder().encode(encValue);
			
		} catch (Exception e)
		{
			e.printStackTrace();
			return encryptedValue;
		}
		
		return encryptedValue;
	}
	
	public static String GetDecryptValue(String encryptedValue)
	{
		String decryptedValue="";
		try
		{
			String enc_key = SERVER_DB_KEY;
			Key key = generateKey(enc_key);
			Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.DECRYPT_MODE, key);
			byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedValue);
			byte[] decValue = c.doFinal(decordedValue);//////////LINE 50
			 decryptedValue = new String(decValue);
			
		} catch (Exception e)
		{
			e.printStackTrace();
			return decryptedValue;
		}
		
		return decryptedValue;
	}


}

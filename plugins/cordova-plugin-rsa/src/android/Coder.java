package com.deppon.nci.plugins.rsa;


import android.util.Base64;

import java.security.MessageDigest;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public abstract class Coder {
	public static final String KEY_SHA1 = "SHA1";
	public static final String KEY_MD5 = "MD5";
	public static final String KEY_SALT = "NCIstAJfAAeAzYCb6EPiRWRJ3HlOennsYVr";

	/**
	 * MAC算法可选以下多种算法
	 * 
	 * <pre>
	 *  
	 * HmacMD5  
	 * HmacSHA1  
	 * HmacSHA256  
	 * HmacSHA384  
	 * HmacSHA512 
	 * 签名：私钥负责签名，公钥负责验证
	 * 加密：公钥负责加密，私钥负责解密
	 * </pre>
	 */
	public static final String KEY_MAC = "HmacMD5";

	/**
	 * BASE64解密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptBASE64(String key) throws Exception {
		return Base64.decode(key,Base64.DEFAULT);
	}

	/**
	 * BASE64加密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptBASE64(byte[] key) throws Exception {
		return new String(Base64.decode(key,Base64.DEFAULT));
	}

	/**
	 * MD5加密
	 * 加盐
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptMD5WithSalt(byte[] data, String salt) throws Exception {

		if (salt == null || salt.equals("")) {
			MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
			md5.update(data);
			return md5.digest();
		}

		MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);

		md5.update(data);

		byte[] b = md5.digest();

		String pass = encryptBASE64(b);

		md5.update(decryptBASE64(pass+salt));

		return md5.digest();

	}

	/**
	 * SHA1加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptSHA1(byte[] data) throws Exception {

		MessageDigest sha = MessageDigest.getInstance(KEY_SHA1);
		sha.update(data);

		return sha.digest();

	}

	/**
	 * 初始化HMAC密钥
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String initMacKey() throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);

		SecretKey secretKey = keyGenerator.generateKey();
		return encryptBASE64(secretKey.getEncoded());
	}

	/**
	 * HMAC加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptHMAC(byte[] data, String key) throws Exception {

		SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);
		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
		mac.init(secretKey);

		return mac.doFinal(data);

	}
}
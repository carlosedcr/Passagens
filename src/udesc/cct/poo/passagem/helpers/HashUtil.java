package udesc.cct.poo.passagem.helpers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;

public class HashUtil {
	
	public static String stringParaMD5(String string) {
		String md5 = null;
		
		if (null == string) {
			return null;
		}
		
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
			digest.update(string.getBytes(), 0, string.length());
			//Converte para hexa
			md5 = new BigInteger(1, digest.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return md5;
		

	}

}

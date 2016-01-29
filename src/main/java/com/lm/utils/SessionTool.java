package com.lm.utils;

import java.net.InetAddress;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SessionTool {

	private static final char DICTIONARY[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
			'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	private String computerAddr;
	private int instanceIdx;
	private static Object syncObj = new Object();
	private static long counter = 0;

	public String generateSessionId() {

		String sessionKey = getSessionKey();

		byte abyte0[] = sessionKey.getBytes();

		String encryptionAlgorithm = "SHA";
		MessageDigest messageDigest;

		try {
			messageDigest = MessageDigest.getInstance(encryptionAlgorithm);
			abyte0 = messageDigest.digest(abyte0);
		} catch (NoSuchAlgorithmException e) {
			return "";
		}

		StringBuffer stringbuffer = new StringBuffer(40);

		for (int l = 0; l < abyte0.length; l++) {
			int k = abyte0[l] + 128;
			int i = k / DICTIONARY.length;
			int j = k % DICTIONARY.length;
			stringbuffer.append(DICTIONARY[i]);
			stringbuffer.append(DICTIONARY[j]);
		}

		return stringbuffer.toString();
	}

	/**
	 * 生成用于生成SessionId的key。
	 * 
	 * @return key
	 */
	private String getSessionKey() {
		char SEPARATOR = '#';
		StringBuffer buf = new StringBuffer();

		buf.append(getLocalHost()); // 机器地址

		buf.append(SEPARATOR);

		buf.append(String.valueOf(instanceIdx)); // 实例计数
		buf.append(SEPARATOR);

		long l;
		synchronized (syncObj) {
			l = ++counter;
		}

		buf.append(Long.toHexString(l)); // 计数器
		buf.append(SEPARATOR);

		buf.append(Long.toHexString(System.currentTimeMillis())); // 当前时间
		buf.append(SEPARATOR);

		return buf.toString();

	}

	/**
	 * 获得本地IP地址。
	 * 
	 * @return 本地IP地址
	 */
	private String getLocalHost() {
		if (computerAddr == null) {
			try {
				computerAddr = InetAddress.getLocalHost().getHostAddress();
			} catch (Exception _ex) {
				computerAddr = "UNKNOW_IP";
			}
		}
		return computerAddr;
	}

}

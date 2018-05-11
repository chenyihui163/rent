package com.rent.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 序列化工具SerializeUtils
 * @author chenyihui
   2018年3月25日
 */
public class SerializeUtils {
	/**
	 * 序列化对象
	* @Title: serialize   
	* @Description: TODO(这里用一句话描述这个方法的作用)   
	* @param @param obj
	* @param @return    设定文件   
	* @return byte[]    返回类型   
	* @throws
	 */
	public static byte[] serialize(Object obj) {
		byte[] bytes = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			;
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			bytes = baos.toByteArray();
			baos.close();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bytes;
	}

	/**
	 * 反序列化对象
	* @Title: deSerialize   
	* @Description: TODO(这里用一句话描述这个方法的作用)   
	* @param @param bytes
	* @param @return    设定文件   
	* @return Object    返回类型   
	* @throws
	 */
	public static Object deSerialize(byte[] bytes) {
		Object obj = null;
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			obj = ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
}
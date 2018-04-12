package com.cplatform.surf.coco.entity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import lombok.Data;

import com.cplatform.surf.coco.Contants;
import com.cplatform.surf.coco.util.ByteUtil;

/**
 * 交易对象 Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018年3月27日 下午4:56:17
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: wenjpa@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Data
public class Tran {

	/**
	 * 交易长度(交易字节数，包含自身)
	 */
	int length;

	/**
	 * 交易对应解析版本
	 */
	int version;

	/**
	 * 交易hash
	 */
	String hash;

	/**
	 * 输入数量(4位)
	 */
	int inputCount;

	/**
	 * 输出数量
	 */
	int outputCount;

	/**
	 * 公钥
	 */
	byte[] publicKey;

	/**
	 * 签名长度
	 */
	int signLength;

	/**
	 * 签名
	 */
	String sign;
	
	/**
	 * 签名内容通过公钥解密后的md5值
	 */
	String validHash;

	/**
	 * 时间戳
	 */
	int timestamp;
	
	
	public void setSign(String sign){
		this.signLength = sign.getBytes().length;
		this.sign = sign;
	}
	
	
	public int processLength(){
		this.length =  Contants.TRAN_HEAD_LENGTH +signLength;
		return this.length;
	}

	public byte[] toByte() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		try {
			bos.write(ByteUtil.int2bytes(length));

			bos.write(ByteUtil.int2bytes(version));
			bos.write(hash.getBytes());
			
			bos.write(ByteUtil.int2bytes(inputCount));
			bos.write(ByteUtil.int2bytes(outputCount));
			bos.write(publicKey);
			
			bos.write(ByteUtil.int2bytes(signLength));
			bos.write(sign.getBytes());
			bos.write(validHash.getBytes());
			bos.write(ByteUtil.int2bytes(timestamp));
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bos.toByteArray();
	}

}

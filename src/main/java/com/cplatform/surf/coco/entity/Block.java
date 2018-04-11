package com.cplatform.surf.fcoin.entity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import com.cplatform.surf.fcoin.Contants;
import com.cplatform.surf.fcoin.util.ByteUtil;

/**
 * 区块 Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018年3月27日 下午4:41:07
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: wenjpa@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Data
@EqualsAndHashCode
@ToString
public class Block {

	/**
	 * 魔数
	 */
	int magic = 0xC0FFC0C0;

	/**
	 * 区块对应的版本
	 */
	int version = 1;

	/**
	 * 高度
	 */
	int height;

	/**
	 * 前一个区块hash
	 */
	String preHash;
	
	/**
	 * 随机数，用来计算hash
	 */
	int nonce;

	/**
	 * 块长(包括自身版本信息)
	 */
	int length;

	/**
	 * 交易数
	 */
	int count;

	/**
	 * 交易内容hash(128)
	 */
	String transHash;

	/**
	 * 交易详情
	 */
	List<Tran> transList;
	
	
	public int processLength(){
		int tmp = Contants.BLOCK_HEAD_LENGTH;
		for (Tran tran : transList) {
			tmp += tran.getLength();
		}
		
		this.length = tmp;
		return length;
	}

	public byte[] toByte() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		ByteArrayOutputStream bosTmp = new ByteArrayOutputStream();
		try {

			if (length > Contants.BLOCK_HEAD_LENGTH) {
				// 除去交易的基础长度
				length = Contants.BLOCK_HEAD_LENGTH;
			}
			for (Tran tran : transList) {
				byte[] transByte = tran.toByte();
				length += transByte.length;
				bosTmp.write(tran.toByte());
			}

			bos.write(ByteUtil.int2bytes(magic));

			bos.write(ByteUtil.int2bytes(version));
			bos.write(ByteUtil.int2bytes(height));
			byte[] preHashByte = preHash.getBytes();
			bos.write(preHashByte);
			
			bos.write(ByteUtil.int2bytes(nonce));
			bos.write(ByteUtil.int2bytes(length));

			bos.write(ByteUtil.int2bytes(count));

			bos.write(transHash.getBytes());
			
			bos.write(bosTmp.toByteArray());
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bos.toByteArray();

	}

	void writeObject(ObjectOutputStream oos) throws IOException {
		oos.write(this.toByte());
	}

}

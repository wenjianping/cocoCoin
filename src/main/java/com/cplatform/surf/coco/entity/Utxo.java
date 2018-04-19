package com.cplatform.surf.coco.entity;

import lombok.Data;

/**
 * utxo对象
 */
@Data
public class Utxo {

	//utxo字符串
	String utxo;
	
	/**
	 * utxo对应的地址
	 */
	String address;
	
	/**
	 * utxo对应的数量
	 */
	int count;
}

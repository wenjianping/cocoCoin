package com.cplatform.surf.coco.entity;

import java.util.HashMap;
import java.util.Map;


/**
 * 可用的utxo对象，在构建交易、验证交易时可以快速使用utxo对象
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018年4月19日 下午5:53:41
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: wenjpa@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public class UtxoPool {

	Map<String,Utxo> utxoMap = new HashMap<>(); 
	
	private static UtxoPool utxoPool = new UtxoPool();
	
	static{
		init();
	}
	
	public static UtxoPool getInstance(){
		return utxoPool;
	}
	
	/**
	 * 初始化utxo对象池
	 */
	private static void init() {
	    // TODO Auto-generated method stub
	    
    }

	public Utxo getUtxo(String utxo){
		Utxo cacheUtxo = utxoMap.get(utxo);
		if(cacheUtxo != null){
			return cacheUtxo;
		}
		return null;
	}
	
	public void add(Utxo utxo){
		utxoMap.put(utxo.getUtxo(), utxo);
	}
	
	public void remove(String utxo){
		utxoMap.remove(utxo);
	}
	
}

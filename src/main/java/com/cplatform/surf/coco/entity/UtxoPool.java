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
	
	public Utxo getUtxo(String utxo){
		Utxo cacheUtxo = utxoMap.get(utxo);
		if(cacheUtxo != null){
			return cacheUtxo;
		}
		return null;
	}
	
}

package com.cplatform.surf.coco.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 链
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018年4月13日 下午3:06:36
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: wenjpa@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public class BlockChain {

	List<Block> blockList = new ArrayList<>();

	static BlockChain chain = new BlockChain();

	public static BlockChain getInstance() {
		return chain;
	}

	/**
	 * 查询指定高度的，主链上的块。
	 * 
	 * @param height
	 * @return
	 */
	public Block getMainBlock(int height) {
		return blockList.get(height);
	}
	
	/**
	 * 查询链的最大高度
	 * @return
	 */
	public int getHeight(){
		return blockList.size();
	}

	/**
	 * 查询同一高度的多个块
	 * 
	 * @param hegith
	 * @return
	 */
	public List<Block> getSubBlock(int hegith) {
		//todo 返回包含分叉的链条数据(特别最新区块的分叉)
		
		return null;
	}
}

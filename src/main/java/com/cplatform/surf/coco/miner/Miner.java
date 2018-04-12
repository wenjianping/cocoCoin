package com.cplatform.surf.coco.miner;

import com.cplatform.surf.coco.util.HashUtil;

/**
 * 挖矿
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018年4月10日 下午1:52:14
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: wenjpa@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public class Miner {

	public int mine() {
		String preHash = "1123131322111";
		int height = 3;
		int i = mine(preHash, height);

//		System.out.println("hash result:" + HashUtil.hash128((preHash + i).getBytes()) + ",prehash:" + preHash + ",difficulty:" + difficulty);
		return i;
	}

	/**
	 * 挖矿
	 * @param preHash
	 * @param height
	 * @return
	 */
	private int mine(String preHash, int height) {
		int i = 0;
		String diffPrefix = new Difficulty().getPrefix(height);
		System.out.println("zero prefix:" + diffPrefix);
		while (true) {
			String hash64 = HashUtil.hash64(preHash + i);
			System.out.println(i + "," + hash64);
			if (hash64.compareTo(diffPrefix) < 0) {
				return i;
			}
			i++;
		}
		
		
//		int i = 0;
//		String diffPrefix = StringUtils.rightPad("0", difficulty) + "1";
//		System.out.println("zero prefix:" + diffPrefix);
//		while (true) {
//			String hash64 = HashUtil.hash64(preHash + i);
//			System.out.println(i + "," + hash64);
//			if (hash64.compareTo(diffPrefix) <= 0) {
//				return i;
//			}
//			i++;
//		}
	}

	public static void main(String[] args) {
		System.out.println(new Miner().mine());
	}
}

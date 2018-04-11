package com.cplatform.surf.fcoin.valid;

import com.cplatform.surf.fcoin.entity.Block;
import com.cplatform.surf.fcoin.miner.Difficulty;
import com.cplatform.surf.fcoin.util.HashUtil;

/**
 * 对区块数据进行校验
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018年4月10日 下午2:45:05
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: wenjpa@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public class Validation {

	public boolean valid(Block block) {
		if (block == null) {
			return false;
		}

		if (block.getMagic() != 0xC0FFC0C0) {
			return false;
		}

		if (!validPow(block)) {
			return false;
		}

		if (!validTran(block)) {
			return false;
		}

		// todo other valid
		
		return true;

	}

	private boolean validTran(Block block) {
	    // TODO Auto-generated method stub
	    return false;
    }

	private boolean validPow(Block block) {
		
		//todo 需要验证preHash是否确实为前一区块的preHash
		String preHash = block.getPreHash();

		int nonce = block.getNonce();
		int height = block.getHeight();
		String hash = HashUtil.hash64(preHash + nonce);
		
		String diffPrefix = new Difficulty().getPrefix(height);
		if (hash.compareTo(diffPrefix) < 0) {
			return true;
		}
		return false;
	}
}

package com.cplatform.surf.coco.valid;

import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.cplatform.surf.coco.entity.Block;
import com.cplatform.surf.coco.entity.Tran;
import com.cplatform.surf.coco.miner.Difficulty;
import com.cplatform.surf.coco.util.BlockUtil;
import com.cplatform.surf.coco.util.HashUtil;
import com.cplatform.surf.coco.util.RSAUtils;

/**
 * 对区块数据进行校验 Title. <br>
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

		List<Tran> tranList = block.getTransList();

		// 至少需要有挖矿的交易
		if (CollectionUtils.isEmpty(tranList)) {
			return false;
		}

		for (Tran tran : tranList) {
			if (!validTran(tran)) {
				return false;
			}
		}
		return true;
	}

	private boolean validPow(Block block) {

		// todo 需要验证preHash是否确实为前一区块的preHash
		String preHash = block.getPreHash();

		Block preBlock = BlockUtil.getPreBlock(block);
		if (preBlock != null) {
			if (!preHash.equals(HashUtil.hash128(preBlock.toByte()))) {
				return false;
			}
		}

		int nonce = block.getNonce();
		int height = block.getHeight();
		String hash = HashUtil.hash64(preHash + nonce);

		String diffPrefix = new Difficulty().getPrefix(height);
		if (hash.compareTo(diffPrefix) < 0) {
			return true;
		}
		return false;
	}

	/**
	 * 验证单笔交易是否生效
	 * 
	 * @param tran
	 * @return
	 */
	public boolean validTran(Tran tran) {

		// a.先验证交易基础的交易头信息是否正常（跳过），
		// b.然后交易的内容(解密后)和md5是否匹配，
		// c.交易的hash是否和实际生成的hash一致
		// d.最后校验交易内容的输入是否OK：公钥生成的地址是否为utxo的输入地址，utxo是否有效

		byte[] publicKey = tran.getPublicKey();
		String sign = tran.getSign();
		String validHash = tran.getValidHash();
		String hash = tran.getHash();

		RSAPublicKey rsaPublicKey;
		try {
			
			//验证交易
			byte[] contentByte = tran.contentByte();			
			String tranValidHash = HashUtil.hash64(contentByte);
			if (!hash.equals(tranValidHash)) {
				return false;
			}
			
			
			//验证交易内容真实性
			rsaPublicKey = RSAUtils.getPublicKey(publicKey);
			String encrpt = RSAUtils.publicEncrypt(sign, rsaPublicKey);
			if (!validHash.equals(HashUtil.md5(encrpt))) {
				return false;
			}
			
			
			//验证交易数据
			
			
			
			
			

			return true;
		}
		catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}
}

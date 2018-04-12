package com.cplatform.surf.coco.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cplatform.surf.coco.Contants;
import com.cplatform.surf.coco.entity.Block;
import com.cplatform.surf.coco.entity.Tran;

public class BlockUtil {

	public static byte[] toByte(Block block) {

		return block.toByte();
	}

	public static Block formByte(byte[] bytes) {

	ByteArrayInputStream outStream = new ByteArrayInputStream(bytes);
		Block block = new Block();
		try {
			byte[] magicByte = new byte[4];
			outStream.read(magicByte);

			byte[] versionByte = new byte[4];
			outStream.read(versionByte);

			byte[] heightByte = new byte[4];
			outStream.read(heightByte);

			byte[] preHashByte = new byte[128];
			outStream.read(preHashByte);
			
			byte[] nonceByte = new byte[4];
			outStream.read(nonceByte);

			byte[] lengthByte = new byte[4];
			outStream.read(lengthByte);

			byte[] countByte = new byte[4];
			outStream.read(countByte);

			byte[] transHashByte = new byte[128];
			outStream.read(transHashByte);

			int magic = ByteUtil.bytes2int(magicByte);
			block.setMagic(magic);
			int version = ByteUtil.bytes2int(versionByte);
			block.setVersion(version);
			
			int height = ByteUtil.bytes2int(heightByte);			
			block.setHeight(height);
			
			String prehash = new String(preHashByte);
			block.setPreHash(prehash);
			int nonce = ByteUtil.bytes2int(nonceByte);
			block.setNonce(nonce);
			int length = ByteUtil.bytes2int(lengthByte);
			block.setLength(length);
			int count = ByteUtil.bytes2int(countByte);
			block.setCount(count);
			String transHash = new String(transHashByte);
			block.setTransHash(transHash);
			List<Tran> tranList = new ArrayList<>();
			block.setTransList(tranList);

			int offset = Contants.BLOCK_HEAD_LENGTH;

			for (int i = 0; i < count; i++) {
				byte[] tranLengthByte = new byte[4];
				outStream.read(tranLengthByte);
				
				
				int tranLength = ByteUtil.bytes2int(tranLengthByte);
				
				byte[] tranByte = Arrays.copyOfRange(bytes, offset, offset + tranLength);
				Tran tran = BlockUtil.fromByteToTran(tranByte);
				tranList.add(tran);
				offset += tranLength;
				
				outStream.skip(tranLength -4);
			}

		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return block;

	}

	public static byte[] toByte(Tran tran) {
		return tran.toByte();
	}

	public static Tran fromByteToTran(byte[] bytes) {
		ByteArrayInputStream outStream = new ByteArrayInputStream(bytes);

		Tran tran = new Tran();

		try {
			
			byte[] lengthByte = new byte[4];
			outStream.read(lengthByte);
			int length  = ByteUtil.bytes2int(lengthByte);
			tran.setLength(length);
						
			byte[] versionByte = new byte[4];
			outStream.read(versionByte);		
			int version = ByteUtil.bytes2int(versionByte);
			tran.setVersion(version);

			byte[] hashByte = new byte[64];
			outStream.read(hashByte);
			
			String hash = new String(hashByte);
			tran.setHash(hash);
			
			byte[] inputCountByte = new byte[4];
			outStream.read(inputCountByte);
			int inputCount = ByteUtil.bytes2int(inputCountByte);
			tran.setInputCount(inputCount);
			
			byte[] outputCountByte = new byte[4];
			outStream.read(outputCountByte);
			int outputCount = ByteUtil.bytes2int(outputCountByte);
			tran.setOutputCount(outputCount);
			
			byte[] publicKeyByte = new byte[162];
			outStream.read(publicKeyByte);
//			String publicKey = new String(publicKeyByte);
			tran.setPublicKey(publicKeyByte);
			
			byte[] signLengthByte = new byte[4];
			outStream.read(signLengthByte);
			int signLength = ByteUtil.bytes2int(signLengthByte);
			tran.setSignLength(signLength);
			
			byte[] signByte = new byte[signLength];
			outStream.read(signByte);
			String sign = new String(signByte);
			tran.setSign(sign);

			byte[] validHashByte = new byte[32];
			outStream.read(validHashByte);
			String validHash = new String(validHashByte);
			tran.setValidHash(validHash);
			
			byte[] timestampByte = new byte[4];
			outStream.read(timestampByte);
			int timestamp = ByteUtil.bytes2int(timestampByte);
			tran.setTimestamp(timestamp);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tran;
	}
}

package com.cplatform.surf.coco;

import java.util.ArrayList;
import java.util.List;

import com.cplatform.surf.coco.entity.Block;
import com.cplatform.surf.coco.entity.BlockChain;
import com.cplatform.surf.coco.entity.Tran;
import com.cplatform.surf.coco.miner.Miner;
import com.cplatform.surf.coco.util.BlockUtil;

public class Main {

	public static void main(String[] args) {
		Block b = new Block();

		b.setVersion(1);
		b.setHeight(0);
		b.setCount(1);
		b.setLength(200);
		b.setPreHash("00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
		b.setTransHash("00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
		List<Tran> transList = new ArrayList<>();
		Tran tran = new Tran();
		transList.add(tran);
		b.setTransList(transList);

//		tran.setHash("0000000000000000000000000000000000000000000000000000000000000000");
		tran.setInputCount(1);
		tran.setOutputCount(1);
		tran.setPublicKey("000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000".getBytes());
//		tran.setSignLength(3);
		tran.setSign("abcasdfaaaaaaaaaaaaaaaaaaaaaaa");
		tran.setValidHash("00000000000000000000000000000000");
		tran.setTimestamp(12345);
		tran.setVersion(1);
		tran.processLength();
		tran.proessHash();

		b.processLength();

		byte[] bytes = b.toByte();

		Block b2 = BlockUtil.formByte(bytes);
		System.out.println(b);
		System.out.println(b2);
		System.out.println(b.equals(b2));
		
		BlockChain blockChain = 	BlockChain.getInstance();
		
		Miner miner = new Miner();
		miner.mine();
		
	}
}

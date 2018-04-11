package com.cplatform.surf.fcoin.miner;


/**
 * 获得难度对应的前缀. 每个0 代表16个难度
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018年4月10日 下午2:00:27
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: wenjpa@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public class Difficulty {
	
	char[] append = {'f','e','d','c','b','a','9','8','7','6','5','4','3','2','1','0'};

	/**
	 * 难度前缀计算，计算原则初始为65(0000f)， 每16个块难度+1。 每增加一个0分为16层级,因此每256个区块增加一个0。前缀计算：{难度值}/{16} + (c[{难度值}%{16}] ->字母)
	 * @param height
	 * @return
	 */
	public String getPrefix(int height){
		
		int addDifficulty = height/16;
		
		//初始0的长度
		int defaultPrefixZero = 4;
		
		//根据难度
		int addZero = addDifficulty/16;
		
		int prefxiSub = addDifficulty%16;
		
		char[] c = new char[defaultPrefixZero + addZero + 1];
		
		
		//默认4个0前缀（难度64）
		for(int i=0;i<defaultPrefixZero;i++){
			c[i] = '0';
		}
		for(int i=0;i<addZero;i++){
			c[i+defaultPrefixZero] = '0';
		}
		
		c[defaultPrefixZero+addZero ] = append[prefxiSub];
		String prefix = new String(c);
		
		
		
		return prefix;
	}
	
	public static void main(String[] args) {
		Difficulty d= new Difficulty();
		
		
		
//	    System.out.println(d.getPrefix(1));
	    System.out.println(d.getPrefix(17));
	    
	    System.out.println(d.getPrefix(22));
	    System.out.println(d.getPrefix(88));
	    
	    System.out.println(d.getPrefix(258));
    }
}

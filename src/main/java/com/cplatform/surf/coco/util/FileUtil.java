package com.cplatform.surf.coco.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 保存数据入库
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2018年4月4日 下午3:15:24
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: wenjpa@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public class FileUtil {

	public static void write(String fileName, byte[] bytes) {
		write(fileName,bytes,false);
	}
	
	
	public static void write(String fileName, byte[] bytes,boolean append) {
		FileOutputStream f1 = null;
		BufferedOutputStream b1 = null;
		try {
			File f = new File(fileName);
			if (!f.exists()) {
				if (!f.getParentFile().exists()) {
					f.getParentFile().mkdirs();
				}
			}
			f1 = new FileOutputStream(fileName,append);

			b1 = new BufferedOutputStream(f1);
			b1.write(bytes);

		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if (b1 != null) {
				try {
	                b1.close();
                }
                catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
                }
			}

		}
	}
}

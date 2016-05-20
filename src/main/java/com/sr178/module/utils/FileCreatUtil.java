package com.sr178.module.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class FileCreatUtil {
	public static void outFile(String path, String name, String content) throws FileNotFoundException, IOException {
// 		String path2 = "D:";
// 		System.out.println(path.equals(path2));
		String sDirF = System.getProperty("file.separator");
		// 检测指定路径
		if (path.substring(path.length() - 1).equals(sDirF)) {
			path = path.substring(0, path.length() - 1);
		}

		File pathDir = null;
		File file = null;
		FileOutputStream fout = null;
			// 创建目录
			pathDir = new File(path + sDirF );
			if (!(pathDir.isDirectory())) {
				if (!(pathDir.mkdirs())) {
					System.out.println("制定输出目录错误"+pathDir);
				}
			}
			file = new File(path + sDirF +  name);
			System.out.println("开始创建文件："+file);
			if (file.exists()) {
				file.delete();
			}
			if (file.createNewFile()) {
				fout = new FileOutputStream(file);
				fout.write(content.getBytes("utf-8"));
				System.out.println("成功创建文件"+name);
			}
			if (pathDir != null) {
				pathDir = null;
			}
			if (file != null) {
				file = null;
			}
			if (fout != null) {
					fout.close();
			}

	}
	
	public static void appendFile(String fileName, String content) {
		
        FileWriter writer = null;  
        try {     
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件     
            writer = new FileWriter(fileName, true);     
            writer.write(content);       
        } catch (IOException e) {     
            e.printStackTrace();     
        } finally {     
            try {     
                if(writer != null){  
                    writer.close();     
                }  
            } catch (IOException e) {     
                e.printStackTrace();     
            }     
        }   
    }     
	
	public static File creatNewFile(String fileName){
		File file = new File(fileName);  
		if (file.exists()) {
			file.delete();
		}
        try {
			if(file.createNewFile()){  
			    System.out.println("Create file successed"+fileName);  
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}  
        return file;
	}
}

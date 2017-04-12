
package 爬虫;

import java.io.File;
import java.io.FileWriter;

public class WriteTxt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("C:/Users/liuxi/Desktop/input");
		if (file.isFile()) {
			System.out.println("文件存在");
			System.out.println(file.length());
		} else {
			System.out.println("文件不存在");
			try {
				file.mkdir();
				System.out.println("文件创建成功");
				System.out.println(file.length());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		

	}

}
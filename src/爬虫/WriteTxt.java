
package ����;

import java.io.File;
import java.io.FileWriter;

public class WriteTxt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("C:/Users/liuxi/Desktop/input");
		if (file.isFile()) {
			System.out.println("�ļ�����");
			System.out.println(file.length());
		} else {
			System.out.println("�ļ�������");
			try {
				file.mkdir();
				System.out.println("�ļ������ɹ�");
				System.out.println(file.length());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		

	}

}
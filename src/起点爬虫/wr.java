package 起点爬虫;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class wr {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String inputname = "1";
		String outputname = "2";
		File inputfile = new File("C:/Users/liuxi/Desktop/"+inputname+".txt");
		File outfile = new File("C:/Users/liuxi/Desktop/"+outputname+".txt");
		if (inputfile.isFile()) {
			System.out.println(inputname+"文件存在");
		} else {
			System.out.println(inputname+"文件不存在");
			try {
				inputfile.createNewFile();
				System.out.println(inputname+"文件创建成功");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		try {
			FileWriter fileWriter = new FileWriter(inputfile);
			FileReader fileReader = new FileReader(outfile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			int num = 1;
			while (bufferedReader.ready()) {
				
				String string = bufferedReader.readLine();
				System.out.println(string);
				fileWriter.write(string);
				fileWriter.write("\r\n");
				fileWriter.write("\r\n");
			}
			fileWriter.close();
			fileReader.close();
			System.out.println("asdasd"+(char)fileReader.read());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}

package 起点爬虫;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public String GetFromUrl(String url) {
		String result = "";
		// 定义一个缓冲字符输入流
		BufferedReader in = null;
		try {
			// 将string转成url对象
			URL realUrl = new URL(url);
			// 初始化一个链接到那个url的连接
			URLConnection connection = realUrl.openConnection();
			// 开始实际的连接
			connection.connect();
			// 初始化 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			// 用来临时存储抓取到的每一行的数据
			String line;
			while ((line = in.readLine()) != null) {
				// 遍历抓取到的每一行并将其存储到result里面
				result += line + "\n";
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	int num = 0;

	public void Get1(String result) {
		// 定义一个样式模板，此中使用正则表达式，括号中是要抓的内容
		// 相当于埋好了陷阱匹配的地方就会掉下去
		Pattern patternName = Pattern.compile("data-eid=\"qd_B58\" target=\"_blank\">(.+?)<");
		Pattern patternNameUrl = Pattern.compile("<td><a class=\"name\" href=\"(.+?)\" ");
		Pattern patternClassify1 = Pattern.compile("「</em>(.+?)<");
		Pattern patternClassify2 = Pattern.compile("data-eid=\"qd_B61\">(.+?)<");
		Pattern patternTotal = Pattern.compile("<span class=\"total\">(.+?)<");
		Pattern patternAuthor = Pattern.compile("target=\"_blank\" data-eid=\"qd_B59\">(.+?)<");
		Pattern patternUpdatTime = Pattern.compile("<td class=\"date\">(.+?)<");
		// 定义一个matcher用来做匹配
		Matcher matcherName = patternName.matcher(result);
		Matcher matcherNameUrl = patternNameUrl.matcher(result);
		Matcher matcherClassify1 = patternClassify1.matcher(result);
		Matcher matcherClassify2 = patternClassify2.matcher(result);
		Matcher matcherTotal = patternTotal.matcher(result);
		Matcher matcherAuthor = patternAuthor.matcher(result);
		Matcher matcherUpdatTime = patternUpdatTime.matcher(result);

		File file = new File("C:/Users/liuxi/Desktop/input.txt");
		if (file.isFile()) {
			System.out.println("文件存在");
			System.out.println(file.length());
		} else {
			System.out.println("文件不存在");
			try {
				file.createNewFile();
				System.out.println("文件创建成功");
				System.out.println(file.length());
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		// 如果找到了
		while (matcherName.find() & matcherNameUrl.find() & matcherClassify1.find() & matcherClassify2.find()
				& matcherTotal.find() & matcherAuthor.find() & matcherUpdatTime.find()) {
			// 打印出结果
			num++;
			System.out.print(num + " ");
			// System.out.print(matcherName.group(1) + " ");
			// System.out.print("http:" + matcherNameUrl.group(1) + " ");
			// System.out.print(matcherClassify1.group(1) + "/" +
			// matcherClassify2.group(1) + " ");
			// System.out.print(matcherTotal.group(1) + " ");
			// System.out.print(matcherAuthor.group(1) + " ");
			// System.out.println(matcherUpdatTime.group(1));
			try {
				FileWriter fileWriter = new FileWriter(file, true);
				fileWriter.write(num + " ");
				fileWriter.write(matcherName.group(1) + " ");
				fileWriter.write("http:" + matcherNameUrl.group(1) + " ");
				fileWriter.write(matcherClassify1.group(1) + "/" + matcherClassify2.group(1) + " ");
				fileWriter.write(matcherTotal.group(1) + " ");
				fileWriter.write(matcherAuthor.group(1) + " ");
				fileWriter.write(matcherUpdatTime.group(1));
				fileWriter.write("\r\n");
				fileWriter.close();
				System.out.println("写入成功");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		}
	}

	public static void main(String[] args) {
		// 访问链接并获取页面内容
		Main main = new Main();
		String result = " ";
		String result1 = null;
		int pagenum = 1;
		while (pagenum != 11285) {
			// while (pagenum<=10) {
			String url = "http://a.qidian.com/?size=-1&sign=-1&tag=-1&chanId=-1&subCateId=-1&orderId=&update=-1&page="
					+ pagenum + "&month=-1&style=2&action=-1&vip=-1";
			result = main.GetFromUrl(url);
			main.Get1(result);
			pagenum++;
			try {
				Thread.sleep(300);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (pagenum % 10 == 0) {
				try {
					Thread.sleep(2000);
					System.out.println("#################################################");
					System.out.println();
					System.out.println(pagenum);
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS:SSSS");// 设置日期格式
					System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
					System.out.println();
					System.out.println("#################################################");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		System.out.println("爬完！共" + pagenum + "页。");
	}
	// 562562
	// 42421
}

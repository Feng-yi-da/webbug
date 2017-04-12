package 创世爬虫;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static String GetFromUrl(String url) {
		String result = "";
		// 定义�?个缓冲字符输入流
		BufferedReader in = null;
		try {
			// 将string转成url对象
			URL realUrl = new URL(url);
			// 初始化一个链接到那个url的连�?
			URLConnection connection = realUrl.openConnection();
			// �?始实际的连接
			connection.connect();
			// 初始�? BufferedReader输入流来读取URL的响�?
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			// 用来临时存储抓取到的每一行的数据
			String line;
			while ((line = in.readLine()) != null) {
				// 遍历抓取到的每一行并将其存储到result里面
				result += line + "\n";
			}
		} catch (Exception e) {
			System.out.println("发�?�GET请求出现异常�?" + e);
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


	public static void Get1(String result) {
		// 定义�?个样式模板，此中使用正则表达式，括号中是要抓的内�?
		// 相当于埋好了陷阱匹配的地方就会掉下去
		Pattern patternNum = Pattern.compile("</td></tr><tr><td>(.+?)</td><td><a href=");

		Pattern patternClassifyUrl = Pattern.compile("</td><td><a href=\"(.+?)\" class=\"grey2\">");
		Pattern patternClassify = Pattern.compile("class=\"grey2\">\\[(.+?)\\]");

		Pattern patternNameUrl = Pattern.compile("</td><td align=\"left\"><a href=\"(.+?)\"");
		Pattern patternName = Pattern.compile("class=\"green\" target=\"_blank\">(.+?)</a><a href=\"");

		Pattern patternUpdateChaterUrl = Pattern.compile("</a><a href=\"(.*?)\"");
		Pattern patternUpdateChater = Pattern.compile("class=\"grey\" title=\"(.*?)\" target=\"_blank\">");

		Pattern patternTotal = Pattern.compile("</a></td><td>(.+?)</td><td><a rel=\"nofollow\"");

		Pattern patternAuthorUrl = Pattern.compile("</td><td><a rel=\"nofollow\" href=\"(.+?)\"");
		Pattern patternAuthor = Pattern.compile("class=\"grey3\" target=\"_blank\">(.+?)</a></td><td><span>");

		Pattern patternUpdatTime = Pattern.compile("</a></td><td><span>(.+?)</span>");
		

		Matcher matcherNum = patternNum.matcher(result);

		Matcher matcherClassifyUrl = patternClassifyUrl.matcher(result);
		Matcher matcherClassify = patternClassify.matcher(result);

		Matcher matcherNameUrl = patternNameUrl.matcher(result);
		Matcher matcherName = patternName.matcher(result);

		Matcher matcherUpdateChaterUrl = patternUpdateChaterUrl.matcher(result);
		Matcher matcherUpdateChater = patternUpdateChater.matcher(result);

		Matcher matcherTotal = patternTotal.matcher(result);

		Matcher matcherAuthorUrl = patternAuthorUrl.matcher(result);
		Matcher matcherAuthor = patternAuthor.matcher(result);

		Matcher matcherUpdatTime = patternUpdatTime.matcher(result);

		int num = 1;
		String inputname = "input";
		File inputfile = new File("C:/Users/liuxi/Desktop/" + inputname + ".txt");
		if (inputfile.isFile()) {
			System.out.println(inputname + "文件存在");
		} else {
			System.out.println(inputname + "文件不存�?");
			try {
				inputfile.createNewFile();
				System.out.println(inputname + "文件创建成功");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		

		while (matcherNum.find() & matcherClassifyUrl.find() & matcherClassify.find() & matcherNameUrl.find()
				& matcherName.find() & matcherUpdateChaterUrl.find() & matcherUpdateChater.find() & matcherTotal.find()
				& matcherAuthorUrl.find() & matcherAuthor.find() & matcherUpdatTime.find()) {

			String reslut1 = Get2(GetFromUrl(matcherNameUrl.group(1)));
			try {
				FileWriter fileWriter = new FileWriter(inputfile, true);
				
				fileWriter.write(matcherNum.group(1) + " ");

				fileWriter.write(matcherClassifyUrl.group(1) + " ");
				fileWriter.write(matcherClassify.group(1) + " ");

				fileWriter.write(matcherNameUrl.group(1) + " ");
				fileWriter.write(matcherName.group(1) + " ");

				fileWriter.write(matcherUpdateChaterUrl.group(1) + " ");
				fileWriter.write(matcherUpdateChater.group(1).replace(" ", "") + " ");

				fileWriter.write(matcherTotal.group(1) + " ");

				fileWriter.write(matcherAuthorUrl.group(1) + " ");
				fileWriter.write(matcherAuthor.group(1) + " ");

				fileWriter.write(matcherUpdatTime.group(1)+" ");
				
				fileWriter.write(reslut1);

				fileWriter.write("\r\n");
				fileWriter.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
				
			System.out.println("num"+num);
			num++;
			 if (num == 4) {
			 System.out.println("#######################3");
//			 break;
			 }
		}
	}

	public static String Get2(String result) {
		String result1 = "";
		Pattern pattern = Pattern.compile("</td></tr><tr><td>总点击：(.+?)</td><td>总人气：(.+?)</td><td>总推荐：(.+?)"
				+ "</td></tr><tr><td>月点击：(.+?)</td><td>月人气：(.+?)</td><td>月推荐：(.+?)"
				+ "</td></tr><tr><td>周点击：(.+?)</td><td>周人气：(.+?)</td><td>周推荐：(.+?)</td></tr><tr><td>.+?"
				+ "连载状�?�： <span class=\"red2\">(.+?)</span></td></tr></table>");

		Pattern patternIntroduce = Pattern.compile("<p>(.+?)</p></div><div class=\"tags\">");

		Matcher matcher = pattern.matcher(result);

		Matcher matcherIntroduce = patternIntroduce.matcher(result);
		while (matcher.find() & matcherIntroduce.find()) {
			 System.out.print(matcher.group(1) + " ");
			 System.out.print(matcher.group(2) + " ");
			 System.out.print(matcher.group(3) + " ");
			 System.out.print(matcher.group(4) + " ");
			 System.out.print(matcher.group(5) + " ");
			 System.out.print(matcher.group(6) + " ");
			 System.out.print(matcher.group(7) + " ");
			 System.out.print(matcher.group(8) + " ");
			 System.out.print(matcher.group(9) + " ");
			 System.out.print(matcher.group(10) + " ");
			 System.out.println(matcherIntroduce.group(1).replaceAll("</p><p>",
			 ""));
			for (int i = 1; i <= 10; i++) {
				result1 += matcher.group(i) + " ";
			}
			result1 += matcherIntroduce.group(1).replaceAll("</p><p>", "");
			System.out.println(result1);
		}

		return result1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int pagenum = 1301;
		while (pagenum != 1302) {
			String url = "http://chuangshi.qq.com/bk/so1/p/" + pagenum + ".html";
			pagenum++;
			Get1(GetFromUrl(url));
			System.out.println(pagenum+" 页");
			if (pagenum == 1302 ) {
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (pagenum % 10 == 0) {
				try {
					Thread.sleep(5000);
					System.out.println("#################################################");
					System.out.println();
					System.out.println(pagenum);
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS:SSSS");// 设置日期格式
					System.out.println(df.format(new Date()));// new
																// Date()为获取当前系统时�?
					System.out.println();
					System.out.println("#################################################");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			
		}
		System.out.println("爬完！共" + pagenum + "页");
	}
}
//
//try {
//	FileWriter fileWriter = new FileWriter(inputfile, true);
//
//	fileWriter.write(
//			"序号 分类网址 分类 小说网址 小说�?  更新网址  更新章节 总字�? 作�?�网�? 作�?? 更新时间 总点�? 总人�? 总推�? 月点�? 月人�? 月推�? 周点�? 周人�? 周推�? 连载状�?? 概要");
//	fileWriter.write("\r\n");
//	fileWriter.write("\r\n");
//	fileWriter.close();
//} catch (Exception e) {
//	// TODO: handle exception
//	e.printStackTrace();
//}

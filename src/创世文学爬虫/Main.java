package 创世文学爬虫;

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
		BufferedReader in = null;
		try {
			URL realUrl = new URL(url);
			URLConnection connection = realUrl.openConnection();
			connection.connect();
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line + "\n";
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
			result=GetFromUrl(url);
		} finally {
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

		Pattern pattern = Pattern.compile("</td></tr><tr><td>(.+?)</td><td><a href=.+?class=\"grey2\">\\[" // 排名
				+ "(.+?)\\]</a></td><td align=\"left\"><a href=\""// 分类
				+ "(.+?)\"\n.+?class=\"green\" target=\"_blank\">"// 小说链接
				+ "(.+?)</a><a href=\""// 小说名
				+ "(.*?)\"\n.+?class=\"grey\" title=\".+?target=\"_blank\">"// 最新章节链接
				+ "(.*?)</a>"// 最新章节名
				+ "(.*?)</td><td>"// VIP
				+ "(.+?)</td><td><a rel=\"nofollow\" href=\""// 字数
				+ "(.+?)\" class=\"grey3\" target=\"_blank\">"// 作者链接
				+ "(.+?)</a></td><td><span>"// 作者名
				+ "(.+?)</span>");// 最新更新时间
		Matcher matcher = pattern.matcher(result);

		int num = 1;
		String inputname = "input";
		File inputfile = new File("C:/Users/liuxi/Desktop/" + inputname + ".txt");
		if (inputfile.isFile()) {
			System.out.println(inputname + "文件存在");
		} else {
			System.out.println(inputname + "文件不存在");
			try {
				inputfile.createNewFile();
				FileWriter fileWriter = new FileWriter(inputfile, true);
				fileWriter.write("序号\\分类\\小说网址\\小说名\\更新网址\\更新章节\\vip\\总字数\\作者网址\\作者\\更新时间" + "\\总点击数\\总人气\\总推荐"
						+ "\\月点击\\月人气\\月推荐" + "\\周点击\\周人气\\周推荐" + "\\连载状态\\概要");
				fileWriter.write("\r\n");
				fileWriter.close();
				System.out.println(inputname + "文件创建成功");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		while (matcher.find()) {
			// System.out.println(matcher.group(3));

			if (true) {
				String reslut1 = "";
				reslut1 = Get2(GetFromUrl(matcher.group(3)));
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					FileWriter fileWriter = new FileWriter(inputfile, true);

					for (int i = 1; i <= matcher.groupCount(); i++) {
						if (i == 7) {
							if (matcher.group(i).equals("")) {
								fileWriter.write(matcher.group(i) + "\\");
							} else {
								fileWriter.write("VIP" + "\\");
							}
						} else {
							fileWriter.write(matcher.group(i) + "\\");
						}
					}
					System.out.println(matcher.group(1) + " 写入成功 1！");
					fileWriter.write(reslut1);
					// System.out.println(reslut1 + " 写入成功 2！");
					fileWriter.write("\r\n");
					fileWriter.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				// for (int i = 1; i <= 10; i++) {
				// System.out.print(matcher.group(i)+" ");
				// }
				// System.out.println();
				// System.out.println("num" + num);
				// if (num == 1) {
				// System.out.println("#######################3");
				// break;
				// }
			}
			if (num % 34 ==0) {
				try {
					Thread.sleep((long) (Math.random()*1000));
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			num++;

		}
	}

	public static String Get2(String result) {
		String result1 = "";
		Pattern pattern = Pattern.compile("</td></tr><tr><td>总点击：(.+?)</td><td>总人气：(.+?)</td><td>总推荐：(.+?)"
				+ "</td></tr><tr><td>月点击：(.+?)</td><td>月人气：(.+?)</td><td>月推荐：(.+?)"
				+ "</td></tr><tr><td>周点击：(.+?)</td><td>周人气：(.+?)</td><td>周推荐：(.+?)</td></tr><tr><td>.+?"
				+ "连载状态： <span class=\"red2\">(.+?)</span></td></tr></table>");

		Pattern patternIntroduce = Pattern.compile("<p>(.+?)</div><div class=\"tags\">");

		Matcher matcher = pattern.matcher(result);

		Matcher matcherIntroduce = patternIntroduce.matcher(result);
		// System.out.println(result);
		// System.out.println(matcher.find() +""+ matcherIntroduce.find());
		while (matcher.find() & matcherIntroduce.find()) {
			// System.out.print(matcher.group(1) + " ");
			// System.out.print(matcher.group(2) + " ");
			// System.out.print(matcher.group(3) + " ");
			// System.out.print(matcher.group(4) + " ");
			// System.out.print(matcher.group(5) + " ");
			// System.out.print(matcher.group(6) + " ");
			// System.out.print(matcher.group(7) + " ");
			// System.out.print(matcher.group(8) + " ");
			// System.out.print(matcher.group(9) + " ");
			// System.out.print(matcher.group(10) + " ");
			// System.out.println(matcherIntroduce.group(1).replaceAll("</p><p>",
			// ""));
			for (int i = 1; i <= 10; i++) {
				result1 += matcher.group(i) + "\\";
			}
			result1 += matcherIntroduce.group(1).replaceAll("</p>", "").replaceAll("<p>", "").replaceAll("</a>", "")
					.replaceAll("&amp;amp;gt;", "");
			// System.out.println(result1);
		}
		// System.out.println(result1);
		// try {
		// Thread.sleep(300);
		// } catch (InterruptedException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		return result1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int pagenum = 1168;
		while (pagenum != 1302) {
			String url = "http://chuangshi.qq.com/bk/so1/p/" + pagenum + ".html";
			Get1(GetFromUrl(url));
			System.out.println(pagenum + " 页写入成功");
			if (pagenum == 2) {
				// break;
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
					System.out.println(df.format(new Date()));
					System.out.println();
					System.out.println("#################################################");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			pagenum++;
		}
		System.out.println("爬完！共" + (pagenum) + "页");
	}
}

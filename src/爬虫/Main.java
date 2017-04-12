
package 爬虫;

import java.awt.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	static String SendGet(String url) {
		// 定义一个字符串用来存储网页内容
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
		System.out.println(result);

		return result;
	}

	public static String Get2(String result) {
		String result1 = "";

		Pattern pattern = Pattern
				.compile("</td></tr><tr><td>总点击：(.+?)</td><td>总人气：(.+?)</td><td>总推荐：(.+?)"
						+ "</td></tr><tr><td>月点击：(.+?)</td><td>月人气：(.+?)</td><td>月推荐：(.+?)"
						+ "</td></tr><tr><td>周点击：(.+?)</td><td>周人气：(.+?)</td><td>周推荐：(.+?)</td></tr><tr><td>.+?"
						+ "连载状态： <span class=\"red2\">(.+?)</span></td></tr></table>");

		Pattern patternIntroduce = Pattern.compile("<p>(.+?)</p></div><div class=\"tags\">");

		Matcher matcher = pattern.matcher(result);

		Matcher matcherIntroduce = patternIntroduce.matcher(result);
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
			// System.out.println(matcherIntroduce.group(1).replaceAll("</p><p>",
			// ""));
		}
		return result1;

	}

	static String RegexString(String targetStr, String patternStr) {
		// 定义一个样式模板，此中使用正则表达式，括号中是要抓的内容
		// 相当于埋好了陷阱匹配的地方就会掉下去
		Pattern pattern = Pattern.compile(patternStr);
		// 定义一个matcher用来做匹配
		Matcher matcher = pattern.matcher(targetStr);
		// 如果找到了
		while (matcher.find()) {
			// 打印出结果
			System.out.println(matcher.group(1));
		}
		return "final";
	}

	public static void main(String[] args) {
		// 定义即将访问的链接
		String url = "http://chuangshi.qq.com/bk/xh/349652.html";
		// 访问链接并获取页面内容
		String result = SendGet(url);
		// System.out.println(result);
		// 使用正则匹配图片的src内容
		// String imgSrc = RegexString(result, "data-eid=\"qd_B58\"
		// target=\"_blank\">(.+?)<");
		// String string = RegexString(result, "data-eid=\"qd_B61\">(.+?)<");

	}
}
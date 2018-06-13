package edu.xmu.rebot.IKAnalyzer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

public class ChineseParticiple {

	public static String IKAnalysis(String input) {
		//System.out.println("输入：" + input);
		if (input.getBytes().length == input.length()) {
			// 如果不包含中文，就直接返回。
			return input;
		} else {
			// 由于IK分词器，不支持特殊字符，所以将 * 改为中文字符“艾丽丝星号”,将 _ 改为中文字符串“艾丽丝下划线”，
			// 在粉刺过程中个在扩展字典中加入两个词语“艾丽丝星号”和“艾丽丝下划线”；
			// 中文分词以后再将“艾丽丝星号”和“艾丽丝下划线”在替换为对应的"*"和"_"
			input = input.replaceAll("\\*", "艾丽丝星号").replaceAll("_", "艾丽丝下划线");
		}

		StringBuffer sbuffer = new StringBuffer();
		try {
			byte[] bt = input.getBytes();
			InputStream inputpStream = new ByteArrayInputStream(bt);
			Reader read = new InputStreamReader(inputpStream);
			// 设置为智能分词，true开启只能分词模式，如果不设置默认为false，也就是细粒度分割
			IKSegmenter iks = new IKSegmenter(read, true);
			Lexeme t;
			while ((t = iks.next()) != null) {
				// 在每个分词元之后添加空格
				sbuffer.append(t.getLexemeText() + " ");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		//System.out.println("分词器：" + sbuffer.toString());
		return sbuffer.toString().replaceAll("艾丽丝 星号", "*").replaceAll("艾丽丝 下划线", "_").trim();
	}

	public static void main(String[] args) { // TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("待分词语句:");
			String input = scanner.nextLine();
			if (input.toLowerCase().equals("bye")) {
				break;
			}
			System.out.println("分词的结果：" + IKAnalysis(input).replaceAll(" ", " /"));
		}
		scanner.close();
	}

}

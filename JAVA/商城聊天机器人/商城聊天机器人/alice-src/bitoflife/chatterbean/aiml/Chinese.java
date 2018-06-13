package bitoflife.chatterbean.aiml;

import org.xml.sax.Attributes;

import bitoflife.chatterbean.Match;

public class Chinese extends TemplateElement {
	public Chinese() {
	}

	public Chinese(Attributes attributes) {
	}

	public int hashCode() {
		return 131072;
	}

	public String process(Match match) {
		return "我在测试自定义标签，哈哈。";
	}

}

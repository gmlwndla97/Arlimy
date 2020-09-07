package teampl;

import java.io.IOException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class MyTest {
	String str = "";

	public String News(int want) throws Exception {
		String rankingURL1 = "http://news.naver.com/main/ranking/popularDay.nhn?rankingType=popular_day&sectionId=000&date=20171208";
		Document rankingdoc1 = Jsoup.connect(rankingURL1).get();
		if (want == 0) { // ����1��
			Elements links = rankingdoc1.select("li.num1 a");
			String Href = links.get(0).attr("abs:href");
			this.str += "< ���̹� ��ġ ��� >\n";
			init(Href);
		}

		String rankingURL = "http://news.naver.com/main/ranking/popularDay.nhn?mid=etc&sid1=111";
		Document rankingdoc = Jsoup.connect(rankingURL).get();
		if (want == 1) { // ��ġ
			Elements links = rankingdoc.select("li.num1 a");
			String Href = links.get(0).attr("abs:href");
			this.str += "< ���̹� ��ġ ��� >\n";
			init(Href);
		} else if (want == 2) { // ����
			Elements links = rankingdoc.select("li.num1 a");
			String Href = links.get(2).attr("abs:href");
			this.str += "< ���̹� ���� ��� >\n";
			init(Href);
		} else if (want == 3) { // ��ȸ
			Elements links = rankingdoc.select("li.num1 a");
			String Href = links.get(4).attr("abs:href");
			this.str += "< ���̹� ��ȸ ��� >\n";
			init(Href);
		} else if (want == 4) { // ��Ȱ��ȭ
			Elements links = rankingdoc.select("li.num1 a");
			String Href = links.get(6).attr("abs:href");
			this.str += "< ���̹� ��Ȱ/��ȭ ��� >\n";
			init(Href);
		} else if (want == 5) { // ����
			Elements links = rankingdoc.select("li.num1 a");
			String Href = links.get(8).attr("abs:href");
			this.str += "< ���̹� ���� ��� >\n";
			init(Href);
		}
		System.out.println();
		return this.str;
	}

	public void init(String Href) throws IOException {
		Document doc = Jsoup.connect(Href).get(); // ���ڷ� ��ƿ� href(�����۸�ũ)�� �ҽ��ڵ� ���θ� doc�� �����Ѵ�.
		Elements Title = doc.select("title"); // ������ �ҽ��ڵ� �߿� title�� �ش��ϴ� �κи� ����
		Elements Contents = doc.select("div#articleBodyContents"); // ������ �ҽ��ڵ� �߿� ���̵� articleBodyContents�� div�±׸� ����
		this.str = this.str + Title.text() + "\n" + Contents.text() + "\n\n"; // title �κп��� �ؽ�Ʈ�� ����. ����
	}

	public String toString() {
		return str;
	}
}
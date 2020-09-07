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
		if (want == 0) { // 종합1위
			Elements links = rankingdoc1.select("li.num1 a");
			String Href = links.get(0).attr("abs:href");
			this.str += "< 네이버 정치 기사 >\n";
			init(Href);
		}

		String rankingURL = "http://news.naver.com/main/ranking/popularDay.nhn?mid=etc&sid1=111";
		Document rankingdoc = Jsoup.connect(rankingURL).get();
		if (want == 1) { // 정치
			Elements links = rankingdoc.select("li.num1 a");
			String Href = links.get(0).attr("abs:href");
			this.str += "< 네이버 정치 기사 >\n";
			init(Href);
		} else if (want == 2) { // 경제
			Elements links = rankingdoc.select("li.num1 a");
			String Href = links.get(2).attr("abs:href");
			this.str += "< 네이버 경제 기사 >\n";
			init(Href);
		} else if (want == 3) { // 사회
			Elements links = rankingdoc.select("li.num1 a");
			String Href = links.get(4).attr("abs:href");
			this.str += "< 네이버 사회 기사 >\n";
			init(Href);
		} else if (want == 4) { // 생활문화
			Elements links = rankingdoc.select("li.num1 a");
			String Href = links.get(6).attr("abs:href");
			this.str += "< 네이버 생활/문화 기사 >\n";
			init(Href);
		} else if (want == 5) { // 세계
			Elements links = rankingdoc.select("li.num1 a");
			String Href = links.get(8).attr("abs:href");
			this.str += "< 네이버 세계 기사 >\n";
			init(Href);
		}
		System.out.println();
		return this.str;
	}

	public void init(String Href) throws IOException {
		Document doc = Jsoup.connect(Href).get(); // 인자로 담아온 href(하이퍼링크)의 소스코드 전부를 doc에 저장한다.
		Elements Title = doc.select("title"); // 저장한 소스코드 중에 title에 해당하는 부분만 저장
		Elements Contents = doc.select("div#articleBodyContents"); // 저장한 소스코드 중에 아이디가 articleBodyContents인 div태그만 저장
		this.str = this.str + Title.text() + "\n" + Contents.text() + "\n\n"; // title 부분에서 텍스트만 저장. 제목
	}

	public String toString() {
		return str;
	}
}
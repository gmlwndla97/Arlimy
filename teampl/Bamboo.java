package teampl;

import java.io.IOException;
import java.util.Scanner;
import java.util.TimerTask;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

//매일 11시 59분에 예약하여 발송하기 

public class Bamboo {
	String str = "";

	public String Bam(int choice) {

		switch (choice) {
		case 1:
			mail(1, "서울대학교 대나무숲", "https://www.facebook.com/pg/SNUBamboo/posts/?ref=page_internal");
			break;
		case 2:
			mail(2, "고려대학교 대나무숲", "https://www.facebook.com/pg/koreabamboo/posts/?ref=page_internal");
			break;
		case 3:
			mail(3, "연세대학교 대나무숲", "https://www.facebook.com/pg/yonseibamboo/posts/?ref=page_internal");
			break;
		}
		return this.str;

	}

	private void mail(int choice, String univ, String univUrl) { // 대숲 긁어서 메일 보내기
		try {
			Document url = Jsoup.connect(univUrl).get();
			Connection.Response response = (Response) Jsoup.connect(univUrl).method(Connection.Method.GET).execute();
			Document document = response.parse();
			String text = document.text();

			String[] array;
			String story;
			array = text.split(univ);
			story = dumpArray(choice, array);

			this.str += "< " + univ + " >" + "\n" + story + "\n\n";

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String dumpArray(int choice, String[] array) { // "더보기" 없애서 출력
		String res = " ";
		if (choice == 2) { // 고대숲은 [0]~[5]제외
			for (int i = 6; i < 16; i++) {
				String s = replaceLast(array[i], "더 보기", " ");
				res = res + s;
			}
		} else {// 서울대숲 연대숲은 [0][1][2][3] 제외
			for (int i = 4; i < 14; i++) {
				String s = replaceLast(array[i], "더 보기", " ");
				res = res + s;
			}
		}
		return res;
	}

	private String replaceLast(String string, String toReplace, String replacement) {
		int pos = string.lastIndexOf(toReplace);
		if (pos > -1) {
			return string.substring(0, pos) + replacement + string.substring(pos + toReplace.length(), string.length())
					+ "\n";
		} else {
			return string;
		}
	}
}
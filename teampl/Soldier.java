package teampl;

import java.util.Random;

public class Soldier { // 각 군인의 정보 저장 class
	String name;
	String birth;
	String enter;
	int[] cnews = new int[6];
	int[] cbam = new int[3];
	String sletter = "";
	MyTest news = new MyTest();
	Bamboo bam = new Bamboo();
	SendMail mail = new SendMail();
	String id = "arlimy";
	String pw = "Q1w2e3r4*";

	public Soldier(String name, String birth, String enter, int politics, int economy, int society, int culture,
			int world, int seoul, int korea, int yonsei) {
		// 무제한 편지 부대 -> 선택한 카테고리만 편지 생성
		this.name = name;
		this.birth = birth;
		this.enter = enter;
		cnews[1] = politics;
		cnews[2] = economy;
		cnews[3] = society;
		cnews[4] = culture;
		cnews[5] = world;
		cbam[0] = seoul;
		cbam[1] = korea;
		cbam[2] = yonsei;
	}

	public Soldier(String name, String birth, String enter) {
		// 1일 1편지 부대 & 자동 발송 yes -> 종합 1위기사 + 대숲 랜덤 한개 편지 생성
		this.name = name;
		this.birth = birth;
		this.enter = enter;
		cnews[0] = 1; // 종합 1위 뉴스
		Random r = new Random();
		cbam[r.nextInt(3)] = 1; // 대나무숲 하나 랜덤 선택
	}

	public void makeSendLetter() { // 군인이 선택한 카테고리별로 편지 생성, 발송
		sletter += "훈련병 이름 : " + this.name + "  생년월일 : " + this.birth + "   입대날짜 : " + this.enter + "\n";
		for (int i = 0; i < 6; i++) {
			if (cnews[i] == 1) {
				try {
					sletter += news.News(i) + "\n";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		for (int i = 0; i < 3; i++) {
			if (cbam[i] == 1) {
				sletter += bam.Bam(i + 1) + "\n";
			}
		}
		mail.main(sletter, id, pw);
	}
}
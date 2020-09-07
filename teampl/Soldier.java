package teampl;

import java.util.Random;

public class Soldier { // �� ������ ���� ���� class
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
		// ������ ���� �δ� -> ������ ī�װ��� ���� ����
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
		// 1�� 1���� �δ� & �ڵ� �߼� yes -> ���� 1����� + �뽣 ���� �Ѱ� ���� ����
		this.name = name;
		this.birth = birth;
		this.enter = enter;
		cnews[0] = 1; // ���� 1�� ����
		Random r = new Random();
		cbam[r.nextInt(3)] = 1; // �볪���� �ϳ� ���� ����
	}

	public void makeSendLetter() { // ������ ������ ī�װ����� ���� ����, �߼�
		sletter += "�Ʒú� �̸� : " + this.name + "  ������� : " + this.birth + "   �Դ볯¥ : " + this.enter + "\n";
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
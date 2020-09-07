package teampl;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class Scheduler {
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR)); // ���� �⵵
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)); // ���� ��
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)); // ���� ��¥
		cal.set(Calendar.HOUR_OF_DAY, 3); // ���� �ð�
		cal.set(Calendar.MINUTE, 11); // ���� ��
		cal.set(Calendar.SECOND, 0); // ���� ��

		Date d = cal.getTime();

		while (true) {
			ServerMain server = new ServerMain();
			Timer t = new Timer();

			t.schedule(server, d);
			try {
				Thread.sleep(1000 * 60 * 60 * 24); // 1000�и��� * 60��* 60��*24�ð� =���ϸ��� ����
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
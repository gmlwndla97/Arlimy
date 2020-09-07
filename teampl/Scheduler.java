package teampl;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class Scheduler {
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR)); // 현재 년도
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)); // 현재 달
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)); // 현재 날짜
		cal.set(Calendar.HOUR_OF_DAY, 3); // 설정 시각
		cal.set(Calendar.MINUTE, 11); // 설정 분
		cal.set(Calendar.SECOND, 0); // 설정 초

		Date d = cal.getTime();

		while (true) {
			ServerMain server = new ServerMain();
			Timer t = new Timer();

			t.schedule(server, d);
			try {
				Thread.sleep(1000 * 60 * 60 * 24); // 1000밀리초 * 60초* 60분*24시간 =매일마다 실행
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
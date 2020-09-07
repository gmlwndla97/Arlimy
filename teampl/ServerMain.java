package teampl;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TimerTask;

// 메인함수
// DB에 저장된 군인 정보 불러와서 지정된 시간에 한꺼번에 메일 전송
// ==> Arlimy 이메일로 일괄 전송 
// 단, 전송되는 편지의 첫번쨰 줄은 군인의 이름, 생년월일, 입대날짜로 구분
// 11시 58분에 DB에 저장된 군인 정보 가져오기 + 메일 보내기 (scheduler)

public class ServerMain extends TimerTask {
	ArrayList<Soldier> list = new ArrayList<Soldier>();
	Soldier sol;

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	static String name, birth, enter;
	static int politics, economy, society, culture, world, seoul, korea, yonsei;
	int count1; // DB에 저장된 군인의 수 (userinfo)
	int count2; // (oneletter)

	public void run() {
		connectDB();
		getDB();
		sendAll();
		closeDB();
	}

	// 1. DB에서 가져옴 (행으로 구분)
	public void getDB() {
		// 한 행씩 읽어와서 각 군인의 정보를 list에 저장
		this.count1 = getCount1();
		for (int i = 1; i <= this.count1; i++) {
			// 무제한 편지 부대 -> 선택한 카테고리만 편지 생성
			String sql = "select * from userinfo where line='" + i + "'";
			try {
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					name = rs.getString(1);
					birth = rs.getString(2);
					enter = rs.getString(3);
					politics = rs.getInt(4);
					economy = rs.getInt(5);
					society = rs.getInt(6);
					culture = rs.getInt(7);
					world = rs.getInt(8);
					seoul = rs.getInt(9);
					korea = rs.getInt(10);
					yonsei = rs.getInt(11);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			sol = new Soldier(name, birth, enter, politics, economy, society, culture, world, seoul, korea, yonsei);
			// 2. 각 군인 정보 Soldier에 저장
			list.add(sol);
		}

		this.count2 = getCount2();
		for (int i = 1; i <= this.count2; i++) {
			// 1일 1편지 부대 & 자동 발송 yes -> 종합 1위기사 + 대숲 랜덤 한개 편지 생성
			String sql2 = "select name,birth,enter from oneletter where auto=1 and line='" + i + "'";
			try {
				pstmt = conn.prepareStatement(sql2);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					name = rs.getString(1);
					birth = rs.getString(2);
					enter = rs.getString(3);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			sol = new Soldier(name, birth, enter);
			list.add(sol);
		}
	}

	// 3. 메일 보냄
	public void sendAll() {
		for (int i = 0; i < list.size(); i++) {
			list.get(i).makeSendLetter();
		}
	}

	public int getCount1() { // userinfo 에 저장된 군인 수 가져오기
		String sql = "select MAX(line) from userinfo";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count1 = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count1;
	}

	public int getCount2() { // oneletter 에 저장된 군인 수 가져오기
		String sql = "select MAX(line) from oneletter";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count2 = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count2;
	}

	public void connectDB() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:5306/alimy", "root", "1234");
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void closeDB() {
		try {
			pstmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
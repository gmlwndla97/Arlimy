package teampl;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TimerTask;

// �����Լ�
// DB�� ����� ���� ���� �ҷ��ͼ� ������ �ð��� �Ѳ����� ���� ����
// ==> Arlimy �̸��Ϸ� �ϰ� ���� 
// ��, ���۵Ǵ� ������ ù���� ���� ������ �̸�, �������, �Դ볯¥�� ����
// 11�� 58�п� DB�� ����� ���� ���� �������� + ���� ������ (scheduler)

public class ServerMain extends TimerTask {
	ArrayList<Soldier> list = new ArrayList<Soldier>();
	Soldier sol;

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	static String name, birth, enter;
	static int politics, economy, society, culture, world, seoul, korea, yonsei;
	int count1; // DB�� ����� ������ �� (userinfo)
	int count2; // (oneletter)

	public void run() {
		connectDB();
		getDB();
		sendAll();
		closeDB();
	}

	// 1. DB���� ������ (������ ����)
	public void getDB() {
		// �� �྿ �о�ͼ� �� ������ ������ list�� ����
		this.count1 = getCount1();
		for (int i = 1; i <= this.count1; i++) {
			// ������ ���� �δ� -> ������ ī�װ��� ���� ����
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
			// 2. �� ���� ���� Soldier�� ����
			list.add(sol);
		}

		this.count2 = getCount2();
		for (int i = 1; i <= this.count2; i++) {
			// 1�� 1���� �δ� & �ڵ� �߼� yes -> ���� 1����� + �뽣 ���� �Ѱ� ���� ����
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

	// 3. ���� ����
	public void sendAll() {
		for (int i = 0; i < list.size(); i++) {
			list.get(i).makeSendLetter();
		}
	}

	public int getCount1() { // userinfo �� ����� ���� �� ��������
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

	public int getCount2() { // oneletter �� ����� ���� �� ��������
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
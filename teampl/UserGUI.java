package teampl;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class UserGUI extends JFrame {
	myListener listener = new myListener();
	SendMail mail = new SendMail();
	JPanel panel1, panel2; // 기본 Panel
	CardLayout card;
	JPanel plogin, pcho1, pcho2, parmy, pstan, ponearmy, ponestan, pcate, pwrite, pautow, preserve, pnoreserve, pstore,
			psend;
	JButton nextbtn, onebtn, autobtn, armybtn, stanbtn, letbtn;
	JButton prevbtn, writebtn, reservebtn;
	JComboBox commode;
	JCheckBox[] newsbox;
	JCheckBox[] bambox;
	JCheckBox[] reserve;
	JCheckBox[] auto;
	JCheckBox[] week;
	int checknum = 0;
	String sletter = "";
	boolean bauto = false;
	boolean onemode = false;
	String id = null;
	char[] pw = null;
	String spw = "";
	JTextField tfname, tbirthd, endate;
	JTextField tfname1, tbirthd1, endate1;
	String soldiername;
	boolean searchSoldier = false;
	String moncheck = null;
	String tuecheck = null;
	String wedcheck = null;
	String thucheck = null;
	String fricheck = null;
	String satcheck = null;
	String suncheck = null;
	String[] weekreserve = { "", "", "", "", "", "", "" };
	String monid = "";
	String tueid = "";
	String wedid = "";
	String thuid = "";
	String friid = "";
	String satid = "";
	String sunid = "";
	String[] reservedId = { "", "", "", "", "", "", "" };
	String warn = "";
	int politics = 0, economy = 0, society = 0, culture = 0, world = 0, seoul = 0, korea = 0, yonsei = 0;
	String aname, birth, enter;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	int reserveOrNot = 0;
	int autoOrNot = 0;

	public UserGUI() { // 기본 생성자
		this.setTitle("Arlimy");
		this.setSize(450, 500);
		init();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void init() { // 기본적인 panel 설정 및 추가
		panel1 = new JPanel();
		prevbtn = new JButton("처음화면으로");
		prevbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panel2, "log");
			}
		});
		panel1.add(prevbtn);
		card = new CardLayout();
		panel2 = new JPanel(card); // panel2 를 Cardlayout으로 설정

		plogin = new JPanel();
		pcho1 = new JPanel();
		pcho2 = new JPanel();
		parmy = new JPanel();
		pstan = new JPanel();
		pcate = new JPanel();
		pwrite = new JPanel();
		pautow = new JPanel();
		ponearmy = new JPanel();
		ponestan = new JPanel();
		pstore = new JPanel();
		psend = new JPanel();
		preserve = new JPanel();
		pnoreserve = new JPanel();
		// panel2에 넣을 panel들 생성

		login();
		modecho();
		usercho();
		armyinfo();
		staninfo();
		onearmyinfo();
		onestaninfo();
		categorycho();
		writeletter();
		storeDB();
		sendLetter();
		reserve();
		// 각 함수 호출

		panel2.add("log", plogin);
		panel2.add("mc", pcho1);
		panel2.add("uc", pcho2);
		panel2.add("ai", parmy);
		panel2.add("si", pstan);
		panel2.add("oai", ponearmy);
		panel2.add("osi", ponestan);
		panel2.add("cc", pcate);
		panel2.add("wt", pwrite);
		panel2.add("aw", pautow);
		panel2.add("st", pstore);
		panel2.add("sd", psend);
		panel2.add("re", preserve);
		panel2.add("rno", pnoreserve);
		// panel2에 card로 추가

		Container container = this.getContentPane();
		container.add(panel1, BorderLayout.NORTH);
		container.add(panel2, BorderLayout.CENTER);
	}

	public void login() { // login 화면 생성
		plogin = new JPanel();
		plogin.setLayout(null);
		JLabel llogin = new JLabel("< LOGIN >");
		JLabel lid = new JLabel("      id : ");
		JLabel lpw = new JLabel("password : ");
		JTextField tid = new JTextField(10);
		JPasswordField tpw = new JPasswordField(10);
		llogin.setBounds(10, 5, 200, 20);
		lid.setBounds(10, 50, 100, 20);
		tid.setBounds(100, 50, 100, 20);
		lpw.setBounds(10, 100, 100, 20);
		tpw.setBounds(100, 100, 100, 20);
		plogin.add(llogin);
		plogin.add(lid);
		plogin.add(tid);
		plogin.add(lpw);
		plogin.add(tpw);
		nextbtn = new JButton("Next");
		nextbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id = tid.getText();
				pw = tpw.getPassword();
				for (int i = 0; i < pw.length; i++) {
					spw += pw[i];
				}
				card.show(panel2, "mc");
			}
		});
		JPanel pnext = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pnext.setBounds(250, 350, 100, 50);
		pnext.add(nextbtn);
		plogin.add(pnext);
	}

	public void modecho() { // 1일 1편지 or 무제한 편지 훈련소 선택 화면 생성
		pcho1 = new JPanel();
		pcho1.setLayout(null);
		onebtn = new JButton("1일 1편지 훈련소");
		autobtn = new JButton("무제한 편지 훈련소");
		onebtn.addActionListener(listener);
		autobtn.addActionListener(listener);
		onebtn.setBounds(120, 50, 150, 50);
		autobtn.setBounds(120, 150, 150, 50);
		pcho1.add(onebtn);
		pcho1.add(autobtn);
	}

	public void usercho() { // 군인 or 일반인 화면 생성
		pcho2 = new JPanel();
		pcho2.setLayout(null);
		armybtn = new JButton("군인");
		stanbtn = new JButton("일반인");
		armybtn.addActionListener(listener);
		stanbtn.addActionListener(listener);
		armybtn.setBounds(120, 50, 150, 50);
		stanbtn.setBounds(120, 150, 150, 50);
		pcho2.add(armybtn);
		pcho2.add(stanbtn);
	}

	public void armyinfo() { // 무제한 편지 훈련소 - 군인 정보 입력 화면
		parmy = new JPanel();
		parmy.setLayout(null);

		JPanel parmypan = new JPanel();
		parmypan.setLayout(null);

		JLabel larmy = new JLabel("< 무제한 편지 훈련소 - 군인 정보 입력 >");
		larmy.setBounds(10, 0, 300, 20);
		JLabel name = new JLabel("이름 : ");
		JTextField tfname = new JTextField(5);
		name.setBounds(10, 30, 100, 20);
		tfname.setBounds(100, 30, 100, 20);

		JLabel birthd = new JLabel("생년월일 : ");
		JLabel birthd_ex = new JLabel("ex) 19991231");
		JTextField tfYear = new JTextField(10);
		JPanel pBirth = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pBirth.add(tfYear);
		birthd.setBounds(10, 60, 100, 30);
		birthd_ex.setBounds(10, 75, 100, 30);
		pBirth.setBounds(100, 60, 200, 30);

		JLabel enterd = new JLabel("입대날짜 : ");
		JLabel enterd_ex = new JLabel("ex) 20171201");
		JTextField enYear = new JTextField(10);
		JPanel pgoArmy = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pgoArmy.add(enYear);
		enterd.setBounds(10, 90, 100, 30);
		enterd_ex.setBounds(10, 105, 100, 30);
		pgoArmy.setBounds(100, 90, 200, 30);

		JLabel sadan = new JLabel("입대할 훈련소 : ");
		String[] arrsadan = { "---", "논산훈련소", "1사단", "2사단", "3사단", "5사단", "6사단", "7사단", "8사단", "9사단" };
		JComboBox comsadan = new JComboBox(arrsadan);
		JPanel psadan = new JPanel(new FlowLayout(FlowLayout.LEFT));
		psadan.add(comsadan);
		sadan.setBounds(10, 120, 100, 30);
		psadan.setBounds(100, 120, 200, 30);

		nextbtn = new JButton("Next");
		nextbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aname = tfname.getText();
				birth = tfYear.getText();
				enter = enYear.getText();
				connectDB();
				registerUser(aname, birth, enter, politics, economy, society, culture, world, seoul, korea, yonsei);
				closeDB();
				card.show(panel2, "st");
			}
		});
		JPanel pnext = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pnext.setBounds(0, 400, 380, 500);
		pnext.add(nextbtn);

		parmypan.add(larmy);
		parmypan.add(name);
		parmypan.add(tfname);
		parmypan.add(birthd);
		parmypan.add(birthd_ex);
		parmypan.add(pBirth);
		parmypan.add(enterd);
		parmypan.add(enterd_ex);
		parmypan.add(pgoArmy);
		parmypan.add(sadan);
		parmypan.add(psadan);
		parmypan.add(pnext);

		String[] snews = { "정치", "경제", "사회", "생활/문화", "세계" };
		String[] sbam = { "서울대학교 대나무숲", "고려대학교 대나무숲", "연세대학교 대나무숲" };
		JLabel category = new JLabel("카테고리 선택 : ");

		JCheckBox[] newsbox = new JCheckBox[5];
		JCheckBox[] bambox = new JCheckBox[3];
		JPanel pnews = new JPanel(new FlowLayout());
		JPanel pbam = new JPanel(new FlowLayout());

		for (int i = 0; i < newsbox.length; i++) {
			newsbox[i] = new JCheckBox(snews[i]);
			newsbox[i].addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						if (e.getItem() == newsbox[0]) {
							politics = 1;
						} else if (e.getItem() == newsbox[1]) {
							economy = 1;
						} else if (e.getItem() == newsbox[2]) {
							society = 1;
						} else if (e.getItem() == newsbox[3]) {
							culture = 1;
						} else if (e.getItem() == newsbox[4]) {
							world = 1;
						}
					} else {
						if (e.getItem() == newsbox[0]) {
							politics = 0;
						} else if (e.getItem() == newsbox[1]) {
							economy = 0;
						} else if (e.getItem() == newsbox[2]) {
							society = 0;
						} else if (e.getItem() == newsbox[3]) {
							culture = 0;
						} else if (e.getItem() == newsbox[4]) {
							world = 0;
						}
					}
				}

			});
			pnews.add(newsbox[i]);
		}
		for (int i = 0; i < bambox.length; i++) {
			bambox[i] = new JCheckBox(sbam[i]);
			bambox[i].addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						if (e.getItem() == bambox[0]) {
							seoul = 1;
						} else if (e.getItem() == bambox[1]) {
							korea = 1;
						} else if (e.getItem() == bambox[2]) {
							yonsei = 1;
						}
					} else {
						if (e.getItem() == bambox[0]) {
							seoul = 0;
						} else if (e.getItem() == bambox[1]) {
							korea = 0;
						} else if (e.getItem() == bambox[2]) {
							yonsei = 0;
						}
					}
				}
			});
			pbam.add(bambox[i]);
		}
		JLabel lnews = new JLabel("   < 뉴스 >");
		JLabel lbam = new JLabel(" < 대나무숲 >");
		category.setBounds(10, 150, 200, 50);
		lnews.setBounds(10, 180, 100, 100);
		pnews.setBounds(10, 250, 100, 300);
		lbam.setBounds(180, 180, 150, 100);
		pbam.setBounds(180, 250, 150, 200);

		parmypan.add(category);
		parmypan.add(lnews);
		parmypan.add(pnews);
		parmypan.add(lbam);
		parmypan.add(pbam);
		parmypan.setBounds(0, 0, 400, 500);

		JScrollPane sp = new JScrollPane(parmypan, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		parmy.add(sp);
		parmy.add(parmypan);

	}

	public void staninfo() { // 무제한 편지 훈련소 - 일반인 정보 입력 화면
		pstan = new JPanel();
		pstan.setLayout(null);

		JLabel lstan = new JLabel("< 무제한 편지 훈련소 - 일반인 >");
		lstan.setBounds(10, 20, 300, 20);
		JLabel name = new JLabel("훈련병 이름 : ");
		JTextField tfname = new JTextField(5);
		name.setBounds(10, 90, 100, 20);
		tfname.setBounds(100, 90, 100, 20);

		JLabel birthd = new JLabel("훈련병 생년월일 : ");
		JLabel birthd_ex = new JLabel("ex) 19991231");
		JTextField tfYear = new JTextField(10);
		JPanel pBirth = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pBirth.add(tfYear);
		birthd.setBounds(10, 130, 100, 13);
		birthd_ex.setBounds(10, 140, 100, 20);
		pBirth.setBounds(100, 130, 200, 30);

		JLabel enterd = new JLabel("훈련병 입대날짜 : ");
		JLabel enterd_ex = new JLabel("ex) 20171201");
		JTextField enYear = new JTextField(10);
		JPanel pgoArmy = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pgoArmy.add(enYear);
		enterd.setBounds(10, 180, 100, 13);
		enterd_ex.setBounds(10, 190, 100, 20);
		pgoArmy.setBounds(100, 180, 200, 30);

		JLabel sadan = new JLabel("훈련소 : ");
		String[] arrsadan = { "---", "논산훈련소", "1사단", "2사단", "3사단", "5사단", "6사단", "7사단", "8사단", "9사단" };
		JComboBox comsadan = new JComboBox(arrsadan);
		JPanel psadan = new JPanel(new FlowLayout(FlowLayout.LEFT));
		psadan.add(comsadan);
		sadan.setBounds(10, 220, 100, 30);
		psadan.setBounds(100, 220, 200, 30);

		JLabel uname = new JLabel("보내는 사람 이름 : ");
		JTextField utname = new JTextField(5);
		uname.setBounds(10, 260, 100, 20);
		utname.setBounds(100, 260, 100, 20);

		JLabel mode = new JLabel("편지 발송 모드 : ");
		String[] arrmode = { "---", "일반 편지 작성", "자동 편지 발송" };
		commode = new JComboBox(arrmode);
		commode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (commode.getSelectedItem() == "자동 편지 발송") {
					bauto = true;
				} else {
					bauto = false;
				}
			}
		});
		JPanel pmode = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pmode.add(commode);
		mode.setBounds(10, 300, 100, 30);
		pmode.setBounds(100, 300, 130, 100);

		nextbtn = new JButton("Next");
		nextbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (bauto == true) {
					card.show(panel2, "cc");
				} else {
					card.show(panel2, "wt");
				}
				sletter += "[" + comsadan.getSelectedItem().toString() + "]" + "\n입대날짜 : " + enYear.getText()
						+ " 생년월일 : " + tfYear.getText() + " 훈련병 이름 : " + tfname.getText() + "\n보내는 사람 : "
						+ utname.getText() + "\n\n";

			}
		});
		JPanel pnext = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pnext.setBounds(250, 350, 100, 50);
		pnext.add(nextbtn);

		pstan.add(lstan);
		pstan.add(name);
		pstan.add(tfname);
		pstan.add(birthd);
		pstan.add(birthd_ex);
		pstan.add(pBirth);
		pstan.add(enterd);
		pstan.add(enterd_ex);
		pstan.add(pgoArmy);
		pstan.add(sadan);
		pstan.add(psadan);
		pstan.add(mode);
		pstan.add(pmode);
		pstan.add(pnext);
		pstan.add(uname);
		pstan.add(utname);
	}

	public void onearmyinfo() { // 1일 1편지 훈련소 - 군인 정보 저장 화면
		ponearmy = new JPanel();
		ponearmy.setLayout(null);

		JLabel larmy = new JLabel("< 1일 1편지 훈련소 - 정보 저장 >");
		larmy.setBounds(10, 20, 300, 20);
		JLabel name = new JLabel("이름 : ");
		tfname1 = new JTextField(5);
		name.setBounds(10, 90, 100, 20);
		tfname1.setBounds(100, 90, 100, 20);

		JLabel birthd = new JLabel("생년월일 : ");
		tbirthd1 = new JTextField(8);
		JPanel pBirth = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pBirth.add(tbirthd1);

		birthd.setBounds(10, 120, 100, 30);
		pBirth.setBounds(100, 120, 200, 30);

		JLabel enterd = new JLabel("입대날짜 : ");
		endate1 = new JTextField(8);
		JPanel pgoArmy = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pgoArmy.add(endate1);
		enterd.setBounds(10, 160, 100, 30);
		pgoArmy.setBounds(100, 160, 200, 30);

		JLabel sadan = new JLabel("입대할 훈련소 : ");
		String[] arrsadan = { "---", "해군", "공군", "해병대" };
		JComboBox comsadan = new JComboBox(arrsadan);
		JPanel psadan = new JPanel(new FlowLayout(FlowLayout.LEFT));
		psadan.add(comsadan);
		sadan.setBounds(10, 200, 100, 30);
		psadan.setBounds(100, 200, 200, 30);

		JLabel warn = new JLabel("(**예약서비스를 원하면 체크를 눌러주세요)");
		JPanel pReserve = new JPanel(new FlowLayout(FlowLayout.LEFT));
		String[] checkreserve = { "예약 시스템 신청" };
		reserve = new JCheckBox[1];
		for (int i = 0; i < reserve.length; i++) {
			reserve[i] = new JCheckBox(checkreserve[i]);
			pReserve.add(reserve[i]);
			reserve[i].addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {// 선택되었을때
						reserveOrNot = 1;

					} else {  // 해당없음
						reserveOrNot = 0;
					}
				}
			});
		}
		pReserve.setBounds(10, 235, 300, 30);
		warn.setBounds(10, 260, 300, 30);

		JLabel warn2 = new JLabel("(**편지가 없을경우 자동적으로 뉴스나 대숲이 보내집니다)");
		JPanel pAuto = new JPanel(new FlowLayout(FlowLayout.LEFT));
		String[] checkauto = { "자동 편지 신청" };
		auto = new JCheckBox[1];
		for (int i = 0; i < reserve.length; i++) {
			auto[i] = new JCheckBox(checkauto[i]);
			pAuto.add(auto[i]);
			auto[i].addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {// 선택되었을때
						autoOrNot = 1;
					} else {
						autoOrNot = 0;
					}
				}
			});
		}
		pAuto.setBounds(10, 280, 300, 30);
		warn2.setBounds(10, 305, 400, 30);

		nextbtn = new JButton("저장");
		nextbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panel2, "st");
				connectDB();
				soldierinfo(tfname1.getText(), tbirthd1.getText(), endate1.getText(), reserveOrNot, autoOrNot);
				closeDB();
			}
		});

		JPanel pnext = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pnext.setBounds(250, 350, 100, 50);
		pnext.add(nextbtn);

		ponearmy.add(larmy);
		ponearmy.add(name);
		ponearmy.add(tfname1);
		ponearmy.add(birthd);
		ponearmy.add(pBirth);
		ponearmy.add(enterd);
		ponearmy.add(pgoArmy);
		ponearmy.add(sadan);
		ponearmy.add(psadan);
		ponearmy.add(pnext);
		ponearmy.add(pReserve);
		ponearmy.add(warn);
		ponearmy.add(warn2);
		ponearmy.add(pAuto);
	}

	public void soldierinfo(String n, String b, String en, int reverseOrNot, int autoOrNot) { // 군인 정보와, 예약 또는 자동편지 신청할지
		String sql = "insert into oneletter values(?,?,?,?,?,null,null,null,null,null,null,null,null)";    // 말지 까지 입력받음
		try {
			System.out.println(n);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, n);
			pstmt.setString(2, b);
			pstmt.setString(3, en);
			pstmt.setInt(4, reverseOrNot);
			pstmt.setInt(5, autoOrNot);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void onestaninfo() { // 1일 1편지 훈련소 - 일반인 정보 검색 화면
		ponestan = new JPanel();
		ponestan.setLayout(null);

		JLabel larmy = new JLabel("< 일반인 - 정보검색 >");
		larmy.setBounds(10, 20, 300, 20);
		JLabel name = new JLabel("이름 : ");
		tfname = new JTextField(5);
		name.setBounds(10, 90, 100, 20);
		tfname.setBounds(100, 90, 100, 20);

		JLabel birthd = new JLabel("생년월일 : ");
		tbirthd = new JTextField(8);
		JPanel pBirth = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pBirth.add(tbirthd);

		birthd.setBounds(10, 120, 100, 30);
		pBirth.setBounds(100, 120, 200, 30);

		JLabel enterd = new JLabel("입대날짜 : ");
		endate = new JTextField(8);
		JPanel pgoArmy = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pgoArmy.add(endate);
		enterd.setBounds(10, 160, 100, 30);
		pgoArmy.setBounds(100, 160, 200, 30);

		JLabel sadan = new JLabel("입대할 훈련소 : ");
		String[] arrsadan = { "---", "해군", "공군", "해병대" };
		JComboBox comsadan = new JComboBox(arrsadan);
		JPanel psadan = new JPanel(new FlowLayout(FlowLayout.LEFT));
		psadan.add(comsadan);
		sadan.setBounds(10, 200, 100, 30);
		psadan.setBounds(100, 200, 200, 30);

		reservebtn = new JButton("예약하기");
		reservebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// db연결
				connectDB();
				findSoldier();
				calendar(id, monid, tueid, wedid, thuid, friid, satid, sunid);
				closeDB();
			}
		});
		writebtn = new JButton("편지쓰기");
		writebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 편지쓰는 화면
				card.show(panel2, "wt");
			}
		});

		JPanel pnext = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pnext.setBounds(200, 350, 100, 50);
		pnext.add(reservebtn);
		JPanel pnext2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pnext2.setBounds(250, 350, 140, 50);
		pnext2.add(writebtn);

		ponestan.add(larmy);
		ponestan.add(name);
		ponestan.add(tfname);
		ponestan.add(birthd);
		ponestan.add(pBirth);
		ponestan.add(enterd);
		ponestan.add(pgoArmy);
		ponestan.add(sadan);
		ponestan.add(psadan);
		ponestan.add(pnext);
		ponestan.add(pnext2);
	}

	public void categorycho() { // 자동 편지 카테고리 선택 화면
		pcate = new JPanel();
		pcate.setLayout(null);
		String[] snews = { "정치", "경제", "사회", "생활/문화", "세계" };
		String[] sbam = { "서울대학교 대나무숲", "고려대학교 대나무숲", "연세대학교 대나무숲" };
		JLabel category = new JLabel("카테고리 선택 : ");

		JCheckBox[] newsbox = new JCheckBox[5];
		JCheckBox[] bambox = new JCheckBox[3];
		JPanel pnews = new JPanel(new FlowLayout());
		JPanel pbam = new JPanel(new FlowLayout());

		for (int i = 0; i < newsbox.length; i++) {
			newsbox[i] = new JCheckBox(snews[i]);
			pnews.add(newsbox[i]);
		}
		for (int i = 0; i < bambox.length; i++) {
			bambox[i] = new JCheckBox(sbam[i]);
			pbam.add(bambox[i]);
		}
		JLabel lnews = new JLabel("   < 뉴스 >");
		JLabel lbam = new JLabel(" < 대나무숲 >");
		category.setBounds(10, 1, 200, 50);
		lnews.setBounds(10, 30, 100, 100);
		pnews.setBounds(10, 100, 100, 300);
		lbam.setBounds(180, 30, 150, 100);
		pbam.setBounds(180, 100, 150, 200);

		pcate.add(category);
		pcate.add(lnews);
		pcate.add(pnews);
		pcate.add(lbam);
		pcate.add(pbam);

		pautow = new JPanel(new BorderLayout());
		JPanel pal1 = new JPanel();
		JPanel pal2 = new JPanel();
		JTextArea letter = new JTextArea(23, 33);

		MyTest news = new MyTest();
		Bamboo bam = new Bamboo();
		JButton nextbtn2 = new JButton("Next");
		nextbtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 5; i++) {
					if (newsbox[i].isSelected()) {
						try {
							sletter += news.News(i + 1) + "\n";
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
				for (int i = 0; i < 3; i++) {
					if (bambox[i].isSelected()) {
						sletter += bam.Bam(i + 1) + "\n";
					}
				}
				letter.setText(sletter);

				card.show(panel2, "aw");
			}
		});
		JPanel pnext = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		// 자동 편지 로드 화면
		pnext.setBounds(250, 350, 100, 50);
		pnext.add(nextbtn2);
		pcate.add(pnext);

		letter.setBounds(10, 10, 100, 100);
		letter.setLineWrap(true);
		JScrollPane sp = new JScrollPane(letter, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		JLabel label = new JLabel("자동 편지 작성");
		pal1.add(label);
		pal2.add(sp);
		letbtn = new JButton("제출");
		letbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sendLetter();
				try {
					mail.main(sletter, id, spw);
					card.show(panel2, "sd");
				} catch (Exception e2) {
					System.out.println("전송 오류");
				}
			}
		});
		pal2.add(letbtn);
		pautow.add(pal1, BorderLayout.NORTH);
		pautow.add(pal2, BorderLayout.CENTER);
	}

	public void writeletter() { // 일반 편지 입력 화면
		pwrite = new JPanel(new BorderLayout());
		JPanel pletter1 = new JPanel();
		JPanel pletter2 = new JPanel();
		JTextArea letter = new JTextArea(20, 33);
		letter.setBounds(10, 10, 100, 100);
		letter.setLineWrap(true);
		JScrollPane sp = new JScrollPane(letter, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JLabel label = new JLabel("편지 작성");
		pletter1.add(label);
		pletter2.add(sp);
		letbtn = new JButton("제출");

		pletter2.add(letbtn);
		pwrite.add(pletter1, BorderLayout.NORTH);
		pwrite.add(pletter2, BorderLayout.CENTER);
		letbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String wletter = letter.getText();

				sendLetter();
				mail.main(wletter, id, spw);
			}
		});
	}

	public void storeDB() { // 저장 완료 화면
		pstore = new JPanel();
		pstore.setLayout(null);
		JLabel lst = new JLabel("저장 완료!");
		lst.setBounds(100, 100, 100, 30);
		pstore.add(lst);
	}

	public void sendLetter() { // 전송 완료 화면
		psend = new JPanel();
		psend.setLayout(null);
		JLabel lsd = new JLabel("전송 완료!");
		lsd.setBounds(100, 100, 100, 30);
		psend.add(lsd);
	}

	public void findSoldier() {
		String sql = "select * from oneletter where name=? and reserve=1";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tfname.getText());
			soldiername = tfname.getText();
			rs = pstmt.executeQuery();

			while (rs.next()) {// rs에 검색한 군인이름 저장 , 군인 이름이 있으면
				// 예약 페이지로 이동
				searchSoldier = true;

			}
			if (searchSoldier == true) {
				card.show(panel2, "re");
			} else if (searchSoldier == false) {
				// 없습니다 출력하는 페이지
				card.show(panel2, "rno");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void calendar(String id, String monid, String tueid, String wedid, String thuid, String friid, String satid,
			String sunid) {
		connectDB();
		String sql = "select mon,tue,wed,thu,fri,sat,sun from oneletter where name=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, soldiername);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				monid = rs.getString(1);
				tueid = rs.getString(2);
				wedid = rs.getString(3);
				thuid = rs.getString(4);
				friid = rs.getString(5);
				satid = rs.getString(6);
				sunid = rs.getString(7);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeDB();

		JLabel warn3 = new JLabel("매주 주말에 신청할 수 있습니다. " + "\n" + "(예약 최대 2일까지 가능)");

		JLabel mon = new JLabel(monid);
		JLabel tue = new JLabel(tueid);
		JLabel wed = new JLabel(wedid);
		JLabel thu = new JLabel(thuid);
		JLabel fri = new JLabel(friid);
		JLabel sat = new JLabel(satid);
		JLabel sun = new JLabel(sunid);

		JPanel calendar = new JPanel(new FlowLayout(FlowLayout.LEFT));

		String[] date = { "월    ", "화    ", "수    ", "목    ", "금    ", "토    ", "일    " };
		week = new JCheckBox[7];
		checkreserve(id, soldiername, reservedId);
		for (int i = 0; i < week.length; i++) {
			week[i] = new JCheckBox(date[i]);
			calendar.add(week[i]);
			for (int j = 0; j < 7; j++) {
				if (reservedId[i] != null) {
					week[i].setEnabled(false);
				}
			}

			week[i].addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						if (e.getItem() == week[0]) { // 월요일
							checknum++;
							weekreserve[0] = id;
							week[0].isSelected();
							if (checknum == 2) {
								for (int i = 0; i < week.length; i++) {
									if (week[i].isSelected() == false) {
										week[i].setEnabled(false);
									}
								}
							}

						} else if (e.getItem() == week[1]) {// 화요일
							checknum++;
							weekreserve[1] = id;
							week[1].isSelected();
							if (checknum == 2) {
								for (int i = 0; i < week.length; i++) {
									if (week[i].isSelected() == false) {
										week[i].setEnabled(false);
									}
								}
							}

						} else if (e.getItem() == week[2]) {// 수요일
							checknum++;
							weekreserve[2] = id;
							week[2].isSelected();
							if (checknum == 2) {
								for (int i = 0; i < week.length; i++) {
									if (week[i].isSelected() == false) {
										week[i].setEnabled(false);
									}
								}
							}

						} else if (e.getItem() == week[3]) {// 목요일
							checknum++;
							weekreserve[3] = id;
							week[3].isSelected();
							if (checknum == 2) {
								for (int i = 0; i < week.length; i++) {
									if (week[i].isSelected() == false) {
										week[i].setEnabled(false);
									}
								}
							}
						} else if (e.getItem() == week[4]) {// 금요일
							checknum++;
							weekreserve[4] = id;
							week[4].isSelected();
							if (checknum == 2) {
								for (int i = 0; i < week.length; i++) {
									if (week[i].isSelected() == false) {
										week[i].setEnabled(false);
									}
								}
							}

						} else if (e.getItem() == week[5]) {// 토요일
							checknum++;
							weekreserve[5] = id;
							week[5].isSelected();
							if (checknum == 2) {
								for (int i = 0; i < week.length; i++) {
									if (week[i].isSelected() == false) {
										week[i].setEnabled(false);
									}
								}
							}

						} else if (e.getItem() == week[6]) {// 일요일
							checknum++;
							weekreserve[6] = id;
							week[6].isSelected();
							if (checknum == 2) {
								for (int i = 0; i < week.length; i++) {
									if (week[i].isSelected() == false) {
										week[i].setEnabled(false);
									}
								}
							}
						}
					}

					else {// 체크되지 않았을때
						if (e.getItem() == week[0]) { // 월요일
							// 월요일 레이블에 아이디 setText
							checknum--;
							week[0].isSelected();
							weekreserve[0] = "";
							if (checknum < 2) {
								for (int i = 0; i < week.length; i++) {
									if (week[i].isSelected() == false) {
										week[i].setEnabled(true);
									}
								}
							}
						} else if (e.getItem() == week[1]) {// 화요일
							checknum--;
							weekreserve[1] = "";
							week[1].isSelected();
							if (checknum < 2) {
								for (int i = 0; i < week.length; i++) {
									if (week[i].isSelected() == false) {
										week[i].setEnabled(true);
									}
								}
							}
						} else if (e.getItem() == week[2]) {// 수요일
							checknum--;
							weekreserve[2] = "";
							week[1].isSelected();
							if (checknum < 2) {
								for (int i = 0; i < week.length; i++) {
									if (week[i].isSelected() == false) {
										week[i].setEnabled(true);
									}
								}
							}
						} else if (e.getItem() == week[3]) {// 목요일
							checknum--;
							weekreserve[3] = null;
							week[1].isSelected();
							if (checknum < 2) {
								for (int i = 0; i < week.length; i++) {
									if (week[i].isSelected() == false) {
										week[i].setEnabled(true);
									}
								}
							}
						} else if (e.getItem() == week[4]) {// 금요일
							checknum--;
							weekreserve[4] = "";
							if (checknum < 2) {
								for (int i = 0; i < week.length; i++) {
									if (week[i].isSelected() == false) {
										week[i].setEnabled(true);
									}
								}
							}
						} else if (e.getItem() == week[5]) {// 토요일
							checknum--;
							week[1].isSelected();
							weekreserve[5] = "";
							if (checknum < 2) {
								for (int i = 0; i < week.length; i++) {
									if (week[i].isSelected() == false) {
										week[i].setEnabled(true);
									}
								}
							}
						} else if (e.getItem() == week[6]) {// 일요일
							checknum--;
							week[1].isSelected();
							weekreserve[6] = "";
							if (checknum < 2) {
								for (int i = 0; i < week.length; i++) {
									if (week[i].isSelected() == false) {
										week[i].setEnabled(true);
									}
								}
							}
						}

					}
				}
			});
		}
		JLabel warn = new JLabel("");
		warn.setBounds(100, 200, 300, 30);
		preserve.add(warn);
		JLabel warn2 = new JLabel("");
		warn2.setBounds(100, 250, 300, 30);
		preserve.add(warn2);
		JButton reserveDatebtn = new JButton("신청");

		reserveDatebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkreserve(id, soldiername, reservedId);
				System.out.println("날짜예약" + weekreserve[0]);
				System.out.println("아이디" + reservedId[0]);

				if (!(weekreserve[0].equals("")) && reservedId[0] == null) {
					moncheck = id;
					warn.setText("예약 완료되었습니다.");
				} else {
					moncheck = reservedId[0];
				}

				if (!(weekreserve[1].equals("")) && reservedId[1] == null) {
					tuecheck = id;
					warn.setText("예약 완료되었습니다.");
				} else {
					tuecheck = reservedId[1];
				}

				if (!(weekreserve[2].equals("")) && reservedId[2] == null) {
					wedcheck = id;
					warn.setText("예약 완료되었습니다.");
				} else {
					wedcheck = reservedId[2];
				}

				if (!(weekreserve[3].equals("")) && reservedId[3] == null) {
					thucheck = id;
					warn.setText("예약 완료되었습니다.");
				} else {
					thucheck = reservedId[3];
				}

				if (!(weekreserve[4].equals("")) && reservedId[4] == null) {
					fricheck = id;
					warn.setText("예약 완료되었습니다.");
				} else {
					fricheck = reservedId[4];
				}
				if (!(weekreserve[5].equals("")) && reservedId[5] == null) {
					satcheck = id;
					warn.setText("예약 완료되었습니다.");
				} else {
					satcheck = reservedId[5];
				}
				if (!(weekreserve[6].equals("")) && reservedId[6] == null) {
					suncheck = id;
					warn.setText("예약 완료되었습니다.");
				} else {
					suncheck = reservedId[6];
				}
				System.out.println(moncheck);
				update(id, soldiername, moncheck, tuecheck, wedcheck, thucheck, fricheck, satcheck, suncheck,
						reservedId);

			}
		});

		calendar.setBounds(5, 70, 420, 30);
		warn3.setBounds(10, 30, 420, 50);
		mon.setBounds(15, 90, 30, 30);
		tue.setBounds(70, 90, 30, 30);
		wed.setBounds(125, 90, 30, 30);
		thu.setBounds(180, 90, 30, 30);
		fri.setBounds(235, 90, 30, 30);
		sat.setBounds(290, 90, 30, 30);
		sun.setBounds(345, 90, 30, 30);
		reserveDatebtn.setBounds(150, 150, 100, 30);
		preserve.add(calendar);
		preserve.add(warn3);
		preserve.add(mon);
		preserve.add(tue);
		preserve.add(wed);
		preserve.add(thu);
		preserve.add(fri);
		preserve.add(sat);
		preserve.add(sun);
		preserve.add(reserveDatebtn);

	}

	public void checkreserve(String id, String soldiername, String reservedId[]) {
		connectDB();
		String sql = "select mon, tue, wed, thu, fri, sat, sun from oneletter where name=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, soldiername);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				for (int i = 1; i < 8; i++) {
					reservedId[i - 1] = rs.getString(i);
					System.out.println(rs.getString(i));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		closeDB();
	}

	public void update(String id, String soldiername, String moncheck, String tuecheck, String wedcheck,
			String thucheck, String fricheck, String satcheck, String suncheck, String reservedId[]) {
		connectDB();
		if (!(weekreserve[0].equals("")) && reservedId[0] == id)
			moncheck = id;

		if (!(weekreserve[1].equals("")) && reservedId[1] == id)
			tuecheck = id;

		if (!(weekreserve[2].equals("")) && reservedId[2] == id)
			wedcheck = id;

		if (!(weekreserve[3].equals("")) && reservedId[3] == id)
			thucheck = id;

		if (!(weekreserve[4].equals("")) && reservedId[4] == id)
			fricheck = id;

		if (!(weekreserve[5].equals("")) && reservedId[5] == id)
			satcheck = id;

		if (!(weekreserve[6].equals("")) && reservedId[6] == id)
			suncheck = id;

		String sql = "update oneletter set mon=? ,tue=? ,wed=?, thu=?, fri=? , sat=? ,sun=? where name=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, moncheck);
			pstmt.setString(2, tuecheck);
			pstmt.setString(3, wedcheck);
			pstmt.setString(4, thucheck);
			pstmt.setString(5, fricheck);
			pstmt.setString(6, satcheck);
			pstmt.setString(7, suncheck);
			pstmt.setString(8, soldiername);
			pstmt.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		closeDB();
	}

	public void reserve() {
		preserve = new JPanel();
		preserve.setLayout(null);
		// preserve에는 일주일 달력 들어감
		pnoreserve = new JPanel();
		pnoreserve.setLayout(null);

		JLabel noreserve = new JLabel("없는 정보입니다.");
		noreserve.setBounds(10, 20, 300, 20);
		pnoreserve.add(noreserve);
	}

	public static void main(String[] args) {
		new UserGUI();

	}

	public class myListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == onebtn) {
				onemode = true;
				card.show(panel2, "uc");
			} else if (e.getSource() == autobtn) {
				onemode = false;
				card.show(panel2, "uc");
			} else if (e.getSource() == armybtn) {
				if (onemode) {
					card.show(panel2, "oai");
				} else {
					card.show(panel2, "ai");
				}
			} else if (e.getSource() == stanbtn) {
				if (onemode) {
					card.show(panel2, "osi");
				} else {
					card.show(panel2, "si");
				}
			}
		}
	}

	public void connectDB() { // DB에 연결
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:5306/alimy", "root", "1234");
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void registerUser(String str1, String str2, String str3, int politics, int economy, int society, int culture,
			int world, int seoul, int korea, int yonsei) {
		// 군인 정보 DB에 저장
		String sql = "insert into userinfo values(?,?,?,?,?,?,?,?,?,?,?,null)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, str1);
			pstmt.setString(2, str2);
			pstmt.setString(3, str3);
			pstmt.setInt(4, politics);
			pstmt.setInt(5, economy);
			pstmt.setInt(6, society);
			pstmt.setInt(7, culture);
			pstmt.setInt(8, world);
			pstmt.setInt(9, seoul);
			pstmt.setInt(10, korea);
			pstmt.setInt(11, yonsei);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeDB() { // DB 종료
		try {
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
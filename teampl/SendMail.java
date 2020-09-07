package teampl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

	public void main(String args, String userid, String userpw) {
		Properties props = System.getProperties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.naver.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			String id = userid;
			String password = userpw;

			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(id, password);
			}
		});
		MimeMessage msg = new MimeMessage(session);

		try {
			msg.setSentDate(new Date());
			InternetAddress from = new InternetAddress();

			from = new InternetAddress(userid + "<" + userid + "@naver.com>");
			msg.setFrom(from);

			InternetAddress to = new InternetAddress(userid + "@naver.com");
			msg.setRecipient(Message.RecipientType.TO, to);

			Date today = new Date();
			SimpleDateFormat day = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			String todayStr = day.format(today);
			msg.setSubject(todayStr);
			msg.setText(args);

			javax.mail.Transport.send(msg);
		} catch (AddressException addr_e) {
			addr_e.printStackTrace();
		} catch (MessagingException msg_e) {
			msg_e.printStackTrace();
		}
	}
}
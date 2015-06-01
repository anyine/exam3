package org.flycloud.util;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {
	private Properties properties;
	private String pass;
	private String user;

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	private Authenticator authenticator;
	private boolean isInit = false;
	Session sendMailSession;

	private void init() {
		if (isInit)
			return;
		final String user = this.user;
		final String pass = this.pass;
		authenticator = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, pass);
			}
		};
		sendMailSession = Session.getDefaultInstance(this.properties,
				authenticator);
		isInit = true;
	}

	public void send(String email, String title, String content) {
		try {
			init();
			Message mailMessage = new MimeMessage(sendMailSession);
			Address from = new InternetAddress(this.user);
			mailMessage.setFrom(from);
			Address to = new InternetAddress(email);// 设置接收人员
			mailMessage.setRecipient(Message.RecipientType.TO, to);
			mailMessage.setSubject(title);// 设置邮件标题
			mailMessage.setText(content); // 设置邮件内容
			Transport.send(mailMessage);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Properties p = new Properties();
		p.put("mail.smtp.host", "smtp.163.com");
		p.put("mail.smtp.port", "25");
		p.put("mail.smtp.auth", "true");

		MailSender sender = new MailSender();
		sender.setPass("*******");
		sender.setUser("zhangbo7364@163.com");
		sender.setProperties(p);

		sender.send("zhangbo7364@126.com", "重要通知", "晚上一定要吃饭");
		Thread.sleep(5000);
		sender.send("zhangbo7364@126.com", "重要通知", "白天不要睡觉");
	}
}

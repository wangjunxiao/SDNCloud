package cn.dlut.util;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class MonitorMailUtil{

    private MailSender mailSender ;
    
    public String mailReceiver;
    public String mailSubject;
    public String mailText;
    
    private SimpleMailMessage simpleMailMessage ;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
        this.simpleMailMessage = simpleMailMessage;
    }

    public String getMailReceiver() {
		return mailReceiver;
	}

	public void setMailReceiver(String mailReceiver) {
		this.mailReceiver = mailReceiver;
	}

	public String getMailSubject() {
		return mailSubject;
	}

	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	public String getMailText() {
		return mailText;
	}

	public void setMailText(String mailText) {
		this.mailText = mailText;
	}

	public void placeOrder() {

        SimpleMailMessage simpleMailMessage1 = new SimpleMailMessage(this.simpleMailMessage); //SimpleMailMessage只能用来发送text格式的邮件
        simpleMailMessage1.setTo(this.mailReceiver);
        simpleMailMessage1.setText(this.mailText);
        simpleMailMessage1.setSubject(this.mailSubject);
        mailSender.send(simpleMailMessage1); 
    }
	
    public static void main(String args[]) {
	        BeanFactory beanFactory = new ClassPathXmlApplicationContext("/spring/spring-app.xml") ;

	        MonitorMailUtil simpleOrderManager = (MonitorMailUtil)
	                beanFactory.getBean("orderManager");
	        simpleOrderManager.setMailReceiver("mxyenguing@qq.com");
	        simpleOrderManager.setMailSubject("Error");
	        simpleOrderManager.setMailText("Hello,something is wrong");
	        simpleOrderManager.placeOrder();  
	    }
	}


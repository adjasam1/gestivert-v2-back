package gesticert.mail;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * Configuration Gmail pour envoi mail
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
@Configuration
public class MailConfig {

	/**
	 * Configuration mail émetteur (demandeur certificat) et mail recépteur (DASU)
	 */
	public static final String MY_EMAIL = "4samuel.sabot@gmail.com";
	public static final String MY_PASSWORD = "samuL4mail";
	public static final String OTHER_EMAIL = "sodabi-samsam@hotmail.fr";

	/**
	 * Configuration serveur et port pour envoi de mail avec gmail
	 * 
	 * @return envoi un mail
	 */
	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);

		mailSender.setUsername(MailConfig.MY_EMAIL);
		mailSender.setPassword(MailConfig.MY_PASSWORD);

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");

		return mailSender;
	}

}

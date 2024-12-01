package by.it_academy.jd2.userService.service;

import by.it_academy.jd2.exception.EmailSendingException;
import by.it_academy.jd2.userService.config.property.MailProperties;
import by.it_academy.jd2.userService.service.api.IEmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Async
@Service
public class EmailService implements IEmailService {

    private final JavaMailSender mailSender;
    private final MailProperties mailProperties;
    private static final String SUBJECT_EMAIL = "Верификация адреса электронной почты";


    public EmailService(JavaMailSender mailSender, MailProperties mailProperties) {
        this.mailSender = mailSender;
        this.mailProperties = mailProperties;
    }

    @Override
    public void sendVerificationEmail(String to, String verificationCode) {

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(mailProperties.getFrom());
            helper.setTo(to);
            helper.setSubject(SUBJECT_EMAIL);
            helper.setText(verificationCode, true);

            mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            throw new EmailSendingException(String.format("Не удалось отправить email на адрес: %s", to), e);
        }
    }
}

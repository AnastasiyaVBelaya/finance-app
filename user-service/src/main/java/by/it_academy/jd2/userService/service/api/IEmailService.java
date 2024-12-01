package by.it_academy.jd2.userService.service.api;

public interface IEmailService {
    void sendVerificationEmail(String to, String verificationCode);
}

package src.services;

public class MailService implements src.interfaces.MailService {
    @Override
    public void sendEmail(String email, String fileType) {
        System.out.println("Sending " + fileType + " to " + email);
    } 
}

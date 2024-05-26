package dasturlash.uz.service;

import dasturlash.uz.dto.auth.AuthResponseDTO;
import dasturlash.uz.dto.auth.LoginDTO;
import dasturlash.uz.dto.auth.RegistrationDTO;
import dasturlash.uz.entity.ProfileEntity;
import dasturlash.uz.enums.ProfileRole;
import dasturlash.uz.enums.ProfileStatus;
import dasturlash.uz.exp.AppBadException;
import dasturlash.uz.repository.ProfileRepository;
import dasturlash.uz.service.history.EmailHistoryService;
import dasturlash.uz.service.history.SmsHistoryService;
import dasturlash.uz.util.MD5Util;
import dasturlash.uz.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private MailSenderService mailSenderService;
    @Autowired
    private EmailHistoryService emailHistoryService;
    @Autowired
    private SmsSenderService smsSenderService;
    @Autowired
    private SmsHistoryService smsHistoryService;

    public String registration(RegistrationDTO dto) {
//        Optional<ProfileEntity> optional = profileRepository.findByEmailAndVisibleTrue(dto.getEmail());
        Optional<ProfileEntity> optional = profileRepository.findByPhoneAndVisibleTrue(dto.getPhone());
        if (optional.isPresent()) {
            throw new AppBadException("Phone already exists");
        }
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
//        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setPassword(MD5Util.getMD5(dto.getPassword()));
        entity.setCreatedDate(LocalDateTime.now());
        entity.setRole(ProfileRole.ROLE_USER);
        entity.setStatus(ProfileStatus.REGISTRATION);
        profileRepository.save(entity);
        // send email
     /*   String url = "http://localhost:8080/auth/verification/" + entity.getId();
        String formatText = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<style>\n" +
                "    a:link, a:visited {\n" +
                "        background-color: #f44336;\n" +
                "        color: white;\n" +
                "        padding: 14px 25px;\n" +
                "        text-align: center;\n" +
                "        text-decoration: none;\n" +
                "        display: inline-block;\n" +
                "    }\n" +
                "\n" +
                "    a:hover, a:active {\n" +
                "        background-color: red;\n" +
                "    }\n" +
                "</style>\n" +
                "<body>\n" +
                "<div style=\"text-align: center\">\n" +
                "    <h1>Welcome to kun.uz web portal</h1>\n" +
                "    <br>\n" +
                "    <p>Please button lick below to complete registration</p>\n" +
                "    <div style=\"text-align: center\">\n" +
                "        <a href=\"%s\" target=\"_blank\">This is a link</a>\n" +
                "    </div>\n" +
                "</div>\n" +
                "\n" +
                "</body>\n" +
                "</html>";
        String text = String.format(formatText, url);
        mailSenderService.send(dto.getEmail(), "Complete registration", text);*/
        smsSenderService.sendSms(dto.getPhone());
        return "To complete your registration please verify your email.";
    }
    public String authorizationVerification(Integer userId) {
        Optional<ProfileEntity> optional = profileRepository.findById(userId);
        if (optional.isEmpty()) {
            throw new AppBadException("User not found");
        }
        ProfileEntity entity = optional.get();
        if (!entity.getVisible() || !entity.getStatus().equals(ProfileStatus.REGISTRATION)) {
            throw new AppBadException("Registration not completed");
        }
        profileRepository.updateStatus(userId, ProfileStatus.ACTIVE);
        return "Success";
    }

    public AuthResponseDTO login(LoginDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByEmailAndPasswordAndVisible(
                dto.getEmail(),
                MD5Util.getMD5(dto.getPassword()),
                true);
        if (optional.isEmpty()) {
             throw new AppBadException("Email or password incorrect");
        }
        ProfileEntity entity = optional.get();
        if (entity.getStatus().equals(ProfileStatus.ACTIVE)) {
            throw new AppBadException("Wrong status");
        }
        AuthResponseDTO responseDTO = new AuthResponseDTO();
        responseDTO.setName(entity.getName());
        responseDTO.setSurname(entity.getSurname());
        responseDTO.setRole(entity.getRole());
        return responseDTO;
    }
    // phone resend
    public String registrationResendPhone(String phone) {
        Optional<ProfileEntity> optional = profileRepository.findByPhoneAndVisibleTrue(phone);
        if (optional.isEmpty()) {
            throw new AppBadException("Phone not exists");
        }
        ProfileEntity entity = optional.get();
        smsHistoryService.isNotExpiredPhone(entity.getPhone());// check for expireation date
        if (!entity.getVisible() || !entity.getStatus().equals(ProfileStatus.REGISTRATION)) {
            throw new AppBadException("Registration not completed");
        }
        smsHistoryService.checkPhoneLimit(phone);
        sendRegistrationPhone(entity.getId(), phone);
        return "To complete your registration please verify your phone.";
    }
    //email resend
    public String registrationResendEmail(String email) {
        Optional<ProfileEntity> optional = profileRepository.findByEmailAndVisibleTrue(email);
        if (optional.isEmpty()) {
            throw new AppBadException("Email not exists");
        }
        ProfileEntity entity = optional.get();
        emailHistoryService.isNotExpiredEmail(entity.getEmail());// check for expireation date
        if (!entity.getVisible() || !entity.getStatus().equals(ProfileStatus.REGISTRATION)) {
            throw new AppBadException("Registration not completed");
        }
        emailHistoryService.checkEmailLimit(email);
        sendRegistrationEmail(entity.getId(),email);
        return "To complete your registration please verify your email.";
    }

    public void sendRegistrationPhone(Integer profileId, String phone) {
        // send email
        String url = "http://localhost:8080/auth/verification/" + profileId;
        String text = String.format(RandomUtil.getRandomSmsCode(), url);
        smsSenderService.send(phone, "Complete registration");
        smsHistoryService.crete(phone, text); // create history
    }
    public void sendRegistrationEmail(Integer profileId, String email) {
        // send email
        String url = "http://localhost:8080/auth/verification/" + profileId;

        String text = String.format(RandomUtil.getRandomPassword(), url);
        mailSenderService.send(email, "Complete registration",text);
        emailHistoryService.crete(email,text); // create history
    }

}

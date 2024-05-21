package dasturlash.uz.service.history;

import dasturlash.uz.entity.history.SmsHistoryEntity;
import dasturlash.uz.exp.AppBadException;
import dasturlash.uz.repository.SmsHistoryRepository;
import dasturlash.uz.service.SmsSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SmsHistoryService {
    @Autowired

    private SmsHistoryRepository smsHistoryRepository; ;
    @Autowired
    private SmsSenderService smsService;

    public void crete(String toEmail, String text) {
        SmsHistoryEntity smsHistoryEntity=new SmsHistoryEntity();
        smsHistoryEntity.setPhone(toEmail);
        smsHistoryEntity.setMessage(text);
        smsHistoryRepository.save(smsHistoryEntity);
    }

    public void checkPhoneLimit(String phone) { // 1 minute -3 attempt
        // 23/05/2024 19:01:13
        // 23/05/2024 19:01:23
        // 23/05/2024 19:01:33

        // 23/05/2024 19:00:55 -- (current -1)
        // 23/05/2024 19:01:55 -- current

        LocalDateTime to = LocalDateTime.now();
        LocalDateTime from = to.minusMinutes(2);

        long count = smsHistoryRepository.countByPhoneAndCreatedDateBetween(phone, from, to);
        if (count >= 3) {
            throw new AppBadException("Sms limit reached. Please try after some time");
        }
    }

    public void isNotExpiredPhone(String phone) {
        Optional<SmsHistoryEntity> optional = smsHistoryRepository.findByPhone(phone);
        if (optional.isEmpty()) {
            throw new AppBadException("Phone history not found");
        }
        SmsHistoryEntity entity = optional.get();
        if (entity.getCreatedDate().plusDays(1).isBefore(LocalDateTime.now())) {
            throw new AppBadException("Confirmation time expired");
        }
    }
}

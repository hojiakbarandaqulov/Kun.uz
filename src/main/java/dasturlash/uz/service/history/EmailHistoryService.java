package dasturlash.uz.service.history;

import dasturlash.uz.dto.CategoryDTO;
import dasturlash.uz.dto.history.EmailDTO;
import dasturlash.uz.entity.CategoryEntity;
import dasturlash.uz.entity.history.EmailHistoryEntity;
import dasturlash.uz.exp.AppBadException;
import dasturlash.uz.repository.EmailHistoryRepository;
import dasturlash.uz.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class EmailHistoryService {
    @Autowired
    private EmailHistoryRepository emailHistoryRepository;

    @Autowired
    private SmsService smsService;

    public String crete(String toEmail, String text) {
        EmailHistoryEntity entity = new EmailHistoryEntity();
        if (toEmail.equals(entity.getEmail())) {
            throw new AppBadException("Email is already in use");
        }
        entity.setEmail(toEmail);
        entity.setMessage(text);
        emailHistoryRepository.save(entity);
        return null;
    }

    public void checkEmailLimit(String email) { // 1 minute -3 attempt
        // 23/05/2024 19:01:13
        // 23/05/2024 19:01:23
        // 23/05/2024 19:01:33

        // 23/05/2024 19:00:55 -- (current -1)
        // 23/05/2024 19:01:55 -- current

        LocalDateTime to = LocalDateTime.now();
        LocalDateTime from = to.minusMinutes(2);

        long count = emailHistoryRepository.countByEmailAndCreatedDateBetween(email, from, to);
        if (count >= 3) {
            throw new AppBadException("Sms limit reached. Please try after some time");
        }
    }

    public void isNotExpiredEmail(String email) {
        Optional<EmailHistoryEntity> optional = emailHistoryRepository.findByEmail(email);
        if (optional.isEmpty()) {
            throw new AppBadException("Email history not found");
        }
        EmailHistoryEntity entity = optional.get();
        if (entity.getCreatedDate().plusDays(1).isBefore(LocalDateTime.now())) {
            throw new AppBadException("Confirmation time expired");
        }
    }

    public EmailDTO getByEmail(String email, EmailDTO emailDTO) {
        Optional<EmailHistoryEntity> byEmail = emailHistoryRepository.findByEmail(email);
        if (byEmail.isEmpty()) {
            throw new AppBadException("Email not found");
        }
        EmailHistoryEntity entity = new EmailHistoryEntity();
        entity.setCreatedDate(LocalDateTime.now());
        entity.setEmail(emailDTO.getEmail());
        entity.setMessage(emailDTO.getMessage());
        return emailDTO(entity);
    }

    private EmailDTO emailDTO(EmailHistoryEntity entity) {
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setCreatedDate(entity.getCreatedDate());
        emailDTO.setEmail(entity.getEmail());
        emailDTO.setMessage(entity.getMessage());
        return emailDTO;
    }

    public EmailDTO getByCreatedDate(EmailDTO emailDTO, LocalDateTime createdDate) {
        Optional<EmailHistoryEntity> byEmail = emailHistoryRepository.findByCreatedDate(createdDate);
        if (byEmail.isEmpty()) {
            throw new AppBadException("CreatedDate not found");
        }
        EmailHistoryEntity entity = new EmailHistoryEntity();
        entity.setCreatedDate(LocalDateTime.now());
        entity.setEmail(emailDTO.getEmail());
        entity.setMessage(emailDTO.getMessage());
        return emailDTO(entity);
    }

    public PageImpl<EmailDTO> paginationEmail(int page, int size) {
        Sort sort = Sort.by(Sort.Order.desc("createdDate"));
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<EmailHistoryEntity> all = emailHistoryRepository.findAll(pageable);

        List<EmailDTO> email = new LinkedList<>();
        for (EmailHistoryEntity emailEntity : all.getContent()) {
            email.add(emailDTO(emailEntity));
        }
        Long totalCount = all.getTotalElements();
        return new PageImpl<EmailDTO>(email, pageable, totalCount);
    }
}

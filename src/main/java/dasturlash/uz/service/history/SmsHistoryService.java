package dasturlash.uz.service.history;

import dasturlash.uz.dto.history.SmsDTO;
import dasturlash.uz.dto.history.SmsDTO;
import dasturlash.uz.entity.history.SmsHistoryEntity;
import dasturlash.uz.entity.history.SmsHistoryEntity;
import dasturlash.uz.exp.AppBadException;
import dasturlash.uz.repository.SmsHistoryRepository;
import dasturlash.uz.service.SmsService;
import dasturlash.uz.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class SmsHistoryService {
    private final SmsHistoryRepository smsHistoryRepository;

    public SmsHistoryService(SmsHistoryRepository smsHistoryRepository, SmsService smsService) {
        this.smsHistoryRepository = smsHistoryRepository;
    }

    public String crete(String toPhone, String text) {
        SmsHistoryEntity smsHistoryEntity=new SmsHistoryEntity();
        smsHistoryEntity.setPhone(toPhone);
        smsHistoryEntity.setCode(text);
        smsHistoryRepository.save(smsHistoryEntity);
        return null;
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



    public SmsHistoryEntity getByPhone(String phone,SmsDTO smsDTO) {
        Optional<SmsHistoryEntity> byEmail = smsHistoryRepository.findByPhone(phone);
        if (byEmail.isEmpty()) {
            throw new AppBadException("Phone not found");
        }
        SmsHistoryEntity entity =new SmsHistoryEntity();
        entity.setCreatedDate(LocalDateTime.now());
        entity.setPhone(smsDTO.getPhone());
        entity.setCode(smsDTO.getMessage());
        return entity;
    }

    public SmsHistoryEntity getByCreatedDate(LocalDateTime createdDate, SmsDTO smsDTO) {
        Optional<SmsHistoryEntity> byEmail = smsHistoryRepository.findByCreatedDate(createdDate);
        if (byEmail.isEmpty()) {
            throw new AppBadException("CreatedDate not found");
        }
        SmsHistoryEntity entity =new SmsHistoryEntity();
        entity.setCreatedDate(LocalDateTime.now());
        entity.setPhone(smsDTO.getPhone());
        entity.setCode(smsDTO.getMessage());
        return entity;
    }
    public PageImpl<SmsDTO> paginationPhone(int page, int size) {
        Sort sort=Sort.by(Sort.Order.desc("createdDate"));
        Pageable pageable= PageRequest.of(page,size,sort);
        Page<SmsHistoryEntity> all = smsHistoryRepository.findAll(pageable);
        List<SmsDTO> sms=new LinkedList<>();
        for (SmsHistoryEntity smsEntity : all.getContent()) {
            sms.add(smsToDTO(smsEntity));
        }
        Long totalCount=all.getTotalElements();
        return new PageImpl<SmsDTO>(sms,pageable,totalCount);
    }

    private SmsDTO smsToDTO(SmsHistoryEntity smsEntity) {
        SmsDTO emailDTO = new SmsDTO();
        emailDTO.setCreatedDate(smsEntity.getCreatedDate());
        emailDTO.setMessage(smsEntity.getCode());
        emailDTO.setPhone(smsEntity.getPhone());
        return emailDTO;
    }
}

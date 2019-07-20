package control.sun.serviceImpl;

import control.sun.domain.UserAttachmentRel;
import control.sun.repository.UserAttachmentRelRepository;
import control.sun.service.UserAttachmentRelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户附件实现类
 */
@Service
public class UserAttachmentRelServiceImpl implements UserAttachmentRelService {

    @Resource
    private UserAttachmentRelRepository userAttachmentRelRepository;

    @Override
    public UserAttachmentRel save(UserAttachmentRel userAttachmentRel) {

        return userAttachmentRelRepository.save(userAttachmentRel);

    }

}

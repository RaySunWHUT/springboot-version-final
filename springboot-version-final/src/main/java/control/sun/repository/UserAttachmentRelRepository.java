package control.sun.repository;

import control.sun.domain.UserAttachmentRel;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 用户附件Repository
 */
public interface UserAttachmentRelRepository extends MongoRepository<UserAttachmentRel, String> {


}

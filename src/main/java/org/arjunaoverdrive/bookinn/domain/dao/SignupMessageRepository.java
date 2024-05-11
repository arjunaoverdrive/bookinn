package org.arjunaoverdrive.bookinn.domain.dao;

import org.arjunaoverdrive.bookinn.domain.entities.mongo.UserMongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SignupMessageRepository extends MongoRepository<UserMongoEntity, String> {

}

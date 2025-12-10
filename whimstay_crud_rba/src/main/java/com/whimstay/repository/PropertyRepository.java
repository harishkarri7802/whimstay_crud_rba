package com.whimstay.repository;

import com.whimstay.entity.Property;
import lombok.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends MongoRepository<@NonNull Property,@NonNull String> {
}

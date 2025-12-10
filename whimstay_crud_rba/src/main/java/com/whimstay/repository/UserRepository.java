package com.whimstay.repository;
import com.whimstay.entity.User;
import lombok.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<@NonNull User,@NonNull String> {
    Optional<User> findByEmail(String email);
    @Query("{ 'email' : ?0 }")
    Optional<User> findUserByEmail(String email);
    boolean existsByEmail(String email);
}

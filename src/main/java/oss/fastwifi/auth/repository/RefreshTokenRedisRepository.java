package oss.fastwifi.auth.repository;

import org.springframework.data.repository.CrudRepository;
import oss.jwt.RefreshToken;

public interface RefreshTokenRedisRepository extends CrudRepository<RefreshToken, String> {
    RefreshToken findByUserId(long userId);
}

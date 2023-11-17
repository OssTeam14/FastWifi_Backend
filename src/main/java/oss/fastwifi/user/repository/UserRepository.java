package oss.fastwifi.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import oss.fastwifi.user.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findById(Long id);
    Optional<User> findByUid(String uid);
    boolean existsByUid(String uid);

    boolean existsByNameAndEmail(String name, String email);

    List<User> findAllByNameAndEmail(String name, String email);
}

package oss.fastwifi.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import oss.fastwifi.member.entity.Member;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

    Optional<Member> findById(Long id);
    Optional<Member> findByEmail(String email);
    boolean existsByEmail(String email);

    List<Member> findAllByEmail(String email);
}

package oss.fastwifi.member.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import oss.fastwifi.member.entity.Member;

@Service
@AllArgsConstructor
public class MemberService {
    private MemberRepository userRepository;

    private Member getUser(Long userId){
        return userRepository.findById(userId).orElse(null);
    }
}

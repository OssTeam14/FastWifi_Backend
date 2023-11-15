package oss.fastwifi.user.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import oss.fastwifi.user.entity.User;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    private User getUser(Long userId){
        return userRepository.findById(userId).orElse(null);
    }
}

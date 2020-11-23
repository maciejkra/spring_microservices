package pl.jsystems.micro.serivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jsystems.micro.model.User;
import pl.jsystems.micro.repository.UserRepository;

@Service        // klasa o znaczeniu specjalnym - implementuje logikę biznesową aplikacji
public class UserService {
//    @Autowired                        // wstrzykiwanie zależności przez pole
//    UserRepository userRepository;
    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user){
        return userRepository.save(user);
    }
}

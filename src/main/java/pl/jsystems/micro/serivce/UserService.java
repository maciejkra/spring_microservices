package pl.jsystems.micro.serivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jsystems.micro.model.Role;
import pl.jsystems.micro.model.User;
import pl.jsystems.micro.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service        // klasa o znaczeniu specjalnym - implementuje logikę biznesową aplikacji
public class UserService {
    //    @Autowired                        // wstrzykiwanie zależności przez pole
//    UserRepository userRepository;
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user, Role role) {
        User userToSave = null;
        if (userRepository.findFirstByEmail(user.getEmail()) == null) {
            userToSave = user;
            Set<Role> roles = user.getRoles();      // do aktualnego zbioru ról dodaję role
            roles.add(role);
            userToSave.setRoles(roles);
            userRepository.save(userToSave);
        }
        return userToSave;
    }

    public Optional<User> getUserById(long userId) {
        return userRepository.findById(userId);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean activateUserById(long userId) {
        boolean isActivated = false;
        Optional<User> userOptinal = getUserById(userId);
        if (userOptinal.isPresent()) {
            User userToActivate = userOptinal.get();
            userToActivate.setStatus(true);
            userRepository.save(userToActivate);        // save() wykonana na istniejącym w db obiekcie to realizuje update
            isActivated = true;
        }
        return isActivated;
    }

    public boolean deleteUserById(long userId) {
        boolean isDeleted = false;
        if (getUserById(userId).isPresent()) {
            userRepository.deleteById(userId);
            isDeleted = true;
        }
        return isDeleted;

    }
}

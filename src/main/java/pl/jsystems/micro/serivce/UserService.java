package pl.jsystems.micro.serivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jsystems.micro.model.Role;
import pl.jsystems.micro.model.User;
import pl.jsystems.micro.repository.UserRepository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    public String passwordEncoder(String password)  {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            String output = "";
            for (int i = 0; i < encodedhash.length; i++) {
                output += String.format("%x", encodedhash[i]);
            }
            return output;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }

    public User addUser(User user, Role role) {
        User userToSave = null;
        if (userRepository.findFirstByEmail(user.getEmail()) == null) {
            userToSave = user;
            Set<Role> roles = user.getRoles();      // do aktualnego zbioru ról dodaję role
            roles.add(role);
            userToSave.setRoles(roles);
            userToSave.setPassword(passwordEncoder(userToSave.getPassword())); // szyfrowanie hasła
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

package pl.jsystems.micro.serivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jsystems.micro.model.Role;
import pl.jsystems.micro.repository.RoleRepository;

import java.util.Optional;

@Service
public class RoleService {
    private RoleRepository roleRepository;
    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    // SELECT * FROM roles WHERE role_id = ?
    public Optional<Role> getRolerById(long roleId){
        return roleRepository.findById(roleId);
    }
}

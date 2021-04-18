package id.giyomi.vms.backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import id.giyomi.vms.backend.controller.rest.model.ReqUser;
import id.giyomi.vms.backend.entity.Role;
import id.giyomi.vms.backend.entity.User;
import id.giyomi.vms.backend.repository.RoleRepository;
import id.giyomi.vms.backend.repository.UserRepository;
import id.giyomi.vms.backend.util.JsonLogger;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleService roleService;

    private JsonLogger jsonLogger = new JsonLogger(UserService.class);

    public UserService(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    public User createUser(ReqUser reqUser) {
        Role roleEntity = roleService.getRoleById(reqUser.getRoleId());
        User user = userRepository.save(
                new User(
                        reqUser.getUsername(),
                        PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("pass_ceo"),
                        reqUser.getEmail(),
                        reqUser.getTelepon(),
                        reqUser.getFotoDownloadUrl(),
                        roleEntity
                ));
        return user;
    }

    public User getUser(Long id) throws ResourceNotFoundException {
        User user = userRepository.findById(id).orElse(null);
        if(user==null)
            throw new ResourceNotFoundException("Not found user with id " + id);
        return user;
    }

    public List<User> getUsers(String role) throws ResourceNotFoundException {
        List<User> users;
        if(role!=null){
            Role roleStaff = roleService.getRoleByNama(role);
            users = userRepository.findAllByRoleOrderByCreatedAt(roleStaff);
        }else{
            users = new ArrayList<>(userRepository.findAll());
        }
        return users;
    }
    public boolean isStaff(User user){
        return user.getRole().getNama().compareTo("staff")!=0;
    }

    public List<User> getUsers() {
        return getUsers(null);
    }

    public User editUser(ReqUser reqUser, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("Not found user with id " + userId));
        if(reqUser.getEmail()!=null)
            user.setEmail(reqUser.getEmail());
        if(reqUser.getFotoDownloadUrl()!=null)
            user.setFotoDownloadUrl(reqUser.getFotoDownloadUrl());
        if(reqUser.getTelepon()!=null)
            user.setTelepon(reqUser.getTelepon());
        if(reqUser.getUsername()!=null)
            user.setUsername(reqUser.getUsername());
        if(reqUser.getPassword()!=null)
            user.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(reqUser.getPassword()));
        if(reqUser.getRoleId()!=null){
            Role role = roleService.getRoleById(reqUser.getRoleId());
            user.setRole(role);
        }
        return userRepository.save(user);
    }

    public String deleteUser(Long userId){
        userRepository.deleteById(userId);
        return "User with id " + userId + " deleted successfully";
    }
}

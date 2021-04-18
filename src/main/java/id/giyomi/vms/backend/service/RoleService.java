package id.giyomi.vms.backend.service;

import id.giyomi.vms.backend.entity.Role;
import id.giyomi.vms.backend.repository.RoleRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleByNama(String roleNama) throws ResourceNotFoundException {
        Set<Role> roles = roleRepository.findAllByNama(roleNama);
        if(roles.size()<1)
            throw new ResourceNotFoundException("Not found role with nama " + roleNama);
        Iterator<Role> iter = roles.iterator();
        return iter.next();
    }

    public Role getRoleById(Long roleId){
        Role role = roleRepository.findById(roleId)
                .orElseThrow(()-> new ResourceNotFoundException("Not found role with id " + roleId));
        return role;
    }

    public boolean isExistByNama(String roleNama){
        return roleRepository.findAllByNama(roleNama).size()>0;
    }
}

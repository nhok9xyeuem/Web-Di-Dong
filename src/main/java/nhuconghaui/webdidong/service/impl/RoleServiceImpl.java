package nhuconghaui.webdidong.service.impl;

import nhuconghaui.webdidong.model.Role;
import nhuconghaui.webdidong.repository.RoleRepository;
import nhuconghaui.webdidong.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}

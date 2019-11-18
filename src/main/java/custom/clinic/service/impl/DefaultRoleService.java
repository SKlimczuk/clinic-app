package custom.clinic.service.impl;

import custom.clinic.dao.RoleDao;
import custom.clinic.model.Role;
import custom.clinic.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DefaultRoleService implements RoleService {

    @Resource
    private RoleDao roleDao;

    @Override
    public Role findByName(String name) {
        return roleDao.findByName(name);
    }
}

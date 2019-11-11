package custom.clinic.service;

import custom.clinic.model.Role;

public interface RoleService {
    Role findByName(String name);
}

package org.beny.ama.service;

import org.beny.ama.model.Role;
import org.beny.ama.repository.RoleRepository;
import org.beny.ama.util.AmaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends BaseService<Role, RoleRepository> {

    @Autowired
    public RoleService(RoleRepository repository) {
        super(repository);
    }

    public Role findByRole(Role.Roles role) throws AmaException {
        return getRepository().findByRole(role).orElseThrow(() -> new AmaException(AmaException.AmaErrors.ROLE_NOT_EXISTS));
    }

}

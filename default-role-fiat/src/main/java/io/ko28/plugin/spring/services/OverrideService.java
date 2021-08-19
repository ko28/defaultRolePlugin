package io.ko28.plugin.spring.services;

import com.netflix.spinnaker.fiat.model.resources.Role;
import com.netflix.spinnaker.fiat.roles.file.FileBasedUserRolesProvider;
import com.netflix.spinnaker.fiat.permissions.ExternalUser;

import java.util.*;

public class OverrideService extends FileBasedUserRolesProvider {

    @Override
    public List<Role> loadRoles(ExternalUser user) {
        List<Role> roles = super.loadRoles(user);
        if (roles.isEmpty()) {
            roles = Collections.singletonList(new Role("public"));
        } else {
            roles.add(new Role("public"));
        }
        return roles;
    }

}

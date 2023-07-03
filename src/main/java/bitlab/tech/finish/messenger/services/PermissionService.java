package bitlab.tech.finish.messenger.services;

import bitlab.tech.finish.messenger.models.Permission;
import bitlab.tech.finish.messenger.repositories.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {

    @Autowired
    PermissionRepository permissionRepository;

        public boolean hasPermission(String permission, String userPermissions){
            String[] permissions = userPermissions.split(",");
            for (String p:permissions){
                if (p.equals(permission)){
                    return true;
                }
            }
            return false;
        }

        public Permission findPermission(String permission){
            return permissionRepository.findByRole(permission);
        }
        public Permission userRolePermission(){
            return permissionRepository.findByRole("USER_ROLE");
        }


}

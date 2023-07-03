package bitlab.tech.finish.messenger.repositories;

import bitlab.tech.finish.messenger.models.Permission;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Permission findByRole(String permissionRoleName);


}

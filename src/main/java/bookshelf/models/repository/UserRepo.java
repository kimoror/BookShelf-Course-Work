package bookshelf.models.repository;

import bookshelf.models.entities.User;
import bookshelf.models.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Repository for {@link User}
 */
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    /**
     * @param userId - id of the user whose status we want to change
     * @param role - new user role
     */
    @Modifying
    @Transactional
    @Query("UPDATE User SET role = :role where id = :userId")
    public void changeUserRole(long userId, Role role);
}

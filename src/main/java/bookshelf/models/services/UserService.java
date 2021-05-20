package bookshelf.models.services;

import bookshelf.aspect.Loggable;
import bookshelf.exceptions.EntityNotFoundException;
import bookshelf.models.dto.DtoConverter;
import bookshelf.models.dto.UserDto;
import bookshelf.models.entities.User;
import bookshelf.models.enums.Role;
import bookshelf.models.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class UserService {
    final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * Find all users
     *
     * @return users
     */
    @Transactional
    @Loggable
    public List<User> findAll(){
        return userRepo.findAll();
    }

    @Transactional
    @Loggable
    public User findByEmail(String email){
        return userRepo.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("user"));
    }

    @Transactional
    @Loggable
    public void changeUserRole(long userId, Role role){
        userRepo.changeUserRole(userId, role);
    }
}

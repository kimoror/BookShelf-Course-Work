package bookshelf.models.services;

import bookshelf.models.entities.User;
import bookshelf.models.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    /**
     *
     * @param user entity to save
     */
    public void save(User user){
        userRepo.save(user);
    }

    /**
     * Find all users
     *
     * @return users
     */
    public List<User> findAll(){
        return userRepo.findAll();
    }
}

package bookshelf.models.services;

import bookshelf.exceptions.EntityNotFoundException;
import bookshelf.models.dto.DtoConverter;
import bookshelf.models.dto.UserDto;
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
     * @param userDto dto to save
     */
    public void save(UserDto userDto){
        userRepo.save(DtoConverter.dtoToUser(userDto));
    }

    /**
     * Find all users
     *
     * @return users
     */
    public List<User> findAll(){
        return userRepo.findAll();
    }

    public User findByEmail(String email){
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(String.format("User with email '%s' doesn't exist", email)));
    }

    public Long findUserId(String email){
        return  findByEmail(email).getId();
    }
}

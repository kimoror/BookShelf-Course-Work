package bookshelf.models.services;

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
    public List<UserDto> findAll(){
        return DtoConverter.userListToDtos(userRepo.findAll());
    }
}

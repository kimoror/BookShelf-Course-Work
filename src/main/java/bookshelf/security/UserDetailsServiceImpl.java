package bookshelf.security;


import bookshelf.aspect.Loggable;
import bookshelf.exceptions.EntityNotFoundException;
import bookshelf.models.entities.User;
import bookshelf.models.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("userDetailsServiceImpl")
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepo userRepo;

    @Autowired
    public UserDetailsServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * @param email - email of user
     * @return Security user if we found him in database
     * @throws UsernameNotFoundException
     */
    @Override
    @Transactional
    @Loggable
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));
        return SecurityUser.fromUser(user);
    }

    /**
     * Add user to database if he exist
     * @param user - new user
     * @throws EntityNotFoundException
     */
    @Transactional
    @Loggable
    public void addNewUser(User user) throws EntityNotFoundException {
        if(userExists(user.getEmail())){
            throw new EntityNotFoundException(String.format("User with '%s' email already exist", user.getEmail()));
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepo.save(user);
    }

    /**
     *
     * @param userEmail
     * @return true if user with userEmail exists in database
     */
    @Transactional
    @Loggable
    public boolean userExists(String userEmail){
        return userRepo.findByEmail(userEmail).isPresent();
    }
}

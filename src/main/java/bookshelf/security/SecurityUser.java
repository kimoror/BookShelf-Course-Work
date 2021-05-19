package bookshelf.security;

import bookshelf.models.enums.Role;
import bookshelf.models.enums.UserStatus;
import bookshelf.models.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Data
@AllArgsConstructor
public class SecurityUser implements UserDetails {

    private final String username;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final Role role;
    private final boolean isActive;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Role getRole(){
        return role;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    /**
     * Convert User type to UserDetails
     * If the user's status is banned, he will not be able to authenticate
     * @param user model class
     * @return converted UserDetails
     */
    public static UserDetails fromUser(User user){
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getUserStatus().equals(UserStatus.ACTIVE),
                user.getUserStatus().equals(UserStatus.ACTIVE),
                user.getUserStatus().equals(UserStatus.ACTIVE),
                user.getUserStatus().equals(UserStatus.ACTIVE),
                user.getRole().getAuthorities()
        );
    }
}

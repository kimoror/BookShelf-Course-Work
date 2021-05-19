package bookshelf.models.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(Set.of(Permission.SHOPPING_CART_VIEW, Permission.SHOPPING_CART_BUY)),
    ADMIN(Set.of(Permission.SHOPPING_CART_VIEW, Permission.SHOPPING_CART_BUY,
            Permission.CHANGE_ORDER_STATUS,
            Permission.SEND_MESSAGE, Permission.CHANGE_USER_ROLE));

    private final Set<Permission> permissions;

    /**
     * Constructor for enum
     * @param permissions - set of permission, owned by role
     */
    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions(){
        return permissions;
    }

    /**
     * Convert permission class to SimpleGrantedAuthority class
     * @return Set<SimpleGrantedAuthority> - Transformed set
     */
    public Set<SimpleGrantedAuthority> getAuthorities(){
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}

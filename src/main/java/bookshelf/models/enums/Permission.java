package bookshelf.models.enums;

import lombok.Getter;

@Getter
public enum Permission {
    READ("read"),
    WRITE("write");

    private final String permission;

    /**
     * Constructor for Permission enum
     * @param permission - permission of role
     */
    Permission(String permission){this.permission = permission;}
}

package bookshelf.models.enums;

import lombok.Getter;

@Getter
public enum Permission {
    SHOPPING_CART_VIEW("SHOPPING_CART_VIEW"),
    SHOPPING_CART_BUY("SHOPPING_CART_BUY"),
    ADD_PRODUCT("add_product"),
    DELETE_PRODUCT("delete_product"),
    CHANGE_PRODUCT("change_product");

    private final String permission;

    /**
     * Constructor for Permission enum
     * @param permission - permission of role
     */
    Permission(String permission){this.permission = permission;}
}

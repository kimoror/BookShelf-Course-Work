package bookshelf.models.enums;

import lombok.Getter;

@Getter
public enum Permission {
    SHOPPING_CART_VIEW("shopping_cart_buy"),
    SHOPPING_CART_BUY("shopping_cart_buy"),
    SEND_MESSAGE("send_message"),
    CHANGE_USER_ROLE("change_user_role"),
    CHANGE_ORDER_STATUS("change_order_status");

    private final String permission;

    /**
     * Constructor for Permission enum
     * @param permission - permission of role
     */
    Permission(String permission){this.permission = permission;}
}

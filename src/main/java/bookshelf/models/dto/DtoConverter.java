package bookshelf.models.dto;


import bookshelf.models.entities.*;

import java.util.List;
import java.util.stream.Collectors;

public  class DtoConverter {

    public static MakerDto makerToDto(Maker maker){
        return new MakerDto(maker);
    }

    public static List<MakerDto> makerListToDto(List<Maker> makers){
        return makers.stream()
                .map(DtoConverter::makerToDto)
                .collect(Collectors.toList());
    }

    public static Maker dtoToMaker(MakerDto makerDto){
        Maker maker = new Maker();
        maker.setName(makerDto.getName());
        return maker;
    }



    public static OrderDto orderToDto(Order order){
        return new OrderDto(order);
    }
    public static List<OrderDto> orderListToDtos(List<Order> orders){
        return orders.stream()
                .map(DtoConverter::orderToDto)
                .collect(Collectors.toList());
    }

    public static Order dtoToOrder(OrderDto orderDto){
        Order order = new Order();
        order.setUser(DtoConverter.dtoToUser(orderDto.getUser()));
        order.setOrders_time(orderDto.getOrders_time());
        return order;
    }



    public static UserDto userToDto(User user){
        return new UserDto(user);
    }

    public static List<UserDto> userListToDtos(List<User> users){
        return users.stream()
                .map(DtoConverter::userToDto)
                .collect(Collectors.toList());
    }

    public static User dtoToUser(UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setAge(userDto.getAge());
        user.setPhone_number(userDto.getPhone_number());
        user.setAddress(userDto.getAddress());
        user.setHave_account(userDto.isHave_account());
        return user;
    }



    public static Product_type dtoToProduct_type(Product_typeDto product_typeDto){
        Product_type product_type = new Product_type();
        product_type.setName(product_typeDto.getName());
        return product_type;
    }

    public static Product_typeDto product_typeToDto(Product_type product_type){
        return new Product_typeDto(product_type);
    }
    public static List<Product_typeDto> product_typeListToDtos(List<Product_type> users){
        return users.stream()
                .map(DtoConverter::product_typeToDto)
                .collect(Collectors.toList());
    }



    public static ProductDto productToDto(Product product){
        return new ProductDto(product);
    }

    public static List<ProductDto> productListToDtos(List<Product> products){
        return products.stream()
                .map(DtoConverter :: productToDto)
                .collect(Collectors.toList());
    }

    public static Product dtoToProduct(ProductDto productDto){
        Product product = new Product();
        product.setName(productDto.getName());
        product.setCost(productDto.getCost());
        product.setImg_path(productDto.getImg_path());
        product.setDescription(productDto.getDescription());
        product.setShort_description(productDto.getShort_description());
        product.setMaker(DtoConverter.dtoToMaker(productDto.getMaker()));
        product.setProduct_type(DtoConverter.dtoToProduct_type(productDto.getProduct_type()));
        return product;
    }


    public static ProductInOrderDto productInOrderToDto(ProductInOrder productInOrder){
        return new ProductInOrderDto(productInOrder);
    }

    public static List<ProductInOrderDto> productInOrderListToDto(List<ProductInOrder> productInOrders){
        return productInOrders.stream()
                .map(DtoConverter :: productInOrderToDto)
                .collect(Collectors.toList());
    }

    public static ProductInOrder dtoToProductInOrder(ProductInOrderDto productInOrderDto){
        ProductInOrder productInOrder = new ProductInOrder();
        productInOrder.setOrder(DtoConverter.dtoToOrder(productInOrderDto.getOrder()));
        productInOrder.setProduct(DtoConverter.dtoToProduct(productInOrderDto.getProduct()));
        productInOrder.setNum_of_product(productInOrderDto.getNum_of_product());
        return productInOrder;
    }

}

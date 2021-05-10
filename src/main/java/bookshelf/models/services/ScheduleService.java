package bookshelf.models.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

@Slf4j
@Service
public class ScheduleService {
    private final String path = "DBBackup";

    private final MakerService makerService;
    private final OrderService orderService;
    private final Product_typeService product_typeService;
    private final ProductService productService;
    private final ProductInOrderService productInOrderService;
    private final UserService userService;

    public ScheduleService(MakerService makerService, OrderService orderService,
                           Product_typeService product_typeService, ProductService productService,
                           ProductInOrderService productInOrderService, UserService userService) {
        this.makerService = makerService;
        this.orderService = orderService;
        this.product_typeService = product_typeService;
        this.productService = productService;
        this.productInOrderService = productInOrderService;
        this.userService = userService;
    }

    private BufferedWriter createWriter(String dir, String filename) throws IOException{
        return new BufferedWriter(new FileWriter(String.format("%s/%s", dir, filename)));
    }


    //TODO - изменить время
    @Scheduled(cron = "0 0/30 * * * *")
    public void start() throws IOException, NullPointerException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        log.info("SCHEDULED task started at {}", formatter.format(date));

        File dir = ResourceUtils.getFile(path);
        Arrays.stream(Objects.requireNonNull(dir.listFiles())).forEach(file -> {
            if (file.isFile()) {
                log.info("File " + file.getName() + " was deleted: " + file.delete());
            }
        });

        BufferedWriter makersBufWriter = createWriter(path, "maker.txt");
        makersBufWriter.write("id|name\n");
        makerService.findAll().forEach(maker -> {
            try{
                makersBufWriter.write(
                        String.format(
                                "%d|%s\n",
                                maker.getId(),
                                maker.getName()
                        ));
            }catch (IOException e){
                e.printStackTrace();
            }
        });
        makersBufWriter.close();

        BufferedWriter ordersBufWriter = createWriter(path, "orders.txt");
        ordersBufWriter.write("id|user_id|order_time|status\n");
        orderService.findAll().forEach(order -> {
            try{
                ordersBufWriter.write(
                        String.format(
                                "%d|%d|%s|%s\n",
                                order.getId(),
                                order.getUser().getId(),
                                order.getOrder_time(),
                                order.getOrderStatus()
                        ));
            }catch (IOException e){
                e.printStackTrace();
            }
        });


        ordersBufWriter.close();

        BufferedWriter product_typesBufWriter = createWriter(path, "product_types.txt");
        product_typesBufWriter.write("id|name\n");
        product_typeService.findAll().forEach(product_type -> {
            try{
                product_typesBufWriter.write(
                        String.format(
                                "%d|%s\n",
                                product_type.getId(),
                                product_type.getName()
                        ));
            }catch (IOException e){
                e.printStackTrace();
            }
        });
        product_typesBufWriter.close();

        BufferedWriter productsBufWriter = createWriter(path, "products.txt");
        productsBufWriter.write("id|name|cost|img_path|description|maker_id|product_type|short_description\n");
        productService.findAll().forEach(product -> {
            try{
                productsBufWriter.write(
                        String.format(
                                "%d|%s|%d|%s|%s|%d|%s|%s\n",
                                product.getId(),
                                product.getName(),
                                product.getCost(),
                                product.getImg_path(),
                                product.getDescription(),
                                product.getMaker().getId(),
                                product.getProduct_type().getName(),
                                product.getShort_description()
                        ));
            }catch (IOException e){
                e.printStackTrace();
            }
        });
        productsBufWriter.close();

        BufferedWriter productInOrderBufWriter = createWriter(path, "productInOrder.txt");
        productInOrderBufWriter.write("id|order_id|product_id|num_of_product\n");
        productInOrderService.findAll().forEach(productInOrder -> {
            try{
                productInOrderBufWriter.write(
                        String.format(
                                "%d|%d|%d|%d\n",
                                productInOrder.getId(),
                                productInOrder.getOrder_id(),
                                productInOrder.getProduct_id(),
                                productInOrder.getNum_of_product()
                        ));
            }catch (IOException e){
                e.printStackTrace();
            }
        });
        productInOrderBufWriter.close();

        BufferedWriter usersBufWriter = createWriter(path, "users.txt");
        usersBufWriter.write("id|name|age|phone_number|address|email|password|role|status\n");
        userService.findAll().forEach(user -> {
            try{
                usersBufWriter.write(
                        String.format(
                                "%d|%s|%d|%s|%s|%s|%s|%s|%s\n",
                                user.getId(),
                                user.getName(),
                                user.getAge(),
                                user.getPhone_number(),
                                user.getAddress(),
                                user.getEmail(),
                                user.getPassword(),
                                user.getRole(),
                                user.getUserStatus()
                        ));
            }catch (IOException e){
                e.printStackTrace();
            }
        });
        usersBufWriter.close();

        log.info("SCHEDULED task finished.");
    }
}

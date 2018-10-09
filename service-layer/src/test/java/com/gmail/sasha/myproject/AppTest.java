package com.gmail.sasha.myproject;


//import com.gmail.sasha.myproject.config.AppConfig;
import com.gmail.sasha.myproject.service.model.*;
import com.gmail.sasha.myproject.service.service.*;
import com.gmail.sasha.myproject.service.service.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;


@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {HibernateConfig.class, AppConfig.class}, loader = AnnotationConfigContextLoader.class)
public class AppTest {

    private static final Logger logger = LogManager.getLogger(AppTest.class);
    private String[] arrayDescription = {"уникальное описание 1",
            "описание 2",
            "описание 3",
            "описание 4",
            "описание 5",
            "описание 6",
            "описание 7",
            "описание 8",
            "описание 9",
            "описание 10",

    };
    private List<String> list = new ArrayList<>(Arrays.asList(arrayDescription));

    private static String getRandomValue(Set<String> someSet) {
        int rd = new Random().nextInt(someSet.size());
        int index = 0;
        for (String element : someSet) {
            if (index == rd) {
                return element;
            }
            index++;
        }
        return null;

    }

    @Autowired
    ItemService itemService;

    @Test
    public void createItems() {
        //ItemService itemService = new ItemServiceImpl();

        List<ItemDTO> itemDTOList = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            itemDTOList.add(generateItems());

        }
        List<ItemDTO> receivedItems = itemService.save(itemDTOList);

        System.out.println(receivedItems);


    }

    @Test
    public void createDiscount() {


        DiscountDTO discountDTO1 = new DiscountDTO();
        discountDTO1.setExpirationDate(LocalDateTime.now().plusDays(3));
        discountDTO1.setInterestRate(new BigDecimal(30));
        discountDTO1.setName("discount 30%");
        DiscountDTO discountDTO2 = new DiscountDTO();
        discountDTO2.setExpirationDate(LocalDateTime.now().plusDays(5));
        discountDTO2.setInterestRate(new BigDecimal(10));
        discountDTO2.setName("discount 10%");
        DiscountDTO discountDTO3 = new DiscountDTO();
        discountDTO3.setExpirationDate(LocalDateTime.now().plusDays(7));
        discountDTO3.setInterestRate(new BigDecimal(20));
        discountDTO3.setName("discount 20%");

        List<DiscountDTO> discounts = new ArrayList<>();
        discounts.add(discountDTO1);
        discounts.add(discountDTO3);
        discounts.add(discountDTO2);

        DiscountService discountService = new DiscountServiceImpl();
        discountService.save(discounts);


    }

    @Test
    public void assignDiscountsToItems() {
        ItemDiscountService itemDiscountService = new ItemDiscountServiceImpl();
        itemDiscountService.assignToRangeOfItemsCorrespondingDiscounts(200, 299, new BigDecimal(10));
        itemDiscountService.assignToRangeOfItemsCorrespondingDiscounts(300, 399, new BigDecimal(20));
        itemDiscountService.assignToRangeOfItemsCorrespondingDiscounts(400, 500, new BigDecimal(30));

    }

    @Test
    public void showListItemWithDiscount() {
        ItemDiscountService itemDiscountService = new ItemDiscountServiceImpl();
        System.out.println("===========");
        for (ItemWithDiscountedPrice element : itemDiscountService.showItemsWithDiscountedPrice()) {
            System.out.println(element);
        }
        System.out.println("===========");
    }

    @Test
    public void createUser() {
        UserService userService = new UserServiceImpl();
        UserDTO user = new UserDTO();
        user.setEmail("some_email@tut.by");
        user.setSurname("some_surname");
        user.setName("some_name");
        user.setPassword("1234password");
        userService.save(user);
    }

    @Test
    public void assignToUserItsDiscount() {
        UserService us = new UserServiceImpl();
        us.assignDiscountToUser();
    }
    @Test
    public void createFourOrders(){
        OrderService orderService = new OrderServiceImpl();
        orderService.createFourOrders();
    }


    @Test
    public void showInfoAboutOrders(){
        OrderService orderService = new OrderServiceImpl();
        List<OrderDTO>  orderDTOS = orderService.getOrdersInfo(1, 15);

    }

    @Autowired
    UserService userService;
    @Test
    void testRetrieveUsers(){
        System.out.println( userService.getUsers());
    }
    /**
     * Rigorous Test :-)
     */


    private ItemDTO generateItems() {

        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setUniqueNumber(generateUniqNumber());
        itemDTO.setName(getRandomValue(generateRandomName()));
        itemDTO.setDescription(generateRandomDescription());
        itemDTO.setPrice(getRandomPrice());
        return itemDTO;

        /*  */
    }

    @Test
    public void getItems() {
        ItemService itemService = new ItemServiceImpl();
        itemService.getAllItems();
    }

    private Set<String> generateRandomName() {

        Set<String> names = new HashSet<>();
        try (Stream<String> stream = Files.lines(Paths.get("items"))) {
            stream.forEach(names::add);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return names;


    }

    private String generateUniqNumber() {
        return UUID.randomUUID().toString();
    }

    private String generateRandomDescription() {

        int randindex = new Random().nextInt(list.size());
        //list.remove(randindex);
        return list.get(randindex);

    }

    private BigDecimal getRandomPrice() {
        String range = String.valueOf(new Random().nextInt(500 - 100) + 100);
        return new BigDecimal(range + ".0");

    }


}

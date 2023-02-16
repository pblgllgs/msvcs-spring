package com.pblgllgs.bookingmicroservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.pblgllgs.bookingmicroservice.client.StockClient;
import com.pblgllgs.bookingmicroservice.dto.OrderDTO;
import com.pblgllgs.bookingmicroservice.entity.Order;
import com.pblgllgs.bookingmicroservice.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/booking")
@AllArgsConstructor
public class BookingController {

    public final OrderRepository orderRepository;

    private final StockClient stockClient;

    @PostMapping("/order")
    @ResponseStatus(HttpStatus.CREATED)
    @HystrixCommand(fallbackMethod = "fallbackToStockService")
    public String saveOrder(@RequestBody OrderDTO orderDTO){
        boolean inStock = orderDTO.getOrderItems().stream()
                .allMatch(orderItem -> stockClient.stockAvailable(orderItem.getCode()));
        if(inStock){
            Order order = new Order();
            order.setOrderNo(UUID.randomUUID().toString());
            order.setOrderItems(orderDTO.getOrderItems());
            orderRepository.save(order);
            return "Order saved";
        }
        return "Order cannot be saved";
    }

    private String fallbackToStockService(){
        return "Something went wrong, please lee logs";
    }
}

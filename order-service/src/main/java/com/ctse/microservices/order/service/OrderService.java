package com.ctse.microservices.order.service;

import com.ctse.microservices.order.client.InventoryClient;
import com.ctse.microservices.order.event.OrderPlacedEvent;
import com.ctse.microservices.order.model.Order;
import com.ctse.microservices.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final InventoryClient inventoryClient;

    private final KafkaTemplate<String,OrderPlacedEvent> kafkaTemplate;

    public OrderService(OrderRepository orderRepository,
                        InventoryClient inventoryClient,
                        KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate) {
        this.orderRepository = orderRepository;
        this.inventoryClient = inventoryClient;
        this.kafkaTemplate = kafkaTemplate;
    }

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    public Order placeOrder(Order order) {

        var isProductInStock = inventoryClient.inInStock(order.getSkuCode(), order.getQuantity());

        if (isProductInStock) {
            order.setOrderNumber(UUID.randomUUID().toString());
            Order savedOrder = orderRepository.save(order);

            OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent(order.getOrderNumber() , order.getUserDetails().getEmail());
            log.info("Start - Sending Order Placed Event: {} to kafka topic order-placed" , orderPlacedEvent);
            kafkaTemplate.send("order-placed", orderPlacedEvent);
            log.info("End - Sending Order Placed Event: {} to kafka topic order-placed" , orderPlacedEvent);

            return savedOrder;

        }else {
            throw  new RuntimeException("Product with SkuCode "+order.getSkuCode()+" is not in stock");
        }

    }
}

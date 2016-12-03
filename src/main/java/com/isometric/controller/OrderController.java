package com.isometric.controller;

import com.isometric.entity.Bid;
import com.isometric.entity.ID;
import com.isometric.entity.Order;
import com.isometric.repository.BidRepository;
import com.isometric.repository.IDRepository;
import com.isometric.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping(value = "/isometric")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;
    private Order order;
    private List<Order> orderList;

    @Autowired
    private IDRepository idRepository;
    private ID id;

    @Autowired
    private BidRepository bidRepository;
    private Bid bid;

    private BigInteger getOrderId() {
        BigInteger orderId;
        id = idRepository.findOne("key");
        orderId = id.getOrderId();
        id.setOrderId(orderId.add(BigInteger.valueOf(1)));
        idRepository.save(id);
        return orderId;
    }

    @CrossOrigin(origins = "http://localhost:9090")
    @RequestMapping(value = "/{userId}/order/{bidId}", method = RequestMethod.POST)
    public void createOrder(@PathVariable(value = "userId") BigInteger userId, @PathVariable(value = "bidId") BigInteger bidId) {
        bid = bidRepository.findOne(bidId);
        orderRepository.save(new Order(getOrderId(), userId, bid.getPostId(), bidId, 1, bid.getBidAmount(), false));
    }

    @CrossOrigin(origins = "http://localhost:9090")
    @RequestMapping(value = "/order/remove/{orderId}", method = RequestMethod.POST)
    public void removeOrder(@PathVariable(value = "orderId") BigInteger orderId) {
        orderRepository.delete(orderId);
    }

    @CrossOrigin(origins = "http://localhost:9090")
    @RequestMapping(value = "/cart/{userId}", method = RequestMethod.GET)
    public List<Order> getCart(@PathVariable(value = "userId") BigInteger userId) {
        orderList = orderRepository.findByUserIdAndIsCheckedOutIsFalse(userId);
        return orderList;
    }

    @CrossOrigin(origins = "http://localhost:9090")
    @RequestMapping(value = "/orders/{userId}", method = RequestMethod.GET)
    public List<Order> getOrders(@PathVariable(value = "userId") BigInteger userId) {
        orderList = orderRepository.findByUserIdAndIsCheckedOutIsTrue(userId);
        return orderList;
    }

    @CrossOrigin(origins = "http://localhost:9090")
    @RequestMapping(value = "/checkout/{userId}", method = RequestMethod.POST)
    public void checkout(@PathVariable(value = "userId") BigInteger userId) {
        orderList = orderRepository.findByUserIdAndIsCheckedOutIsFalse(userId);
        for (Order o : orderList) {
            o.setCheckedOut(true);
            orderRepository.save(o);
        }
    }

    @CrossOrigin(origins = "http://localhost:9090")
    @RequestMapping(value = "/order/save/{orderId}", method = RequestMethod.POST)
    public void saveOrder(@PathVariable(value = "orderId") BigInteger orderId, @RequestParam(value = "quantity") int quantity, @RequestParam(value = "amount") double amount) {
        order = orderRepository.findOne(orderId);
        order.setQuantity(quantity);
        order.setAmount(amount);
        orderRepository.save(order);
    }
}

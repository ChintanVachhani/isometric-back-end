package com.isometric.controller;

import com.google.common.collect.Lists;
import com.isometric.GlobalConstants;
import com.isometric.entity.Bid;
import com.isometric.entity.ID;
import com.isometric.entity.Order;
import com.isometric.entity.User;
import com.isometric.repository.BidRepository;
import com.isometric.repository.IDRepository;
import com.isometric.repository.OrderRepository;
import com.isometric.repository.UserRepository;
import it.ozimov.springboot.templating.mail.model.Email;
import it.ozimov.springboot.templating.mail.model.impl.EmailImpl;
import it.ozimov.springboot.templating.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.math.BigInteger;
import java.nio.charset.Charset;
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

    @Autowired
    private UserRepository userRepository;
    private User user;

    @Autowired
    EmailService emailService;


    private BigInteger getOrderId() {
        BigInteger orderId;
        id = idRepository.findOne("key");
        orderId = id.getOrderId();
        id.setOrderId(orderId.add(BigInteger.valueOf(1)));
        idRepository.save(id);
        return orderId;
    }

    @CrossOrigin(origins = GlobalConstants.origin)
    @RequestMapping(value = "/{userId}/order/{bidId}", method = RequestMethod.POST)
    public void createOrder(@PathVariable(value = "userId") BigInteger userId, @PathVariable(value = "bidId") BigInteger bidId) {
        bid = bidRepository.findOne(bidId);
        orderRepository.save(new Order(getOrderId(), userId, bid.getPostId(), bidId, 1, bid.getBidAmount(), false));
    }

    @CrossOrigin(origins = GlobalConstants.origin)
    @RequestMapping(value = "/order/remove/{orderId}", method = RequestMethod.POST)
    public void removeOrder(@PathVariable(value = "orderId") BigInteger orderId) {
        orderRepository.delete(orderId);
    }

    @CrossOrigin(origins = GlobalConstants.origin)
    @RequestMapping(value = "/cart/{userId}", method = RequestMethod.GET)
    public List<Order> getCart(@PathVariable(value = "userId") BigInteger userId) {
        orderList = orderRepository.findByUserIdAndIsCheckedOutIsFalse(userId);
        return orderList;
    }

    @CrossOrigin(origins = GlobalConstants.origin)
    @RequestMapping(value = "/orders/{userId}", method = RequestMethod.GET)
    public List<Order> getOrders(@PathVariable(value = "userId") BigInteger userId) {
        orderList = orderRepository.findByUserIdAndIsCheckedOutIsTrue(userId);
        return orderList;
    }

    @CrossOrigin(origins = GlobalConstants.origin)
    @RequestMapping(value = "/checkout/{userId}", method = RequestMethod.POST)
    public void checkout(@PathVariable(value = "userId") BigInteger userId) {
        orderList = orderRepository.findByUserIdAndIsCheckedOutIsFalse(userId);
        for (Order o : orderList) {

            User purchaser = userRepository.findOne(o.getUserId());
            User bidder = userRepository.findOne(bidRepository.findOne(o.getBidId()).getUserId());

            try {
                final Email emailToPurchaser = EmailImpl.builder()
                        .from(new InternetAddress("isometric.thevoidsquad@gmail.com"))
                        .to(Lists.newArrayList(new InternetAddress(purchaser.getEmail())))
                        .subject("Order Confirmation")
                        .body("You purchased an order with ID: " + o.getOrderId() + " from " + bidder.getEmail())
                        .encoding(Charset.forName("UTF-8")).build();
                emailService.send(emailToPurchaser);
                final Email emailToBidder = EmailImpl.builder()
                        .from(new InternetAddress("isometric.thevoidsquad@gmail.com"))
                        .to(Lists.newArrayList(new InternetAddress(bidder.getEmail())))
                        .subject("Order Confirmation")
                        .body("You received an order with ID: " + o.getOrderId() + " from " + purchaser.getEmail() + " on your bid with ID: " + o.getBidId())
                        .encoding(Charset.forName("UTF-8")).build();
                emailService.send(emailToBidder);
            } catch (AddressException e) {
                e.printStackTrace();
            }

            o.setCheckedOut(true);
            orderRepository.save(o);
        }
    }

    @CrossOrigin(origins = GlobalConstants.origin)
    @RequestMapping(value = "/order/save/{orderId}", method = RequestMethod.POST)
    public void saveOrder(@PathVariable(value = "orderId") BigInteger orderId, @RequestParam(value = "quantity") int quantity, @RequestParam(value = "amount") double amount) {
        order = orderRepository.findOne(orderId);
        order.setQuantity(quantity);
        order.setAmount(amount);
        orderRepository.save(order);
    }

}

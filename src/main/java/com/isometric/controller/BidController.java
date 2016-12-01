package com.isometric.controller;

import com.isometric.entity.Bid;
import com.isometric.entity.ID;
import com.isometric.repository.BidRepository;
import com.isometric.repository.IDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping(value = "/isometric")
public class BidController {

    @Autowired
    private BidRepository bidRepository;
    private Bid bid;
    private List<Bid> bidList;

    @Autowired
    private IDRepository idRepository;
    private ID id;

    private BigInteger getBidId() {
        BigInteger bidId;
        id = idRepository.findOne("key");
        bidId = id.getBidId();
        id.setBidId(bidId.add(BigInteger.valueOf(1)));
        idRepository.save(id);
        return bidId;
    }

    @CrossOrigin(origins = "http://localhost:9090")
    @RequestMapping(value = "/{userId}/bid", method = RequestMethod.POST)
    public void createPost(@PathVariable(value = "userId") BigInteger userId, @RequestParam(value = "postId") BigInteger postId, @RequestParam(value = "postTitle") String postTitle, @RequestParam(value = "bidAmount") double bidAmount) {
        bidRepository.save(new Bid(getBidId(), userId, postId, postTitle, bidAmount));
    }

    @CrossOrigin(origins = "http://localhost:9090")
    @RequestMapping(value = "/{userId}/bids", method = RequestMethod.GET)
    public List<Bid> getBids(@PathVariable(value = "userId") BigInteger userId) {
        bidList = bidRepository.findByUserId(userId);
        return bidList;
    }

    @CrossOrigin(origins = "http://localhost:9090")
    @RequestMapping(value = "/bids/{postId}", method = RequestMethod.GET)
    public List<Bid> getBidsByPost(@PathVariable(value = "postId") BigInteger postId) {
        bidList = bidRepository.findByPostId(postId);
        return bidList;
    }
}

package com.isometric.controller;

import com.isometric.GlobalConstants;
import com.isometric.MemcachedHelper;
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

    @CrossOrigin(origins = GlobalConstants.origin)
    @RequestMapping(value = "/{userId}/bid", method = RequestMethod.POST)
    public void createBid(@PathVariable(value = "userId") BigInteger userId, @RequestParam(value = "postId") BigInteger postId, @RequestParam(value = "postTitle") String postTitle, @RequestParam(value = "bidAmount") double bidAmount) {
        bidRepository.save(new Bid(getBidId(), userId, postId, postTitle, bidAmount));
        //MemcachedHelper.removeFromCache(userId.toString());
        //MemcachedHelper.removeFromCache(postId.toString());
    }

    @CrossOrigin(origins = GlobalConstants.origin)
    @RequestMapping(value = "/{userId}/bids", method = RequestMethod.GET)
    public List<Bid> getBids(@PathVariable(value = "userId") BigInteger userId) {
        /*if (MemcachedHelper.getFromCache(userId.toString()) != null)
            bidList = (List<Bid>) MemcachedHelper.getFromCache(userId.toString());
        else {*/
            bidList = bidRepository.findByUserId(userId);
            /*MemcachedHelper.putInCache(userId.toString(), bidList);
        }*/
        return bidList;
    }

    @CrossOrigin(origins = GlobalConstants.origin)
    @RequestMapping(value = "/bids/{postId}", method = RequestMethod.GET)
    public List<Bid> getBidsByPost(@PathVariable(value = "postId") BigInteger postId) {
        /*if (MemcachedHelper.getFromCache(postId.toString()) != null)
            bidList = (List<Bid>) MemcachedHelper.getFromCache(postId.toString());
        else {*/
            bidList = bidRepository.findByPostId(postId);
            /*MemcachedHelper.putInCache(postId.toString(), bidList);
        }*/
        return bidList;
    }
}

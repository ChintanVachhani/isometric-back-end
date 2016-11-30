package com.isometric.controller;

import com.isometric.entity.Bid;
import com.isometric.entity.ID;
import com.isometric.repository.BidRepository;
import com.isometric.repository.IDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

package com.isometric.controller;

import com.isometric.entity.ID;
import com.isometric.entity.Post;
import com.isometric.repository.IDRepository;
import com.isometric.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping(value = "/isometric")
public class PostController {

    @Autowired
    private PostRepository postRepository;
    private Post post;
    private List<Post> postList;

    @Autowired
    private IDRepository idRepository;
    private ID id;

    private BigInteger getPostId() {
        BigInteger postId;
        id = idRepository.findOne("key");
        postId = id.getPostId();
        id.setPostId(postId.add(BigInteger.valueOf(1)));
        idRepository.save(id);
        return postId;
    }

    @CrossOrigin(origins = "http://localhost:63343")
    @RequestMapping(value = "/{userId}/post", method = RequestMethod.POST)
    public void createPost(@PathVariable(value = "userId") BigInteger userId, @RequestParam(value = "postTitle") String postTitle, @RequestParam(value = "postDescription") String postDescription, @RequestParam(value = "postTime") String postTime, @RequestParam(value = "itemMaterial") String itemMaterial, @RequestParam(value = "itemSize") String itemSize, @RequestParam(value = "itemBuiltType") String itemBuiltType, @RequestParam(value = "itemColorType") String itemColorType) {
        postRepository.save(new Post(getPostId(), postTitle, postDescription, userId, postTime, itemMaterial, itemSize, itemBuiltType, itemColorType));
    }
}

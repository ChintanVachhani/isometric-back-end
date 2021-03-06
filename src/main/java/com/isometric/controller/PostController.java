package com.isometric.controller;

import com.isometric.GlobalConstants;
import com.isometric.MemcachedHelper;
import com.isometric.entity.ID;
import com.isometric.entity.Post;
import com.isometric.repository.IDRepository;
import com.isometric.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
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

    @CrossOrigin(origins = GlobalConstants.origin)
    @RequestMapping(value = "/{userId}/post", method = RequestMethod.POST)
    public void createPost(@PathVariable(value = "userId") BigInteger userId, @RequestParam(value = "postTitle") String postTitle, @RequestParam(value = "postDescription") String postDescription, @RequestParam(value = "postTime") String postTime, @RequestParam(value = "itemMaterial") String itemMaterial, @RequestParam(value = "itemSize") String itemSize, @RequestParam(value = "itemBuiltType") String itemBuiltType, @RequestParam(value = "itemColorType") String itemColorType) {
        postRepository.save(new Post(getPostId(), postTitle, postDescription, userId, postTime, itemMaterial, itemSize, itemBuiltType, itemColorType));
        MemcachedHelper.removeFromCache("AllPosts");
        //MemcachedHelper.removeFromCache(userId.toString());
    }

    @CrossOrigin(origins = GlobalConstants.origin)
    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public List<Post> getPosts() {
        if (MemcachedHelper.getFromCache("AllPosts") != null)
            postList = (List<Post>) MemcachedHelper.getFromCache("AllPosts");
        else {
            postList = postRepository.findAll();
            MemcachedHelper.putInCache("AllPosts", postList);
        }
        return postList;
    }

    @CrossOrigin(origins = GlobalConstants.origin)
    @RequestMapping(value = "/post/{postId}", method = RequestMethod.GET)
    public Post getPost(@PathVariable(value = "postId") BigInteger postId) {
        if (MemcachedHelper.getFromCache(postId.toString()) != null)
            post = (Post) MemcachedHelper.getFromCache(postId.toString());
        else
            post = postRepository.findOne(postId);
        return post;
    }

    @CrossOrigin(origins = GlobalConstants.origin)
    @RequestMapping(value = "/{userId}/posts", method = RequestMethod.GET)
    public List<Post> getPostsByUser(@PathVariable(value = "userId") BigInteger userId) {
        /*if (MemcachedHelper.getFromCache(userId.toString()) != null)
            postList = (List<Post>) MemcachedHelper.getFromCache(userId.toString());
        else {*/
            postList = postRepository.findByUserId(userId);
            /*MemcachedHelper.putInCache(userId.toString(), postList);
        }*/

        return postList;
    }

    //Naive Implementation of Search functionality
    @CrossOrigin(origins = GlobalConstants.origin)
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<Post> searchPosts(@RequestParam(value = "postSearch") String postSearch, @RequestParam(value = "plastic") String plastic, @RequestParam(value = "glass") String glass, @RequestParam(value = "polycarbonate") String polycarbonate, @RequestParam(value = "polyamide") String polyamide, @RequestParam(value = "S") String s, @RequestParam(value = "M") String m, @RequestParam(value = "L") String l, @RequestParam(value = "XL") String xl, @RequestParam(value = "solid") String solid, @RequestParam(value = "hollow") String hollow, @RequestParam(value = "mono") String mono, @RequestParam(value = "dual") String dual, @RequestParam(value = "multi") String multi) {
        List<String> itemMaterial = new ArrayList<String>();
        List<String> itemSize = new ArrayList<String>();
        List<String> itemBuiltType = new ArrayList<String>();
        List<String> itemColorType = new ArrayList<String>();
        if (plastic.equals("on")) {
            itemMaterial.add("Plastic");
        }
        if (glass.equals("on")) {
            itemMaterial.add("Glass");
        }
        if (polycarbonate.equals("on")) {
            itemMaterial.add("Polycarbonate");
        }
        if (polyamide.equals("on")) {
            itemMaterial.add("Polyamide");
        }
        if (s.equals("on")) {
            itemSize.add("S");
        }
        if (m.equals("on")) {
            itemSize.add("M");
        }
        if (l.equals("on")) {
            itemSize.add("L");
        }
        if (xl.equals("on")) {
            itemSize.add("XL");
        }
        if (solid.equals("on")) {
            itemBuiltType.add("Solid");
        }
        if (hollow.equals("on")) {
            itemBuiltType.add("Hollow");
        }
        if (mono.equals("on")) {
            itemColorType.add("Mono");
        }
        if (dual.equals("on")) {
            itemColorType.add("Dual");
        }
        if (multi.equals("on")) {
            itemColorType.add("Multi");
        }
        if (itemMaterial.isEmpty() && itemSize.isEmpty() && itemBuiltType.isEmpty() && itemColorType.isEmpty()) {
            postList = postRepository.findByPostTitleLikeIgnoreCase(postSearch);
        } else if (itemMaterial.isEmpty() && itemSize.isEmpty() && itemBuiltType.isEmpty()) {
            postList = postRepository.findByPostTitleLikeIgnoreCaseAndItemColorTypeIn(postSearch, itemColorType);
        } else if (itemMaterial.isEmpty() && itemSize.isEmpty() && itemColorType.isEmpty()) {
            postList = postRepository.findByPostTitleLikeIgnoreCaseAndItemBuiltTypeIn(postSearch, itemBuiltType);
        } else if (itemMaterial.isEmpty() && itemBuiltType.isEmpty() && itemColorType.isEmpty()) {
            postList = postRepository.findByPostTitleLikeIgnoreCaseAndItemSizeIn(postSearch, itemSize);
        } else if (itemSize.isEmpty() && itemBuiltType.isEmpty() && itemColorType.isEmpty()) {
            postList = postRepository.findByPostTitleLikeIgnoreCaseAndItemMaterialIn(postSearch, itemMaterial);
        } else if (itemSize.isEmpty() && itemMaterial.isEmpty()) {
            postList = postRepository.findByPostTitleLikeIgnoreCaseAndItemBuiltTypeInAndItemColorTypeIn(postSearch, itemBuiltType, itemColorType);
        } else if (itemBuiltType.isEmpty() && itemMaterial.isEmpty()) {
            postList = postRepository.findByPostTitleLikeIgnoreCaseAndItemSizeInAndItemColorTypeIn(postSearch, itemSize, itemColorType);
        } else if (itemBuiltType.isEmpty() && itemSize.isEmpty()) {
            postList = postRepository.findByPostTitleLikeIgnoreCaseAndItemMaterialInAndItemColorTypeIn(postSearch, itemMaterial, itemColorType);
        } else if (itemColorType.isEmpty() && itemMaterial.isEmpty()) {
            postList = postRepository.findByPostTitleLikeIgnoreCaseAndItemSizeInAndItemBuiltTypeIn(postSearch, itemSize, itemBuiltType);
        } else if (itemColorType.isEmpty() && itemBuiltType.isEmpty()) {
            postList = postRepository.findByPostTitleLikeIgnoreCaseAndItemMaterialInAndItemSizeIn(postSearch, itemMaterial, itemSize);
        } else if (itemColorType.isEmpty() && itemSize.isEmpty()) {
            postList = postRepository.findByPostTitleLikeIgnoreCaseAndItemMaterialInAndItemBuiltTypeIn(postSearch, itemMaterial, itemBuiltType);
        } else if (itemMaterial.isEmpty()) {
            postList = postRepository.findByPostTitleLikeIgnoreCaseAndItemSizeInAndItemBuiltTypeInAndItemColorTypeIn(postSearch, itemSize, itemBuiltType, itemColorType);
        } else if (itemBuiltType.isEmpty()) {
            postList = postRepository.findByPostTitleLikeIgnoreCaseAndItemMaterialInAndItemSizeInAndItemColorTypeIn(postSearch, itemMaterial, itemSize, itemColorType);
        } else if (itemSize.isEmpty()) {
            postList = postRepository.findByPostTitleLikeIgnoreCaseAndItemMaterialInAndItemBuiltTypeInAndItemColorTypeIn(postSearch, itemMaterial, itemBuiltType, itemColorType);
        } else if (itemColorType.isEmpty()) {
            postList = postRepository.findByPostTitleLikeIgnoreCaseAndItemMaterialInAndItemSizeInAndItemBuiltTypeIn(postSearch, itemMaterial, itemSize, itemBuiltType);
        } else {
            postList = postRepository.findByPostTitleLikeIgnoreCaseAndItemMaterialInAndItemSizeInAndItemBuiltTypeInAndItemColorTypeIn(postSearch, itemMaterial, itemSize, itemBuiltType, itemColorType);
        }

        return postList;
    }
}

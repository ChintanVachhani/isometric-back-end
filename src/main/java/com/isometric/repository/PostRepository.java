package com.isometric.repository;

import com.isometric.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;
import java.util.List;

public interface PostRepository extends MongoRepository<Post, BigInteger> {
    List<Post> findByUserId(BigInteger userId);

    List<Post> findByPostTitleLikeIgnoreCaseAndItemMaterialInAndItemSizeInAndItemBuiltTypeInAndItemColorTypeIn(String postTitle, List<String> itemMaterial, List<String> itemSize, List<String> itemBuiltType, List<String> itemColorType);

    List<Post> findByPostTitleLikeIgnoreCaseAndItemMaterialInAndItemSizeInAndItemBuiltTypeIn(String postTitle, List<String> itemMaterial, List<String> itemSize, List<String> itemBuiltType);

    List<Post> findByPostTitleLikeIgnoreCaseAndItemMaterialInAndItemBuiltTypeInAndItemColorTypeIn(String postTitle, List<String> itemMaterial, List<String> itemBuiltType, List<String> itemColorType);

    List<Post> findByPostTitleLikeIgnoreCaseAndItemMaterialInAndItemSizeInAndItemColorTypeIn(String postTitle, List<String> itemMaterial, List<String> itemSize, List<String> itemColorType);

    List<Post> findByPostTitleLikeIgnoreCaseAndItemSizeInAndItemBuiltTypeInAndItemColorTypeIn(String postTitle, List<String> itemSize, List<String> itemBuiltType, List<String> itemColorType);

    List<Post> findByPostTitleLikeIgnoreCaseAndItemMaterialInAndItemBuiltTypeIn(String postTitle, List<String> itemMaterial, List<String> itemBuiltType);

    List<Post> findByPostTitleLikeIgnoreCaseAndItemMaterialInAndItemSizeIn(String postTitle, List<String> itemMaterial, List<String> itemSize);

    List<Post> findByPostTitleLikeIgnoreCaseAndItemSizeInAndItemBuiltTypeIn(String postTitle, List<String> itemSize, List<String> itemBuiltType);

    List<Post> findByPostTitleLikeIgnoreCaseAndItemMaterialInAndItemColorTypeIn(String postTitle, List<String> itemMaterial, List<String> itemColorType);

    List<Post> findByPostTitleLikeIgnoreCaseAndItemSizeInAndItemColorTypeIn(String postTitle, List<String> itemSize, List<String> itemColorType);

    List<Post> findByPostTitleLikeIgnoreCaseAndItemBuiltTypeInAndItemColorTypeIn(String postTitle, List<String> itemBuiltType, List<String> itemColorType);

    List<Post> findByPostTitleLikeIgnoreCaseAndItemMaterialIn(String postTitle, List<String> itemMaterial);

    List<Post> findByPostTitleLikeIgnoreCaseAndItemSizeIn(String postTitle, List<String> itemSize);

    List<Post> findByPostTitleLikeIgnoreCaseAndItemBuiltTypeIn(String postTitle, List<String> itemBuiltType);

    List<Post> findByPostTitleLikeIgnoreCaseAndItemColorTypeIn(String postTitle, List<String> itemColorType);

    List<Post> findByPostTitleLikeIgnoreCase(String postTitle);
}

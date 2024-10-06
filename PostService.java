package com.example.demo.service;

import com.example.demo.model.Post;
import com.example.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public void savePost(String content, MultipartFile image) {
        Post post = new Post();
        post.setContent(content);
        if (!image.isEmpty()) {
            String imageUrl = saveImage(image);
            post.setImageUrl(imageUrl);
        }
        postRepository.save(post);
    }

    private String saveImage(MultipartFile image) {
        String imageName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
        String imagePath = "uploads/" + imageName;
        try {
            Files.createDirectories(Paths.get("uploads"));
            image.transferTo(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imagePath;
    }
}

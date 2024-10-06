package com.example.demo.controller;

import com.example.demo.model.Post;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/post")
    public String post() {
        return "post";
    }

    @PostMapping("/post")
    public String createPost(@RequestParam("content") String content, @RequestParam("image") MultipartFile image) {
        postService.savePost(content, image);
        return "redirect:/post";
    }
}

package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Post;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PostRepository;

@Service
public class PostService {
	private final PostRepository postRepository;
	
	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
	public Post createPost(Post post) {
		post.setCreatedAt(LocalDateTime.now());
		return postRepository.save(post);
	}
	
	public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

	public Post updatePost(Long id, Post updatedPost) {
	    Post post = postRepository.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("Post with ID " + id + " not found"));
	    post.setTitle(updatedPost.getTitle());
	    post.setContent(updatedPost.getContent());
	    post.setUpdatedAt(LocalDateTime.now());
	    return postRepository.save(post);
	}

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

}

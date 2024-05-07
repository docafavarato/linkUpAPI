package com.example.demo.resources;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.domain.Post;
import com.example.demo.domain.User;
import com.example.demo.dto.AuthorDTO;
import com.example.demo.dto.CommentDTO;
import com.example.demo.dto.PostDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.PostService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private PostService postService;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> obj = service.findAll();
		List<UserDTO> objDto = service.toDtoList(obj);
		return ResponseEntity.ok().body(objDto);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	@GetMapping(value="/{id}/posts")
	public ResponseEntity<List<PostDTO>> findPosts(@PathVariable String id, @RequestParam(value="orderByDate", defaultValue="false", required=false) Boolean orderByDate) {
		User user = service.findById(id);
		List<PostDTO> obj = new ArrayList<>();
		if (orderByDate) {
			obj = postService.findPostsByUserIdOrderByDateDesc(id).stream().map(x -> new PostDTO(x)).collect(Collectors.toList());
		} else {
			obj = user.getPosts().stream().map(x -> new PostDTO(x)).collect(Collectors.toList());
		}
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping(value="/{id}/likedPosts")
	public ResponseEntity<List<PostDTO>> findLikedPostsOrderByDateDesc(@PathVariable String id) {
		List<PostDTO> objDto = postService.findByUsersThatLikedOrderByDateDesc(id).stream().map(x -> new PostDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(objDto);
	}
	
	@GetMapping(value="/{id}/comments")
	public ResponseEntity<List<CommentDTO>> findComments(@PathVariable String id) {
		List<CommentDTO> obj = service.findById(id).getComments();
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/search", params={"userName"})
	public ResponseEntity<List<UserDTO>> findByUserName(@RequestParam String userName) {
		List<User> obj = service.findByUserName(userName);
		List<UserDTO> objDto = service.toDtoList(obj);
		return ResponseEntity.ok().body(objDto);
	}
	
	@GetMapping(value="/search", params={"email"})
	public ResponseEntity<UserDTO> findByEmail(@RequestParam String email) {
		User obj = service.findByEmail(email);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	@PostMapping(value="/insert")
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto) {
		User user = service.fromDTO(objDto);
		user = service.insert(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable String id) {
		User user = service.fromDTO(objDto);
		user.setId(id);
		user = service.update(user);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value="/{id}/like", params={"postId"})
	public ResponseEntity<Void> likePost(@PathVariable String id, @RequestParam String postId) {
		service.likePost(id, postId);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value="/{id}/unlike", params={"postId"})
	public ResponseEntity<Void> unlikePost(@PathVariable String id, @RequestParam String postId) {
		service.unlikePost(id, postId);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value="/{id}/follow", params={"userId"})
	public ResponseEntity<Void> follow(@PathVariable String id, @RequestParam String userId) {
		service.follow(id, userId);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value="/{id}/unfollow", params={"userId"})
	public ResponseEntity<Void> unfollow(@PathVariable String id, @RequestParam String userId) {
		service.unfollow(id, userId);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value="/{id}/followingPosts")
	public ResponseEntity<List<PostDTO>> findFollowingPosts(@PathVariable String id) {
		List<Post> posts = service.findFollowingPosts(id);
		List<PostDTO> obj = posts.stream().map(x -> new PostDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/{id}/following")
	public ResponseEntity<List<UserDTO>> findFollowing(@PathVariable String id) {
		User user = service.findById(id);
		List<UserDTO> obj = user.getFollowing();
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value="/{id}/followers")
	public ResponseEntity<List<UserDTO>> findFollowers(@PathVariable String id) {
		User user = service.findById(id);
		List<UserDTO> obj = user.getFollowers();
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping(value="/{id}/comment", params={"postId"})
	public ResponseEntity<Void> comment(@PathVariable String id, @RequestParam String postId, @RequestBody CommentDTO commentDto) {
		LocalDateTime localDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
		commentDto.setAuthor(new AuthorDTO(service.findById(id)));
		commentDto.setDate(localDate.format(formatter));
		service.comment(id, postId, commentDto);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value="/{id}/deleteComment", params={"postId", "commentId"})
	public ResponseEntity<Void> deleteComment(@PathVariable String id, @RequestParam String postId, @RequestParam String commentId) {
		User user = service.findById(id);
		Post post = postService.findById(postId);
		user.getComments().removeIf(x -> x.getId().equals(commentId));
		post.getComments().removeIf(x -> x.getId().equals(commentId));
		
		service.save(user);
		postService.save(post);
		
		return ResponseEntity.noContent().build();
	}
}

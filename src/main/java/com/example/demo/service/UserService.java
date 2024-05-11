package com.example.demo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Comment;
import com.example.demo.domain.Post;
import com.example.demo.domain.User;
import com.example.demo.dto.AuthorDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserLikeDTO;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.exception.BadRequestException;
import com.example.demo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentRepository commentRepository;

	public User findById(String id) throws ObjectNotFoundException {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("User not found"));
	}
	
	public List<User> findAll() {
		List<User> obj = repository.findAll();
		return obj;
	}
	
	public List<User> findByUserName(String userName) {
		List<User> obj = repository.findByUserNameIgnoreCaseContains(userName);
		return obj;
	}
	
	public List<Post> findFollowingPosts(String userId) {
		User user = repository.findById(userId).get();
		List<Post> obj = new ArrayList<>();
		
		for (UserDTO u : user.getFollowing()) {
			User other = repository.findById(u.getId()).get();
			obj.addAll(other.getPosts());
		}
		
		Collections.sort(obj, new Comparator<Post>() {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			
			@Override
			public int compare(Post post1, Post post2) {
				try {
					Date date1 = sdf.parse(post1.getDate());
					Date date2 = sdf.parse(post2.getDate());
					return date2.compareTo(date1);
				} catch (ParseException e) {
					return 0;
				}
			}
		});
		
		return obj;
	}
	
	public User findByEmail(String email) {
		User obj = repository.findByEmail(email);
		return obj;
	}
	
	public User insert(User obj) {
		obj.setImgUrl("https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png");
		return repository.insert(obj);
	}
	
	public User save(User obj) {
		return repository.save(obj);
	}
	
	public void delete(String id) {
		findById(id); // throw exception
		repository.deleteById(id);
		postRepository.deleteAllByAuthorId(id);
	}
	
	public User update(User obj) {
		User newObj = repository.findById(obj.getId()).get();
		updateData(newObj, obj);
		return repository.save(newObj);
	}
	
	public void updateData(User newObj, User obj) {
		newObj.setUserName(obj.getUserName());
		newObj.setEmail(obj.getEmail());
		newObj.setPassword(obj.getPassword());
		newObj.setImgUrl(obj.getImgUrl());
		newObj.setDescription(obj.getDescription());
		newObj.setBirthDate(obj.getBirthDate());
	}
	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail(), objDto.getPassword(),
				objDto.getImgUrl(), objDto.getDescription(), objDto.getBirthDate());
	}
	
	public List<UserDTO> toDtoList(List<User> obj) {
		List<UserDTO> objDto = obj.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return objDto;
	}
	
	public void likeComment(String userId, String commentId) throws BadRequestException {
		User user = repository.findById(userId).get();
		Comment comment = commentRepository.findById(commentId).get();
		Post post = comment.getPost();
		
		if (!user.getLikedComments().contains(comment)) {
			comment.getUsersThatLiked().add(new UserLikeDTO(user));
			user.getLikedComments().add(comment);
			
			commentRepository.save(comment);
			repository.save(user);
			postRepository.save(post);
		} else {
			throw new BadRequestException("The user already liked this comment");
		}
	}
	
	public void unlikeComment(String userId, String commentId) throws BadRequestException {
		User user = repository.findById(userId).get();
		Comment comment = commentRepository.findById(commentId).get();
		Post post = comment.getPost();
		
		if (user.getLikedComments().contains(comment)) {
			comment.getUsersThatLiked().remove(new UserLikeDTO(user));
			user.getLikedComments().remove(comment);
			
			commentRepository.save(comment);
			repository.save(user);
			postRepository.save(post);
		} else {
			throw new BadRequestException("The user never liked this comment");
		}
	}
	
	public void likePost(String userId, String postId) throws BadRequestException {
		User user = repository.findById(userId).get();
		Post post = postRepository.findById(postId).get();
	
		if (!user.getlikedPosts().contains(post)) {
			post.getUsersThatLiked().add(new UserLikeDTO(user));
			user.getlikedPosts().add(post);
			
			postRepository.save(post);
			repository.save(user);
		} else {
			throw new BadRequestException("The user already liked this post");
		}
	
	}
	
	public void unlikePost(String userId, String postId) throws BadRequestException {
		User user = repository.findById(userId).get();
		Post post = postRepository.findById(postId).get();
	
		if (user.getlikedPosts().contains(post)) {
			post.getUsersThatLiked().remove(new UserLikeDTO(user));
			user.getlikedPosts().remove(post);
			
			postRepository.save(post);
			repository.save(user);
		} else {
			throw new BadRequestException("The user never liked this post");
		}
	
	}
	
	public void comment(String userId, String postId, Comment comment) {
		User user = repository.findById(userId).get();
		Post post = postRepository.findById(postId).get();
		comment.setPost(post);
		commentRepository.insert(comment);
		
		
		user.getComments().add(comment);
		post.getComments().add(comment);
		comment.setAuthor(new AuthorDTO(user));
		
		repository.save(user);
		postRepository.save(post);
		commentRepository.save(comment);
	}
	
	public void follow(String userId, String followedId) throws BadRequestException {
		User user = repository.findById(userId).get();
		User followed = repository.findById(followedId).get();
		
		if (!userId.equals(followedId)) {
			if (!user.getFollowing().contains(new UserDTO(followed))) {
				user.getFollowing().add(new UserDTO(followed));
				followed.getFollowers().add(new UserDTO(user));
				
				repository.saveAll(Arrays.asList(user, followed));
			} else {
				throw new BadRequestException("The user already follows the other user");
			}
		} else {
			throw new BadRequestException("The user can't follow himself");
		}
	}
	
	public void unfollow(String userId, String followedId) throws BadRequestException {
		User user = repository.findById(userId).get();
		User followed = repository.findById(followedId).get();
		
		if (user.getFollowing().contains(new UserDTO(followed))) {
			followed.getFollowers().remove(new UserDTO(user));
			user.getFollowing().remove(new UserDTO(followed));
			
			repository.saveAll(Arrays.asList(user, followed));
		} else {
			throw new BadRequestException("The user never followed the other user");
		}
	}
}

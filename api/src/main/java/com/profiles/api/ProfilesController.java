package com.profiles.api;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.thymeleaf.expression.Lists;

import com.profiles.api.data.ProfileRepository;
import com.profiles.api.model.Profile;

@Controller
public class ProfilesController {
	
	private ProfileRepository profileRepository;
	
	@RequestMapping(value = "/profiles")
	public String listProfiles(ModelMap map) {
		/*
		 * Return page that lists profiles from excel sheet
		 */
//		List<Post> list = this.postRepository.getAllPosts();
//		map.put("posts", list);
		return "";
	}
	
	@RequestMapping(value="/profile/{id}")
	public String postDetails(@PathVariable Long id, ModelMap map) {
		/*
		 * Return page for individual profile
		 */
//		Post post = this.postRepository.findById(id);
//		map.put("post", post);
		return "";
	}
	
	@PatchMapping(value="/profile/{id}")
	public void updateProfile(@PathVariable Long id, ModelMap map) {
		/*
		 * update a profile 
		 */
	}
	
	@PutMapping(value="/profile/{id}")
	public void addProfile(@PathVariable Long id, ModelMap map) {
		/*
		 * add a profile
		 */
	}
	
	@DeleteMapping(value="/profile/{id}")
	public void deleteProfile(@PathVariable Long id, ModelMap map){
		/*
		 * delete a profile
		 */
	}
}

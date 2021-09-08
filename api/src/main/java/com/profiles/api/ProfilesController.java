package com.profiles.api;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.thymeleaf.expression.Lists;

import software.amazon.awssdk.enhanced.dynamodb.Key;

import org.apache.catalina.valves.LoadBalancerDrainingValve;
import org.springframework.beans.factory.annotation.Autowired;

import com.profiles.api.data.ProfileRepository;
import com.profiles.api.model.Profile;

@Controller
// @ResponseBody
public class ProfilesController {

	private ProfileRepository profileRepository;

	@Autowired
	private DynamoEnhanced dde;

	@RequestMapping(value = "/profiles")
	@ResponseBody
	public String listProfiles(ModelMap map) {
		/*
		 * Return page that lists profiles from excel sheet
		 */
		// List<Post> list = this.postRepository.getAllPosts();
		// map.put("posts", list);

		Profile profile = new Profile(123L, "name", "email", 1231231234, 
		"city", "state", "track", "account", 1, LocalDate.now());

		dde.putRow(profile);

		Profile gotten = dde.getRow(123);

		return gotten.get_name();
	}

	@RequestMapping(value = "/profile/{id}")
	public String postDetails(@PathVariable Long id, ModelMap map) {
		/*
		 * Return page for individual profile
		 */
		// Post post = this.postRepository.findById(id);
		// map.put("post", post);
		return "";
	}

	@PatchMapping(value = "/profile/{id}")
	public void updateProfile(@PathVariable Long id, ModelMap map) {
		/*
		 * update a profile
		 */
	}

	@PutMapping(value = "/profile/{id}")
	public void addProfile(@PathVariable Long id, ModelMap map) {
		/*
		 * add a profile
		 */
	}

	@DeleteMapping(value = "/profile/{id}")
	public void deleteProfile(@PathVariable Long id, ModelMap map) {
		/*
		 * delete a profile
		 */
	}
}

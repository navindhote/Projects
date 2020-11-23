package com.rest.webservices.restfulwebservices.subscription;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class SubscriptionResource {

	@Autowired
	private SubscriptionDaoService service;

	@GetMapping("/subscriptions")
	public List<Subscription> retrieveAllUsers() {
		return service.findAll();
	}

	@GetMapping("/subscriptions/{id}")
	public Resource<Subscription> retrieveUser(@PathVariable int id) {
		Subscription subscription = service.findOne(id);
		
		if(subscription==null)
			throw new SubscriptionNotFoundException("id-"+ id);
		
		
		//"all-users", SERVER_PATH + "/subscriptions"
		//retrieveAllSubscriptions
		Resource<Subscription> resource = new Resource<Subscription>(subscription);
		
		ControllerLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).retrieveAllUsers());
		
		resource.add(linkTo.withRel("all-users"));
		
		//HATEOAS
		
		return resource;
	}

	@DeleteMapping("/subscriptions/{id}")
	public void deleteSubscription(@PathVariable int id) {
		Subscription subscription = service.deleteById(id);
		
		if(subscription==null)
			throw new SubscriptionNotFoundException("id-"+ id);		
	}

	//
	// input - details of user
	// output - CREATED & Return the created URI
	
	//HATEOAS
	
	@PostMapping("/subscriptions")
	public ResponseEntity<Object> createSubscription(@Valid @RequestBody Subscription subscription) {
		Subscription savedSubscription = service.save(subscription);
		// CREATED
		// /subscription/{id}     savedSubscription.getId()
		
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedSubscription.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}
}
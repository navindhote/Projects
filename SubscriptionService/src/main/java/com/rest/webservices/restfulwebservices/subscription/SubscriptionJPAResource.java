package com.rest.webservices.restfulwebservices.subscription;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
public class SubscriptionJPAResource {

	@Autowired
	private SubscriptionDaoService service;
	
	@Autowired
	private SubscriptionRepository subscriptionRepository;

	@GetMapping("/jpa/subscription")
	public List<Subscription> retrieveAllSubscriptions() {
		return subscriptionRepository.findAll();
	}

	@GetMapping("/jpa/subscriptions/{id}")
	public Resource<Subscription> retrieveUser(@PathVariable int id) {
		Optional<Subscription> subscription = subscriptionRepository.findById(id);
		
		if(!subscription.isPresent())
			throw new SubscriptionNotFoundException("id-"+ id);
		
		//"all-subscriptions", SERVER_PATH + "/subscriptions"
		//retrieveAllUsers
		Resource<Subscription> resource = new Resource<Subscription>(subscription.get());
		
		ControllerLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).retrieveAllSubscriptions());
		
		resource.add(linkTo.withRel("all-subscriptions"));
		
		//HATEOAS
		
		return resource;
	}

	@DeleteMapping("/jpa/subscription/{id}")
	public void deleteSubscription(@PathVariable int id) {
		Subscription subscription = service.deleteById(id);
		
		if(subscription==null)
			throw new SubscriptionNotFoundException("id-"+ id);		
	}

	//
	// input - details of subscription
	// output - CREATED & Return the created URI
	
	//HATEOAS
	
	@PostMapping("/jpa/subscription")
	public ResponseEntity<Object> createSubscription(@Valid @RequestBody Subscription subscription) {
		Subscription savedSubscription = service.save(subscription);
		
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedSubscription.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}
}
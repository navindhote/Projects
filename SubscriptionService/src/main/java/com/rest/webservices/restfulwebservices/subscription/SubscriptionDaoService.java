package com.rest.webservices.restfulwebservices.subscription;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class SubscriptionDaoService {
	
	private static List<Subscription> subscriptions = new ArrayList<>();

	private static int subscriptionsCount = 3;

	static {
		subscriptions.add(new Subscription(1, "DAILY", new Date(), new Date(),10));
		subscriptions.add(new Subscription(2, "WEEKLY", new Date(),new Date(),10));
		subscriptions.add(new Subscription(3, "MONTHLY", new Date(),new Date(),10));
	}

	public List<Subscription> findAll() {
		return subscriptions;
	}

	public Subscription save(Subscription Subscription) {
		if (Subscription.getId() == null) {
			Subscription.setId(++subscriptionsCount);
		}
		subscriptions.add(Subscription);
		return Subscription;
	}

	public Subscription findOne(int id) {
		for (Subscription Subscription : subscriptions) {
			if (Subscription.getId() == id) {
				return Subscription;
			}
		}
		return null;
	}

	public Subscription deleteById(int id) {
		Iterator<Subscription> iterator = subscriptions.iterator();
		while (iterator.hasNext()) {
			Subscription Subscription = iterator.next();
			if (Subscription.getId() == id) {
				iterator.remove();
				return Subscription;
			}
		}
		return null;
	}

}

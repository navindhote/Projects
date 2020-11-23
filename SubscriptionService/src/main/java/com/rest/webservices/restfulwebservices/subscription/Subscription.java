package com.rest.webservices.restfulwebservices.subscription;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="All details about the subscription. ")
@Entity
public class Subscription {

	@Id
	@GeneratedValue
	private Integer id;
	
	private Integer invoiceAmount;

	private String subscriptionType;

	private Date startDate;
	
	private Date endDate;

	protected Subscription() {

	}

	public Subscription(Integer id, String subscriptionType, Date startDate,Date endDate,Integer invoiceAmount) {
		super();
		this.id = id;
		this.subscriptionType = subscriptionType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.invoiceAmount = invoiceAmount;
	}

	public Integer getInvoiceamount() {
		return invoiceAmount;
	}

	public void setInvoiceamount(Integer invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "Subscription [id=" + id + ", subscriptionType=" + subscriptionType + ", startDate=" + startDate
				+ ", endDate=" + endDate + "]";
	}

}

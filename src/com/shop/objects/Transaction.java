package com.shop.objects;

import java.util.Date;
import java.util.List;

import com.shop.enums.StatusType;

public class Transaction {
	private int transactionID;
	private User user;
	private List<Product> products;
	private Double totalPrise;
	private Date buyDate;
	private Date payDate;
	private StatusType status;

}

package com.ravi.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Public {
	
	private String id;
	private String name;
	private String productname = "Reebok Shoes";
	private Date date;
	private String message;
}

package com.pass.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tags_master")
public class Tags {

	private String tag[];

	public String[] getTag() {
		return tag;
	}

	public void setTag(String[] tag) {
		this.tag = tag;
	}
}
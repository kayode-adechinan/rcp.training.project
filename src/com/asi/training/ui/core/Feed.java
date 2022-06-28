package com.asi.training.ui.core;

public class Feed {
	private String name;
	private String url;
	public Feed(String name, String url) {
		this.name = name;
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public String getUrl() {
		return url;
	}
}
package com.sky.spider.advance.java8.lambda1;

public class User {
	private String  name ;
	
	private int score ;
	
	

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", score=" + score + "]";
	}

	public User(String name, int score) {
		super();
		this.name = name;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	

}

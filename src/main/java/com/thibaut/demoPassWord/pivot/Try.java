package com.thibaut.demoPassWord.pivot;

import java.time.LocalDateTime;

import lombok.Getter;

public class Try {
	
	public Try(int i, LocalDateTime now) {
		this.conter=i;
		this.date=now;
	}

	@Getter 
	private int conter;
	
	@Getter
	private LocalDateTime date;
	
	public int getConter() {
		return this.conter;
	}
	
	public LocalDateTime getDate() {
		return this.date;
	}
	
}

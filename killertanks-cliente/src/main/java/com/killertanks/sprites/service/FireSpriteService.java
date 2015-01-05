package com.killertanks.sprites.service;

import java.util.List;

import com.killertanks.sprites.FireSprite;

public class FireSpriteService {
	
	private static FireSpriteService INSTANCE;
	
	private FireSpriteService(){}
	
	public static FireSpriteService getInstance(){
		if(INSTANCE==null){
			INSTANCE = new FireSpriteService();
		}
		return INSTANCE;
	}
	
	private List<FireSprite> firesA;
	private int numFiresA = 0;
	private List<FireSprite> firesB;
	private int numFiresB = 0;

	public int getNumFiresA() {
		return numFiresA;
	}

	public void setNumFiresA(int numFiresA) {
		this.numFiresA = numFiresA;
	}

	public int getNumFiresB() {
		return numFiresB;
	}

	public void setNumFiresB(int numFiresB) {
		this.numFiresB = numFiresB;
	}

	public List<FireSprite> getFiresA() {
		return firesA;
	}

	public void setFiresA(List<FireSprite> firesA) {
		this.firesA = firesA;
	}

	public List<FireSprite> getFiresB() {
		return firesB;
	}

	public void setFiresB(List<FireSprite> firesB) {
		this.firesB = firesB;
	}
}

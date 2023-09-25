package com.KoreaIT.java.BAM.dao;

public abstract class Dao {
	protected int lastId;

	public Dao() {
		lastId = 0;
	}

	public abstract int getLastId();
}

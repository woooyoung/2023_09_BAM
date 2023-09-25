package com.KoreaIT.java.BAM.dao;

import java.util.ArrayList;
import java.util.List;

import com.KoreaIT.java.BAM.dto.Article;

public class ArticleDao extends Dao {
	public List<Article> articles;

	public int getLastId() {
		return lastId;
	}

	public ArticleDao() {
		articles = new ArrayList<>();
	}

	public int setNewId() {
		return lastId + 1;
	}

	public void add(Article article) {
		articles.add(article);
		lastId++;
	}
}

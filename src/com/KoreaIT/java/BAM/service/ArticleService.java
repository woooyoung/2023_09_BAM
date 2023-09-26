package com.KoreaIT.java.BAM.service;

import java.util.List;

import com.KoreaIT.java.BAM.container.Container;
import com.KoreaIT.java.BAM.dao.ArticleDao;
import com.KoreaIT.java.BAM.dto.Article;

public class ArticleService {

	private ArticleDao articleDao;

	public ArticleService() {
		this.articleDao = Container.articleDao;
	}

	public int setNewId() {
		int id = articleDao.setNewId();
		return id;
	} 

	public void add(Article article) {
		articleDao.add(article);
	}

	public List<Article> getForPrintArticles(String searchKeyword) {

		List<Article> articles = articleDao.getArticles(searchKeyword);

		return articles;
	}

	public Article getArticleById(int id) {
		Article article = articleDao.getArticleById(id);
		return article;
	}

	public void remove(Article foundArticle) {
		articleDao.remove(foundArticle);
	}

}

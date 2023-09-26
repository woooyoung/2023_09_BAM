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

	public List<Article> getArticles(String searchKeyword) {
		if (searchKeyword.length() != 0 && searchKeyword != null) {

			List<Article> forPrintArticles = new ArrayList<Article>();

			if (searchKeyword.length() > 0) {
				for (Article article : articles) {
					if (article.title.contains(searchKeyword)) {
						forPrintArticles.add(article);
					}
				}
			}
			return forPrintArticles;
		}

		return articles;
	}

	public void remove(Article foundArticle) {
		articles.remove(foundArticle);
	}

	public int getArticleIndexById(int id) {

		for (int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);
			if (article.id == id) {
				return i;
			}
		}

		return -1;
	}

	public Article getArticleById(int id) {

		int index = getArticleIndexById(id);

		if (index != -1) {
			return articles.get(index);
		}

		return null;
	}

	public void add(Article article) {
		articles.add(article);
		lastId++;
	}

}

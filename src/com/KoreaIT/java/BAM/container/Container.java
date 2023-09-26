package com.KoreaIT.java.BAM.container;

import com.KoreaIT.java.BAM.dao.ArticleDao;
import com.KoreaIT.java.BAM.dao.MemberDao;
import com.KoreaIT.java.BAM.service.ArticleService;

public class Container {
	public static ArticleDao articleDao;
	public static MemberDao memberDao;
	
	public static ArticleService articleService;
	
	static {
		articleDao = new ArticleDao();
		memberDao = new MemberDao();
		
		articleService = new ArticleService();
	}
}

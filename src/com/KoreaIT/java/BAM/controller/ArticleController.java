package com.KoreaIT.java.BAM.controller;

import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.BAM.container.Container;
import com.KoreaIT.java.BAM.dto.Article;
import com.KoreaIT.java.BAM.dto.Member;
import com.KoreaIT.java.BAM.service.ArticleService;
import com.KoreaIT.java.BAM.util.Util;

public class ArticleController extends Controller {

	private List<Article> articles;
	private Scanner sc;
	private String actionMethodName;
	private String command;

	private ArticleService articleService;

	public ArticleController(Scanner sc) {
		this.articleService = Container.articleService;
//		this.articles = Container.articleDao.articles;
		this.sc = sc;
	}

	public void doAction(String actionMethodName, String command) {
		this.actionMethodName = actionMethodName;
		this.command = command;

		switch (actionMethodName) {
		case "write":
			doWrite();
			break;
		case "list":
			showList();
			break;
		case "detail":
			showDetail();
			break;
		case "modify":
			doModify();
			break;
		case "delete":
			doDelete();
			break;
		default:
			System.out.println("그런 세부기능은 없어");
			break;
		}
	}

	public void showList() {

		String searchKeyword = command.substring("article list".length()).trim();

		System.out.println("searchKeyword : " + searchKeyword);

		List<Article> forPrintArticles = articleService.getForPrintArticles(searchKeyword);

		if (forPrintArticles.size() == 0) {
			System.out.println("게시글 없음");
			return;
		}

		List<Member> members = Container.memberDao.members;

		System.out.println("번호      /    제목      /    작성자       /    조회   ");
		for (int i = forPrintArticles.size() - 1; i >= 0; i--) {

			Article article = forPrintArticles.get(i);
			String writerName = null;

			for (Member member : members) {
				if (article.memberId == member.id) {
					writerName = member.name;
					break;
				}
			}

			System.out.printf(" %4d     /   %5s    /      %4s      /      %4d  \n", article.id, article.title,
					writerName, article.hit);
		}

	}

	public void doWrite() {
		int id = articleService.setNewId();
		String regDate = Util.getNow();
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		Article article = new Article(id, regDate, regDate, loginedMember.id, title, body);
		articleService.add(article);

		System.out.printf("%d번글이 생성되었습니다.\n", id);

	}

	public void showDetail() {
		String[] commandDiv = command.split(" ");

		int id = Integer.parseInt(commandDiv[2]);

		Article foundArticle = articleService.getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 없어\n", id);
			return;
		}

		List<Member> members = Container.memberDao.members;

		String writerName = null;

		for (Member member : members) {
			if (foundArticle.memberId == member.id) {
				writerName = member.name;
				break;
			}
		}

		foundArticle.hit++;

		System.out.println("번호 : " + foundArticle.id);
		System.out.println("작성날짜 : " + foundArticle.regDate);
		System.out.println("수정날짜 : " + foundArticle.updateDate);
		System.out.println("작성자 : " + writerName);
		System.out.println("제목 : " + foundArticle.title);
		System.out.println("내용 : " + foundArticle.body);
		System.out.println("조회수 : " + foundArticle.hit);

	}

	public void doModify() {
		String[] commandDiv = command.split(" ");

		int id = Integer.parseInt(commandDiv[2]);

		Article foundArticle = articleService.getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 없어\n", id);
			return;
		}

		if (foundArticle.memberId != loginedMember.id) {
			System.out.println("너한텐 권한이 없어");
			return;
		}

		System.out.printf("제목 : ");
		String newTitle = sc.nextLine();
		System.out.printf("내용 : ");
		String newBody = sc.nextLine();

		String updateDate = Util.getNow();
		foundArticle.title = newTitle;
		foundArticle.body = newBody;
		foundArticle.updateDate = updateDate;

	}

	public void doDelete() {
		String[] commandDiv = command.split(" ");

		int id = Integer.parseInt(commandDiv[2]);
		Article foundArticle = articleService.getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 없어\n", id);
			return;
		}

		if (foundArticle.memberId != loginedMember.id) {
			System.out.println("너한텐 권한이 없어");
			return;
		}

		articleService.remove(foundArticle);
		System.out.println(id + "번 글을 삭제했어");

	}

	public void makeTestData() {
		System.out.println("테스트를 위한 게시글 데이터 5개 생성 완료");
		articleService.add(new Article(1, Util.getNow(), Util.getNow(), 1, "제목1", "내용1", 11));
		articleService.add(new Article(2, Util.getNow(), Util.getNow(), 2, "제목2", "내용2", 22));
		articleService.add(new Article(3, Util.getNow(), Util.getNow(), 3, "제목3", "내용3", 33));
		articleService.add(new Article(4, Util.getNow(), Util.getNow(), 1, "제목11", "내용11", 44));
		articleService.add(new Article(5, Util.getNow(), Util.getNow(), 3, "제목21", "내용21", 55));
	}

}

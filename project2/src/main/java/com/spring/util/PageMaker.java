package com.spring.util;

import lombok.Data;

// PageNation
@Data
public class PageMaker {

	// 페이지안의 데이터를 담당할 객체(현재페이지번호,데이터량 몇개씩)
	private Criteria criteria = null;
	private double totalDataCount; // 전체게시글 수
	// boolean
	// prev
	// next

	// int
	// start
	// end
	private boolean prev, next;
	private int startPage, endPage; // 40 * 10 -> 400
	private int realEndPage; // 34 * 10 -> 340
	// dpa 5 : prev 1 2 3 4 5 next, dpa 10 : prev 1 2 3 4 5~ 10 next
	private int displayPageAmount;
	private int startNum;
	private int endNum;
	
	public PageMaker() {
		criteria = new Criteria(1, 10);
		// 페이지네이션 보여줄 페이지 갯수
		this.displayPageAmount = 10;
		init();
	}
	/*
	 * public PageMaker(double totalDataCount) { this(); // 전체 데이터 갯수 -> next에 관련된 것
	 * this.totalDataCount = totalDataCount; init(); }
	 */

	public void init() {
		// 게시글 333, diplayPageAmount 10, cri, amount 10
		// case 1 startPage 1, endPage 10 : 1~100
		// case 2 startPage 11, endPage 20 : 101 ~ 200
		// case 3 startPage 21, endPage 30 : 201 ~ 300
		// case 4 startpage 31, endPage 34 : 301 ~ 340

		// totalDataCount = 333;
		// endPage계산
		// pageNum = 8 -> amount=71 ~ 80, startPage=1 endPage=10 :
		// pageNum = 12 -> amount=111 ~ 120,startPage=11, endPage=20;
		// pageNum = 32 -> amount=311 ~ 320,startPage=31, endPage=34;
		// pageNum = 34 -> amount=331 ~ 333,startPage=31, endPage=34;
		// realEndPage=> cri.amount=10, -> 339/10 = 33.3, ceil -> 34

		// endPage
		// diplayPageAmount 10
		// pageNum=8, display 1~10, (8 * 1.0)/10, ceil(0.8)-> 1.0 * 10 -> 10
		// pageNum=12, display 11~20, (12*1.0)/10, ceil(1.2)-> 2.0 * 10 -> 20
		// pageNum 32, display 31~40, (32*1.0)/10, ceil(3.2)-> 4.0 * 10 -> 40

		endPage = (int) (Math.ceil((this.criteria.getPageNum() * 1.0) / displayPageAmount) * displayPageAmount);

		// realEndPage 계산
		realEndPage = (int) Math.ceil((this.getTotalDataCount() / (double) this.criteria.getAmount()));

		// startPage 계산
		// pageNum=8, endPage=10, startPage=1, displayAmount=10
		// 식: endPage - (displayAmount-1) -> 10 - (10 - 1) = 1, startPage=1
		// pageNum=12, endPage=20, startPage=11
		// pageNum=32, endPage=40, startPage=31
		startPage = endPage - (displayPageAmount - 1);

		// endPage vs realEndPage 계산
		// pageNum=8, endPage=10, realEndPage=34
		// pageNum=12, endPage=20, realEndPage=34
		// pageNum=32, endPage=40, realEndPage=34 -> endPage=34
		if (realEndPage < endPage) {
			endPage = realEndPage;
		}

		// totalDataCount=333(게시글)
		// pageNum=8, startPage > 1
		// prev(false) 1 2 3 4 5~ 10 next(true)
		// pageNum=12,
		// prev(true) 11 12 13 14 15~ 20 next(true)
		// pageNum=32,
		// prev(true) 31 32 33 34 next(false)
		prev = startPage > 1 && startPage > displayPageAmount;
		next = endPage < realEndPage;
	}

	public static PageMaker getPageMaker(int pageNum) {
		return getPageMaker(pageNum, 0);
	}
	
	public static PageMaker getPageMaker(int pageNum, double totalDataCount) {
		PageMaker pageMaker = new PageMaker();
		pageMaker.setTotalDataCount(totalDataCount);
		if(totalDataCount != 0) {
			// "?pageNo=12344"
			if (pageNum <= 0) {
				pageNum = 1;
			} else if (pageMaker.getRealEndPage() < pageNum) {
				pageNum = 1;
			}
		}
		pageMaker.setCriteriaPageNum(pageNum);
		return pageMaker;
	}
	private void caculatePageNum() {
		
		if(totalDataCount != 0) {
			// "?pageNo=12344"
			if (this.criteria.pageNum <= 0) {
				this.criteria.pageNum = 1;
			} else if (getRealEndPage() < this.criteria.pageNum) {
				this.criteria.pageNum = 1;
			}
		}
	}

	// --------------------------getter/setter
	public double getTotalDataCount() {
		return totalDataCount;
	}

	public void setTotalDataCount(double totalDataCount) {
		this.totalDataCount = totalDataCount;
		init();
		//caculatePageNum();
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getRealEndPage() {
		return realEndPage;
	}

	public void setRealEndPage(int realEndPage) {
		this.realEndPage = realEndPage;
	}

	public int getDisplayPageAmount() {
		return displayPageAmount;
	}

	public void setDisplayPageAmount(int displayPageAmount) {
		this.displayPageAmount = displayPageAmount;
		init();
	}

	public Criteria getCriteria() {
		return this.criteria;
	}

	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
		init();
	}

	public int getCriteriaPageNum() {
		return this.criteria.getPageNum();
	}

	public void setCriteriaPageNum(int pageNum) {
		this.criteria.setPageNum(pageNum);
		init();
	}

	public int getCriteriaAmount() {
		return this.criteria.getAmount();
	}

	public void setCriteriaAmount(int amount) {
		this.criteria.setAmount(amount);
		init();
	}

	// 내가 클릭한 페이지 번호 -> pageNum
	// 테이블에 몇개씩 뿌려줄꺼야 -> amount
	private class Criteria {
		private int pageNum; // 현재 내가 클릭한 페이지
		private int amount; // 한 페이지당 보여줄 데이터량

		private Criteria(int pageNum, int amount) {
			this.pageNum = pageNum;
			this.amount = amount;
		}

		private int getPageNum() {
			return pageNum;
		}

		private void setPageNum(int pageNum) {
			this.pageNum = pageNum;
		}

		private int getAmount() {
			return amount;
		}

		private void setAmount(int amount) {
			this.amount = amount;
		}
	}
	
	public int getStartNum() {
		return (this.getCriteriaPageNum() * this.getCriteriaAmount()	- (this.getCriteriaAmount()));
	}
	
	public int getEndNum() {
		return this.getCriteriaAmount();
	}

	public static void main(String[] args) {
		// 게시글 totalPageCount=333, diplayPageAmount 10, cri, amount 10
		// pageNum=8 -> prev=false, startPage=1, endPage=10, realEndPage=34, next=true
		// pageNum=12 -> prev=true, startPage=11, endPage=20, realEndPage=34, next=true
		// pageNum=32 -> prev=true, startPage=31, endPage=34, realEndPage=34, next=false

		int pageNum = 8;
		int totalCount = 333;
		int displayPageAmount=5;
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setDisplayPageAmount(displayPageAmount);
		
		pageMaker.setTotalDataCount(totalCount);
		pageMaker.setCriteriaPageNum(pageNum);
		printPageNation(pageMaker);

		pageNum = 12;
		pageMaker.setCriteriaPageNum(pageNum);
		printPageNation(pageMaker);

		pageNum = 32;
		pageMaker.setCriteriaPageNum(pageNum);
		printPageNation(pageMaker);
		System.out.println(pageMaker.getTotalDataCount());

	}


	
	public static void printPageNation(PageMaker pageMaker) {
		String sentence = "pageNum=" + pageMaker.getCriteriaPageNum() + " -> prev=" + pageMaker.isPrev()
				+ ", startPage=" + pageMaker.getStartPage() + ", endPage=" + pageMaker.getEndPage() + ", realEndPage="
				+ pageMaker.getRealEndPage() + ", next=" + pageMaker.isNext();
		System.out.println(sentence);
	}
}

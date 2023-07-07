package com.feed.feedsoup.util;

public class PageInfo {

    private int currentPage;//현재페이지
    private int totalNumberPosts;//총게시글수
    private final int PAGE_POSTS_LIMIT = 10;//한 페이지 게시글 수
    private final int PAGE_LIST_LIMIT = 10;//한 페이지에 표시될 리스트 수

    //전체 페이지 중 가장 마지막 페이지->101 / 10 = 10.1 => 11페이지
    public int getMaxPage(){
        return (int)Math.ceil((double)this.totalNumberPosts / this.currentPage);
    }

    //페이징 된 페이지 중 시작 페이지 -> 1... 11... 21... 31...
    public int getStartPage(){
        return (this.PAGE_POSTS_LIMIT * ((this.currentPage - 1) / this.PAGE_POSTS_LIMIT)) + 1;
    }

    //페이징 된 페이지 중 마지막 페이지->10, 20, 30, 40, ....
    public int getEndPage(){
        int endPage = this.getStartPage() + this.PAGE_POSTS_LIMIT - 1;
        return endPage > this.getMaxPage() ? this.getMaxPage() : endPage;
    }

    //현재 페이지에서 시작되는 게시글의 번호
    public int getStartList() {
        return (this.getCurrentPage() - 1) * this.PAGE_LIST_LIMIT + 1;
    }

    //현재 페이지의 마지막 게시글의 번호
    public int getEndList() {
        int endList = this.getStartList() + this.PAGE_LIST_LIMIT - 1;
        return endList > this.totalNumberPosts ? this.totalNumberPosts : endList;
    }

    //현재 페이지
    public int getCurrentPage() {
        return this.currentPage;
    }

    //하나의 페이지에 표시될 페이지 수
    public int getPageListLimit() {
        return this.PAGE_LIST_LIMIT;
    }

    //이전 페이지
    public int getPrevPage() {
        int prevPage = this.getCurrentPage() - 1;
        return prevPage < 1 ? 1 : prevPage;
    }

    //다음 페이지
    public int getNextPage() {
        int nextPage = this.getCurrentPage() + 1;
        return nextPage > this.getMaxPage() ? this.getMaxPage() : nextPage;
    }


}

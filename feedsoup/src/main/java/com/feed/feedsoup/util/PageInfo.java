package com.feed.feedsoup.util;

import com.feed.feedsoup.dto.SearchDTO;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageInfo {

    private int currentPage;//현재페이지
    private int totalNumberPosts;//총게시글수
    private int pagePostsLimit;//한 페이지 게시글 수
    private int pageListLimit;//한 페이지에 표시될 리스트 수

    public PageInfo(int currentPage, int totalNumberPosts) {
        this.currentPage = currentPage;
        this.totalNumberPosts = totalNumberPosts;
        this.pagePostsLimit = 10;
        this.pageListLimit = 10;
    }

    //전체 페이지 중 가장 마지막 페이지->101 / 10 = 10.1 => 11페이지
    public int getMaxPage(){
        return (int)Math.ceil((double)this.totalNumberPosts / this.pageListLimit);
    }

    //페이징 된 페이지 중 시작 페이지 -> 1... 11... 21... 31...
    public int getStartPage(){
        return (this.pagePostsLimit * ((this.currentPage - 1) / this.pagePostsLimit)) + 1;
    }

    //페이징 된 페이지 중 마지막 페이지->10, 20, 30, 40, ....
    public int getEndPage(){
        int endPage = this.getStartPage() + this.pagePostsLimit - 1;
        return endPage > this.getMaxPage() ? this.getMaxPage() : endPage;
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

    //현재 페이지에서 시작되는 게시글의 번호
    public int getStartList() {
        return (this.getCurrentPage() - 1) * this.pageListLimit + 1;
    }

    //현재 페이지의 마지막 게시글의 번호
    public int getEndList() {
        int endList = this.getStartList() + this.pageListLimit - 1;
        return endList > this.totalNumberPosts ? this.totalNumberPosts : endList;
    }

    public void pageSettings(){
        if (this.getCurrentPage() > this.getEndPage()){
            this.setCurrentPage(1);
        }
    }

    public int getLimit(){
        return this.getPageListLimit();
    }

    public int getOffset(){
        return (this.getCurrentPage() - 1) * this.getLimit();
    }

}

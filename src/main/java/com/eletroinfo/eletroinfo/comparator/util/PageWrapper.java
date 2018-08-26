package com.eletroinfo.eletroinfo.comparator.util;

import org.springframework.data.domain.Page;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class PageWrapper<T> {

    private Page<T> page;
    private UriComponentsBuilder uriBuilder;

    public PageWrapper(Page<T> page, HttpServletRequest httpServletRequest) {
        this.page = page;
        String httpUrl = httpServletRequest.getRequestURL().append(
                httpServletRequest.getQueryString() != null ? "?" + httpServletRequest.getQueryString() : "")
                .toString().replace("\\+", "%20").replaceAll("deleted", "");
        this.uriBuilder = ServletUriComponentsBuilder.fromHttpUrl(httpUrl);

    }

    public List<T> getContent(){
        return page.getContent();
    }

    public boolean isEmpty(){
        return page.getContent().isEmpty();
    }

    public int getCurrentPage(){
        return page.getNumber();
    }

    public boolean isFirst(){
        return page.isFirst();
    }

    public boolean isLast(){
        return page.isLast();
    }

    public int getTotalPages(){
        return page.getTotalPages();
    }

    public String urlForPage(int page){
        return uriBuilder.replaceQueryParam("page", page).build(true).encode().toUriString();
    }

}

package by.epam.ivanov.aviacompany.util;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class PagedListHolderImpl<T> {
    private boolean isNewPage;
    private List<T> list;
    private Integer pageSize;
    private int page;

    public PagedListHolderImpl(List<T> list) {
        this.list = list;
        this.pageSize = 10;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<T> getPageList() {
        return this.getList().subList(this.getFirstElementOfPage(), this.getLastElementOfPage() + 1);
    }

    private int getLastElementOfPage() {
        int endIndex = this.getPageSize() * (this.getPage() + 1);
        int size = this.list.size();
        return (endIndex > size ? size : endIndex) - 1;
    }

    private int getFirstElementOfPage() {
        return this.getPageSize() * this.getPage();
    }

    public int getNumberOfPages() {
        return (int) Math.ceil((double) this.list.size() / this.getPageSize());
    }

    public void setPadding(HttpServletRequest request, List<T> list){
        setList(list);
        page = 0;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e){
        }
        request.setAttribute("maxPages" , getNumberOfPages());
        String url = request.getRequestURI();
        request.setAttribute("currentPage", url + "?action=page");
        if (page < 1 || page > getNumberOfPages())
            page = 1;
        request.setAttribute("page", page);
        setPage(page-1);
        request.setAttribute("list", list);
    }
}

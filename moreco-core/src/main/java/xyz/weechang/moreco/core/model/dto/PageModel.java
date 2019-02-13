package xyz.weechang.moreco.core.model.dto;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;

/**
 * 分页 工具
 *
 * @author zhangwei
 * date 2018/10/28
 * time 21:10
 */
@Data
public class PageModel<T> implements Serializable {

    //当前页数
    private int currentPage;
    //每页记录数
    private int pageSize;
    //总记录数
    private long totalCount;
    //总页数
    private int totalPage;
    //列表数据
    private List<T> list;

    public PageModel() {
    }

    /**
     * 分页
     * @param currentPage 当前页数
     */
    public PageModel(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * 分页
     * @param currentPage 当前页数
     * @param pageSize 每页记录数
     */
    public PageModel(int currentPage, int pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    /**
     * 分页
     *
     * @param list       列表数据
     * @param totalCount 总记录数
     * @param pageSize   每页记录数
     * @param currPage   当前页数
     */
    public PageModel(List<T> list, long totalCount, int pageSize, int currPage) {
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.totalPage = (int) Math.ceil((double) totalCount / pageSize);
    }

    /**
     * 分页
     */
    public PageModel(Page<T> page) {
        this.list = page.getContent();
        this.totalCount = page.getTotalElements();
        this.pageSize = page.getSize();
        this.currentPage = page.getNumber() + 1;
        this.totalPage = page.getTotalPages();
    }

    /**
     * pageUtil 转 pageRequest
     *
     * @return pageRequest
     */
    public PageRequest toPageRequest() {
        currentPage = currentPage <= 1 ? 1 : currentPage;
        pageSize = pageSize <= 0 ? 10 : pageSize;
        return new PageRequest(currentPage - 1, pageSize);
    }

    /**
     * pageUtil 转 pageRequest
     *
     * @param sort 排序方式
     * @return pageRequest
     */
    public PageRequest toPageRequest(Sort sort) {
        currentPage = currentPage <= 1 ? 1 : currentPage;
        pageSize = pageSize <= 0 ? 10 : pageSize;
        return new PageRequest(currentPage - 1, pageSize, sort);
    }
}

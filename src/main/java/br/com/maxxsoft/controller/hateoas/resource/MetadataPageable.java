package br.com.maxxsoft.controller.hateoas.resource;

import com.google.common.base.Preconditions;

import java.util.HashMap;

/**
 * Created by master on 06/03/17.
 */
public class MetadataPageable {
    private Long page;
    private Long pageSize;
    private Long totalPages;
    private Long totalRecords;
    private HashMap<String, String> links;

    public MetadataPageable() {
        this.links = new HashMap();
    }

    public MetadataPageable(Long page, Long pageSize, Long totalPages, Long totalRecords) {
        this();
        Preconditions.checkNotNull(page);
        Preconditions.checkNotNull(pageSize);
        Preconditions.checkNotNull(totalPages);
        Preconditions.checkNotNull(totalRecords);
        this.page = page;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.totalRecords = totalRecords;
    }

    public MetadataPageable(Long page, Long pageSize, Long totalPages, Long totalRecords, HashMap<String, String> links) {
        this(page, pageSize, totalPages, totalRecords);
        this.links = links;
    }

    public Long getPage() {
        return page;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public Long getTotalPages() {
        return totalPages;
    }

    public Long getTotalRecords() {
        return totalRecords;
    }

    public HashMap<String, String> getLinks() {
        return links;
    }
}

package br.com.maxxsoft.controller.hateoas.resource;

import java.io.Serializable;
import java.util.List;

/**
 * Created by master on 06/03/17.
 */
public class PageableResource implements Serializable {

    private List records;
    private MetadataPageable _metadata;

    public PageableResource(List records, MetadataPageable _metadata) {
        this.records = records;
        this._metadata = _metadata;
    }

    public List getRecords() {
        return records;
    }

    public MetadataPageable get_metadata() {
        return _metadata;
    }
}

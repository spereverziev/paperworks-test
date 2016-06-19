package com.payworks.test.web.dto;

import javax.validation.constraints.NotNull;
import java.util.List;

public class IdsListDto {

    @NotNull
    private List<Long> idList;

    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }
}

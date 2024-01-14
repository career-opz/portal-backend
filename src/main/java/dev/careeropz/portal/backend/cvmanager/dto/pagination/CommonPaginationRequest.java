package dev.careeropz.portal.backend.cvmanager.dto.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonPaginationRequest {
    private int pageNo;
    private int pageSize;
    private String sortBy;
    private String sortDirection;

}

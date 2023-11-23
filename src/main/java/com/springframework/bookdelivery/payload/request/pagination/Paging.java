package com.springframework.bookdelivery.payload.request.pagination;

import org.springframework.data.domain.Pageable;

public interface Paging {

    Pageable toPageable();
}

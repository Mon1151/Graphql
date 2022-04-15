package com.example.graphsql.service.datafetcher;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseFetchData<T> extends DataFetcher<Page<T>> {
    Page<T> get(DataFetchingEnvironment environment);
    T findById (DataFetchingEnvironment environment);
    T create (DataFetchingEnvironment environment);
    T update (DataFetchingEnvironment environment);
    Boolean delete (DataFetchingEnvironment environment);
}

package com.github.tianjing.test.alibaba.webapp.repository;

import com.github.tianjing.test.alibaba.webapp.model.User2;
import in.togetu.tablestore.repository.BaseRepository;
import in.togetu.tablestore.repository.bean.Key;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface User2Repository  extends BaseRepository<User2, Key> {
}

package com.github.tianjing.test.alibaba.webapp.repository;

import com.github.tianjing.test.alibaba.webapp.model.User;
import in.togetu.tablestore.repository.bean.Key;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository  extends PagingAndSortingRepository<User, Key> {
}

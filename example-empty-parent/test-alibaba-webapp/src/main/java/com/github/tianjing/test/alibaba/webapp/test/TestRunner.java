package com.github.tianjing.test.alibaba.webapp.test;

import com.github.tianjing.test.alibaba.webapp.model.User;
import com.github.tianjing.test.alibaba.webapp.model.User2;
import com.github.tianjing.test.alibaba.webapp.repository.User2Repository;
import com.github.tianjing.test.alibaba.webapp.repository.UserRepository;
import in.togetu.tablestore.repository.bean.Key;
import in.togetu.tablestore.repository.bean.KeyRange;
import in.togetu.tablestore.repository.bean.TableStorePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class TestRunner implements ApplicationRunner {

    @Autowired
    private User2Repository user2Repository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        TableStorePage vPage = new TableStorePage(1, 10);
        vPage.setKeyRange(new KeyRange() {{
            setTo(new Key("ID_", "2"));
            setFrom(new Key("ID_", "1"));

        }});
        Iterable<User> vUserList = userRepository.findAll(vPage);
        Iterable<User2> vUser2List = user2Repository.findAll(vPage);
        vUserList.forEach((item) -> {
            System.out.println(item.toString());
        });

        vUser2List.forEach((item) -> {
            System.out.println(item.toString());
        });

        System.out.println(vUserList);
        System.out.println(vUser2List);

    }
}

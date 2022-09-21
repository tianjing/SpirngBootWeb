package com.github.tianjing.test.alibaba.webapp.test;

import com.github.tianjing.test.alibaba.webapp.model.User2;
import com.github.tianjing.test.alibaba.webapp.repository.User2Repository;
import com.github.tianjing.test.alibaba.webapp.repository.UserRepository;
import in.togetu.tablestore.repository.bean.Key;
import in.togetu.tablestore.repository.bean.KeyRange;
import in.togetu.tablestore.repository.bean.TableStorePage;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

//@Component
public class DataTestRunner implements ApplicationRunner {

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
        //Iterable<User> vUserList = userRepository.findAll(vPage);
        Iterable<User2> vUser2List = user2Repository.findAll(vPage);
        Iterable<User2> vUser2List1 = user2Repository.findAll();
        Sort vSort = Sort.by("ID_");
        Iterable<User2> vUser2List2 = user2Repository.findAll(vSort);
        long fd = user2Repository.count();
        System.out.println(fd);
        Optional<User2> vUser2findById = user2Repository.findById(new Key("ID_", "1"));
        System.out.println(vUser2findById.get());

        boolean vUser2existsById = user2Repository.existsById(new Key("ID_", "1"));
        System.out.println(vUser2existsById);

        vUser2List.forEach((item) -> {
            System.out.println(item.toString());
        });

        vUser2List1.forEach((item) -> {
            System.out.println(item.toString());
        });
        vUser2List2.forEach((item) -> {
            System.out.println(item.toString());
        });

        vUser2findById.get().setName(DateFormatUtils.format(new Date(), "yyyy-MM-DD hh:mm:ss"));
        user2Repository.update(vUser2findById.get(), new ArrayList() {{
            add("NAME");
        }});

        User2 vSaveUser2 = new User2();
        vSaveUser2.setId("321321321321");
        vSaveUser2.setName("fdaafdasfdasdfsa");
        user2Repository.save(vSaveUser2);


        User2 vSaveUser11 = new User2();
        vSaveUser11.setId("777777777777777");
        vSaveUser11.setName("fdf放大放大是发大水发大水");
        User2 vSaveUser22 = new User2();
        vSaveUser22.setId("888888888888888888");
        vSaveUser22.setName("发打发打发打发士大夫");
        List<User2> vUser2SaveList = new ArrayList() {{
            add(vSaveUser11);
            add(vSaveUser22);
        }};
        user2Repository.saveAll(vUser2SaveList);

        user2Repository.delete(vSaveUser2);
        user2Repository.deleteAll(vUser2SaveList);
        System.out.println("===============================================");
    }
}

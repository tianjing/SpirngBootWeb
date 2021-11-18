package com.github.tianjing.test.db.webapp.bo;



import com.github.tianjing.test.db.webapp.mybatis.db1.MyUserDO;

import java.util.Set;

/**
 * @author 田径
 * @Title
 * @Description
 * @date 12:43
 */
public class UserBO {
    private MyUserDO mUser;
    private Set<String> mRole;


    public MyUserDO getUser() {
        return mUser;
    }

    public void setUser(MyUserDO pUser) {
        mUser = pUser;
    }

    public Set<String> getRole() {
        return mRole;
    }

    public void setRole(Set<String> pRole) {
        mRole = pRole;
    }
}

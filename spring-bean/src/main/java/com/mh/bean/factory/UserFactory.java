package com.mh.bean.factory;

import com.mh.ioc.model.User;

/**
 * user工厂类
 */
public interface UserFactory {

    default User createUser(){
        return User.createUser();
    }

}

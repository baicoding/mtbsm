package org.msgtu.sys.service.intf;

import org.msgtu.sys.entry.User;

/**
 * Created by Wonshine on 2017-07-07.
 */
public interface UserService {
    User findByUserLogin(String userlogin);
}

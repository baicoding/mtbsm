package org.msgtu.sys.service.intf;

import org.msgtu.sys.entry.User;

/**
 * Created by Wonshine on 2017-09-06.
 */
public interface TokenManagerService {
    String createToken(User user, String ip);
    boolean checkToken(String token);
    boolean checkToken(String token, User user, String ip);
    void deleteToken(String token);
    User getUserInfo(String token);
}

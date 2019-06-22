package org.msgtu.sys.service.impl;

import org.msgtu.common.utils.DES;
import org.msgtu.sys.entry.User;
import org.msgtu.sys.service.intf.TokenManagerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by Wonshine on 2018-4-3.
 */
@Service
public class TokenManagerServiceImpl implements TokenManagerService {

    @Value("${mtbsm.token.timeout}")
    private Long expiresMinutes;

    private static String key = "msgtukey";

    @Resource
    private RedisTemplate<String, User> redisTemplate;

    @Override
    public String createToken(User user, String ip) {
        String token = UUID.randomUUID().toString().replace("-", "");
        String data = String.format("%s-%s-%tQ", token, ip, Calendar.getInstance().getTime());
        try {
            String value = DES.encrypt(data, key);
            this.redisTemplate.boundValueOps(token).set(user, expiresMinutes, TimeUnit.MINUTES);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public boolean checkToken(String token) {
        return checkToken(token, null, null);
    }

    @Override
    public boolean checkToken(String token, User user, String ip) {
        try {
            String[] data = DES.decrypt(token, key).split("-");
            String tv = data[0];
            String ip2 = data[1];
            User obj = this.redisTemplate.boundValueOps(tv).get();
            if (obj != null && ((user != null && user.getUserId().equals(obj.getUserId())) || user == null)
                    && ((ip != null && ip.equals(ip2)) || ip == null)) {
                this.redisTemplate.boundValueOps(tv).expire(expiresMinutes, TimeUnit.MINUTES);//验证成功，延长token的有效期
                return true;
            } else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void deleteToken(String token) {
        this.redisTemplate.delete(token);
    }

    @Override
    public User getUserInfo(String token) {
        try {
            return this.redisTemplate.boundValueOps(DES.decrypt(token, key).split("-")[0]).get();
        } catch (Exception e) {
            return null;
        }
    }
}

package org.msgtu.sys.mapper.relational;

import org.apache.ibatis.annotations.Mapper;
import org.msgtu.sys.entry.User;

/**
 * Created by Wonshine on 2017-07-06.
 */
@Mapper
public interface UserMapper {
    User findByUserLogin(String userLogin);
}

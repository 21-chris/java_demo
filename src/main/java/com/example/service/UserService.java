package com.example.service;
import com.example.common.Result;
import com.example.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User findUserById(int id);
    User findUserByName(User user);

    int insertUser(User user);
    int updateUser(User user);
    Result loginUser(User user);

}


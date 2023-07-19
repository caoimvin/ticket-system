package com.caoimvin.security.user;

import java.util.List;

public interface IUserService {
    List<UserRecord> getAllUsers();
    User getUser(Long id);
}

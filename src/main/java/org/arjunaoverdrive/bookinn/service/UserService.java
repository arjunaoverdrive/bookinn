package org.arjunaoverdrive.bookinn.service;

import org.arjunaoverdrive.bookinn.domain.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User findById(Long id);
    User findByUsername (String username);

    Page<User> findUsersPage(Pageable pageable);
    User createUser(User user);
    User updateUser(User user);
    void deleteById(Long id);

}

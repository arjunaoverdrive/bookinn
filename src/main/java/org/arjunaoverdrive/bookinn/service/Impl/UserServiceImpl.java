package org.arjunaoverdrive.bookinn.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.arjunaoverdrive.bookinn.domain.dao.UserRepository;
import org.arjunaoverdrive.bookinn.domain.entities.User;
import org.arjunaoverdrive.bookinn.exception.CannotPersistEntityException;
import org.arjunaoverdrive.bookinn.exception.EntityNotFoundException;
import org.arjunaoverdrive.bookinn.service.UserService;
import org.arjunaoverdrive.bookinn.util.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                MessageFormat.format("User with ID {0} not found", id)));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByLogin(username).orElseThrow(() ->
                new EntityNotFoundException(
                        MessageFormat.format("User with username {0} not found", username)));
    }

    @Override
    public Page<User> findUsersPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User createUser(User user) {
        return save(user);
    }

    @Override
    public User updateUser(User user) {
        User fromDb = findById(user.getId());
        BeanUtils.copyNonNullProperties(user, fromDb);

        return save(fromDb);
    }

    private User save(User user){
        try{
            user = userRepository.save(user);
        }catch (Exception e){
            throw new CannotPersistEntityException(e.getMessage());
        }
        return user;
    }
    @Override
    public void deleteById(Long id) {
        User toDelete = findById(id);
        log.info("Deleting user {}", toDelete);
        userRepository.delete(toDelete);
    }
}
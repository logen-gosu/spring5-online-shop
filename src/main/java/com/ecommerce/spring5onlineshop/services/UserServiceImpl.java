package com.ecommerce.spring5onlineshop.services;

import com.ecommerce.spring5onlineshop.commands.UserCommand;
import com.ecommerce.spring5onlineshop.converters.UserCommandToUser;
import com.ecommerce.spring5onlineshop.converters.UserToUserCommand;
import com.ecommerce.spring5onlineshop.model.ShopUserDetails;
import com.ecommerce.spring5onlineshop.model.User;
import com.ecommerce.spring5onlineshop.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserCommandToUser userCommandToUser;
    private final UserToUserCommand userToUserCommand;

    public UserServiceImpl(UserRepository userRepository, UserCommandToUser userCommandToUser, UserToUserCommand userToUserCommand) {
        this.userRepository = userRepository;
        this.userCommandToUser = userCommandToUser;
        this.userToUserCommand = userToUserCommand;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.getUserByUsername(username);

        if (user != null) {
            return new ShopUserDetails(user);
        }

        throw new UsernameNotFoundException("User '" + username + "' not found");
    }

    @Override
    public User findUserByUsername(String username) {

        return userRepository.getUserByUsername(username);
    }

    @Override
    @Transactional
    public UserCommand saveUserCommand(UserCommand command) {
        User detachedUser = userCommandToUser.convert(command);

        User savedUser = userRepository.save(detachedUser);
        log.debug("Saved user ID: " + savedUser.getId());
        return userToUserCommand.convert(savedUser);
    }
}

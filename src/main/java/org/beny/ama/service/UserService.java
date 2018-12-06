package org.beny.ama.service;

import org.beny.ama.model.Token;
import org.beny.ama.model.User;
import org.beny.ama.model.UserContext;
import org.beny.ama.repository.UserRepository;
import org.beny.ama.util.AmaException;
import org.beny.ama.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService extends BaseService<User> implements UserDetailsService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public UserContext loadUserByUsername(String email) throws UsernameNotFoundException {
        return new UserContext(repository.findOneByEmail(email).orElseThrow(() -> new UsernameNotFoundException("The e-mail does not exist in database")));
    }

    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    public void createUser(User user) throws AmaException {
        create(user, User.Type.U);
    }

    public void createBusiness(User user) throws AmaException {
        create(user, User.Type.B);
    }

    private void create(User user, User.Type type) throws AmaException {
        if (existsByEmail(user.getEmail())) throw new AmaException(AmaException.AmaErrors.USER_EXISTS);
        user.setType(type);
        user.setActive(false);
        user.setToken(UUID.randomUUID().toString());
        user = saveAndFlush(user);
        MailUtil.sendActivationEmail(user.getEmail(), user.getToken().getToken());
    }

    public void activate(User user) {
        user.setActive(true);
        user.setToken((Token) null);
        save(user);
    }

    public void activate(UserContext ctx, Long userId) throws AmaException {
        User user = findOneAdmin(ctx, userId);
        user.setActive(true);
        user.setToken((Token) null);
        save(user);
    }

    public User findByEmail(String email) throws AmaException {
        return repository.findOneByEmail(email).orElseThrow(() -> new AmaException(AmaException.AmaErrors.EMAIL_NOT_EXISTS));
    }

    public void resendToken(User user) throws AmaException {
        if (user.isActive()) {
            throw new AmaException(AmaException.AmaErrors.USER_ALREADY_ACTIVE);
        }
        user.setToken(UUID.randomUUID().toString());
        user = saveAndFlush(user);
        MailUtil.sendActivationEmail(user.getEmail(), user.getToken().getToken());
    }

}

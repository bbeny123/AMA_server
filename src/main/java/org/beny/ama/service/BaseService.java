package org.beny.ama.service;

import org.beny.ama.model.UserContext;
import org.beny.ama.repository.BaseRepository;
import org.beny.ama.util.AmaException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public abstract class BaseService<T> {

    private final BaseRepository<T> repository;

    public BaseService(BaseRepository<T> repository) {
        this.repository = repository;
    }

    @Transactional
    void saveAdmin(UserContext ctx, T data) throws AmaException {
        checkAdmin(ctx);
        save(data);
    }

    @Transactional
    void save(T data) {
        repository.save(data);
    }

    @Transactional
    public T saveAndFlushAdmin(UserContext ctx, T data) throws AmaException {
        checkAdmin(ctx);
        return saveAndFlush(data);
    }

    @Transactional
    T saveAndFlush(T data) {
        return repository.saveAndFlush(data);
    }

    public List<T> findAllAdmin(UserContext ctx) throws AmaException {
        checkAdmin(ctx);
        return findAll();
    }

    private List<T> findAll() {
        return repository.findAll();
    }

    T findOneAdmin(UserContext ctx, Long id) throws AmaException {
        checkAdmin(ctx);
        return findOne(id);
    }


    T findOne(Long id) throws AmaException {
        return repository.findById(id).orElseThrow(() -> new AmaException(AmaException.AmaErrors.ITEM_NOT_EXISTS));
    }

    void checkAdmin(UserContext ctx) throws AmaException {
        if (!ctx.isAdmin()) throw new AmaException(AmaException.AmaErrors.UNAUTHORIZED);
    }

}

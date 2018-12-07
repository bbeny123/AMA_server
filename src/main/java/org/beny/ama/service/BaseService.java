package org.beny.ama.service;

import org.beny.ama.model.UserContext;
import org.beny.ama.repository.BaseRepository;
import org.beny.ama.util.AmaException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class BaseService<T, U extends BaseRepository<T>> {

    private final U repository;

    public BaseService(U repository) {
        this.repository = repository;
    }

    @Transactional
    void save(T data) {
        repository.save(data);
    }

    @Transactional
    void saveAdmin(UserContext ctx, T data) throws AmaException {
        checkAdmin(ctx);
        save(data);
    }

    @Transactional
    void saveBusiness(UserContext ctx, T data) throws AmaException {
        checkBusiness(ctx);
        save(data);
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

    public List<T> findAll() {
        return repository.findAll();
    }

    public T findOne(Long id) throws AmaException {
        return repository.findById(id).orElseThrow(() -> new AmaException(AmaException.AmaErrors.ITEM_NOT_EXISTS));
    }

    public T findOneAdmin(UserContext ctx, Long id) throws AmaException {
        checkAdmin(ctx);
        return findOne(id);
    }

    public T findOneBusiness(UserContext ctx, Long id) throws AmaException {
        checkBusiness(ctx);
        return findOne(id);
    }

    void checkAdmin(UserContext ctx) throws AmaException {
        if (!ctx.isAdmin()) throw new AmaException(AmaException.AmaErrors.FORBIDDEN);
    }

    void checkBusiness(UserContext ctx) throws AmaException {
        if (!ctx.isAdmin() && !ctx.isBusiness()) throw new AmaException(AmaException.AmaErrors.FORBIDDEN);
    }

    protected U getRepository() {
        return repository;
    }
}

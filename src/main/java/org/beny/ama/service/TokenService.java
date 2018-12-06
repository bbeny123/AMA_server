package org.beny.ama.service;

import org.beny.ama.util.AmaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.beny.ama.model.Token;
import org.beny.ama.repository.TokenRepository;

@Service
public class TokenService extends BaseService<Token> {

    private final TokenRepository repository;

    @Autowired
    public TokenService(TokenRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Token findByToken(String token) throws AmaException {
        return repository.findByToken(token).orElseThrow(() -> new AmaException(AmaException.AmaErrors.TOKEN_NOT_EXISTS));
    }

}

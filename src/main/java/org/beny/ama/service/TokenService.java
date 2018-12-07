package org.beny.ama.service;

import org.beny.ama.model.Token;
import org.beny.ama.repository.TokenRepository;
import org.beny.ama.util.AmaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService extends BaseService<Token, TokenRepository> {

    @Autowired
    public TokenService(TokenRepository repository) {
        super(repository);
    }

    public Token findByToken(String token) throws AmaException {
        return getRepository().findByToken(token).orElseThrow(() -> new AmaException(AmaException.AmaErrors.TOKEN_NOT_EXISTS));
    }

}

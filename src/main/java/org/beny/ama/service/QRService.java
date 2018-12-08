package org.beny.ama.service;

import org.beny.ama.model.QR;
import org.beny.ama.model.UserContext;
import org.beny.ama.repository.QRRepository;
import org.beny.ama.util.AmaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QRService extends BaseService<QR, QRRepository> {

    @Autowired
    public QRService(QRRepository repository) {
        super(repository);
    }

    public void create(UserContext ctx, QR qr) throws AmaException {
        qr.setUserId(ctx.getUserId());
        saveBusiness(ctx, qr);
    }

    public void modify(UserContext ctx, QR qr) throws AmaException {
        if (!ctx.isAdmin() && !qr.getUserId().equals(ctx.getUserId())) throw new AmaException(AmaException.AmaErrors.FORBIDDEN);
        saveBusiness(ctx, qr);
    }

    public List<QR> findOwn(UserContext ctx) {
        return getRepository().findByUserId(ctx.getUserId());
    }

    public QR findOneOwn(UserContext ctx, Long id) {
        QR qr = findOne(id);
        if (!ctx.isAdmin() && !qr.getUserId().equals(ctx.getUserId())) throw new AmaException(AmaException.AmaErrors.FORBIDDEN);
        return qr;
    }

}

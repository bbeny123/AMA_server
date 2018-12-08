package org.beny.ama.service;

import org.beny.ama.model.QR;
import org.beny.ama.model.UserContext;
import org.beny.ama.model.UserQR;
import org.beny.ama.repository.QRRepository;
import org.beny.ama.util.AmaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
public class QRService extends BaseService<QR, QRRepository> {

    private final PointsService pointsService;

    @Autowired
    public QRService(QRRepository repository, PointsService pointsService) {
        super(repository);
        this.pointsService = pointsService;
    }

    public void create(UserContext ctx, QR qr) throws AmaException {
        qr.setUserId(ctx.getUserId());
        saveBusiness(ctx, qr);
    }

    public void modify(UserContext ctx, QR qr) throws AmaException {
        if (!ctx.isAdmin() && !qr.getUserId().equals(ctx.getUserId()))
            throw new AmaException(AmaException.AmaErrors.FORBIDDEN);
        saveBusiness(ctx, qr);
    }

    public void changeStatus(UserContext ctx, Long id, boolean active) throws AmaException {
        QR qr = findOneBusiness(ctx, id);
        qr.setActive(active);
        modify(ctx, qr);
    }

    public void delete(UserContext ctx, Long id) throws AmaException {
        QR qr = findOneBusiness(ctx, id);
        if (!ctx.isAdmin() && !qr.getUserId().equals(ctx.getUserId()))
            throw new AmaException(AmaException.AmaErrors.FORBIDDEN);
        getRepository().delete(qr);
    }

    public List<QR> findOwn(UserContext ctx) {
        return getRepository().findByUserId(ctx.getUserId());
    }

    public QR findOneOwn(UserContext ctx, Long id) {
        QR qr = findOne(id);
        if (!ctx.isAdmin() && !qr.getUserId().equals(ctx.getUserId()))
            throw new AmaException(AmaException.AmaErrors.FORBIDDEN);
        return qr;
    }

    @Transactional
    public void scan(UserContext ctx, Long id, Long businessId) throws AmaException {
        QR qr = getRepository().findOneById(id);

        if (!qr.isActive())
            throw new AmaException(AmaException.AmaErrors.QR_INACTIVE);
        if (qr.getEndDate() != null && LocalDate.now().isAfter(qr.getEndDate()))
            throw new AmaException(AmaException.AmaErrors.QR_ENDED);
        if (!qr.getUserId().equals(businessId))
            throw new AmaException(AmaException.AmaErrors.INTERNAL_SERVER_ERROR);

        UserQR userQR = qr.getUsers().stream().filter(q -> q.getUserId().equals(ctx.getUserId())).max(Comparator.comparing(UserQR::getDate)).orElse(null);

        if (userQR != null && QR.Useability.O == qr.getUseability())
            throw new AmaException(AmaException.AmaErrors.QR_MORE_THAN_ONCE);
        else if (userQR != null && !LocalDate.now().isAfter(userQR.getDate()))
            throw new AmaException(AmaException.AmaErrors.QR_MORE_THAN_ONCE_PER_DAY);

        pointsService.addPoints(ctx.getUserId(), qr.getUserId(), qr.getPoints());

        userQR = new UserQR();
        userQR.setUserId(ctx.getUserId());
        userQR.setQrId(id);
        qr.getUsers().add(userQR);
        save(qr);
    }

}

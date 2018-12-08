package org.beny.ama.service;

import org.beny.ama.model.Points;
import org.beny.ama.model.UserContext;
import org.beny.ama.repository.PointsRepository;
import org.beny.ama.util.AmaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PointsService extends BaseService<Points, PointsRepository> {

    @Autowired
    public PointsService(PointsRepository repository) {
        super(repository);
    }

    public Map<Long, Long> ownPoints(UserContext ctx) {
        return getRepository().findByUserId(ctx.getUserId()).stream().collect(Collectors.toMap(Points::getBusinessUserId, Points::getPoints));
    }

    public void addPoints(Long userId, Long businessId, Long value) {
        Points points = getRepository().findOneByUserIdAndBusinessUserId(userId, businessId).orElse(new Points());
        if (points.getUserId() == null) {
            points.setUserId(userId);
            points.setBusinessUserId(businessId);
        }
        points.setPoints(points.getPoints() + value);
        save(points);
    }

    public void subtractPoints(Long userId, Long businessId, Long value) throws AmaException {
        Points points = getRepository().findOneByUserIdAndBusinessUserId(userId, businessId).orElse(null);

        if (points == null || points.getPoints() < value)
            throw new AmaException(AmaException.AmaErrors.COUPON_INSUFFICIENT_POINTS);

        points.setPoints(points.getPoints() - value);
        save(points);
    }

}

package org.beny.ama.service;

import org.beny.ama.model.Points;
import org.beny.ama.repository.PointsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointsService extends BaseService<Points, PointsRepository> {

    @Autowired
    public PointsService(PointsRepository repository) {
        super(repository);
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

}

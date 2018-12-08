package org.beny.ama.dto.response;

import org.beny.ama.model.User;

public class PointsResponse extends UserListResponse {

    private Long points;

    public PointsResponse(User user, Long points) {
        super(user);
        this.points = points;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }
}

package org.beny.ama.dto.request;

import javax.validation.constraints.NotEmpty;

public class ScanRequest {

    private String request;

    @NotEmpty
    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

}

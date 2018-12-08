package org.beny.ama.dto.request;

import org.beny.ama.util.AmaException;

import javax.validation.constraints.NotEmpty;

public class PasswordRequest {

    private String currentPassword;
    private String newPassword;
    private String confirmedPassword;

    @NotEmpty
    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    @NotEmpty
    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @NotEmpty
    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }

    public void isValid() throws AmaException {
        if (!newPassword.equals(confirmedPassword)) throw new AmaException(AmaException.AmaErrors.PASSWORD_NOT_MATCH);
        if (currentPassword.equals(newPassword)) throw new AmaException(AmaException.AmaErrors.PASSWORD_SAME_AS_OLD);
    }
}

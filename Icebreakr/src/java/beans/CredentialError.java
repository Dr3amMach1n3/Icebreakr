/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.beans.*;
import java.io.Serializable;
/**
 *
 * @author Pierce
 */
public class CredentialError implements Serializable{
    private boolean nameErr;
    private boolean passErr;
    private boolean emptyErr;
    
    public CredentialError() {
        nameErr = false;
        passErr = false;
        emptyErr = false;
    }

    public void setEmptyErr(boolean emptyErr) {
        this.emptyErr = emptyErr;
    }

    public boolean isEmptyErr() {
        return emptyErr;
    }

    public void setNameErr(boolean nameErr) {
        this.nameErr = nameErr;
    }

    public void setPassErr(boolean passErr) {
        this.passErr = passErr;
    }

    public boolean isNameErr() {
        return nameErr;
    }

    public boolean isPassErr() {
        return passErr;
    }
}

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
    
    public CredentialError() {
        nameErr = false;
        passErr = false;
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

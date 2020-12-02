/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.beans.*;
import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author Pierce
 */
public class Messages implements Serializable{
    
    private String target;
    private ArrayList<String> sender;
    private ArrayList<String> receiver;
    private ArrayList<String> content;
    
    public Messages() {
        sender = new ArrayList<String>();
        receiver = new ArrayList<String>();
        content = new ArrayList<String>();
    }
    
    public void addTarget(String s) {
        target = s;
    }
    
    public void addSender(String s) {
        sender.add(s);
    }
    
    public void addReceiver(String r) {
        receiver.add(r);
    }
    
    public void addContent(String c) {
        content.add(c);
    }

    public String getSender(int i) {
        return sender.get(i);
    }
    
    public String getReceiver(int i) {
        return receiver.get(i);
    }
    
    public String getContent(int i) {
        return content.get(i);
    }
}

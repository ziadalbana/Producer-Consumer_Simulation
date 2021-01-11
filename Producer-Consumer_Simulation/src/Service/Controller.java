/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

/**
 *
 * @author Alex
 */
public class Controller {
    Strucuture data=new Strucuture();
    public void addQueue(String order){
        data.addQueue(order);
    }
    public void fillqueueZero(String colour){
        data.fillqueueZero(colour);
    }
    public void setConnection(String from,String to){
        data.setConnection(from, to);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl;

/**
 *
 * @author David
 */
public class Nt {
    
    String value;
    String valueNoHtml = "";
    public Nt(String valor){
        this.value=valor;
    }
    
    public void setValueNoHtml(String s){
        this.valueNoHtml = s;
    }
}

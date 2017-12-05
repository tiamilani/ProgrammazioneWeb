/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.utility.tris;

/**
 *
 * @author mattia
 */
public class tris<L,C,R> {
    private L l;
    private C c;
    private R r;
    public tris(L l,C c, R r){
        this.l = l;
        this.c = c;
        this.r = r;
    }
    public L getL(){ return l; }
    public R getR(){ return r; }
    public C getC() { return c; }
    public void setC(C c) { this.c = c; }
    public void setL(L l){ this.l = l; }
    public void setR(R r){ this.r = r; }
}

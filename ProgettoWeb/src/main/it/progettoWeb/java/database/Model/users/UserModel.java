/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Model.UserModel;

/**
 * Calsse utilizzata per modellizzare la tabella User del db
 * @author mattia
 */

public class UserModel {
    
    private int id;
    private String nome;
    private String cognome;
    private String mail;
    private String password;
    private int avatar;
    private double valutazione;
    private int UtenteType;
    private boolean emailConfermata;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public double getValutazione() {
        return valutazione;
    }

    public void setValutazione(double valutazione) {
        this.valutazione = valutazione;
    }

    public int getUtenteType() {
        return UtenteType;
    }

    public void setUtenteType(int UtenteType) {
        this.UtenteType = UtenteType;
    }

    public boolean isEmailConfermata() {
        return emailConfermata;
    }

    public void setEmailConfermata(boolean emailConfermata) {
        this.emailConfermata = emailConfermata;
    }
    
    @Override
    public String toString() {
        return "User [id= "+getId()+", nome= "+getNome()+", cognome= "+getCognome()+", mail= "+getMail()+", password= "+getPassword()+", avatar= "+getAvatar()+", valutazione= "+getValutazione()+", UtenteType= "+getUtenteType()+", emailConfermata= "+isEmailConfermata()+"]";
    }  
}

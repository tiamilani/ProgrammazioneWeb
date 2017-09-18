/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Model.Utente;

/**
 * classe utilizzata per gestire gli oggetti utente
 * @author mattia
 */
public class ModelloUtente {
      
    /**
     * Variabile utilizzata per identificare l'utente
     */
    private int id;
    /**
     * Variabile utilizzata per il nome dell'utente
     */
    private String nome;
    /**
     * Variabile utilizzata per il cognome
     */
    private String cognome;
    /**
     * Variabile utilizzata per la mail dell'utente
     */
    private String mail;
    /**
     * Variabile utilizzata per salvare la password in md5 
     */
    private String password;
    /**
     * Funzione utilizzata per il percorso all'immagine dell'utente
     */
    private String avatar;
    /**
     * Funzione utilizzata per la valutazione dell'utente
     */
    private double valutazione;
    /**
     * Funzione utilizzata per il mantenere il tipo di utente
     */
    private int UtenteType;
    /**
     * Funzione utilizzata per la conferma dell'email
     */
    private boolean emailConfermata;

    /**
     * Funzione utilizzata per ottenre l'id dell'utente
     * @return int id
     */
    public int getId() {
        return id;
    }

    /**
     * Funzione utilizzata per settare l'id dell'utente
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Funzione utilizzata per ottenre il nome dell'utente
     * @return String nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Funzione utilizzata per settare il nome dell'utente
     * @param nome 
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Funzione utilizzata per ottenre il cognome dell'utente
     * @return String cognome
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Funzione utilizzata per settare il cognome dell'utente
     * @param cognome 
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Funzione utilizzata per ottenre l'email dell'utente
     * @return String mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * Funzione utilizzata per settare l'email dell'utente
     * @param mail 
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Funzione utilizzata per ottenre la password dell'utente in md5
     * @return String password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Funzione utilizzata per settare la password dell'utente in md5
     * @param password 
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Funzione per ottenre l'avatar dell'utente
     * @return String avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * Funzione per settare l'avatar dell'utente
     * @param avatar 
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * Funzione utilizzata per otterne la valutazione dell'utente
     * @return doube valutazione
     */
    public double getValutazione() {
        return valutazione;
    }

    /**
     * Funzione utilizzata per settare la valutazione dell'utente
     * @param valutazione 
     */
    public void setValutazione(double valutazione) {
        this.valutazione = valutazione;
    }

    /**
     * Funzione utilizzata per ottenre il tipo di utente
     * @return int UtenteType
     */
    public int getUtenteType() {
        return UtenteType;
    }

    /**
     * Funzione utilizzata per settare il tipo di utente
     * @param UtenteType 
     */
    public void setUtenteType(int UtenteType) {
        this.UtenteType = UtenteType;
    }

    /**
     * Funzione utilizzata per ottenre se l'email è confermata o meno
     * @return boolean emailConfermata
     */
    public boolean isEmailConfermata() {
        return emailConfermata;
    }

    /**
     * Funzione utilizzata per settare se l'email è confermata o meno
     * @param emailConfermata 
     */
    public void setEmailConfermata(boolean emailConfermata) {
        this.emailConfermata = emailConfermata;
    }
    
    /**
     * Funzione utilizzata per ritornare una stringa contenente le informazioni dell'utente
     * @return 
     */
    @Override
    public String toString() {
        return "User [id= "+getId()+", nome= "+getNome()+", cognome= "+getCognome()+", mail= "+getMail()+", password= "+getPassword()+", avatar= "+getAvatar()+", valutazione= "+getValutazione()+", UtenteType= "+getUtenteType()+", emailConfermata= "+isEmailConfermata()+"]";
    }  
}

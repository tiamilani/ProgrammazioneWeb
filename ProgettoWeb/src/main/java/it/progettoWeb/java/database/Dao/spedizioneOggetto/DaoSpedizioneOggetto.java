/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Dao.spedizioneOggetto;

/**
 * Classe utilizzta per gestire gli accessi al database della tabella SpedizioneOggetto
 * @author mattia
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import it.progettoWeb.java.database.Util.DbUtil;

public class DaoSpedizioneOggetto {
    
    private Connection connection;
    
    public DaoSpedizioneOggetto() {
        connection = DbUtil.getConnection();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.query.objects;
/**
 *
 * @author mattia
 */
public class objectsQuery {
    public static String hello() {
        return "Hello from" + objectsQuery.class.toString();
    }
    
    
    public static String selectObjectByName(String nomeDownCase)
    {
        return "SELECT * FROM Oggetto WHERE nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    
    public static String selectObjectByCategory(int idCategoria)
    {
        return "SELECT * FROM Oggetto WHERE categoria = " + idCategoria + ";";
    }
    
    
    public static String selectObjectByCategoryAndName(int idCategoria, String nomeDownCase)
    {
        return "SELECT * FROM Oggetto WHERE categoria=" + idCategoria + " AND nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    
    public static String selectObjectLowerThanPrice(double prezzo)
    {
        return "SELECT * FROM Oggetto WHERE prezzo >= " + prezzo + ";";
    }
    
    
    public static String selectObjectHigherThanPrice(double prezzo)
    {
        return "SELECT * FROM Oggetto WHERE prezzo <= " + prezzo + ";";
    }
    
    
    public static String selectObjectBetweenPrices(double prezzoMin, double prezzoMax)
    {
        return "SELECT * FROM Oggetto WHERE prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + ";";
    }
    
    
    public static String selectObjectByCategoryAndHigherThanPrice(int idCategoria, double prezzoMin)
    {
        return "SELECT * FROM Oggetto WHERE categoria=" + idCategoria + " AND prezzo >= " + prezzoMin + ";";
    }
    
    
    public static String selectObjectByCategoryAndLowerThanPrice(int idCategoria, double prezzoMax)
    {
        return "SELECT * FROM Oggetto WHERE categoria=" + idCategoria + " AND prezzo <= " + prezzoMax + ";";
    }
    
    
    public static String selectObjectByCategoryAndBetweenPrices(int idCategoria, double prezzoMin, double prezzoMax)
    {
        return "SELECT * FROM Oggetto WHERE categoria=" + idCategoria + " AND prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + ";";
    }
    
    
    public static String selectObjectByNameAndHigherThanPrice(String nomeDownCase, double prezzoMin)
    {
        return "SELECT * FROM Oggetto WHERE prezzo >= " + prezzoMin + " AND nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    
    public static String selectObjectByNameAndLowerThanPrice(String nomeDownCase, double prezzoMax)
    {
        return "SELECT * FROM Oggetto WHERE prezzo <= " + prezzoMax + " AND nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    
    public static String selectObjectByNameAndBetweenPrices(String nomeDownCase, double prezzoMin, double prezzoMax)
    {
        return "SELECT * FROM Oggetto WHERE prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + " AND nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    
    public static String selectObjectByCategoryAndNameAndHigherThanPrice(int idCategoria, String nomeDownCase, double prezzoMin)
    {
        return "SELECT * FROM Oggetto WHERE categoria=" + idCategoria + " AND prezzo >= " + prezzoMin + " AND nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    
    public static String selectObjectByCategoryAndNameAndLowerThanPrice(int idCategoria, String nomeDownCase, double prezzoMax)
    {
        return "SELECT * FROM Oggetto WHERE categoria=" + idCategoria + " AND prezzo <= " + prezzoMax + " AND nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    
    public static String selectObjectByCategoryAndNameAndBetweenPrices(int idCategoria, String nomeDownCase, double prezzoMin, double prezzoMax)
    {
        return "SELECT * FROM Oggetto WHERE categoria=" + idCategoria + " AND prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + " AND nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    
    public static String selectObjectByShop(int idNegozio)
    {
        return "SELECT * FROM Oggetto WHERE Oggetto.idNegozio = " + idNegozio + ";";
    }
    
    
    public static String selectObjectByShopAndName(int idNegozio, String nomeDownCase)
    {
        return "SELECT * FROM Oggetto WHERE idNegozio=" + idNegozio + " AND nomeDownCase LIKE '%" + nomeDownCase + "%'";
    }
    
    
    public static String selectObjectByCategoryAndShop(int idCategoria, int idNegozio)
    {
        return "SELECT * FROM Oggetto WHERE idNegozio=" + idNegozio + " AND Oggetto.categoria=" + idCategoria + ";";
    }
    
    
    public static String selectObjectByCategoryAndShopAndName(int idCategoria, int idNegozio, String nomeDownCase)
    {
        return "SELECT * FROM Oggetto WHERE idNegozio=" + idNegozio + " AND Oggetto.categoria=" + idCategoria + " AND Oggetto.nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    
    public static String selectObjectByShopAndHigherThanPrice(int idNegozio, double prezzoMin)
    {
        return "SELECT * FROM Oggetto WHERE idNegozio=" + idNegozio + " AND Oggetto.prezzo >= " + prezzoMin + ";";
    }
    
    
    public static String selectObjectByShopAndLowerThanPrice(int idNegozio, double prezzoMax)
    {
        return "SELECT * FROM Oggetto WHERE idNegozio=" + idNegozio + " AND Oggetto.prezzo <= " + prezzoMax + ";";
    }
    
    
    public static String selectObjectByShopAndBetweenPrices(int idNegozio, double prezzoMin, double prezzoMax)
    {
        return "SELECT * FROM Oggetto WHERE idNegozio=" + idNegozio + " AND prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + ";";
    }
    
    
    public static String selectObjectByCategoryAndShopAndHigherThanPrice(int idCategoria, int idNegozio, double prezzoMin)
    {
        return "SELECT * FROM Oggetto WHERE idNegozio=" + idNegozio + " AND categoria=" + idCategoria + " AND prezzo >= " + prezzoMin + ";";
    }
    
    
    public static String selectObjectByCategoryAndShopAndLowerThanPrice(int idCategoria, int idNegozio, double prezzoMax)
    {
        return "SELECT * FROM Oggetto WHERE idNegozio=" + idNegozio + " AND categoria=" + idCategoria + " AND prezzo <= " + prezzoMax + ";";
    }
    
    
    public static String selectObjectByCategoryAndShopAndBetweenPrices(int idCategoria, int idNegozio, double prezzoMin, double prezzoMax)
    {
        return "SELECT * FROM Oggetto WHERE idNegozio=" + idNegozio + " AND categoria=" + idCategoria + " AND prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + ";";
    }
    
    
    public static String selectObjectByShopAndNameAndHigherThanPrice(int idNegozio, String nomeDownCase, double prezzoMin)
    {
        return "SELECT * FROM Oggetto WHERE idNegozio=" + idNegozio + " AND Oggetto.prezzo >= " + prezzoMin + " AND Oggetto.nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    
    public static String selectObjectByShopAndNameAndLowerThanPrice(int idNegozio, String nomeDownCase, double prezzoMax)
    {
        return "SELECT * FROM Oggetto WHERE idNegozio=" + idNegozio + " AND Oggetto.prezzo <= " + prezzoMax + " AND Oggetto.nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    
    public static String selectObjectByShopAndNameAndBetweenPrices(int idNegozio, String nomeDownCase, double prezzoMin, double prezzoMax)
    {
        return "SELECT * FROM Oggetto WHERE idNegozio=" + idNegozio + " AND Oggetto.prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + " AND Oggetto.nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    
    public static String selectObjectByCategoryAndShopAndNameAndHigherThanPrice(int idNegozio, int idCategoria, String nomeDownCase, double prezzoMin)
    {
        return "SELECT * FROM Oggetto WHERE idNegozio=" + idNegozio + "  AND categoria=" + idCategoria + " AND Oggetto.prezzo >= " + prezzoMin + " AND Oggetto.nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    
    public static String selectObjectByCategoryAndShopAndNameAndLowerThanPrice(int idNegozio, int idCategoria, String nomeDownCase, double prezzoMax)
    {
        return "SELECT * FROM Oggetto WHERE idNegozio=" + idNegozio + " AND categoria=" + idCategoria + " AND Oggetto.prezzo <= " + prezzoMax + " AND Oggetto.nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    
    public static String selectObjectByCategoryAndShopAndNameAndBetweenPrices(int idNegozio, int idCategoria, String nomeDownCase, double prezzoMin, double prezzoMax)
    {
        return "SELECT * FROM Oggetto WHERE idNegozio=" + idNegozio + " AND categoria=" + idCategoria + " AND Oggetto.prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + " AND Oggetto.nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    
    /*---1*/
    
    /*---2*/
    public static String selectObjectByNameAndPIS(String nomeDownCase, int ritiroInNegozio)  //PIS = Pickup In Store
    {
        return "SELECT * FROM Oggetto WHERE nomeDownCase LIKE '%" + nomeDownCase + "%' AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
    
    
    public static String selectObjectByCategory(int idCategoria, int ritiroInNegozio)
    {
        return "SELECT * FROM Oggetto WHERE categoria = " + idCategoria + " AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
    
    
    public static String selectObjectByCategoryAndName(int idCategoria, String nomeDownCase, int ritiroInNegozio)
    {
        return "SELECT * FROM Oggetto WHERE categoria=" + idCategoria + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
    
    
    public static String selectObjectLowerThanPrice(double prezzo, int ritiroInNegozio)
    {
        return "SELECT * FROM Oggetto WHERE prezzo >= " + prezzo + " AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
    
    
    public static String selectObjectHigherThanPrice(double prezzo, int ritiroInNegozio)
    {
        return "SELECT * FROM Oggetto WHERE prezzo <= " + prezzo + " AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
    
    
    public static String selectObjectBetweenPrices(double prezzoMin, double prezzoMax, int ritiroInNegozio)
    {
        return "SELECT * FROM Oggetto WHERE prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + " AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
    
    
    public static String selectObjectByCategoryAndHigherThanPrice(int idCategoria, double prezzoMin, int ritiroInNegozio)
    {
        return "SELECT * FROM Oggetto WHERE categoria=" + idCategoria + " AND prezzo >= " + prezzoMin + " AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
    
    
    public static String selectObjectByCategoryAndLowerThanPrice(int idCategoria, double prezzoMax, int ritiroInNegozio)
    {
        return "SELECT * FROM Oggetto WHERE categoria=" + idCategoria + " AND prezzo <= " + prezzoMax + " AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
    
    
    public static String selectObjectByCategoryAndBetweenPrices(int idCategoria, double prezzoMin, double prezzoMax, int ritiroInNegozio)
    {
        return "SELECT * FROM Oggetto WHERE categoria=" + idCategoria + " AND prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + " AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
    
    
    public static String selectObjectByNameAndHigherThanPrice(String nomeDownCase, double prezzoMin, int ritiroInNegozio)
    {
        return "SELECT * FROM Oggetto WHERE prezzo >= " + prezzoMin + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
    
    
    public static String selectObjectByNameAndLowerThanPrice(String nomeDownCase, double prezzoMax, int ritiroInNegozio)
    {
        return "SELECT * FROM Oggetto WHERE prezzo <= " + prezzoMax + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
    
    
    public static String selectObjectByNameAndBetweenPrices(String nomeDownCase, double prezzoMin, double prezzoMax, int ritiroInNegozio)
    {
        return "SELECT * FROM Oggetto WHERE prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
    
    
    public static String selectObjectByCategoryAndNameAndHigherThanPrice(int idCategoria, String nomeDownCase, double prezzoMin, int ritiroInNegozio)
    {
        return "SELECT * FROM Oggetto WHERE categoria=" + idCategoria + " AND prezzo >= " + prezzoMin + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
    
    
    public static String selectObjectByCategoryAndNameAndLowerThanPrice(int idCategoria, String nomeDownCase, double prezzoMax, int ritiroInNegozio)
    {
        return "SELECT * FROM Oggetto WHERE categoria=" + idCategoria + " AND prezzo <= " + prezzoMax + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
    
    
    public static String selectObjectByCategoryAndNameAndBetweenPrices(int idCategoria, String nomeDownCase, double prezzoMin, double prezzoMax, int ritiroInNegozio)
    {
        return "SELECT * FROM Oggetto WHERE categoria=" + idCategoria + " AND prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
    
    
    
    
    /*---3*/
    
    /*---4*/
    
    
    public static String selectObjectByNameAnd()
    {
        return "";
    }
    
    
    public static String a()
    {
        return "";
    }
    
    
    public static String a()
    {
        return "";
    }
    
    
    public static String a()
    {
        return "";
    }
    
    
    public static String a()
    {
        return "";
    }
    
    
    public static String a()
    {
        return "";
    }
    
    
    public static String a()
    {
        return "";
    }
    
    
    public static String a()
    {
        return "";
    }
    
    
    public static String a()
    {
        return "";
    }
    
    
    public static String a()
    {
        return "";
    }
    
    
    public static String a()
    {
        return "";
    }
    
    
    public static String a()
    {
        return "";
    }
    
    
    public static String a()
    {
        return "";
    }
    
    
    public static String a()
    {
        return "";
    }
    
    
    public static String a()
    {
        return "";
    }
    
    
    public static String a()
    {
        return "";
    }
    
    
    public static String a()
    {
        return "";
    }
    
    
    public static String a()
    {
        return "";
    }
    
    
    public static String a()
    {
        return "";
    }
    
    
    public static String a()
    {
        return "";
    }
    
    
    public static String a()
    {
        return "";
    }
    
    
    public static String a()
    {
        return "";
    }
    
    
    public static String a()
    {
        return "";
    }
    
    
    public static String a()
    {
        return "";
    }
    
    
    public static String a()
    {
        return "";
    }
    
    
    public static String a()
    {
        return "";
    }
    
    
    public static String a()
    {
        return "";
    }
    
    
    public static String a()
    {
        return "";
    }
    
    
    public static String a()
    {
        return "";
    }
    
    
    public static String a()
    {
        return "";
    }
    
    
    public static String a()
    {
        return "";
    }
    
    
    public static String a()
    {
        return "";
    }
    
    
    public static String a()
    {
        return "";
    }
    
    
    public static String a()
    {
        return "";
    }
    
    
    public static String a()
    {
        return "";
    }
    
    
    public static String a()
    {
        return "";
    }
    
    
    public static String a()
    {
        return "";
    }
    
    
    public static String a()
    {
        return "";
    }
    
    
    public static String a()
    {
        return "";
    }
    
    
    public static String a()
    {
        return "";
    }
    
    
    public static String a()
    {
        return "";
    }
    
    
    public static String a()
    {
        return "";
    }
    
    
    public static String a()
    {
        return "";
    }
    
    
    public static String a()
    {
        return "";
    }
    
    
    public static String a()
    {
        return "";
    }
    
    
    
}

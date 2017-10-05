import csv
import MySQLdb

table = input("In che tabella vuoi inserire i valori? ")
readfile = input("Da che file vuoi prelevare i valori? ")


def utente():
    outDoc.write("INSERT INTO utente (id, nome, cognome, mail, password, avatar, valutazione, UtenteType, emailConfermata) VALUES\n")
    with open(inDoc) as csvfile:
        read = csv.reader(csvfile, delimiter = ',')
        first = True
        for row in read:
            if (not first):
                outDoc.write(",\n")
            first = False
            outDoc.write("(NULL, '" + row[0] + "', '" + row[1] + "', '" + row[2] + "', MD5('" + row[3] + "'), " + row[4] + ", " + row[5] + ", " + row[6] + ", " +
            row[7] + ")")

        outDoc.write(";\n")
    return;

def indirizzo():
    outDoc.write("INSERT INTO indirizzo (idI, stato, regione, provincia, citta, via, nCivico, interno, latitudine, longitudine) VALUES\n")
    with open(inDoc) as csvfile:
        read = csv.reader(csvfile, delimiter = ',')
        first = True
        for row in read:
            if (not first):
                outDoc.write(",\n")
            first = False
            outDoc.write("(NULL, '" + row[0] + "', '" + row[1] + "', '" + row[2] + "', '" + row[3] + "', '" + row[4] + "', " + row[5] + ", " + row[6] + ", " +
            row[7] + ", " + row[8] + ")")

        outDoc.write(";\n")
    return;


outDoc = open(table + ".sql", "w")
inDoc = readfile + ".csv"

if (table == "utente"):
    utente()

elif (table == "indirizzo"):
    indirizzo()

outDoc.close()

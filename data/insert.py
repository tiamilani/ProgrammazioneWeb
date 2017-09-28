table = input("In che tabella vuoi inserire i valori? ")
readfile = input("Da che file vuoi prelevare i valori? ")

inDoc = open(readfile + ".txt", "r")
outDoc = open(table + ".sql", "w")

if(table == 'utente'):
    outDoc.write("INSERT INTO " + table + " (id, nome, cognome, mail, password, avatar, valutazione, UtenteType, emailConfermata) VALUES \n")
    l = 0
    for line in inDoc:
        valori = line.split(",")
        i = 0
        if (l != 0):
            outDoc.write(",\n")
        for char in valori:
            if i == 0:
                outDoc.write("(NULL, '" + char + "', ")
            if i == 1:
                outDoc.write("'" + char + "', ")
            if i == 2:
                outDoc.write("'" + char + "', ")
            if i == 3:
                outDoc.write("MD5('" + char + "'), ")
            if i == 4:
                outDoc.write(char + ", ")
            if i == 5:
                outDoc.write(char + ", ")
            if i == 6:
                outDoc.write(char + ", ")
            if i == 7:
                outDoc.write(char.rstrip() + ")")
            i = i + 1
        l = l + 1

    outDoc.write(";")

if(table == 'indirizzo'):
    outDoc.write("INSERT INTO " + tables + " ()")


outDoc.close()
inDoc.close()

from bs4 import BeautifulSoup
import requests
import hashlib
from geopy.geocoders import Nominatim
from random import *
import pymysql

def generateUtenti(serverDB,utenteDB,passwordDB,dbDB):
    queryU = "INSERT INTO utente (nome,cognome,mail,password,avatar,valutazione,UtenteType,emailConfermata) VALUES "

    for i in range(MAXU):
        print("Utente ", i)

        if randint(1, 100) > 50:
            url = "http://www.fakenamegenerator.com/advanced.php?t=country&n%5B%5D=it&c%5B%5D=it&gen=100&age-min=19&age-max=85"
            sex = 0
        else:
            url = "http://www.fakenamegenerator.com/advanced.php?t=country&n%5B%5D=it&c%5B%5D=it&gen=0&age-min=19&age-max=85"
            sex = 1

        response = requests.get(url)
        soup = BeautifulSoup(response.content, "html.parser")
        soup = soup.find("div", class_="info")

        nomeCognome = soup.div.div.h3.string
        mail = soup.div.find_all("dd")[8].next_element.strip()
        password = soup.div.find_all("dd")[10].string

        nome = nomeCognome.split(' ')[0]
        cognome = nomeCognome.split(' ')[1]
        password = hashlib.md5(password.encode()).hexdigest()

        if sex == 0:
            queryU += "('" + nome + "','" + cognome + "','" + mail + "','" + password + "','http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/male.jpg',0,0,0),"
        else:
            queryU += "('" + nome + "','" + cognome + "','" + mail + "','" + password + "','http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/female.jpg',0,0,0),"

    queryU = queryU[:-1]
    queryU += ';'

    db = pymysql.connect(serverDB,utenteDB,passwordDB,dbDB)

    cursor = db.cursor()
    try:
        #print(queryU)
        cursor.execute(queryU)
        db.commit()
    except:
        db.rollback()

    db.close()


def generateIndirizzi(serverDB,utenteDB,passwordDB,dbDB):
    queryI = "INSERT INTO indirizzo (stato,regione,provincia,citta,via,nCivico,interno,latitudine,longitudine) VALUES "
    prossimo = False
    i = 0

    while i < MAXI:
        url = "http://www.fakenamegenerator.com/advanced.php?t=country&n%5B%5D=it&c%5B%5D=it&gen=100&age-min=19&age-max=85"

        response = requests.get(url)
        soup = BeautifulSoup(response.content, "html.parser")
        soup = soup.find("div", class_="info")
        indirizzo = soup.div.div.div.get_text().strip()
        coordinate = soup.find("div", class_="extra").a.string

        geolocator = Nominatim()
        location = geolocator.geocode(coordinate, timeout=10)
        location = location.address
        array = location.split(',')

        #print(array)

        if len(array) > 5:
            if len(array[-2].strip()) == 5:
                if len(array[-3].strip()) == 3:
                    array[0] = array[0].strip()
                    if ("via" in array[0]) or ("Via" in array[0]) or ("viale" in array[0]) or ("Viale" in array[0]) or ("strada" in array[0]) or ("Strada" in array[0]):
                        paese = array[-1].strip()
                        regione = array[-3].strip()
                        provincia = array[-4].strip()
                        citta = array[-5].strip().replace('\'',' ')
                        via = array[0].strip().replace('\'',' ')
                        civico = randint(1, 100)
                        i += 1
                        prossimo = True
            else:
                if len(array[-2].strip()) == 3:
                    if ("via" in array[0]) or ("Via" in array[0]) or ("viale" in array[0]) or ("Viale" in array[0]) or ("strada" in array[0]) or ("Strada" in array[0]):
                        paese = array[-1].strip()
                        regione = array[-2].strip()
                        provincia = array[-3].strip()
                        citta = array[-4].strip().replace('\'',' ')
                        via = array[0].strip().replace('\'',' ')
                        civico = randint(1, 100)
                        i += 1
                        prossimo = True


        if prossimo == True:
            print("Indirizzo", (i-1), "\t->\tACCEPT")
            prossimo = False

            latitudine = coordinate.split(',')[0].strip()
            longitudine = coordinate.split(',')[1].strip()

            queryI += "('" + paese + "','" + regione + "','" + provincia + "','" + citta + "','" + via + "'," + str(civico) + ",0," + str(latitudine) + "," + str(longitudine) + "),"
        else:
            print("Indirizzo", i, "\t->\tDISCARD")

    queryI = queryI[:-1]
    queryI += ';'

    db = pymysql.connect(serverDB,utenteDB,passwordDB,dbDB)

    cursor = db.cursor()
    try:
        #print(queryI)
        cursor.execute(queryI)
        db.commit()
    except:
        db.rollback()

    db.close()

serverDB = input('Nome server [localhost] ')
if len(serverDB) < 1:
    serverDB = "localhost"
utenteDB = input('Nome utente [root] ')
if len(utenteDB) < 1:
    utenteDB = "root"
passwordDB = input('Nome password [root] ')
if len(passwordDB) < 1:
    passwordDB = "root"
dbDB = input('Nome database [progettoweb] ')
if len(dbDB) < 1:
    dbDB = "progettoweb"

try:
    MAXU = int(input('Quanti Utenti? '))
    if MAXU > 0:
        generateUtenti(serverDB,utenteDB,passwordDB,dbDB)
except ValueError:
    print("Inserisci un numero!")
    exit(1)

try:
    MAXI = int(input('Quanti Indirizzi? '))
    if MAXI > 0:
        generateIndirizzi(serverDB,utenteDB,passwordDB,dbDB)
except ValueError:
    print("Inserisci un numero!")
    exit(1)

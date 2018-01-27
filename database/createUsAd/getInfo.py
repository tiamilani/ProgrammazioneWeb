from bs4 import BeautifulSoup
import requests
import hashlib
from geopy.geocoders import Nominatim
from random import *
import pymysql
import time
import datetime

def generateUtenti(rit):
    MAXU = int(input('Quanti Utenti? '))
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
            queryU += "('" + nome + "','" + cognome + "','" + mail + "','" + password + "','http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/male.jpg',0,2,0),"
        else:
            queryU += "('" + nome + "','" + cognome + "','" + mail + "','" + password + "','http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/female.jpg',0,2,0),"

    queryU = queryU[:-1]
    queryU += ';'

    if rit is 0:
        return queryU
    else:
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

        db = pymysql.connect(serverDB,utenteDB,passwordDB,dbDB)

        cursor = db.cursor()
        try:
            #print(queryU)
            cursor.execute(queryU)
            db.commit()
        except:
            db.rollback()

        db.close()

        return "OK"


def generateIndirizzi(rit):
    MAXI = int(input('Quanti Indirizzi? '))
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
                    if (array[-3].strip() == "PIE") or (array[-3].strip() == "LOM") or (array[-3].strip() == "VEN") or (array[-3].strip() == "TAA"):
                        array[0] = array[0].strip()
                        if ("via" in array[0]) or ("Via" in array[0]) or ("viale" in array[0]) or ("Viale" in array[0]) or ("strada" in array[0]) or ("Strada" in array[0]):
                            paese = array[-1].strip()
                            regione = array[-3].strip()
                            provincia = array[-4].strip()
                            citta = array[-5].strip().replace('\'',' ')
                            via = array[0].replace('\'',' ')
                            civico = randint(1, 100)
                            i += 1
                            prossimo = True
            else:
                if len(array[-2].strip()) == 3:
                    if (array[-2].strip() == "PIE") or (array[-2].strip() == "LOM") or (array[-2].strip() == "VEN") or (array[-2].strip() == "TAA"):
                        array[0] = array[0].strip()
                        if ("via" in array[0]) or ("Via" in array[0]) or ("viale" in array[0]) or ("Viale" in array[0]) or ("strada" in array[0]) or ("Strada" in array[0]):
                            paese = array[-1].strip()
                            regione = array[-2].strip()
                            provincia = array[-3].strip()
                            citta = array[-4].strip().replace('\'',' ')
                            via = array[0].replace('\'',' ')
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

    if rit is 0:
        return queryI
    else:
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

        db = pymysql.connect(serverDB,utenteDB,passwordDB,dbDB)

        cursor = db.cursor()
        try:
            #print(queryI)
            cursor.execute(queryI)
            db.commit()
        except:
            db.rollback()

        db.close()

        return "OK"


def generaAssociazioni():
    limite = int(input('Associazioni da 1 a '))
    queryUI = "INSERT INTO IndirizzoUtente (idI,idU) VALUES "

    limite+=1
    for i in range(limite):
        if i != 0:
            queryUI += "(" + str(i) + "," + str(i) + "),"

    queryUI = queryUI[:-1]
    queryUI += ';'

    return queryUI

def generaAssociazioniImmagini():
    fileI = input('Nome del file: ')
    queryS = "INSERT INTO imageNegozio (src,idN) VALUES "

    with open(fileI) as f:
        content = f.readlines()

    content = [ x.strip() for x in content ]

    for x in content:
        number = x.split(',')
        number = number[0]
        number = number[1:]

        if "Chainsaws" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/gino01.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/gino02.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/gino03.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/gino04.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/gino05.jpg'," + number + "),\n"
        elif "Despar" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/despar01.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/despar02.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/despar03.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/despar04.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/despar05.jpg'," + number + "),\n"
        elif "Ikea" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/ikea01.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/ikea02.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/ikea03.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/ikea04.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/ikea05.jpg'," + number + "),\n"
        elif "Zio Tom" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/ziotom01.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/ziotom02.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/ziotom03.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/ziotom04.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/ziotom05.jpg'," + number + "),\n"
        elif "Mediaworld" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/mediaworld01.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/mediaworld02.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/mediaworld03.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/mediaworld04.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/mediaworld05.jpg'," + number + "),\n"
        elif "McBullone" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/mc01.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/mc02.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/mc03.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/mc04.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/mc05.jpg'," + number + "),\n"
        elif "Tosano" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/tosano01.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/tosano02.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/tosano03.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/tosano04.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/tosano05.jpg'," + number + "),\n"
        elif "Euronics" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/euronics01.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/euronics02.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/euronics03.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/euronics04.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/euronics05.jpg'," + number + "),\n"
        elif "Trony" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/trony01.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/trony02.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/trony03.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/trony04.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/trony05.jpg'," + number + "),\n"
        elif "S lunga" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/slunga01.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/slunga02.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/slunga03.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/slunga04.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/slunga05.jpg'," + number + "),\n"
        elif "This is not" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/notshop01.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/notshop02.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/notshop03.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/notshop04.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/notshop05.jpg'," + number + "),\n"
        elif "Viti per" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/viti01.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/viti02.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/viti03.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/viti04.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/viti05.jpg'," + number + "),\n"
        elif "Supermedia" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/super01.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/super02.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/super03.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/super04.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/super05.jpg'," + number + "),\n"
        elif "Gameshop" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/gameshop01.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/gameshop02.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/gameshop03.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/gameshop04.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/gameshop05.jpg'," + number + "),\n"
        elif "Comet" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/comet01.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/comet02.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/comet03.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/comet04.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/comet05.jpg'," + number + "),\n"
        elif "Tantrum" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/tantrum01.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/tantrum02.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/tantrum03.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/tantrum04.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/tantrum05.jpg'," + number + "),\n"
        elif "Unieuro" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/unieuro01.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/unieuro02.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/unieuro03.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/unieuro04.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/unieuro05.jpg'," + number + "),\n"
        elif "brugola" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/brugola01.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/brugola02.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/brugola03.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/brugola04.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/brugola05.jpg'," + number + "),\n"
        elif "Auchan" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/auchan01.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/auchan02.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/auchan03.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/auchan04.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/auchan05.jpg'," + number + "),\n"
        elif "Gamestop" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/gamestop01.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/gamestop02.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/gamestop03.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/gamestop04.jpg'," + number + "),"
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Stores/gamestop05.jpg'," + number + "),\n"
        else:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/imageNegozio.png'," + number + "),\n"


    queryS = queryS[:-2]
    queryS += ';'

    return queryS

def checkStore():
    fileS = input('Nome del file Negozi: ')

    with open(fileS) as f:
        content = f.readlines()

    content = [ x.strip() for x in content ]
    D = {}

    for x in content:
        idNegozio = x.split(',')
        idNegozio = idNegozio[0]
        idNegozio = int(idNegozio[1:])

        if "Chainsaws" in x:
            if isinstance(D.get('MOTOSEGHE'), list):
                D['MOTOSEGHE'].append(idNegozio)
            else:
                D['MOTOSEGHE'] = [ idNegozio ]
        elif "Despar" in x:
            if isinstance(D.get('CIBO'), list):
                D['CIBO'].append(idNegozio)
            else:
                D['CIBO'] = [ idNegozio ]
        elif "Ikea" in x:
            if isinstance(D.get('ARREDAMENTO'), list):
                D['ARREDAMENTO'].append(idNegozio)
            else:
                D['ARREDAMENTO'] = [ idNegozio ]
        elif "Zio Tom" in x:
            if isinstance(D.get('FERRAMENTA'), list):
                D['FERRAMENTA'].append(idNegozio)
            else:
                D['FERRAMENTA'] = [ idNegozio ]
        elif "Mediaworld" in x:
            if isinstance(D.get('TECNOLOGIA'), list):
                D['TECNOLOGIA'].append(idNegozio)
            else:
                D['TECNOLOGIA'] = [ idNegozio ]
        elif "McBullone" in x:
            if isinstance(D.get('FERRAMENTA'), list):
                D['FERRAMENTA'].append(idNegozio)
            else:
                D['FERRAMENTA'] = [ idNegozio ]
        elif "Tosano" in x:
            if isinstance(D.get('CIBO'), list):
                D['CIBO'].append(idNegozio)
            else:
                D['CIBO'] = [ idNegozio ]
        elif "Euronics" in x:
            if isinstance(D.get('TECNOLOGIA'), list):
                D['TECNOLOGIA'].append(idNegozio)
            else:
                D['TECNOLOGIA'] = [ idNegozio ]
        elif "Trony" in x:
            if isinstance(D.get('TECNOLOGIA'), list):
                D['TECNOLOGIA'].append(idNegozio)
            else:
                D['TECNOLOGIA'] = [ idNegozio ]
        elif "S lunga" in x:
            if isinstance(D.get('CIBO'), list):
                D['CIBO'].append(idNegozio)
            else:
                D['CIBO'] = [ idNegozio ]
        elif "This is not" in x:
            if isinstance(D.get('LIBRI'), list):
                D['LIBRI'].append(idNegozio)
            else:
                D['LIBRI'] = [ idNegozio ]
        elif "Viti per" in x:
            if isinstance(D.get('FERRAMENTA'), list):
                D['FERRAMENTA'].append(idNegozio)
            else:
                D['FERRAMENTA'] = [ idNegozio ]
        elif "Supermedia" in x:
            if isinstance(D.get('TECNOLOGIA'), list):
                D['TECNOLOGIA'].append(idNegozio)
            else:
                D['TECNOLOGIA'] = [ idNegozio ]
        elif "Gameshop" in x:
            if isinstance(D.get('GIOCATTOLI'), list):
                D['GIOCATTOLI'].append(idNegozio)
            else:
                D['GIOCATTOLI'] = [ idNegozio ]
        elif "Comet" in x:
            if isinstance(D.get('TECNOLOGIA'), list):
                D['TECNOLOGIA'].append(idNegozio)
            else:
                D['TECNOLOGIA'] = [ idNegozio ]
        elif "Tantrum" in x:
            if isinstance(D.get('GIOCATTOLI'), list):
                D['GIOCATTOLI'].append(idNegozio)
            else:
                D['GIOCATTOLI'] = [ idNegozio ]
        elif "Unieuro" in x:
            if isinstance(D.get('TECNOLOGIA'), list):
                D['TECNOLOGIA'].append(idNegozio)
            else:
                D['TECNOLOGIA'] = [ idNegozio ]
        elif "brugola" in x:
            if isinstance(D.get('FERRAMENTA'), list):
                D['FERRAMENTA'].append(idNegozio)
            else:
                D['FERRAMENTA'] = [ idNegozio ]
        elif "Auchan" in x:
            if isinstance(D.get('CIBO'), list):
                D['CIBO'].append(idNegozio)
            else:
                D['CIBO'] = [ idNegozio ]
        elif "Gamestop" in x:
            if isinstance(D.get('GIOCATTOLI'), list):
                D['GIOCATTOLI'].append(idNegozio)
            else:
                D['GIOCATTOLI'] = [ idNegozio ]
        elif "Ramonda" in x:
            if isinstance(D.get('ABBIGLIAMENTO'), list):
                D['ABBIGLIAMENTO'].append(idNegozio)
            else:
                D['ABBIGLIAMENTO'] = [ idNegozio ]
        elif "Arcaplanet" in x:
            if isinstance(D.get('ANIMALI'), list):
                D['ANIMALI'].append(idNegozio)
            else:
                D['ANIMALI'] = [ idNegozio ]
        elif "Zalando" in x:
            if isinstance(D.get('ABBIGLIAMENTO'), list):
                D['ABBIGLIAMENTO'].append(idNegozio)
            else:
                D['ABBIGLIAMENTO'] = [ idNegozio ]
        elif "eFarma" in x:
            if isinstance(D.get('SALUTE'), list):
                D['SALUTE'].append(idNegozio)
            else:
                D['SALUTE'] = [ idNegozio ]
        elif "CAM" in x:
            if isinstance(D.get('INFANZIA'), list):
                D['INFANZIA'].append(idNegozio)
            else:
                D['INFANZIA'] = [ idNegozio ]
        elif "LaboTech" in x:
            if isinstance(D.get('SCIENZA'), list):
                D['SCIENZA'].append(idNegozio)
            else:
                D['SCIENZA'] = [ idNegozio ]
        elif "Eurobrico" in x:
            if isinstance(D.get('CASA'), list):
                D['CASA'].append(idNegozio)
            else:
                D['CASA'] = [ idNegozio ]

    return D


def generaTupleOggetti(associat):
    for x in associat:
        print(str(x) + "\t->\t" + str(associat.get(x)))

    fileO = input('Nome del file Oggetti: ')

    queryO = "INSERT INTO Oggetto (id, idNegozio, nome, nomeDownCase, prezzo, descrizione, ritiroInNegozio, disponibilita, statoDisponibilita, sconto, dataFineSconto, categoria) VALUES\n"

    with open(fileO) as f:
        content = f.readlines()

    content = [ x.strip() for x in content ]

    for x in content:
        #time.sleep( 5 )
        array = x.split(',')
        print(array)

        array[3] = array[2].lower()
        choice = array[1].split('#')
        array[11] = choice[1]

        for w in associat:
            if w == choice[0]:
                for z in associat.get(w):
                    array[0] = hashlib.md5((str(z) + array[2]).encode()).hexdigest()
                    array[6] = randint(0, 1)
                    array[7] = randint(0, 100)
                    if array[7] is 0:
                        array[8] = randint(1, 2)
                    else:
                        array[8] = 0
                    queryO += "('" + array[0] + "'," + str(z) + ",'" + array[2] + "','" + array[3] + "'," + array[4] + ",'" + array[5] + "'," + str(array[6]) + "," + str(array[7]) + "," + str(array[8]) + "," + array[9] + "," + array[10] + "," + array[11] + "),\n"

    queryO = queryO[:-2]
    queryO += ';'

    return queryO


def generaAssociazioniImmagini2():
    fileI = input('Nome del file: ')
    queryS = "INSERT INTO imageOggetto (src,idO) VALUES "

    with open(fileI) as f:
        content = f.readlines()

    content = [ x.strip() for x in content ]

    for x in content:
        idO = x.split(',')
        idO = idO[0]
        idO = idO[1:]

        if "Pentola acciaio inossidabile" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/pentola.jpg'," + idO + "),\n"
        elif "Mestolo legno di quercia" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/mestolo.jpg'," + idO + "),\n"
        elif "Set di 24 presine da cucina" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/presine.jpg'," + idO + "),\n"
        elif "Vestito di Marilyn Monroe" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/vestitoMarilyn.jpg'," + idO + "),\n"
        elif "Minigonna in lana caprina" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/minigonna.jpg'," + idO + "),\n"
        elif "Bandana da motociclista" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/bandana.jpg'," + idO + "),\n"
        elif "iPhone 9S" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/iphone9s.jpg'," + idO + "),\n"
        elif "LG OneMore" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/lg.jpg'," + idO + "),\n"
        elif "Samsung Galaxy A78" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/samsung.jpg'," + idO + "),\n"
        elif "Cotechino di Montebelluno" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/cotechino.jpg'," + idO + "),\n"
        elif "Zampone romagnolo" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/zampone.jpg'," + idO + "),\n"
        elif "Riso Scotti scotto" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/risoScotti.jpg'," + idO + "),\n"
        elif "Giubotto protettivo" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/giubottoProtettivo.jpg'," + idO + "),\n"
        elif "Casco integrale rosso" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/cascoRosso.jpg'," + idO + "),\n"
        elif "Casco integrale blu" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/cascoBlu.jpg'," + idO + "),\n"
        elif "Crema anti-age rivitalift" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/cremaLoreal.jpg'," + idO + "),\n"
        elif "Crema anti rughe loreal" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/antirughe.jpg'," + idO + "),\n"
        elif "Crema depilatoria" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/cremaDepilatoria.jpg'," + idO + "),\n"
        elif "Gomma da cancellare" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/gommeCancellare.jpg'," + idO + "),\n"
        elif "Penne colorate" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/penneColorate.jpg'," + idO + "),\n"
        elif "Quaderno a quadretti" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/quadernoQuadretti.jpg'," + idO + "),\n"
        elif "Lucio Dalla in concerto" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/lucioDalla.jpg'," + idO + "),\n"
        elif "Bon Jovi vinile" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/bonJovi.jpg'," + idO + "),\n"
        elif "Jovanotti - La mia casa" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/jovanotti.jpg'," + idO + "),\n"
        elif "Martello multiuso" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/martello.jpg'," + idO + "),\n"
        elif "Pialla in acciaio" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/pialla.jpg'," + idO + "),\n"
        elif "Cacciavite a croce" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/cacciavite.jpg'," + idO + "),\n"
        elif "Die Hard Collection" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/diehard.jpg'," + idO + "),\n"
        elif "Il Signore degli Anelli - La compagnia dell Anello" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/signoreDegliAnelli.jpg'," + idO + "),\n"
        elif "Twilight" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/twilight.jpg'," + idO + "),\n"
        elif "Decespugliatore elettrico" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/decespugliatore.jpg'," + idO + "),\n"
        elif "Cesoie da giardino" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/cesoie.jpg'," + idO + "),\n"
        elif "Guanti da giardinaggio" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/guantiGiardinaggio.jpg'," + idO + "),\n"
        elif "Motosega Parker" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/motosega.jpg'," + idO + "),\n"
        elif "Set 5 videogame anni 90" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/giochiAnni90.jpg'," + idO + "),\n"
        elif "Assassins Creed Revelation" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/ACRevelation.jpg'," + idO + "),\n"
        elif "Action Man" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/actionMan.jpg'," + idO + "),\n"
        elif "Orecchini zaffiro" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/orecchiniZaffiro.jpg'," + idO + "),\n"
        elif "Anello 7 carati" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/anello7carati.jpg'," + idO + "),\n"
        elif "Bracciale in diamante" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/bracciale.jpg'," + idO + "),\n"
        elif "Vaso Ming 1405" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/vasoMing.jpg'," + idO + "),\n"
        elif "Vaso Ming 1405 fasullo" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/vasoMingFasullo.jpg'," + idO + "),\n"
        elif "Sedia in legno di baobab" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/sediaLegno.jpg'," + idO + "),\n"
        elif "Lampadina 2W" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/lampadina2W.jpg'," + idO + "),\n"
        elif "Led 45m" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/led.jpg'," + idO + "),\n"
        elif "Lampada al neon verde" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/neon.jpg'," + idO + "),\n"
        elif "6 Provette da laboratorio" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/provette.jpg'," + idO + "),\n"
        elif "Tuta Hazmat" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/hazmat.jpg'," + idO + "),\n"
        elif "Casco protettivo" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/cascoProtettivoArancione.jpg'," + idO + "),\n"
        elif "JAVA tutorial" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/javaTutorial.jpg'," + idO + "),\n"
        elif "Hard Disk esterno Trekstore 3TB" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/hardDisk.jpg'," + idO + "),\n"
        elif "Penna USB 500 MB" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/usb500.jpg'," + idO + "),\n"
        elif "Halo - The fall of Reach" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/halo.jpg'," + idO + "),\n"
        elif "In cucina con Benedetta" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/benedetta.jpg'," + idO + "),\n"
        elif "Come laurearsi senza fatica" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/laurearsi.jpg'," + idO + "),\n"
        elif "Vestito di Prada rosso carmigno" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/pradaRosso.jpg'," + idO + "),\n"
        elif "Jeans strappati e slavati" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/jeans.jpg'," + idO + "),\n"
        elif "4 T-Shirt muticolor" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/4tshirt.jpg'," + idO + "),\n"
        elif "Orologio Swatch nero" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/swatchNero.jpg'," + idO + "),\n"
        elif "Rolex 24 carati" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/rolex.jpg'," + idO + "),\n"
        elif "Orologio Tissot Everytime Small" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/tissot.jpg'," + idO + "),\n"
        elif "Biberon 2 litri" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/biberon.jpg'," + idO + "),\n"
        elif "Culla Chicco" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/culla.jpg'," + idO + "),\n"
        elif "Pannolini confezione da 75" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/pannolini.jpg'," + idO + "),\n"
        elif "Cibo per cani" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/ciboCane.jpg'," + idO + "),\n"
        elif "Cibo per gatti" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/ciboGatto.jpg'," + idO + "),\n"
        elif "Cibo per canarini" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/ciboCanarino.jpg'," + idO + "),\n"
        elif "Cerotti" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/CerottiRotondi.jpg'," + idO + "),\n"
        elif "Testine spazzolino elettrico" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/testineSpazzolino.jpg'," + idO + "),\n"
        elif "Filo interdentale 4m" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/filoInterdentale.jpg'," + idO + "),\n"
        elif "Borsa di Gucci 2018" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/borsaGucci.jpg'," + idO + "),\n"
        elif "Scarpe ginnastica Nike" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/scarpeNike.jpg'," + idO + "),\n"
        elif "Scarpe tip tap" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/scarpeTipTap.jpg'," + idO + "),\n"
        elif "Windows XP CD di installazione" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/windowsXP.jpg'," + idO + "),\n"
        elif "Windows Office" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/office.jpg'," + idO + "),\n"
        elif "Linux tutorial" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/linuxTutorial.jpg'," + idO + "),\n"
        elif "Valigia Roncato" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/roncato.jpg'," + idO + "),\n"
        elif "Zaino Brugi" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/zaino.jpg'," + idO + "),\n"
        elif "Borsello di pelle nera" in x:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/Objects/borsello.jpg'," + idO + "),\n"
        else:
            queryS += "('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/objectImage.png'," + idO + "),\n"


    queryS = queryS[:-2]
    queryS += ';'

    return queryS


def checkObjects():
    fileO = input('Nome del file Oggetti: ')

    with open(fileO) as f:
        content = f.readlines()

    content = [ x.strip() for x in content ]
    D = {}

    for x in content:
        array = x.split(',')
        idOggetto = array[0]
        idOggetto = idOggetto[1:].strip()
        nomeOggetto = array[3].strip().replace('\'','')

        if isinstance(D.get(nomeOggetto), list):
            D[nomeOggetto].append(idOggetto)
        else:
            D[nomeOggetto] = [ idOggetto ]

    return D


def generaROggetti(dbO):
    dbR = {}
    #for x in associat:
    #    print(str(x) + "\t->\t" + str(associat.get(x)))

    fileO = input('Nome del file RecensioniOggetto: ')

    queryO = "INSERT INTO RecensioneOggetto (id, idOggetto, idUtente, testo, valutazione, data, utilita) VALUES\n"

    with open(fileO) as f:
        content = f.readlines()

    content = [ x.strip() for x in content ]

    for x in content:
        #time.sleep( 10 )
        array = x.split('§')
        print(array)

        nomeOggetto = array[0].strip().lower()
        url = array[1].strip()

        session = requests.Session()
        response = session.get(url)
        soup = BeautifulSoup(response.content, "html.parser")
        soup = soup.find("div", class_="review-views")

        time.sleep( 2 )
        #print(soup)

        for i in soup.find_all("div", class_="review"):
            w=0

            for j in i.div.find_all("div", class_="a-row"):
                if w <= 4:
                    if w is 0:
                        stelle = j.a.i.span.next_element
                        stelle = stelle.replace('.0',',0')

                        stelle = stelle.split(',')
                        stelle = int(stelle[0].strip())

                    if w is 3:
                        testo = j.span.get_text().strip().replace('\'',' ')

                    w+=1
                else:
                    break

            if isinstance(dbR.get(stelle), list):
                dbR[stelle].append(testo)
            else:
                dbR[stelle] = [ testo ]

        #for x in dbO:
        #    print(str(x) + "\t->\t" + str(dbO.get(x)))

        iCasuale = []
        for x in dbR:
            iCasuale.append(x)

        indice=1
        for w in dbO:
            if w == nomeOggetto:
                if len(dbO.get(w)) < 4:
                    for t in range(int(len(dbO.get(w)))):
                        iCasualeS = randint(0, len(iCasuale)-1)
                        now = datetime.datetime.now()
                        queryO += "(" + str(indice) + "," + dbO.get(w)[randint(0, len(dbO.get(w))-1)] + "," + str(randint(21, 220)) + ",'" + dbR.get(iCasuale[iCasualeS])[randint(0, len(dbR.get(iCasuale[iCasualeS]))-1)] + "'," + str(iCasuale[iCasualeS]) + ",'" + str(now.strftime("%Y-%m-%d")) + "'," + str(iCasuale[iCasualeS]) + "),\n"
                        indice+=1
                else:
                    for t in range(int(len(dbO.get(w))/4)):
                        iCasualeS = randint(0, len(iCasuale)-1)
                        now = datetime.datetime.now()
                        queryO += "(" + str(indice) + "," + dbO.get(w)[randint(0, len(dbO.get(w))-1)] + "," + str(randint(21, 220)) + ",'" + dbR.get(iCasuale[iCasualeS])[randint(0, len(dbR.get(iCasuale[iCasualeS]))-1)] + "'," + str(iCasuale[iCasualeS]) + ",'" + str(now.strftime("%Y-%m-%d")) + "'," + str(iCasuale[iCasualeS]) + "),\n"
                        indice+=1

    queryO = queryO[:-2]
    queryO += ';'

    return queryO


def generaRVenditori():
    dbV = {}
    fileV = 'listReviewV' #input('Nome del file RecensioniOggetto: ')

    queryV = "INSERT INTO RecensioneVenditore (id, idVenditore, idUtente, testo, valutazione, data, utilita) VALUES\n"

    with open(fileV) as f:
        content = f.readlines()

    content = [ x.strip() for x in content ]
    reviews = []

    numIndice=1
    for x in content:
        print(x)
        session = requests.Session()
        response = session.get(x)
        soup = BeautifulSoup(response.content, "html.parser")
        soup = soup.find("table", class_="FbOuterYukon")

        time.sleep( 2 )
        #print(soup)

        #soup = soup.tbody

        lineNum=0
        for i in soup.find_all("tr"):
            if lineNum%2 != 0:
                reviews.append(i.td.next_element.next_element.string.strip().replace('\'',' '))
            lineNum+=1

    for v in range(1, 1000):
        valut = randint(0, 5)
        now = datetime.datetime.now()
        queryV += "(" + str(numIndice) + "," + str(randint(21, 220)) + "," + str(randint(1, 20)) + ",'" + reviews[randint(0, len(reviews)-1)] + "'," + str(valut) + ",'" + str(now.strftime("%Y-%m-%d")) + "'," + str(valut) + "),\n"
        numIndice+=1

    queryV = queryV[:-2]
    queryV += ';'

    return queryV


def generaMSpedizini():
    queryS = "INSERT INTO tipoSpedizione (idS,idN,Nome,Prezzo,Corriere,tempoRichiesto,numeroMassimo) VALUES\n"

    descrizioneS = ['Spedizione Veloce','Spedizione su Appuntamento','Spedizione Standard','Spedizione Veloce','Spedizione Standard','Spedizione Veloce','Spedizione Express','Spedizione su Appuntamento','Spedizione standard,SPedizionasda','Spedizione poco sicura']
    corrieriS =['GLS','DHL','SGA','DHL','Non specificato','SGA','Bartolini']

    indice=1
    for i in range(1, 201):
        for j in range(1, len(descrizioneS)-1):
            for w in range(1, len(corrieriS)-1):
                queryS += "(" + str(indice) + "," + str(i) + ",'" + descrizioneS[j] + "'," + str(randint(1, 50)) + ",'" + corrieriS[w] + "'," + str(randint(1, 5)) + "," + str(randint(5, 10)) + "),\n"
                indice+=1

    queryS = queryS[:-2]
    queryS += ';'

    return queryS


def generaOSpedizini(dbO):
    idOggetti = []

    for x in dbO:
        for y in dbO.get(x):
            idOggetti.append(y)

    queryS = "INSERT INTO spedizioneOggetto (idS,idO) VALUES\n"

    for i in range(1, len(idOggetti)-1):
        queryS += "(" + str(randint(1, 8001)) + "," + idOggetti[i] + "),\n"

    queryS = queryS[:-2]
    queryS += ';'

    return queryS


menuT = {
    0   :   'Creazione utenti',
    1   :   'Creazione utenti e inserimento db',
    2   :   'Creazione indirizzi',
    3   :   'Creazione indirizzi e inserimento db',
    4   :   'Crea associazioni',
    5   :   'Associa Immagine-Negozio',
    6   :   'Crea oggetti',
    7   :   'Associa Immagine-Oggetto',
    8   :   'Crea recensioni Oggetto',
    9   :   'Crea recensioni Venditore',
    10  :   'Associa negozio-spedizione',
    11  :   'Associa oggetto-spedizione'
}

for i in menuT:
    print(str(i) + "\t->\t" + menuT[i])

menuC = int(input('Seleziona: '))

if menuC is 0:
    print(generateUtenti(0))
elif menuC is 1:
    print(generateUtenti(1))
elif menuC is 2:
    print(generateIndirizzi(0))
elif menuC is 3:
    print(generateIndirizzi(1))
elif menuC is 4:
    print(generaAssociazioni())
elif menuC is 5:
    print(generaAssociazioniImmagini())
elif menuC is 6:
    print(generaTupleOggetti(checkStore()))
elif menuC is 7:
    print(generaAssociazioniImmagini2())
elif menuC is 8:
    print(generaROggetti(checkObjects()))
elif menuC is 9:
    print(generaRVenditori())
elif menuC is 10:
    print(generaMSpedizini())
elif menuC is 11:
    print(generaOSpedizini(checkObjects()))

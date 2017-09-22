# !/bin/bash

JAVADIR=src/main/java/it
#CLASSDIR=src/main/webapp/WEB-INF/classes/it

function creaFileClass
{
    find it -type f -name *.java > listFile.txt

    for LINE in $(cat listFile.txt)
        do
    		if [ -n "$LINE" ]
    		then
                #echo "LINE: $LINE"
                DIR=$(dirname $LINE)
                #echo "DIR: $DIR"
    			FILEC=$(basename $LINE)
                #echo "FILEC: $FILEC"
    			FILEFP=${FILEC%.*}
                #echo "FILEFP: $FILEFP"

                JAVAN=${LINE/main\/java\/it/main\/webapp\/WEB-INF\/classes\/it}

                if [[ "$LINE" == *"Controller"* ]];
    			then
                    #echo "javac -cp" ".:$SERVLET $JAVAN"
                    javac -cp .:$SERVLET $JAVAN
                else
                    #echo "javac" "$LINE"
                    javac $LINE
                fi
    		fi
        done

    rm listFile.txt
}

function spostaFileClass
{
    find it -type f -name *.class > listFile.txt

    for LINE2 in $(cat listFile.txt)
        do
    		if [ -n "$LINE2" ]
    		then
                #echo "LINE2: $LINE2"
                DIR2=$(pwd $LINE2)/$LINE2
                #echo "DIR2: $DIR2"

                CLASSDIR2=${DIR2/main\/java\/it/main\/webapp\/WEB-INF\/classes\/it}
                #echo "CLASSDIR2: $CLASSDIR2"

                mv $DIR2 $CLASSDIR2
                #echo "mv $DIR2 $CLASSDIR2"
                #echo -e "$(cat listFile.txt | sed '1d')" > listFile.txt
            fi
        done

    rm listFile.txt
}

function rimuoviClass
{
    ROOT=$(pwd .)
    START=${ROOT%ProgettoWeb/*}
    # read -p "Quale directory vuoi scannerizzare? " DELETE
    find "$START" -type f -name *.class -exec rm '{}' +
}

function selezionaUtente
{
    read -p "Chi sei? [A/D/F/M]: " UTENTE
    case $UTENTE in
    	A|a)
    		SERVLET=/Applications/Tomcat/lib/servlet-api.jar
    	;;
    	D|d)
    		SERVLET=/mnt/c/Program Files/Apache Software Foundation/Tomcat 8.5/lib/servlet-api.jar
    	;;
    	F|f)
    		SERVLET=/mnt/c/Program Files/Apache Software Foundation/Tomcat 8.5/lib/servlet-api.jar
    	;;
        M|m)
            SERVLET=/home/mattia/Netbeans/apache-tomcat-8.0.27/lib/servlet-api.jar
        ;;
    	*)
    		echo "Utente: A|D|F|M"
            exit 1
    esac
}

function rilevaDirectory
{
    if [ -f "/Applications/Tomcat/lib/servlet-api.jar" ]
    then
        SERVLET=/Applications/Tomcat/lib/servlet-api.jar
    elif [ -f "/mnt/c/Program Files/Apache Software Foundation/Tomcat 8.5/lib/servlet-api.jar" ]
    then
        SERVLET=/mnt/c/Program Files/Apache Software Foundation/Tomcat 8.5/lib/servlet-api.jar
    elif [ -f "/home/mattia/Netbeans/apache-tomcat-8.0.27/lib/servlet-api.jar" ]
    then
        SERVLET=/home/mattia/Netbeans/apache-tomcat-8.0.27/lib/servlet-api.jar
    fi
}

function creaClassi
{
    rilevaDirectory
    creaFileClass
    echo "JAVA compilati - CLASS creati"
    sleep 2
    spostaFileClass
    echo "CLASS spostati"
    sleep 1
}

function init
{
    echo "Questo script può compilare file java o rimuovere file class. Per la prima funzione attendere, per la secondo rilanciare lo script passando una stringa qualsiasi come parametro aggiuntivo"
    if [ -n "$1" ]
        then rimuoviClass
    else
        creaClassi
    fi
}

init $1

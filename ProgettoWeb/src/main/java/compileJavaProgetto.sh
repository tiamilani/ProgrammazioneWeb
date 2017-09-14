# !/bin/bash

JAVADIR=src/main/java/it
#CLASSDIR=src/main/webapp/WEB-INF/classes/it
SERVLET=/home/mattia/Netbeans/apache-tomcat-8.0.27/lib/servlet-api.jar

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
    read -p "Dove puoi scannerizzare?" DELETE
    find "$DELETE" -type f -name *.class -exec rm '{}' +
}

function creaClassi
{
    creaFileClass
    echo "JAVA compilati - CLASS creati"
    sleep 2
    spostaFileClass
    echo "CLASS spostati"
    sleep 1
}

if [ -n "$1" ]
    then rimuoviClass
else
    creaClassi
fi

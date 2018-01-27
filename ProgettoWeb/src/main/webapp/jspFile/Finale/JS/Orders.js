//--- Richiama il form per il salvataggio dei dati
function saveChanges(goTo)
{
    switch (goTo)
    {
        case 0:
            document.formSaveChanges.action="/ProgettoWeb/OrdineController?save=1&action=listOrders";
            break;
        case 1:
            document.formSaveChanges.action="/ProgettoWeb/OrdineController?save=1&action=delivery";
            break;
        case 2:
            document.formSaveChanges.action="/ProgettoWeb/OrdineController?save=2&action=payment";
            break;
        case 3:
            document.formPayment.action="/ProgettoWeb/OrdineController?save=3&action=finish";
            document.formPayment.submit();
            break;
    }
    document.formSaveChanges.submit();
};

//--- Rimuove l'oggetto specificato
function removeObject(elem)
{
    /*
     * Setto il valore dell'<intput> quantita' relativo all'oggetto selezionato a 0
     * Dopo chiamo la servlet (quindi uso solo la funzione saveChanges) e salvo i parametri cambiati.
     * Se la quantita' e' 0, elimino la riga corrispondente dalla tabella ORDINE
    */
    var iterator = parseInt(elem.getAttribute("data-idInput"));
    var id = "quantita" + iterator.toString();
    document.getElementById(id).value = 0;

    var pageId = elem.getAttribute("data-pageId");
    
    if(pageId === "00")
        saveChanges(0);
    else if(pageId === "01")
        saveChanges(1);
};

//--- Controllo del testo inserito nel numericUpDown
function checkInputText(e, elem)
{
    if(e.keyCode == 13) // invio
    {
        checkInput(elem);
        return true;
    }
    else if(e.keyCode >= 48 && e.keyCode <= 57) // tra 0 e 9
    {
        return true;
    }

    e.returnValue = false;
};

//--- Aggiorno il valore della quantita' dell'ordine
function changeQuantity(elem)
{
    //Per il momento salvo la quantita' vecchia in un attributo dell'elemento (trova metodo alternativo, in modo che l'utente non possa modificarlo)
    var oldQuantita = parseInt(elem.getAttribute("data-oldvalueQuantita"));
    var newQuantita = parseInt(elem.value);
    var prezzoOggetto = 0;
    var oldPrezzoTotale = (parseFloat(document.getElementById("lblResultCart").getAttribute("data-oldvaluePrezzo"))).toFixed(2);
    var oldNumeroArticoli = parseFloat(document.getElementById("lblResultCart").getAttribute("data-oldvalueOggetti"));
    var newPrezzoTotale = 0;
    var newNumeroArticoli = 0;
    var disponibilitaProdotto = parseInt(document.getElementById("lblDisponibilita"+elem.id).getAttribute("data-disponibilita"));

    //Se (newQuantita > disponibilitaProdotto) allora setto (newQuantita = disponibilitaProdotto)
    if(newQuantita > disponibilitaProdotto)
    {
        newQuantita = disponibilitaProdotto;
        elem.value = disponibilitaProdotto.toString();
    }

    //Ottengo il prezzo dell'oggetto
    if(document.getElementById("lblPrezzo"+elem.id) == null)
        prezzoOggetto = (parseFloat(document.getElementById("lblPrezzoScontato"+elem.id).innerHTML.replace(',', '.'))).toFixed(2);
    else
        prezzoOggetto = (parseFloat(document.getElementById("lblPrezzo"+elem.id).innerHTML.replace(',', '.'))).toFixed(2);

   //Ottengo il prezzo dell'oggetto e il vecchio prezzo totale
   prezzoOggetto = parseFloat(prezzoOggetto);
   oldPrezzoTotale = parseFloat(oldPrezzoTotale);

   //Calcolo il nuovo prezzo totale e il nuovo numero di articoli
   newPrezzoTotale = (oldPrezzoTotale + prezzoOggetto * (newQuantita - oldQuantita));
   newNumeroArticoli = oldNumeroArticoli + (newQuantita - oldQuantita);

   //Stampo il nuovo numero di articoli e il nuovo prezzo totale
   var newText = "<b>Prezzo provvisorio (" + newNumeroArticoli + " articoli): &euro; " + newPrezzoTotale.toFixed(2);
   document.getElementById("lblResultCart").innerHTML = newText;

   //Setto i vari attriuti (dei vari elementi utilizzati nel processo) con i nuovi valori
   document.getElementById("lblResultCart").setAttribute("data-oldvaluePrezzo", newPrezzoTotale.toString());
   document.getElementById("lblResultCart").setAttribute("data-oldvalueOggetti", newNumeroArticoli.toString());
   elem.setAttribute("data-oldvalueQuantita", newQuantita.toString());

   document.getElementById("quantita"+elem.id).value = newQuantita.toString();
   document.getElementById("quantita"+elem.id).setAttribute("value", newQuantita.toString());
};

//--- Modifica il testo di un <p> in base al tipo di spedizione selezionata
function changeDescription(id)
{
    var firstIndex = id;
    var secondIndex = document.getElementById(firstIndex).value;
    var index = "option"+firstIndex+secondIndex;

    var nome = document.getElementById(index).getAttribute("data-nome");
    var newSubTot;
    var newTot;
    var oldSubTot = parseFloat(document.getElementById("pSubTot"+firstIndex).getAttribute("data-oldSubTot"));
    var oldTot = parseFloat(document.getElementById("pTot").getAttribute("data-oldTot"));
    
    if(nome === "RitiroInNegozio")
    {
        document.getElementById("DCP"+firstIndex).innerHTML = "Data consegna prevista QUANDO CHE TI VOL";
        document.getElementById("pDesc"+firstIndex).innerHTML = "Ritiro in negozio<br>Il ritiro in negozio non comporta<br> nessun costo di spese di spedizione.";
        
        var prezzoOgg = document.getElementById(index).getAttribute("data-prezzoOgg");
        var quantitaOgg = document.getElementById(index).getAttribute("data-quantitaOgg");
        newSubTot = (prezzoOgg * quantitaOgg);
    }
    else
    {
        var prezzo = document.getElementById(index).getAttribute("data-prezzo");
        var corriere = document.getElementById(index).getAttribute("data-corriere");
        var tempo = document.getElementById(index).getAttribute("data-tempo");
        var nMax = document.getElementById(index).getAttribute("data-nMax");
        var prezzoOgg = document.getElementById(index).getAttribute("data-prezzoOgg");
        var quantitaOgg = document.getElementById(index).getAttribute("data-quantitaOgg");

        document.getElementById("DCP"+firstIndex).innerHTML = "Data consegna prevista " + addDays(parseInt(tempo));

        document.getElementById("pDesc"+firstIndex).innerHTML = nome + "<br>Prezzo: &euro; " + prezzo+
                "<br>Corriere: " + corriere + "<br>Tempo stimato di consegna: " + tempo + " giorni";

        newSubTot = (prezzoOgg * quantitaOgg + (Math.ceil(quantitaOgg / nMax) * prezzo));
    }
    
    newTot = (oldTot - oldSubTot + newSubTot);
    
    document.getElementById("pSubTot"+firstIndex).innerHTML = "Subtotale: &euro; " + (newSubTot.toFixed(2));
    document.getElementById("pSubTot"+firstIndex).setAttribute("data-oldSubTot", newSubTot.toFixed(2));
    
    document.getElementById("pTot").innerHTML = "<b>Totale: &euro; " + newTot.toFixed(2) + "</b>";
    document.getElementById("pTot").setAttribute("data-oldTot", newTot.toFixed(2));
    
    
    var idS = document.getElementById(index).getAttribute("data-idS");
    alert(idS);
    document.getElementById("idS"+firstIndex).setAttribute("value", idS.toString());
};

//--- Funzione per calcolare la data prevista per la consegna (in base alla spedizione selezionata)
function addDays(daysToAdd)
{
    //var daysITA = ["Domenica", "Lunedi'", "Martedi'", "Mercoledi'", "Giovedi'", "Venerdi'", "Sabato"];
    var monthsSITA = ["Gen", "Feb", "Mar", "Apr", "Mag", "Giu", "Lug", "Ago", "Set", "Ott", "Nov", "Dec"];

    var result = new Date();
    result.setDate(result.getDate() + daysToAdd);

    return (/*daysITA[result.getDay()] + " " + */result.getDate() + " " + monthsSITA[result.getMonth()] + " " + result.getFullYear());
};

//--- Funzione per settare descrizione e data prevista della spedizione selezionata (la prima nella <select>).
//--- Questa funzione viene chiamata dall'evento onload presente nel <body>
function setFirstDescription(size)
{
    for (var i = 0; i < size; i++)
        changeDescription(i);
    
    document.getElementById("rbtnAddress0").setAttribute("checked", "checked");
    document.getElementById("idI").setAttribute("value", 
        (document.getElementById("rbtnAddress0").value).toString());
};

//--- Setta l'indirizzo selezionato
function setAddress(elem)
{
    var idI = elem.value;
    document.getElementById("idI").setAttribute("value", idI.toString());
}
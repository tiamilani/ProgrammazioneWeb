-- QUERY AMMINISTRATORI

-- ottenere il numero di richieste di assistenza
SELECT COUNT(id)
FROM Assistenza

-- ottenere il numero di richieste di assistenza in sospeso
SELECT COUNT(id)
FROM Assistenza
WHERE stato=0
    
-- ottenere il numero di richieste di assistenza risolte
SELECT COUNT(id)
FROM Assistenza
WHERE stato=1

-- aggiungere una soluzione ad una richiesta di assistenze
-- modificare lo stato di una richiesta di assistenza

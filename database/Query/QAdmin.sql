-- QUERY AMMINISTRATORI

-- ottenere il numero di richieste di assistenza
SELECT SUM(id)
FROM Assistenza

-- ottenere il numero di richieste di assistenza in sospeso
SELECT SUM(id)
FROM Assistenza
WHERE stato=0

-- ottenere il numero di richieste di assistenza risolte
SELECT SUM(id)
FROM Assistenza
WHERE stato=1

-- aggiungere una soluzione ad una richiesta di assistenze
-- modificare lo stato di una richiesta di assistenza

-- SUMMARY

-- Query Generiche 		-> 	QGenerics.sql
-- Query Utenti 		-> 	QUsers.sql
-- Query Venditori 		-> 	QSellers.sql
-- Query Amministratori -> 	QAdmins.sql

-- Assistenza.stato	=	0	->	In Corso
-- 					=	1	->	Conclusa

-- Negozio.attivo	=	0	->	Chiuso
--					=	1	->	Aperto

-- Oggetto.ritiroInNegozio	=	0	->	No
--							=	1	->	Si

-- Ordine.stato		=	0	->	Nel Carrello
--					=	1	->	Pagato
--					=	2	->	In Lavorazione
--					=	3	->	Spedito
--					=	4	->	Consegnato
--					=	5	->	Nella Lista dei Desideri

-- Utente.UtenteType	=	0	->	Normale
-- 						=	1	->	Venditore
-- 						=	2	->	Amministratore
-- 						=	3	->	Account NON Confermato
-- 						=	4	->	Super Admin

-- il carrello si autogestisce quando viene inserito un ordine nel carrello
-- aumenta o diminuisce in automatico il subtotale
-- crea un carrello quando un utente inserisce per la prima volta un oggetto tra gli ordini
-- aumenta o diminuisce il totale in automatico quando viene inserito o termina uno sconto
-- se il prezzo di un oggetto varia viene variato anche il prezzo del carrello
-- i triggers che gestiscono il carrello sono contenuti nella tabella ordine

-- la gestione dell'inserimento del nome in minuscolo è trasparente all'utente che insersce un oggetto
-- viene gestita da un trigger nella tabella oggetto

-- nella tabella ordini la modifica del prezzo di un oggetto è automatica, se il prezzo varia allora varia anche nell'ordine che a sua volta farà variare il carrello

-- E' presente un evento che gestisce la fine degli sconti, li fa terminare e riporta il prezzo alla normalità
-- questa modifica si ripercuote a cascata su oggetti->ordini->carrello

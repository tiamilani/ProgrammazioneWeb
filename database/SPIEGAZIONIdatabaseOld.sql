-- Nuova ipotesi database

-- IMPORTANTE: manca un sistema per filtrare gli oggetti in base alle loro caratteristiche, e mancano gli index

-- Viene utilizzata una tabella unica per gli utenti
-- Dato che l'unica differenza è che l'utente venditore avrà una valutazione
-- L'utente normale non ha bisogno di altri dati
-- L'utente amministratore potrà ricavare le richieste di assistenza ricevute direttamente dalla tabella assistenza
-- L'utente venditore potrà ricavare i propri negozi direttamente dalla tabella negozi
create table image(
	idI int PRIMARY KEY AUTO_INCREMENT,
	src varchar(255) NOT NULL
);

create table Utente(
	id int primary key AUTO_INCREMENT,
	nome varchar(255) NOT NULL,
	cognome varchar(255) NOT NULL,
	mail varchar(255) NOT NULL,
	password varchar(255) NOT NULL,
	avatar varchar(255) default NULL,	-- All'inizio null, se settata punta ad una immagine caricata sul server
	valutazione double default NULL,		-- Utilizzata solo per gli utenti di tipo venditore
	UtenteType int NOT NULL default 0 -- Utilizzato per identificare il tipo di utente, solo il venditore avrà una valutazione
									-- 0 -> normalUtente
									-- 1 -> seller
									-- 2 -> amministratore
);

CREATE INDEX idUtenteIndex ON Utente (id,UtenteType) USING HASH;

create table Indirizzo(
	idI int AUTO_INCREMENT PRIMARY KEY,
	stato varchar(255) NOT NULL,
	regione varchar(255) NOT NULL,
	provincia varchar(255) NOT NULL,
	citta varchar(255) NOT NULL,
	via varchar(255) NOT NULL,
	nCivico int NOT NULL,
	interno int NOT NULL,
	latitudine double default NULL,	-- Utilizzo latitudine e longitudine per trovare i negozi nelle vicinanze
	longitudine double default NULL
);

-- Identifica un negozio di un determinato venditore
-- Un venditore deve avere almeno un negozio, obbligatoriamente
-- ipotesi: index sugli idVenditori
create table Negozio(
	id int AUTO_INCREMENT PRIMARY KEY,
	idVenditore int NOT NULL,	-- Identifica il venditore associato
	nomeNegozio varchar(255) NOT NULL,
	foto varchar(500) default NULL,
	valutazione double default NULL,
	attivo boolean NOT NULL DEFAULT TRUE, 	-- Serve ad indicare negozi ancora in attività, se il venditore
											-- viene eliminato il negozio resta in memoria, ma lo stato viene messo a false
	idI int NOT NULL,
	FOREIGN KEY (idVenditore) REFERENCES Utente(id) ON DELETE NO ACTION,
	FOREIGN KEY (idI) REFERENCES Indirizzo(idI) ON DELETE NO ACTION
);
CREATE INDEX idNegozioIndex ON Negozio (idVenditore) USING HASH;

-- Tabella che identifica gli indirizzi degli utenti
-- Un utente può avere più indirizzi differenti tra loro
-- ipotesi: index basato sull'id degli utenti
create table IndirizzoUtente(
	idI int NOT NULL,
	idU int NOT NULL,
	PRIMARY KEY(idI, idU),
	FOREIGN KEY (idU) REFERENCES Utente(id) ON DELETE CASCADE,
	FOREIGN KEY (idI) REFERENCES Indirizzo(idI) ON DELETE CASCADE
);

-- Tabella che elenca le categorie di oggetti presenti sul sito
-- ipotesi: Index non necessario, le categorie non supereranno le 1000 unità
create table Categoria(
	id int PRIMARY KEY AUTO_INCREMENT,
	nome char(50) NOT NULL,
	descrizione char(500) NOT NULL,
	oggettiPresenti int NOT NULL DEFAULT 0 -- Da aggiornare una volta inserito un nuovo oggetto da parte di un negozio
);

-- Tabella che identifica un oggetto venduto in un negozio
create table Oggetto(
	id int NOT NULL, -- Codifico il nome in md5 o sh1 in modo da avere la chiave univoca per questo oggetto
	idNegozio int NOT NULL, -- Utilizzato per semplificare le operazioni di filtraggio e per ottenere subito
							-- Il negozio che vende questo oggetto

	-- il negozio ed l'id formano la chiave
	nome varchar(500) NOT NULL,
	prezzo double NOT NULL,
	foto varchar(500) default NULL,
	descrizione varchar(2500) NOT NULL,	-- Per ora le caratteristiche di un prodotto vanno qui
	ritiroInNegozio boolean default false,
	disponibilita int NOT NULL,

	sconto double default 0,	-- Serve ad indicare un eventuale sconto, in %
	variazione double default 100,	-- Serve ad indicare una variazione di prezzo dall'originale, il prezzo di partenza è
									-- Al 100% ma potrei volerlo aumentare o diminuire per un certo periodo
	dataFineSconto datetime,	-- Data di fine sconto/variazione

	categoria int NOT NULL,

	PRIMARY KEY(id,idNegozio),
	FOREIGN KEY (idNegozio) REFERENCES Negozio(id) ON DELETE NO ACTION,
	FOREIGN KEY (categoria) REFERENCES Categoria(id) ON DELETE NO ACTION
);
CREATE INDEX idOggettoIndex ON Oggetto (categoria) USING HASH;

-- Un ordine è singolo per ogni oggetto presente nel carrello/Ordinato dell'utente
-- Al massimo gli oggetti uguali vengolo cumulati attraverso la quantita
-- Questo sistema permette una facile gestione dell'assistenza e dello storico ordini di un utente
-- Complica solo leggeremente il carrello di un utente, risolvibile tramite un hash che mantenga gli
-- ordini di uno stesso utente vicini, minimizzando il tempo per ottenere le pagine
create table Ordine(
	idOrdine int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	idOggetto int NOT NULL, -- E' la coppia id oggetto, negozio ad identificare un oggetto
	idNegozio int NOT NULL, -- Ed in più così da un singolo ordine si possono avere più informazioni riguardanti al negozio direttamente
	idUtente int NOT NULL,
	stato int NOT NULL default 0, -- 0 Ricevuto, 1 In Elaborazione, 2 Spedito, 3 Consegnato
	quantita int NOT NULL default 1,
	FOREIGN KEY (idOggetto) REFERENCES Oggetto(id) ON DELETE NO ACTION,
	FOREIGN KEY (idNegozio) REFERENCES Negozio(id) ON DELETE NO ACTION,
	FOREIGN KEY (idUtente) REFERENCES Utente(id) ON DELETE CASCADE 	-- Se viene eliminato un utente che aveva fatto questo ordine
																	-- Allora elimino anche la lista dei suoi ordini
);

create table Carrello(
	idUtente int NOT NULL,	-- Identitifica l'utente che ha questo ordine nel carrello
	idOrdine int NOT NULL,	-- Identifica l'ordine che è nel carrello dell'utente
	subTotal double NOT NULL default 0,	-- Fa riferimento solo al subtotal del singolo ordine, per avere il totale complessivo sommare
										-- Tutti i subtotal
	PRIMARY KEY(idUtente,idOrdine)
);

create table Assistenza(
	id int AUTO_INCREMENT PRIMARY KEY,
	idUtente int NOT NULL,	-- Identifico l'utente che ha richiesto l'assistenza
	idVenditore int,	-- Identifico il venditore su cui è stata richiesta assistenza da parte di un utente
	idAmministratore int NOT NULL,	-- Identifico l'amministratore designato a gestire l'assistenza
	idOrdine int,	-- Identifico l'ordine su cui si richiede assistenza
	stato int default 0,	-- Identifico lo stato di avanzamento della richiesta di assistenza
	soluzione varchar(2500) default NULL,	-- Identifico attraverso una descrizione la decisione presa dall'amministratore
	FOREIGN KEY (idUtente) REFERENCES Utente(id) ON DELETE NO ACTION,				-- Mantengo i dati sull'assistenza anche dopo la cancellazione di un singolo utente
																				-- ipotesi:
																				-- Sfruttare un trigger in modo che quando viene eliminato un utente
																				-- 1) controllo se aveva eventuali richieste di assistenza a suo carico
																				-- 2) se tutti e 3 gli utenti a cui si faceva riferimento sono stati eliminati allora elimino anche la richiesta di assistenza
																				-- 3) altrimenti resterà in memoria per la consultazione da parte degli altri utenti
	FOREIGN KEY (idVenditore) REFERENCES Utente(id) ON DELETE NO ACTION,
	FOREIGN KEY (idAmministratore) REFERENCES Utente(id) ON DELETE NO ACTION,
	FOREIGN KEY (idOrdine) REFERENCES Ordine(idOrdine) ON DELETE NO ACTION
);

-- I 3 tipi di recensioni devono essere gestite in 3 tabelle separate in modo da mantenere una semplicità
-- di ricerca e di filtraggio
-- Ipotesi: utilizzare un hash basato sull'id dell'oggetto, id negozio che lo vende, in modo da velocizzare le operazioni di ottenimento
-- L'ordine può essere fatto a livello di data, o utilità nella query sql
create table RecensioneOggetto(
	id int NOT NULL AUTO_INCREMENT, -- Identifica la singola recensione
	idOggetto int NOT NULL,	-- Un oggetto viene identificato attraverso l'id oggetto e l'id negozio quindi servono entrambe
	idUtente int NOT NULL,	-- Identifco l'utente che ha scritto quella recensione
	testo varchar(2500) default NULL,
	valutazione int NOT NULL,	-- Valutazione fornita all'oggetto
								-- Può essistere una valutzione senza descrizione quindi servirà solo alla media di stelle dell'oggetto
								-- Non può esistere una recensione con testo senza valutazione
	data datetime NOT NULL,
	utilita int default NULL,
	PRIMARY KEY(id),
	FOREIGN KEY (idOggetto) REFERENCES Oggetto(id) ON DELETE CASCADE			-- Se viene eliminato l'oggetto o il negozio elimino le recensioni
																				-- Se viene eliminato l'utente mantengo le sue recensioni
	FOREIGN KEY (idUtente) REFERENCES Utente(id) ON DELETE NO ACTION
);
CREATE INDEX idRecOggIndex ON RecensioneOggetto (idOggetto) USING HASH;

create table RecensioneNegozio(
	id int NOT NULL,
	idNegozio int NOT NULL,
	idUtente int NOT NULL,
	testo varchar(2500) default NULL,
	valutazione int NOT NULL,
	data datetime NOT NULL,
	utilita int default NULL,
	PRIMARY KEY(id),
	FOREIGN KEY (idNegozio) REFERENCES Negozio(id) ON DELETE CASCADE,
	FOREIGN KEY (idUtente) REFERENCES Utente(id) ON DELETE NO ACTION
);
CREATE INDEX idRecNegIndex ON RecensioneNegozio (idNegozio) USING HASH;

create table RecensioneVenditore(
	id int NOT NULL PRIMARY KEY,
	idVenditore int NOT NULL,
	idUtente int NOT NULL,
	testo varchar(2500) default NULL,
	valutazione int NOT NULL,
	data datetime NOT NULL,
	utilita int default NULL,
	FOREIGN KEY (idVenditore) REFERENCES Utente(id) ON DELETE CASCADE,
	FOREIGN KEY (idUtente) REFERENCES Utente(id) ON DELETE NO ACTION
);
CREATE INDEX idRecVenIndex ON RecensioneVenditore (idVenditore) USING HASH;

-- Le risposte potranno essere fatte solo dal venditore direttamente interessato, che è ricavabile dalla recensione
-- oggetto->negozio->venditore
create table RispostaOggetto(
	idRecensione int NOT NULL,
	testo varchar(2500) NOT NULL,
	data datetime NOT NULL,
	PRIMARY KEY(idRecensione),
	FOREIGN KEY (idRecensione) REFERENCES RecensioneOggetto(id) ON DELETE CASCADE
);

create table RispostaNegozio(
	idRecensione int NOT NULL,
	testo varchar(2500) NOT NULL,
	data datetime NOT NULL,
	PRIMARY KEY(idRecensione),
	FOREIGN KEY (idRecensione) REFERENCES RecensioneNegozio(id) ON DELETE CASCADE
);

create table RispostaVenditore(
	idRecensione int NOT NULL,
	testo varchar(2500) NOT NULL,
	data datetime NOT NULL,
	PRIMARY KEY(idRecensione),
	FOREIGN KEY (idRecensione) REFERENCES RecensioneVenditore(id) ON DELETE CASCADE
);

create table imageUtente(
	idI int,
	idU int,
	FOREIGN KEY (idI) REFERENCES image(idI),
	FOREIGN KEY (idU) REFERENCES Utente(id)
);

create table imageNegozio(
	idI int,
	idN int,
	FOREIGN KEY (idI) REFERENCES image(idI),
	FOREIGN KEY (idN) REFERENCES Negozio(id)
);

create table imageRecensione(
	idI int,
	idR int,
	FOREIGN KEY (idI) REFERENCES image(idI),
	FOREIGN KEY (idR) REFERENCES RecensioneOggetto(id)
);

create table imageOggetto(
	idI int,
	idO int,
	FOREIGN KEY (idI) REFERENCES image(idI),
	FOREIGN KEY (idO) REFERENCES Oggetto(id)
);

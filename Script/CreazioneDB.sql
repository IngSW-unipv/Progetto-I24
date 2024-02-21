drop database if exists unibook;
Create schema if not exists unibook;
use unibook;

Create table if not exists utente(
Matricola 	varchar(7) not null,
Nome		varchar(20) not null,
Cognome		varchar(20) not null,
Tipo		varchar(15) not null,
Email		varchar(50) not null,
Corso		varchar(10),
Password	varchar(32) not null,
primary key (Matricola)
);

create table if not exists laboratorio(
ID int not null,
N_posti int not null,
Nome varchar(15) not null,
primary key (ID)
);

create table if not exists risorsa(
ID		int not null,
Nome 	varchar(15) not null,
Descrizione	TINYTEXT,
Indirizzo	varchar(10) not null,
Tipo	varchar(10) not null,
ID_Lab int,
Matricola_inserimento varchar(7),
Prezzo double,
primary key (ID),
foreign key (ID_Lab) references laboratorio(ID),
foreign key (Matricola_inserimento) references utente (Matricola)
);

create table if not exists prenotazione(
ID_Risorsa int not null,
Matricola varchar(7) not null,
DataOra datetime not null,
tempo smallint,
primary key (ID_Risorsa, Matricola, DataOra),
foreign key (ID_Risorsa) references risorsa (ID),
foreign key (Matricola) references utente(Matricola)
);

create table if not exists affitto(
ID_Risorsa int not null,
Matricola varchar(7) not null,
DataInizio date not null,
DataFine date not null,
Costo double not null,
primary key (ID_Risorsa, Matricola,DataInizio),
foreign key (ID_Risorsa) references risorsa (ID),
foreign key (Matricola) references utente(Matricola)
);

CREATE TABLE IF NOT EXISTS messaggio (
    id INT AUTO_INCREMENT PRIMARY KEY,
    mittente VARCHAR(7) NOT NULL,
    destinatario VARCHAR(7) NOT NULL,
    testo TEXT NOT NULL,
    dataOra TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    letto BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (mittente) REFERENCES utente(Matricola),
    FOREIGN KEY (destinatario) REFERENCES utente(Matricola)
);

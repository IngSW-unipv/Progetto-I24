drop database if exists Unibook;
Create schema if not exists Unibook;
use Unibook;

Create table if not exists Utente(
Matricola 	varchar(7) not null,
Nome		varchar(20) not null,
Cognome		varchar(20) not null,
Tipo		varchar(15) not null,
Email		varchar(50) not null,
Corso		varchar(10),
Password	varchar(32) not null,
primary key (Matricola)
);

create table if not exists Laboratorio(
ID int not null,
N_posti int not null,
primary key (ID)
);

create table if not exists Risorsa(
ID		int not null,
Nome 	varchar(15) not null,
Descrizione	TINYTEXT,
Indirizzo	varchar(10) not null,
Tipo	varchar(10) not null,
ID_Lab int,
Matricola_inserimento varchar(7),
primary key (ID),
foreign key (ID_Lab) references Laboratorio(ID),
foreign key (Matricola_inserimento) references Utente (Matricola)
);

create table if not exists Prenotazione(
ID_Risorsa int not null,
Matricola varchar(7) not null,
DataOra datetime not null,
tempo smallint,
primary key (ID_Risorsa, Matricola, DataOra),
foreign key (ID_Risorsa) references Risorsa (ID),
foreign key (Matricola) references Utente(Matricola)
);

create table if not exists Affitto(
ID_Risorsa int not null,
Matricola varchar(7) not null,
DataInizio date not null,
Durata int not null,
Costo double not null,
primary key (ID_Risorsa, Matricola),
foreign key (ID_Risorsa) references Risorsa (ID),
foreign key (Matricola) references Utente(Matricola)
);

-- SCRIPT DB UTENTE
INSERT INTO `unibook`.`utente` (`Matricola`, `Nome`, `Cognome`, `Tipo`, `Email`, `Corso`, `Password`) VALUES ('S500816', 'Mario', 'Rossi', 'Studente', 'mario.rossi01@universitadipavia.it', 'INF', '123');
INSERT INTO `unibook`.`utente` (`Matricola`, `Nome`, `Cognome`, `Tipo`, `Email`, `Corso`, `Password`) VALUES ('P501934', 'Luca', 'Neri', 'Professore', 'luca.neri01@universitadipavia.it', 'INF', '234');
INSERT INTO `unibook`.`utente` (`Matricola`, `Nome`, `Cognome`, `Tipo`, `Email`, `Corso`, `Password`) VALUES ('R509822', 'Nicola', 'Verdi', 'Ricercatore', 'nicola.verdi01@universitadipavia.it', 'ELE', '345');
INSERT INTO `unibook`.`utente` (`Matricola`, `Nome`, `Cognome`, `Tipo`, `Email`, `Corso`, `Password`) VALUES ('S589231', 'Marco', 'Bianchi', 'Studente', 'marco.bianchi01@universitadipavia.it', 'ELE', '456');
INSERT INTO `unibook`.`utente` (`Matricola`, `Nome`, `Cognome`, `Tipo`, `Email`, `Corso`, `Password`) VALUES ('P503454', 'Andrea', 'Bruni', 'Professore', 'andrea.bruni01@universitadipavia.it', 'ELE', '567');
INSERT INTO `unibook`.`utente` (`Matricola`, `Nome`, `Cognome`, `Tipo`, `Email`, `Corso`, `Password`) VALUES ('S506567', 'Michele', 'Lombardi', 'Studente', 'michele.lombardi01@universitadipavia.it', 'INF', '678');
INSERT INTO `unibook`.`utente` (`Matricola`, `Nome`, `Cognome`, `Tipo`, `Email`, `Corso`, `Password`) VALUES ('P509899', 'Luigi', 'Esposito', 'Professore', 'luigi.esposito01@universitadipavia.it', 'INF', '789');

-- Laboratorio
INSERT INTO `unibook`.`laboratorio` (`ID`, `N_posti`) VALUES ('1', '30');
INSERT INTO `unibook`.`laboratorio` (`ID`, `N_posti`) VALUES ('2', '20');
INSERT INTO `unibook`.`laboratorio` (`ID`, `N_posti`) VALUES ('3', '15');
INSERT INTO `unibook`.`laboratorio` (`ID`, `N_posti`) VALUES ('4', '10');
-- Risorsa
INSERT INTO `unibook`.`risorsa` (`ID`, `Nome`, `Descrizione`, `Indirizzo`, `Tipo`, `ID_Lab`, `Matricola_inserimento`) VALUES ('5', 'Postazione A11', 'Postazione laboratorio informatica', 'INF', 'P', '1', null);
INSERT INTO `unibook`.`risorsa` (`ID`, `Nome`, `Descrizione`, `Indirizzo`, `Tipo`, `ID_Lab`, `Matricola_inserimento`) VALUES ('6', 'Postazione A12', 'Postazione laboratorio informatica', 'INF', 'P', '1', null);
INSERT INTO `unibook`.`risorsa` (`ID`, `Nome`, `Descrizione`, `Indirizzo`, `Tipo`, `ID_Lab`, `Matricola_inserimento`) VALUES ('7', 'Postazione A21', 'Postazione laboratorio elettronica', 'INF', 'P', '2', null);
INSERT INTO `unibook`.`risorsa` (`ID`, `Nome`, `Descrizione`, `Indirizzo`, `Tipo`, `ID_Lab`, `Matricola_inserimento`) VALUES ('8', 'Postazione A22', 'Postazione laboratorio informatica', 'INF', 'P', '2', null);
INSERT INTO `unibook`.`risorsa` (`ID`, `Nome`, `Descrizione`, `Indirizzo`, `Tipo`, `ID_Lab`, `Matricola_inserimento`) VALUES ('9', 'Postazione B11', 'Postazione laboratorio elettronica', 'ELE', 'P', '3', null);
INSERT INTO `unibook`.`risorsa` (`ID`, `Nome`, `Descrizione`, `Indirizzo`, `Tipo`, `ID_Lab`, `Matricola_inserimento`) VALUES ('10', 'Postazione B21', 'Postazione laboratorio elettronica', 'ELE', 'P', '4', null);
INSERT INTO `unibook`.`risorsa` (`ID`, `Nome`, `Descrizione`, `Indirizzo`, `Tipo`, `Matricola_inserimento`) VALUES ('11', 'ROM', 'ROM da 8GB', 'INF', 'P', 'R509822');
INSERT INTO `unibook`.`risorsa` (`ID`, `Nome`, `Descrizione`, `Indirizzo`, `Tipo`, `Matricola_inserimento`) VALUES ('12', 'FPGA', 'FPGA a disposizione degli studenti', 'INF', 'A', 'P501934');
INSERT INTO `unibook`.`risorsa` (`ID`, `Nome`, `Descrizione`, `Indirizzo`, `Tipo`, `Matricola_inserimento`) VALUES ('13', 'Arduino', 'Arduino', 'INF', 'P', 'P501934');

-- Prenotazioni
INSERT INTO `unibook`.`Prenotazione` (`ID_Risorsa`, `Matricola`, `DataOra`, `tempo`) VALUES ('5', 'S500816', '2024-05-06', '2');
INSERT INTO `unibook`.`Prenotazione` (`ID_Risorsa`, `Matricola`, `DataOra`, `tempo`) VALUES ('6', 'S506567', '2024-05-07', '1');
-- Affitto
INSERT INTO `unibook`.`affitto` (`ID_Risorsa`, `Matricola`, `DataInizio`, `Durata`, `Costo`) VALUES ('11', 'S500816', '2024-05-08', '1', '20');
INSERT INTO `unibook`.`affitto` (`ID_Risorsa`, `Matricola`, `DataInizio`, `Durata`, `Costo`) VALUES ('12', 'R509822', '2024-04-09', '1', '30');

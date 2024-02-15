
-- SCRIPT DB UTENTE
INSERT INTO `unibook`.`utente` (`Matricola`, `Nome`, `Cognome`, `Tipo`, `Email`, `Corso`, `Password`) VALUES ('S500816', 'Davide', 'De Vittorio', 'Studente', 'davide.devittorio01@universitadipavia.it', 'INF', '000');
INSERT INTO `unibook`.`utente` (`Matricola`, `Nome`, `Cognome`, `Tipo`, `Email`, `Corso`, `Password`) VALUES ('S503179', 'Migs Matthew', 'Acar', 'Studente', 'migsmatthew.acar01@universitadipavia.it', 'INF', '000');
INSERT INTO `unibook`.`utente` (`Matricola`, `Nome`, `Cognome`, `Tipo`, `Email`, `Corso`, `Password`) VALUES ('S505439', 'Riccardo', 'Rota Mino', 'Studente', 'riccardo.rotamino01@universitadipavia.it', 'INF', '000');
INSERT INTO `unibook`.`utente` (`Matricola`, `Nome`, `Cognome`, `Tipo`, `Email`, `Corso`, `Password`) VALUES ('P501934', 'Luca', 'Neri', 'Professore', 'luca.neri01@universitadipavia.it', 'INF', '234');
INSERT INTO `unibook`.`utente` (`Matricola`, `Nome`, `Cognome`, `Tipo`, `Email`, `Corso`, `Password`) VALUES ('R509822', 'Nicola', 'Verdi', 'Ricercatore', 'nicola.verdi01@universitadipavia.it', 'ELE', '345');
INSERT INTO `unibook`.`utente` (`Matricola`, `Nome`, `Cognome`, `Tipo`, `Email`, `Corso`, `Password`) VALUES ('S589231', 'Marco', 'Bianchi', 'Studente', 'marco.bianchi01@universitadipavia.it', 'ELE', '456');
INSERT INTO `unibook`.`utente` (`Matricola`, `Nome`, `Cognome`, `Tipo`, `Email`, `Corso`, `Password`) VALUES ('P503454', 'Andrea', 'Bruni', 'Professore', 'andrea.bruni01@universitadipavia.it', 'ELE', '567');
INSERT INTO `unibook`.`utente` (`Matricola`, `Nome`, `Cognome`, `Tipo`, `Email`, `Corso`, `Password`) VALUES ('S506567', 'Michele', 'Lombardi', 'Studente', 'michele.lombardi01@universitadipavia.it', 'INF', '678');
INSERT INTO `unibook`.`utente` (`Matricola`, `Nome`, `Cognome`, `Tipo`, `Email`, `Corso`, `Password`) VALUES ('P509899', 'Luigi', 'Esposito', 'Professore', 'luigi.esposito01@universitadipavia.it', 'INF', '789');
INSERT INTO `unibook`.`utente` (`Matricola`, `Nome`, `Cognome`, `Tipo`, `Email`, `Corso`, `Password`) VALUES ('P500999', 'Mario', 'Rossi', 'Ricercatore', 'mario.rossi01@universitadipavia.it', 'ELE', '000');

-- Laboratorio
INSERT INTO `unibook`.`laboratorio` (`ID`, `N_posti`, `Nome`) VALUES ('1', '30', 'Laboratorio A1');
INSERT INTO `unibook`.`laboratorio` (`ID`, `N_posti`, `Nome`) VALUES ('2', '20', 'Laboratorio A2');
INSERT INTO `unibook`.`laboratorio` (`ID`, `N_posti`, `Nome`) VALUES ('3', '15', 'Laboratorio B1');
INSERT INTO `unibook`.`laboratorio` (`ID`, `N_posti`, `Nome`) VALUES ('4', '10', 'Laboratorio B2');
INSERT INTO `unibook`.`laboratorio` (`ID`, `N_posti`, `Nome`) VALUES ('5', '5', 'Laboratorio C');

-- Risorsa
INSERT INTO `unibook`.`risorsa` (`ID`, `Nome`, `Descrizione`, `Indirizzo`, `Tipo`, `ID_Lab`, `Matricola_inserimento`) VALUES ('1', 'Postazione A11', 'Postazione laboratorio A1 informatica', 'INF', 'P', '1', null);
INSERT INTO `unibook`.`risorsa` (`ID`, `Nome`, `Descrizione`, `Indirizzo`, `Tipo`, `ID_Lab`, `Matricola_inserimento`) VALUES ('2', 'Postazione A12', 'Postazione laboratorio A1 informatica', 'INF', 'P', '1', null);
INSERT INTO `unibook`.`risorsa` (`ID`, `Nome`, `Descrizione`, `Indirizzo`, `Tipo`, `ID_Lab`, `Matricola_inserimento`) VALUES ('3', 'Postazione A13', 'Postazione laboratorio A1 informatica', 'INF', 'P', '1', null);
INSERT INTO `unibook`.`risorsa` (`ID`, `Nome`, `Descrizione`, `Indirizzo`, `Tipo`, `ID_Lab`, `Matricola_inserimento`) VALUES ('4', 'Postazione A14', 'Postazione laboratorio A1 informatica', 'INF', 'P', '1', null);
INSERT INTO `unibook`.`risorsa` (`ID`, `Nome`, `Descrizione`, `Indirizzo`, `Tipo`, `ID_Lab`, `Matricola_inserimento`) VALUES ('5', 'Postazione A21', 'Postazione laboratorio A2 informatica', 'INF', 'P', '2', null);
INSERT INTO `unibook`.`risorsa` (`ID`, `Nome`, `Descrizione`, `Indirizzo`, `Tipo`, `ID_Lab`, `Matricola_inserimento`) VALUES ('6', 'Postazione A22', 'Postazione laboratorio A2 informatica', 'INF', 'P', '2', null);
INSERT INTO `unibook`.`risorsa` (`ID`, `Nome`, `Descrizione`, `Indirizzo`, `Tipo`, `ID_Lab`, `Matricola_inserimento`) VALUES ('7', 'Postazione A23', 'Postazione laboratorio A2 informatica', 'INF', 'P', '2', null);
INSERT INTO `unibook`.`risorsa` (`ID`, `Nome`, `Descrizione`, `Indirizzo`, `Tipo`, `ID_Lab`, `Matricola_inserimento`) VALUES ('8', 'Postazione A24', 'Postazione laboratorio A2 informatica', 'INF', 'P', '2', null);
INSERT INTO `unibook`.`risorsa` (`ID`, `Nome`, `Descrizione`, `Indirizzo`, `Tipo`, `ID_Lab`, `Matricola_inserimento`) VALUES ('9', 'Postazione B11', 'Postazione laboratorio B1 elettronica', 'ELE', 'P', '3', null);
INSERT INTO `unibook`.`risorsa` (`ID`, `Nome`, `Descrizione`, `Indirizzo`, `Tipo`, `ID_Lab`, `Matricola_inserimento`) VALUES ('14', 'Postazione B12', 'Postazione laboratorio B1 elettronica', 'ELE', 'P', '3', null);
INSERT INTO `unibook`.`risorsa` (`ID`, `Nome`, `Descrizione`, `Indirizzo`, `Tipo`, `ID_Lab`, `Matricola_inserimento`) VALUES ('10', 'Postazione B21', 'Postazione laboratorio B2 elettronica', 'ELE', 'P', '4', null);
INSERT INTO `unibook`.`risorsa` (`ID`, `Nome`, `Descrizione`, `Indirizzo`, `Tipo`, `ID_Lab`, `Matricola_inserimento`) VALUES ('15', 'Postazione B22', 'Postazione laboratorio B2 elettronica', 'ELE', 'P', '4', null);
INSERT INTO `unibook`.`risorsa` (`ID`, `Nome`, `Descrizione`, `Indirizzo`, `Tipo`, `ID_Lab`, `Matricola_inserimento`) VALUES ('16', 'Postazione C1', 'Postazione laboratorio B2 elettronica', 'ELE', 'P', '4', null);
INSERT INTO `unibook`.`risorsa` (`ID`, `Nome`, `Descrizione`, `Indirizzo`, `Tipo`, `ID_Lab`, `Matricola_inserimento`) VALUES ('17', 'Postazione C2', 'Postazione laboratorio B2 elettronica', 'ELE', 'P', '4', null);
INSERT INTO `unibook`.`risorsa` (`ID`, `Nome`, `Descrizione`, `Indirizzo`, `Tipo`, `ID_Lab`, `Matricola_inserimento`) VALUES ('18', 'Postazione C3', 'Postazione laboratorio B2 elettronica', 'ELE', 'P', '4', null);
INSERT INTO `unibook`.`risorsa` (`ID`, `Nome`, `Descrizione`, `Indirizzo`, `Tipo`, `Matricola_inserimento`) VALUES ('11', 'ROM', 'ROM da 8GB', 'INF', 'A', 'R509822');
INSERT INTO `unibook`.`risorsa` (`ID`, `Nome`, `Descrizione`, `Indirizzo`, `Tipo`, `Matricola_inserimento`) VALUES ('12', 'FPGA', 'FPGA a disposizione degli studenti', 'INF', 'A', 'P501934');
INSERT INTO `unibook`.`risorsa` (`ID`, `Nome`, `Descrizione`, `Indirizzo`, `Tipo`, `Matricola_inserimento`) VALUES ('13', 'Arduino', 'Arduino', 'INF', 'P', 'P501934');


-- Prenotazioni
INSERT INTO `unibook`.`prenotazione` (`ID_Risorsa`, `Matricola`, `DataOra`, `tempo`) VALUES ('16', 'S500816', '2024-05-06 08:00', '2');
INSERT INTO `unibook`.`prenotazione` (`ID_Risorsa`, `Matricola`, `DataOra`, `tempo`) VALUES ('6', 'S506567', '2024-05-07 08:00' , '1');
INSERT INTO `unibook`.`prenotazione` (`ID_Risorsa`, `Matricola`, `DataOra`, `tempo`) VALUES ('17', 'S500816', '2024-05-06 08:00', '2');
INSERT INTO `unibook`.`prenotazione` (`ID_Risorsa`, `Matricola`, `DataOra`, `tempo`) VALUES ('18', 'S500816', '2024-05-06 08:00', '2');

-- Affitto
INSERT INTO `unibook`.`affitto` (`ID_Risorsa`, `Matricola`, `DataInizio`, `Durata`, `Costo`) VALUES ('11', 'S500816', '2024-05-08', '1', '20');
INSERT INTO `unibook`.`affitto` (`ID_Risorsa`, `Matricola`, `DataInizio`, `Durata`, `Costo`) VALUES ('12', 'R509822', '2024-04-09', '1', '30');

-- TABLE DE JOINTURE USER_APPLICATION
insert into tl_utilisateur_application (`id_utilisateur`, `id_application`) values
	(1, 3), (1, 5),
	(2, 1),
	(3, 4), (3, 5),
	(4, 2), (4, 3), (4, 4),
	(5, 1), (5, 2),
	(6, 1), (6, 3), (6, 4), (6, 5),
	(7, 4),
	(8, 1), (8, 2), (8, 3), (8, 4), (8, 5),
	(9, 2), (9, 3), (9, 5),
	(10, 2), (10, 3), (10, 4),
	(11, 1), (11, 2), (11, 3), (11, 4), (11, 5);

-- MODIFICATION TABLE CERTIFICATE
update certificat c1
	set c1.date_emission_certificat = '2014-12-17', 
		c1.fin_validite_certificat = '2016-12-17'
	where c1.nom_certificat = 'GOB-20141217-prod';
update certificat c2
	set c2.date_emission_certificat = '2019-01-25', 
		c2.fin_validite_certificat = '2021-01-25'
	where c2.nom_certificat = 'GOB-20190125-int';
update certificat c3
	set c3.date_emission_certificat = '2017-01-31', 
		c3.fin_validite_certificat = '2019-01-31'
	where c3.nom_certificat = 'J5P-20170131-rec';
update certificat c4
	set c4.date_emission_certificat = '2017-08-03', 
		c4.fin_validite_certificat = '2019-08-03'
	where c4.nom_certificat = 'J5P-20170803-prod';
update certificat c5
	set c5.date_emission_certificat = '2017-03-18', 
		c5.fin_validite_certificat = '2019-03-18'
	where c5.nom_certificat = 'J5P-20170318-int';
update certificat c6
	set c6.date_emission_certificat = '2017-03-29', 
		c6.fin_validite_certificat = '2019-03-29'
	where c6.nom_certificat = 'J5P-20170329-dev';
update certificat c7
	set c7.date_emission_certificat = '2018-08-06', 
		c7.fin_validite_certificat = '2020-08-06'
	where c7.nom_certificat = 'LPM-20180806-int';
update certificat c8
	set c8.date_emission_certificat = '2018-08-06', 
		c8.fin_validite_certificat = '2020-08-06'
	where c8.nom_certificat = 'LPM-20180806-prod';
update certificat c9
	set c9.date_emission_certificat = '2018-04-10', 
		c9.fin_validite_certificat = '2020-04-10'
	where c9.nom_certificat = 'LR_-20180410-dev';
update certificat c10
	set c10.date_emission_certificat = '2018-04-12', 
		c10.fin_validite_certificat = '2020-04-12'
	where c10.nom_certificat = 'LR_-20180412-prod';
update certificat c11
	set c11.date_emission_certificat = '2018-04-12', 
		c11.fin_validite_certificat = '2020-04-12'
	where c11.nom_certificat = 'LR_-20180412-dev';
update certificat c12
	set c12.date_emission_certificat = '2017-07-18', 
		c12.fin_validite_certificat = '2019-07-18'
	where c12.nom_certificat = 'TE_-20170718-prod';
update certificat c13
	set c13.date_emission_certificat = '2017-07-18', 
		c13.fin_validite_certificat = '2019-07-18'
	where c13.nom_certificat = 'TE_-20170718-dev';
    
-- TABLE DE JOINTURE CERTIFICATE_SERVER
insert into tl_certificat_serveur (`id_certificat`, `id_serveur`) values
	(1, 1), (1, 2),
	(2, 8),
	(3, 1), (3, 2), (3, 9),
	(4, 1), (4, 2), (4, 10),
	(5, 1), (5, 2), (5, 10),
	(6, 1), (6, 2), (6, 9),
	(7, 1), (7, 2), (7, 10),
	(8, 1), (8, 2), (8, 10),
	(9, 1), (9, 2), (9, 9),
	(10, 1), (10, 2), (10, 9),
	(11, 1), (11, 2), (11, 9),
	(12, 1), (12, 2), (12, 9),
	(13, 1), (13, 2), (13, 9);
	
-- MODIFICATION TABLE ADDRESSEALTERNATIVE
update adresse_alternative a1
	set a1.id_certificat = 4
    where a1.lien_adresse_alternative = 'www.e-recrutement.rh.laposte.fr';
update adresse_alternative a2
	set a2.id_certificat = 5
    where a2.lien_adresse_alternative = 'www.e-recrutement-int.rh.laposte.fr';
update adresse_alternative a3
	set a3.id_certificat = 9
    where a3.lien_adresse_alternative = 'www.intranet-rh-preprod-ext.rh.intra.laposte.fr';
update adresse_alternative a4
	set a4.id_certificat = 10
    where a4.lien_adresse_alternative = 'www.intranet-rh.rh.intra.laposte.fr';
update adresse_alternative a5
	set a5.id_certificat = 11
    where a5.lien_adresse_alternative = 'www.intranet-rh.int.rh.intra.laposte.fr';
update adresse_alternative a6
	set a6.id_certificat = 12
    where a6.lien_adresse_alternative = 'www.elections-1.rh.intra.laposte.fr';
update adresse_alternative a7
	set a7.id_certificat = 12
    where a7.lien_adresse_alternative = 'www.elections-2.rh.intra.laposte.fr';
update adresse_alternative a8
	set a8.id_certificat = 12
    where a8.lien_adresse_alternative = 'www.elections-3.rh.intra.laposte.fr';
update adresse_alternative a9
	set a9.id_certificat = 12
    where a9.lien_adresse_alternative = 'www.rh-elections.extra.laposte.fr';
update adresse_alternative a10
	set a10.id_certificat = 13
    where a10.lien_adresse_alternative = 'www.elections-1-int.rh.intra.laposte.fr';
update adresse_alternative a11
	set a11.id_certificat = 13
    where a11.lien_adresse_alternative = 'www.elections-2-int.rh.intra.laposte.fr';
update adresse_alternative a12
	set a12.id_certificat = 13
    where a12.lien_adresse_alternative = 'www.rh-elections-int.extra.laposte.fr';
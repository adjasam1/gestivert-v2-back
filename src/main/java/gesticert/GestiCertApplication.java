package gesticert;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Méthode principale de l'application
 * 
 * @author Samuel Sabot
 * @version 0.2
 */
@SpringBootApplication
public class GestiCertApplication implements CommandLineRunner {

	/*
	 * Méthode principale
	 */
	public static void main(String[] args) {
		// Démarrage et lancement en application Spring à partir de la méthode
		// principale
		SpringApplication.run(GestiCertApplication.class, args);
	}

	/*
	 * Codage mots de passe avec BCrypt
	 */
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	/**
//	 * Injection des dépendances pour la création d'un jeu initial de données
//	 * 
//	 * @see DepartmentService
//	 * @see ProfileService
//	 * @see UserService
//	 * @see ApplicationService
//	 * @see EnvironmentService
//	 * @see PlatformService
//	 * @see RootService
//	 * @see ServerService
//	 * @see StatusDemandService
//	 * @see TypeDemandService
//	 * @see CertificateService
//	 * @see AddressAlternativeService
//	 */
//	
//	@Autowired
//	DepartmentService departmentServ;
//	
//	@Autowired
//	ProfileService profileServ;
//	
//	@Autowired
//	UserService userServ;
//	
//	@Autowired
//	ApplicationService applicationServ;
//	
//	@Autowired
//	EnvironmentService environmentServ;
//	
//	@Autowired
//	PlatformService platformServ;
//	
//	@Autowired
//	RootService rootServ;
//	
//	@Autowired
//	ServerService serverServ;
//	
//	@Autowired
//	StatusDemandService statusDemandServ;
//	
//	@Autowired
//	TypeDemandService typeDemandServ;
//	
//	@Autowired
//	CertificateService certificateServ;
//	
//	@Autowired
//	AddressAlternativeService addressAlternativeServ;
//	
//	/**
//	 * Création d'un jeu initial de données dans la base de données
//	 */
	@Override
	public void run(String... params) throws Exception {
//		
//    	Department dep1 = new Department("Dev Web");
//    	departmentServ.createDepartment(dep1);
//    	Department dep2 = new Department("SIGP3");
//    	departmentServ.createDepartment(dep2);
//    	Department dep3 = new Department("Appli HR");
//    	departmentServ.createDepartment(dep3);
//    	Department dep4 = new Department("Dev HR");
//    	departmentServ.createDepartment(dep4);
//    	
//    	Profile pro1 = new Profile("Chef(fe) de Service");
//    	profileServ.createProfile(pro1);
//    	Profile pro2 = new Profile("Chef(fe) de Projet");
//    	profileServ.createProfile(pro2);
//    	Profile pro3 = new Profile("Développeur(se)");
//    	profileServ.createProfile(pro3);
//        
//    	User ala = new User("paaa001", "aaa", "Zanzibar", "Alain", "alain.zanzibar@laposte.fr", "0678564321", 
//        		dep1, pro3, new ArrayList<>(Arrays.asList(Role.ROLE_DEV)));
//        userServ.signup(ala);
//        User bea = new User("pbbb002", "bbb", "Yemen", "Béatrice", "beatrice.yemen@laposte.fr", "0659527564", 
//        		dep1, pro3, new ArrayList<>(Arrays.asList(Role.ROLE_ADMIN)));
//        userServ.signup(bea);
//        User cla = new User("xccc003", "ccc", "Xeres", "Claude", "claude.xeres@laposte.fr", "0765098765", 
//        		dep2, pro3, new ArrayList<>(Arrays.asList(Role.ROLE_DEV)));
//        userServ.signup(cla);
//        User den = new User("pddd004", "ddd", "Wapiti", "Denise", "denise.wapiti@laposte.fr", "0664598765", 
//        		dep1, pro1, new ArrayList<>(Arrays.asList(Role.ROLE_ADMIN)));
//        userServ.signup(den);
//        User eti = new User("xeee005", "eee", "Vanuatu", "Etienne", "etienne.vanuatu@laposte.fr", "0743867290", 
//        		dep1, pro3, new ArrayList<>(Arrays.asList(Role.ROLE_DEV)));
//        userServ.signup(eti);
//        User fra = new User("pfff006", "fff", "Uruguay", "François", "francois.uruguay@laposte.fr", "0749715064", 
//        		dep3, pro3, new ArrayList<>(Arrays.asList(Role.ROLE_DEV)));
//        userServ.signup(fra);
//        User gus = new User("pggg007", "ggg", "Tuvalu", "Gustave", "gustave.tuvalu@laposte.fr", "0603528677", 
//        		dep2, pro1, new ArrayList<>(Arrays.asList(Role.ROLE_ADMIN)));
//        userServ.signup(gus);
//        User hel = new User("xhhh008", "hhh", "Suriname", "Hélène", "helene.suriname@laposte.fr", "0602352963", 
//        		dep1, pro3, new ArrayList<>(Arrays.asList(Role.ROLE_DEV)));
//        userServ.signup(hel);
//        User ire = new User("piii009", "iii", "Rwanda", "Irène", "irene.rwanda@laposte.fr", "0620954826", 
//        		dep1, pro2, new ArrayList<>(Arrays.asList(Role.ROLE_DEV)));
//        userServ.signup(ire);
//        User qua = new User("pjjj010", "jjj", "Quatar", "François", "francois.quatar@laposte.fr", "0644623978", 
//        		dep3, pro1, new ArrayList<>(Arrays.asList(Role.ROLE_ADMIN)));
//        userServ.signup(qua);
//        User jul = new User("xkkk011", "kkk", "Tuvalu", "Julie", "julie.tuvalu@laposte.fr", "0762327490", 
//        		dep1, pro3, new ArrayList<>(Arrays.asList(Role.ROLE_DEV)));
//        userServ.signup(jul);
//        
//        Application ap1 = new Application("LPM", "Intranet HA", "Albanie", "Zoé", "DHAG", "0166557845", "zoe.albanie@laposte.fr", null);
//        applicationServ.createApplication(ap1);
//        Application ap2 = new Application("GOB", "BAHIA", "Benin", "Yann", "CsORH/GID", "0162890464", "yann.benin@laposte.fr", 
//        		"En phase de conception du cahier des charges");
//        applicationServ.createApplication(ap2);
//        Application ap3 = new Application("TE_", "Elections", "Congo", "Xavier", "CsORH/GID", "0169464959", "xavier.congo@laposte.fr", 
//        		"Modification prévue, changement de racine pour ses certificats");
//        applicationServ.createApplication(ap3);
//        Application ap4 = new Application("LR_", "Net-RH", "Danemark", "William", "CsORH/GAPF", "0169464762", "william.danemark@laposte.fr", null);
//        applicationServ.createApplication(ap4);
//        Application ap5 = new Application("J5P", "Carrières V2", "Equateur", "Viviane", "CsORH/GID", "0169464959", "viviane.equateur@laposte.fr", 
//        		"En cours de suppression, remplacement par l'application CCX - XXXX");
//        applicationServ.createApplication(ap5);
//        
//        Environment en1 = new Environment("Développement");
//        environmentServ.createEnvironment(en1);
//        Environment en2 = new Environment("Intégration");
//        environmentServ.createEnvironment(en2);
//        Environment en3 = new Environment("Recette");
//        environmentServ.createEnvironment(en3);
//        Environment en4 = new Environment("Production");
//        environmentServ.createEnvironment(en4);
//        
//        Platform pl1 = new Platform("Amont");
//        platformServ.createPlatform(pl1);
//        Platform pl2 = new Platform("Dev/Amont");
//        platformServ.createPlatform(pl2);
//        Platform pl3 = new Platform("Int/Amont");
//        platformServ.createPlatform(pl3);
//        Platform pl4 = new Platform("Rec/Amont");
//        platformServ.createPlatform(pl4);
//        Platform pl5 = new Platform("Production");
//        platformServ.createPlatform(pl5);
//        
//        Root ro1 = new Root("DSI Centrale");
//        rootServ.createRoot(ro1);
//        Root ro2 = new Root("CertiNomis");
//        rootServ.createRoot(ro2);
//        Root ro3 = new Root("DigiCert");
//        rootServ.createRoot(ro3);
//        Root ro4 = new Root("DASU");
//        rootServ.createRoot(ro4);
//        
//        Server se1 = new Server("Apache");
//        serverServ.createServer(se1);
//        Server se2 = new Server("Red Hat");
//        serverServ.createServer(se2);
//        Server se3 = new Server("Microsoft");
//        serverServ.createServer(se3);
//        Server se4 = new Server("iPlanet");
//        serverServ.createServer(se4);
//        Server se5 = new Server("Javasoft");
//        serverServ.createServer(se5);
//        Server se6 = new Server("IBM");
//        serverServ.createServer(se6);
//        Server se7 = new Server("Web Sphere");
//        serverServ.createServer(se7);
//        Server se8 = new Server("Inet LONDRES");
//        serverServ.createServer(se8);
//        Server se9 = new Server("Londres V5.1");
//        serverServ.createServer(se9);
//        Server se10 = new Server("Londres V5.2");
//        serverServ.createServer(se10);
//        
//    	StatusDemand sd1 = new StatusDemand("Non");
//    	statusDemandServ.createStatusDemand(sd1);
//    	StatusDemand sd2 = new StatusDemand("Oui");
//    	statusDemandServ.createStatusDemand(sd2);
//    	StatusDemand sd3 = new StatusDemand("En cours");
//    	statusDemandServ.createStatusDemand(sd3);
//    	
//    	TypeDemand tp1 = new TypeDemand("Création");
//    	typeDemandServ.createTypeDemand(tp1);
//    	TypeDemand tp2 = new TypeDemand("Renouvellement");
//    	typeDemandServ.createTypeDemand(tp2);
//    	TypeDemand tp3 = new TypeDemand("Révocation");
//    	typeDemandServ.createTypeDemand(tp3);
//    	
//    	Certificate ce1 = new Certificate("GOB-20141217-prod", "www.bahia.rh.intra.laposte.fr", "telechargements/gob1", "GOB1PROD", ap2, en4, pl5, ro1);
//    	certificateServ.createCertificate(ce1);
//    	Certificate ce2 = new Certificate("GOB-20190125-int", "www.bahia-int.rh.intra.laposte.fr", "telechargements/gob2", "GOB2INT", ap2, en2, pl3, ro1);
//    	certificateServ.createCertificate(ce2);
//    	Certificate ce3 = new Certificate("J5P-20170131-rec", "www.e-recrutement-rec.rh.intra.laposte.fr", "telechargements/j5p1", "J5P1REC", ap5, en3, pl4, ro4);
//    	certificateServ.createCertificate(ce3);
//    	Certificate ce4 = new Certificate("J5P-20170803-prod", "www.laposterecrute.fr", "telechargements/j5p2", "J5P2PROD", ap5, en4, pl5, ro1);
//    	certificateServ.createCertificate(ce4);
//    	Certificate ce5 = new Certificate("J5P-20170318-int", "www.int.laposterecrute.fr", "telechargements/j5p3", "J5P3INT", ap5, en2, pl3, ro2);
//    	certificateServ.createCertificate(ce5);
//    	Certificate ce6 = new Certificate("J5P-20170329-dev", "www.e-recrutement-dev.rh.intra.laposte.fr", "telechargements/j5p4", "J5P4DEV", ap5, en1, pl2, ro3);
//    	certificateServ.createCertificate(ce6);
//    	Certificate ce7 = new Certificate("LPM-20180806-int", "www.mon-espace-ha-int.extra.laposte.fr", "telechargements/lpm1", "LPM1INT", ap1, en2, pl3, ro4);
//    	certificateServ.createCertificate(ce7);
//    	Certificate ce8 = new Certificate("LPM-20180806-prod", "www.mon-espace-ha.extra.laposte.fr", "telechargements/lpm2", "LPM1PROD", ap1, en4, pl5, ro3);
//    	certificateServ.createCertificate(ce8);
//    	Certificate ce9 = new Certificate("LR_-20180410-dev", "www.intranet-rh-preprod.rh.intra.laposte.fr", "telechargements/lr_1", "LR_1DEV", ap4, en1, pl2, ro1);
//    	certificateServ.createCertificate(ce9);
//    	Certificate ce10 = new Certificate("LR_-20180412-prod", "www.netrh.extra.laposte.fr", "telechargements/lr_2", "LR_2PROD", ap4, en4, pl5, ro2);
//    	certificateServ.createCertificate(ce10);
//    	Certificate ce11 = new Certificate("LR_-20180412-dev", "www.netrh-int.extra.laposte.fr", "telechargements/lr_3", "LR_3DEV", ap4, en1, pl2, ro1);
//    	certificateServ.createCertificate(ce11);
//    	Certificate ce12 = new Certificate("TE_-20170718-prod", "www.elections.rh.intra.laposte.fr", "telechargements/te_1", "TE_1PROD", ap3, en4, pl5, ro2);
//    	certificateServ.createCertificate(ce12);
//    	Certificate ce13 = new Certificate("TE_-20170718-dev", "www.elections-int.rh.intra.laposte.fr", "telechargements/te_2", "TE_2DEV", ap3, en1, pl2, ro1);
//    	certificateServ.createCertificate(ce13);
//
//		//AddressAlternative aa1 = new AddressAlternative("www.e-recrutement.rh.laposte.fr", ce4);
//		//addressAlternativeServ.createAddressAlternative(aa1);
//		
//		AddressAlternative aa1 = new AddressAlternative("www.e-recrutement.rh.laposte.fr");
//		addressAlternativeServ.createAddressAlternative(aa1);
//		AddressAlternative aa2 = new AddressAlternative("www.e-recrutement-int.rh.laposte.fr");
//		addressAlternativeServ.createAddressAlternative(aa2);
//		AddressAlternative aa3 = new AddressAlternative("www.intranet-rh-preprod-ext.rh.intra.laposte.fr");
//		addressAlternativeServ.createAddressAlternative(aa3);
//		AddressAlternative aa4 = new AddressAlternative("www.intranet-rh.rh.intra.laposte.fr");
//		addressAlternativeServ.createAddressAlternative(aa4);
//		AddressAlternative aa5 = new AddressAlternative("www.intranet-rh.int.rh.intra.laposte.fr");
//		addressAlternativeServ.createAddressAlternative(aa5);
//		AddressAlternative aa6 = new AddressAlternative("www.elections-1.rh.intra.laposte.fr");
//		addressAlternativeServ.createAddressAlternative(aa6);
//		AddressAlternative aa7 = new AddressAlternative("www.elections-2.rh.intra.laposte.fr");
//		addressAlternativeServ.createAddressAlternative(aa7);
//		AddressAlternative aa8 = new AddressAlternative("www.elections-3.rh.intra.laposte.fr");
//		addressAlternativeServ.createAddressAlternative(aa8);
//		AddressAlternative aa9 = new AddressAlternative("www.rh-elections.extra.laposte.fr");
//		addressAlternativeServ.createAddressAlternative(aa9);
//		AddressAlternative aa10 = new AddressAlternative("www.elections-1-int.rh.intra.laposte.fr");
//		addressAlternativeServ.createAddressAlternative(aa10);
//		AddressAlternative aa11 = new AddressAlternative("www.elections-2-int.rh.intra.laposte.fr");
//		addressAlternativeServ.createAddressAlternative(aa11);
//		AddressAlternative aa12 = new AddressAlternative("www.rh-elections-int.extra.laposte.fr");
//		addressAlternativeServ.createAddressAlternative(aa12);

	}

}

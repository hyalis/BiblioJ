import biblioj.Auteur
import biblioj.Livre
import biblioj.Reservation
import biblioj.TypeDocument

class BootStrap {
	def init = { servletContext ->
		TypeDocument t1 = new TypeDocument( intitule : "Nouveauté" )
		TypeDocument t2 = new TypeDocument( intitule : "Livre ado" )
		TypeDocument t3 = new TypeDocument( intitule : "Livre adulte" )
		
		Auteur a1 = new Auteur( nom : "Vigan", prenom : "Delphine" )
		Auteur a2 = new Auteur( nom : "Collins", prenom : "Suzanne" )
		Auteur a3 = new Auteur( nom : "Stockett", prenom : "Kathryn" )
		Auteur a4 = new Auteur( nom : "Carère", prenom : "Emmanuel" )
		Auteur a5 = new Auteur( nom : "Murakami", prenom : "Haruki" )
		Auteur a6 = new Auteur( nom : "Delacourt", prenom : "Gregoire" )
		Auteur a7 = new Auteur( nom : "Larsson", prenom : "Stieg" )
		
		Livre l1 = new Livre( titre : "Rien ne s'oppose à la nuit : roman", type : t1, nombreExemplaires : 5, nombreExemplairesDisponibles : 5 )
		Livre l2 = new Livre( titre : "La couleur des sentiments", type : t1, nombreExemplaires : 5, nombreExemplairesDisponibles : 5 )
		Livre l3 = new Livre( titre : "Limonov", type : t1, nombreExemplaires : 5, nombreExemplairesDisponibles : 5 )
		Livre l4 = new Livre( titre : "1Q84. 1. Avril-juin", type : t1, nombreExemplaires : 5, nombreExemplairesDisponibles : 5 )
		Livre l5 = new Livre( titre : "1Q84. 3. Octobre-décembre", type : t1, nombreExemplaires : 5, nombreExemplairesDisponibles : 5 )
		Livre l6 = new Livre( titre : "1Q84. 2. Juillet-septembre", type : t1, nombreExemplaires : 5, nombreExemplairesDisponibles : 5 )
		
		Livre l7 = new Livre( titre : "Hunger games", type : t2, nombreExemplaires : 8, nombreExemplairesDisponibles : 8 )
		Livre l8 = new Livre( titre : "L'embrasement", type : t2, nombreExemplaires : 10, nombreExemplairesDisponibles : 10 )
		Livre l9 = new Livre( titre : "La révolte", type : t2, nombreExemplaires : 10, nombreExemplairesDisponibles : 10 )
		
		Livre l10 = new Livre( titre : "La liste de mes envies", type : t3, nombreExemplaires : 10, nombreExemplairesDisponibles : 10 )
		Livre l11 = new Livre( titre : "La reine dans le palais des courants d'air", type : t3, nombreExemplaires : 10, nombreExemplairesDisponibles : 10 )
		
		a1.addToLivres(l1);
		
		a2.addToLivres(l7);
		a2.addToLivres(l8);
		a2.addToLivres(l9);
		
		a3.addToLivres(l2);
		
		a4.addToLivres(l3);
		
		a5.addToLivres(l4);
		a5.addToLivres(l5);
		a5.addToLivres(l6);
	
		a6.addToLivres(l10);

		a7.addToLivres(l11);
		
		
		Reservation r1 = new Reservation( code : 1, dateReservation : new Date("29/12/1991") )
		Reservation r2 = new Reservation( code : 2, dateReservation : new Date("30/02/2010") )
		Reservation r3 = new Reservation( code : 3, dateReservation : new Date("14/04/2014") )
		
		l1.ajouteLivreAReservation(r1)
		l2.ajouteLivreAReservation(r1)
		
		l2.ajouteLivreAReservation(r2)
		
		l1.ajouteLivreAReservation(r3)
		l3.ajouteLivreAReservation(r3)
		
		t1.save()
		t2.save()
		t3.save()
		
		a1.save()
		a2.save()
		a3.save()
		a4.save()
		a5.save()
		a6.save()
		a7.save()
		
		
		l1.save()
		l2.save()
		l3.save()
		l4.save()
		l5.save()
		l6.save()
		l7.save()
		l8.save()
		l9.save()
		l10.save()
		l11.save()
		
		r1.save()
		r2.save()
		r3.save()
	}
	def destroy = {
	}
}

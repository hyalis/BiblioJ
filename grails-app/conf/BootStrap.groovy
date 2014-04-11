import biblioj.Auteur
import biblioj.Livre
import biblioj.Reservation
import biblioj.TypeDocument

class BootStrap {
	def init = { servletContext ->
		TypeDocument t1 = new TypeDocument( intitule : "Livre" )
		TypeDocument t2 = new TypeDocument( intitule : "Revue" )
		TypeDocument t3 = new TypeDocument( intitule : "BD" )
		
		
		
		Auteur a1 = new Auteur( nom : "Edouard", prenom : "Clement" )
		Auteur a2 = new Auteur( nom : "Mouly", prenom : "Leo" )
		Auteur a3 = new Auteur( nom : "Lavigue", prenom : "Loic" )
		
		
		
		Livre l1 = new Livre( titre : "Babar", type : t3, nombreExemplaires : 5, nombreExemplairesDisponibles : 5 )
		Livre l2 = new Livre( titre : "JEE", type : t2, nombreExemplaires : 8, nombreExemplairesDisponibles : 8 )
		Livre l3 = new Livre( titre : "James Bond", type : t1, nombreExemplaires : 10, nombreExemplairesDisponibles : 10 )
		
		
		//l1.addToAuteurs()
		
		a1.addToLivres(l1)
		a2.addToLivres(l1)
		
		a3.addToLivres(l2)
		
		a1.addToLivres(l3)
		a3.addToLivres(l3)
		
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
		
		l1.save()
		l2.save()
		l3.save()
		
		r1.save()
		r2.save()
		r3.save()
	}
	def destroy = {
	}
}

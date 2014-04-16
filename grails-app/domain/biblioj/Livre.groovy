package biblioj

class Livre {
	String titre
	int nombreExemplaires
	int nombreExemplairesDisponibles
	TypeDocument type
	static hasMany = [reservations : Reservation, auteurs : Auteur]
	static belongsTo = Reservation
	 
	static mapping = {
		auteurs lazy : false
	}
	
    static constraints = {
		titre blank : false
		nombreExemplaires blank : false
		nombreExemplairesDisponibles blank : false	//TODO : Vérifier que nombreExemplairesDisponibles <= nombreExemplaires
		type blank : true
	}
	
	String toString() {
		type.toString() + " : " + titre + ", nombre d'exemplaires dispo : " + nombreExemplairesDisponibles
	}
	
	void ajouteLivreAReservation(Reservation r) {
		if(nombreExemplairesDisponibles > 0){
			this.addToReservations(r)
			nombreExemplairesDisponibles--
		} else {
			println("Plus d'exmplaire dispo")
		}
		
	}
}

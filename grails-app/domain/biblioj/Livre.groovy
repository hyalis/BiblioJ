package biblioj

class Livre {
	String titre
	int nombreExemplaires
	int nombreExemplairesDisponibles
	static hasMany = [reservations : Reservation, auteurs : Auteur]
	static belongsTo = Reservation
	TypeDocument type 
	
    static constraints = {
		titre blank : false
		nombreExemplaires blank : false
		nombreExemplairesDisponibles blank : false	//TODO : V�rifier que nombreExemplairesDisponibles <= nombreExemplaires
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

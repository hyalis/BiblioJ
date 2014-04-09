package biblioj

class Livre {
	String titre
	int nombreExemplaires
	int nombreExemplairesDisponibles
	static hasMany = [reservations : Reservation, auteurs : Auteur]
	static belongsTo = Reservation, Auteur
	TypeDocument type 
	
    static constraints = {
		titre blank : false
		nombreExemplaires blank : false
		nombreExemplairesDisponibles blank : false	//TODO : Vérifier que nombreExemplairesDisponibles <= nombreExemplaires
		type blank : true
	}
}

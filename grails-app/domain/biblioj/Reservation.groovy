package biblioj

class Reservation {
	int code
	Date dateReservation
	static hasMany = [livres : Livre]
	static belongsTo = Livre
	
    static constraints = {
		code blank: false
		dateReservation blank: false
    }
}

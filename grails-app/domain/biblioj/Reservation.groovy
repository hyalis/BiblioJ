package biblioj

class Reservation {
	int code
	Date dateReservation
	static hasMany = [livres : Livre]
	
    static constraints = {
		code blank: false
		dateReservation blank: false
    }
}

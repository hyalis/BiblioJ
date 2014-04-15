package biblioj



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Reservation)
class ReservationTests {
	
	void testCreation() {
		TypeDocument type = new TypeDocument( intitule : "Livre adulte" )
		Livre livre = new Livre( titre : "LivreTest", type : type, nombreExemplaires : 5, nombreExemplairesDisponibles : 5 )
		Reservation res = new Reservation( code : 1, dateReservation : new Date("14/04/2014") )
		assert res != null
		assert res.save() != null
	}
	
	void testCreationBlank() {
		Reservation res = new Reservation( code : "", dateReservation: "" )
		assert res != null
		assert res.save() == null
	}
	
	void testToString() {
		Reservation res = new Reservation( code : 1, dateReservation : new Date("14/04/2014") )
		assert res.toString() == "#1, Wed Feb 04 00:00:00 CET 2015" //	"#" + code + ", " + dateReservation
	}
}

package biblioj



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Livre)
@Mock([Livre, Reservation])
class LivreTests {

     void testCreation() {
		TypeDocument type = new TypeDocument( intitule : "Livre adulte" )
		Livre livre = new Livre( titre : "LivreTest", type : type, nombreExemplaires : 5, nombreExemplairesDisponibles : 5 )
		assert livre != null
		assert livre.save() != null
    }

	void testCreationBlank() {
		TypeDocument type = new TypeDocument( intitule : "Livre adulte" )
		Livre livre = new Livre( titre : "", type : "", nombreExemplaires : "", nombreExemplairesDisponibles :"" )
		
		assert livre != null
		assert livre.save() == null
	}

	void testToString() {
		TypeDocument type = new TypeDocument( intitule : "Livre adulte" )
		Livre livre = new Livre( titre : "LivreTest", type : type, nombreExemplaires : 5, nombreExemplairesDisponibles : 5 )
		
		assert livre.toString() == "Livre adulte : LivreTest, nombre d'exemplaires dispo : 5"
		//type.toString() + " : " + titre + ", nombre d'exemplaires dispo : " + nombreExemplairesDisponibles
	}
	
	void testAjoutAReservation() {
		TypeDocument type = new TypeDocument( intitule : "Livre adulte" )
		Livre livre = new Livre( titre : "LivreTest", type : type, nombreExemplaires : 5, nombreExemplairesDisponibles : 5 )
		assert livre != null
		assert livre.save(flush: true) != null
		
		assert livre.getNombreExemplairesDisponibles() == 5
		
		Reservation res = new Reservation( code : 83, dateReservation : new Date("14/04/2014") )
		//livre.addToReservations(res)
		
		// TODO ... Impossible d'acceder à addToReservations()... pourquoi ??
		livre.ajouteLivreAReservation(res)
		assert livre.getNombreExemplairesDisponibles() == 4
		livre.ajouteLivreAReservation(res)
		assert livre.getNombreExemplairesDisponibles() == 3
		livre.ajouteLivreAReservation(res)
		assert livre.getNombreExemplairesDisponibles() == 2
		livre.ajouteLivreAReservation(res)
		assert livre.getNombreExemplairesDisponibles() == 1
		livre.ajouteLivreAReservation(res)
		assert livre.getNombreExemplairesDisponibles() == 0
		livre.ajouteLivreAReservation(res)
	}
	
}

package biblioj



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Auteur)
class AuteurTests {

   void testCreation() {
		Auteur auteur = new Auteur( nom : "Prolapse", prenom: "colon" )
		assert auteur != null
		assert auteur.save() != null
    }

	void testCreationBlank() {
		Auteur auteur = new Auteur( nom : "", prenom: "" )
		assert auteur != null
		assert auteur.save() == null
	}

	void testToString() {
		Auteur auteur = new Auteur( nom : "Prolapse", prenom: "colon" )
		assert auteur.toString() == "Prolapse colon"
	}
}

package biblioj



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(TypeDocument)
class TypeDocumentTests {

    void testCreation() {
		TypeDocument type = new TypeDocument( intitule : "Livre adulte" )
		assert type != null
		assert type.save() != null
    }
	
	void testCreationBlank() {
		TypeDocument type = new TypeDocument( intitule : "" )
		assert type != null
		assert type.save() == null
	}
	
	void testToString() {
		TypeDocument type = new TypeDocument( intitule : "Livre adulte" )
		assert type.toString() == "Livre adulte"
	}
}

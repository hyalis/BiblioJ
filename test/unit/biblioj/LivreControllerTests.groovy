package biblioj



import org.junit.*
import grails.test.mixin.*

@TestFor(LivreController)
@Mock(Livre)
class LivreControllerTests {

    def populateValidParams(params) {
        assert params != null
		TypeDocument typeLivre = new TypeDocument( intitule : "Livre adulte" )
        params["titre"] = "Rien ne s'oppose à la nuit : roman"
		params["type"] = typeLivre
		params["nombreExemplaires"] = 5
		params["nombreExemplairesDisponibles"] = 5
    }

    void testIndex() {
        controller.index()
        assert "/livre/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.livreInstanceList.size() == 0
        assert model.livreInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.livreInstance != null
    }

    void testSave() {
        controller.save()

        assert model.livreInstance != null
        assert view == '/livre/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/livre/show/1'
        assert controller.flash.message != null
        assert Livre.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/livre/list'

        populateValidParams(params)
        def livre = new Livre(params)

        assert livre.save() != null

        params.id = livre.id

        def model = controller.show()

        assert model.livreInstance == livre
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/livre/list'

        populateValidParams(params)
        def livre = new Livre(params)
		
        assert livre.save() != null

        params.id = livre.id

        def model = controller.edit()

        assert model.livreInstance == livre
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/livre/list'

        response.reset()

        populateValidParams(params)
        def livre = new Livre(params)

        assert livre.save() != null

        // test invalid parameters in update
        params.id = livre.id
		TypeDocument typeLivre = new TypeDocument( intitule : "Livre adulte" )
		params["titre"] = ""
		params["type"] = typeLivre
		params["nombreExemplaires"] = 5
		params["nombreExemplairesDisponibles"] = 5
        controller.update()

        assert view == "/livre/edit"
        assert model.livreInstance != null

        livre.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/livre/show/$livre.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        livre.clearErrors()

        populateValidParams(params)
        params.id = livre.id
        params.version = -1
        controller.update()

        assert view == "/livre/edit"
        assert model.livreInstance != null
        assert model.livreInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/livre/list'

        response.reset()

        populateValidParams(params)
        def livre = new Livre(params)

        assert livre.save() != null
        assert Livre.count() == 1

        params.id = livre.id

        controller.delete()

        assert Livre.count() == 0
        assert Livre.get(livre.id) == null
        assert response.redirectedUrl == '/livre/list'
    }
	
	void testViderPanier()
	{
		session["panier"] = []
		session["panier"].add(new Livre( titre : "Rien ne s'oppose à la nuit : roman", type : new TypeDocument( intitule : "Nouveauté" ), nombreExemplaires : 5, nombreExemplairesDisponibles : 5 ))
		session["panier"].add(new Livre( titre : "Rien ne s'oppose à la nuit : roman", type : new TypeDocument( intitule : "Nouveauté" ), nombreExemplaires : 5, nombreExemplairesDisponibles : 5 ))
		controller.viderPanier()
		assert session["panier"].size() == 0
	}
	
	void testRemoveItemPanier()
	{
		Livre livre = new Livre( titre : "TESTnuit : roman", type : new TypeDocument( intitule : "Nouveauté" ), nombreExemplaires : 5, nombreExemplairesDisponibles : 5 )
		session["panier"] = []
		session["panier"].add(new Livre( titre : "Rien ne s'oppose à la nuit : roman", type : new TypeDocument( intitule : "Nouveauté" ), nombreExemplaires : 5, nombreExemplairesDisponibles : 5 ))
		session["panier"].add(livre)
		controller.removeItemPanier(livre.getId())
		assert session["panier"].size() == 1
	}
}

package biblioj

class LivreController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max, Integer idLivre) {
		if(session["panier"] == null)
			session["panier"] = []
		session["panier"].add(Livre.findById(idLivre))
		
        params.max = Math.min(max ?: 10, 100)
        [livreInstanceList: Livre.list(params), livreInstanceTotal: Livre.count()]
    }
	
	def listRecherche(Integer max, String type, String auteur, String titre) {
		params.max = Math.min(max ?: 10, 100)
		
		def typeReq = params.type
		def auteurReq = params.auteur
		def titreReq = params.titre
		
		
		if(typeReq == null)
			typeReq = ""
		if(auteurReq == null)
			auteurReq = ""
		if(titreReq == null)
			titreReq = ""
			
		def c = Livre.createCriteria()
		def results = c.listDistinct {
			'in'("type", TypeDocument.findAllByIntituleLike("%" + typeReq + "%"))
			auteurs{
				like("nom", "%"+auteurReq+"%")
			}
			like("titre", "%"+titreReq+"%")
			order("titre","asc")
			
			
			
		}
		
		[livreFiltre: results, livreInstanceTotal: results.count(null), listeDesTypes: TypeDocument.list()]
	}

    def create() {
        [livreInstance: new Livre(params)]
    }

    def save() {
        def livreInstance = new Livre(params)
        if (!livreInstance.save(flush: true)) {
            render(view: "create", model: [livreInstance: livreInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'livre.label', default: 'Livre'), livreInstance.id])
        redirect(action: "show", id: livreInstance.id)
    }

    def show(Long id) {
        def livreInstance = Livre.get(id)
        if (!livreInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'livre.label', default: 'Livre'), id])
            redirect(action: "list")
            return
        }

        [livreInstance: livreInstance]
    }

    def edit(Long id) {
        def livreInstance = Livre.get(id)
        if (!livreInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'livre.label', default: 'Livre'), id])
            redirect(action: "list")
            return
        }

        [livreInstance: livreInstance]
    }

    def update(Long id, Long version) {
        def livreInstance = Livre.get(id)
        if (!livreInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'livre.label', default: 'Livre'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (livreInstance.version > version) {
                livreInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'livre.label', default: 'Livre')] as Object[],
                          "Another user has updated this Livre while you were editing")
                render(view: "edit", model: [livreInstance: livreInstance])
                return
            }
        }

        livreInstance.properties = params

        if (!livreInstance.save(flush: true)) {
            render(view: "edit", model: [livreInstance: livreInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'livre.label', default: 'Livre'), livreInstance.id])
        redirect(action: "show", id: livreInstance.id)
    }

    def delete(Long id) {
        def livreInstance = Livre.get(id)
        if (!livreInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'livre.label', default: 'Livre'), id])
            redirect(action: "list")
            return
        }


            livreInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'livre.label', default: 'Livre'), id])
            redirect(action: "list")
       
    }
	
	def viderPanier() {
		session["panier"] = []
		def targetUri = params.targetUri ?: "/"
		redirect(uri: targetUri)
	}
	
	def removeItemPanier(Integer idItem){
		for(int i = 1; session["panier"] != null && i<session["panier"].size(); i++){
			if(session["panier"][i] != null && session["panier"][i].getId() == idItem){
				session["panier"].remove(i)
				def targetUri = params.targetUri ?: "/"
				redirect(uri: targetUri)
				return(0)
			}
		}
	}
	
}

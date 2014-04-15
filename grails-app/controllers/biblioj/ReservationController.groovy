package biblioj


class ReservationController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [reservationInstanceList: Reservation.list(params), reservationInstanceTotal: Reservation.count()]
    }

    def create() {
        [reservationInstance: new Reservation(params)]
    }

    def save() {
        def reservationInstance = new Reservation(params)
        if (!reservationInstance.save(flush: true)) {
            render(view: "create", model: [reservationInstance: reservationInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'reservation.label', default: 'Reservation'), reservationInstance.id])
        redirect(action: "show", id: reservationInstance.id)
    }

    def show(Long id) {
        def reservationInstance = Reservation.get(id)
        if (!reservationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservation.label', default: 'Reservation'), id])
            redirect(action: "list")
            return
        }

        [reservationInstance: reservationInstance]
    }

    def edit(Long id) {
        def reservationInstance = Reservation.get(id)
        if (!reservationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservation.label', default: 'Reservation'), id])
            redirect(action: "list")
            return
        }

        [reservationInstance: reservationInstance]
    }

    def update(Long id, Long version) {
        def reservationInstance = Reservation.get(id)
        if (!reservationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservation.label', default: 'Reservation'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (reservationInstance.version > version) {
                reservationInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'reservation.label', default: 'Reservation')] as Object[],
                          "Another user has updated this Reservation while you were editing")
                render(view: "edit", model: [reservationInstance: reservationInstance])
                return
            }
        }

        reservationInstance.properties = params

        if (!reservationInstance.save(flush: true)) {
            render(view: "edit", model: [reservationInstance: reservationInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'reservation.label', default: 'Reservation'), reservationInstance.id])
        redirect(action: "show", id: reservationInstance.id)
    }

    def delete(Long id) {
        def reservationInstance = Reservation.get(id)
        if (!reservationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservation.label', default: 'Reservation'), id])
            redirect(action: "list")
            return
        }

        reservationInstance.delete(flush: true)
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'reservation.label', default: 'Reservation'), id])
        redirect(action: "list")
    }
	
	def rapport(Integer max) {
		[monRapport:"Hey Salut"]
	}
	
	def validation(Integer max) {
		
		def maxReservation = 66
		List listeDesLivres = new ArrayList<Livre>()
		for(int i = 1; session["panier"] != null && i<session["panier"].size(); i++){
			println("Nb : " + Livre.findById(session["panier"][i].getId()).getNombreExemplairesDisponibles())
			if(session["panier"][i] != null && Livre.findById(session["panier"][i].getId()).getNombreExemplairesDisponibles() > 0){
				Livre livreAAjouter = Livre.findById(session["panier"][i].getId())
				listeDesLivres.add(session["panier"][i])
				livreAAjouter.setNombreExemplairesDisponibles(livreAAjouter.getNombreExemplairesDisponibles()-1)
			}
		}
		println(listeDesLivres)
		Reservation nouvelleReservation = new Reservation( code : maxReservation, dateReservation : new Date("29/12/2014"), livres : listeDesLivres )
		nouvelleReservation.save()
		
		redirect(action: "list")
	}
}

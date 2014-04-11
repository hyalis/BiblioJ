package biblioj

class Auteur {
	String nom
	String prenom
	static hasMany = [livres : Livre]
	static belongsTo = Livre
	
    static constraints = {
		nom blank: false
		prenom blank: false
    }
	
	String toString() {
		nom + " " + prenom
	}
}

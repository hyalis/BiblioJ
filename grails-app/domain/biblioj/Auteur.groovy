package biblioj

class Auteur {
	String nom
	String prenom
	static hasMany = [livres : Livre]
	static belongsTo = Livre //[livreBelongs : Livre]
	/*
	static mapping = {
		livreBelongs fetch : "join"
	}*/
    static constraints = {
		nom blank: false
		prenom blank: false
    }
	
	String toString() {
		nom + " " + prenom
	}
}




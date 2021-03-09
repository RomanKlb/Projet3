package fr.isika.cdi07.projet3demo.modelform;

public class CommentaireForm {
	
	private Long idProjet;	
	private String libelle;	
	private String message;
	
	
	public CommentaireForm() {
	}

	public CommentaireForm(Long idProjet) {
		this.idProjet = idProjet;
	}

	public Long getIdProjet() {
		return idProjet;
	}

	public void setIdProjet(Long idProjet) {
		this.idProjet = idProjet;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}

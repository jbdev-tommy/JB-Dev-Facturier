package fr.jbdev.domaine;

// Generated 7 janv. 2016 21:47:57 by Hibernate Tools 4.0.0

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Fournisseurs generated by hbm2java
 */
@Entity
@Table(name = "FOURNISSEURS", catalog = "facturierDeux")
public class Fournisseurs implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Integer numFournisseur;
    private Entreprises entreprisesByNumSiret;
    private Entreprises entreprisesByNumSiretEntreprises;
    private String email;
    private byte[] logo;
    private Double solde;
    private String description;
    private Set<Commandes> commandeses = new HashSet<Commandes>(0);

    public Fournisseurs() {
    }

    public Fournisseurs(Entreprises entreprisesByNumSiret,
	    Entreprises entreprisesByNumSiretEntreprises, String email,
	    String description) {
	this.entreprisesByNumSiret = entreprisesByNumSiret;
	this.entreprisesByNumSiretEntreprises = entreprisesByNumSiretEntreprises;
	this.email = email;
	this.description = description;
    }

    public Fournisseurs(Entreprises entreprisesByNumSiret,
	    Entreprises entreprisesByNumSiretEntreprises, String email,
	    byte[] logo, Double solde, String description,
	    Set<Commandes> commandeses) {
	this.entreprisesByNumSiret = entreprisesByNumSiret;
	this.entreprisesByNumSiretEntreprises = entreprisesByNumSiretEntreprises;
	this.email = email;
	this.logo = logo;
	this.solde = solde;
	this.description = description;
	this.commandeses = commandeses;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "NumFournisseur", unique = true, nullable = false)
    public Integer getNumFournisseur() {
	return this.numFournisseur;
    }

    public void setNumFournisseur(Integer numFournisseur) {
	this.numFournisseur = numFournisseur;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "NumSiret", nullable = false)
    public Entreprises getEntreprisesByNumSiret() {
	return this.entreprisesByNumSiret;
    }

    public void setEntreprisesByNumSiret(Entreprises entreprisesByNumSiret) {
	this.entreprisesByNumSiret = entreprisesByNumSiret;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NumSiret_ENTREPRISES", nullable = false)
    public Entreprises getEntreprisesByNumSiretEntreprises() {
	return this.entreprisesByNumSiretEntreprises;
    }

    public void setEntreprisesByNumSiretEntreprises(
	    Entreprises entreprisesByNumSiretEntreprises) {
	this.entreprisesByNumSiretEntreprises = entreprisesByNumSiretEntreprises;
    }

    @Column(name = "Email", nullable = false, length = 254)
    public String getEmail() {
	return this.email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    @Column(name = "Logo")
    public byte[] getLogo() {
	return this.logo;
    }

    public void setLogo(byte[] logo) {
	this.logo = logo;
    }

    @Column(name = "Solde", precision = 22, scale = 0)
    public Double getSolde() {
	return this.solde;
    }

    public void setSolde(Double solde) {
	this.solde = solde;
    }

    @Column(name = "Description", nullable = false, length = 25)
    public String getDescription() {
	return this.description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fournisseurs")
    public Set<Commandes> getCommandeses() {
	return this.commandeses;
    }

    public void setCommandeses(Set<Commandes> commandeses) {
	this.commandeses = commandeses;
    }

}
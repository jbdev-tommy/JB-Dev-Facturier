package fr.jbdev.domaine;

// Generated 7 janv. 2016 21:47:57 by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entreprises generated by hbm2java
 */
@Entity
@Table(name = "ENTREPRISES", catalog = "facturierDeux")
public class Entreprises implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String numSiret;
    private FormesJuridiques formesJuridiques;
    private Adresses adresses;
    private String nom;
    private String telephone;
    private byte[] logo;
    private String identifiantTva;
    private String numAssurance;
    private String codeApe;
    private String solgan;
    private Date dateDeCreation;
    private Set<Clients> clientses = new HashSet<Clients>(0);
    private Set<Exercicecomptable> exercicecomptables = new HashSet<Exercicecomptable>(
	    0);
    private Set<Fournisseurs> fournisseursesForNumSiretEntreprises = new HashSet<Fournisseurs>(
	    0);
    private Set<Entrepots> entrepotses = new HashSet<Entrepots>(0);
    private Set<Categories> categorieses = new HashSet<Categories>(0);
    private Set<Commandes> commandeses = new HashSet<Commandes>(0);
    private Set<Utilisateurs> utilisateurses = new HashSet<Utilisateurs>(0);
    private Set<Produits> produitses = new HashSet<Produits>(0);
    private Set<Fournisseurs> fournisseursesForNumSiret = new HashSet<Fournisseurs>(
	    0);
    private Set<Devis> devises = new HashSet<Devis>(0);

    public Entreprises() {
    }

    public Entreprises(String numSiret, Adresses adresses, String nom,
	    String telephone) {
	this.numSiret = numSiret;
	this.adresses = adresses;
	this.nom = nom;
	this.telephone = telephone;
    }

    public Entreprises(String numSiret, FormesJuridiques formesJuridiques,
	    Adresses adresses, String nom, String telephone, byte[] logo,
	    String identifiantTva, String numAssurance, String codeApe,
	    String solgan, Date dateDeCreation, Set<Clients> clientses,
	    Set<Exercicecomptable> exercicecomptables,
	    Set<Fournisseurs> fournisseursesForNumSiretEntreprises,
	    Set<Entrepots> entrepotses, Set<Categories> categorieses,
	    Set<Commandes> commandeses, Set<Utilisateurs> utilisateurses,
	    Set<Produits> produitses,
	    Set<Fournisseurs> fournisseursesForNumSiret, Set<Devis> devises) {
	this.numSiret = numSiret;
	this.formesJuridiques = formesJuridiques;
	this.adresses = adresses;
	this.nom = nom;
	this.telephone = telephone;
	this.logo = logo;
	this.identifiantTva = identifiantTva;
	this.numAssurance = numAssurance;
	this.codeApe = codeApe;
	this.solgan = solgan;
	this.dateDeCreation = dateDeCreation;
	this.clientses = clientses;
	this.exercicecomptables = exercicecomptables;
	this.fournisseursesForNumSiretEntreprises = fournisseursesForNumSiretEntreprises;
	this.entrepotses = entrepotses;
	this.categorieses = categorieses;
	this.commandeses = commandeses;
	this.utilisateurses = utilisateurses;
	this.produitses = produitses;
	this.fournisseursesForNumSiret = fournisseursesForNumSiret;
	this.devises = devises;
    }

    @Id
    @Column(name = "NumSiret", unique = true, nullable = false, length = 32)
    public String getNumSiret() {
	return this.numSiret;
    }

    public void setNumSiret(String numSiret) {
	this.numSiret = numSiret;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "NumForme")
    public FormesJuridiques getFormesJuridiques() {
	return this.formesJuridiques;
    }

    public void setFormesJuridiques(FormesJuridiques formesJuridiques) {
	this.formesJuridiques = formesJuridiques;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "NumAdresse", nullable = false)
    public Adresses getAdresses() {
	return this.adresses;
    }

    public void setAdresses(Adresses adresses) {
	this.adresses = adresses;
    }

    @Column(name = "Nom", nullable = false, length = 64)
    public String getNom() {
	return this.nom;
    }

    public void setNom(String nom) {
	this.nom = nom;
    }

    @Column(name = "Telephone", nullable = false, length = 10)
    public String getTelephone() {
	return this.telephone;
    }

    public void setTelephone(String telephone) {
	this.telephone = telephone;
    }

    @Column(name = "Logo")
    public byte[] getLogo() {
	return this.logo;
    }

    public void setLogo(byte[] logo) {
	this.logo = logo;
    }

    @Column(name = "IdentifiantTva", length = 32)
    public String getIdentifiantTva() {
	return this.identifiantTva;
    }

    public void setIdentifiantTva(String identifiantTva) {
	this.identifiantTva = identifiantTva;
    }

    @Column(name = "NumAssurance", length = 25)
    public String getNumAssurance() {
	return this.numAssurance;
    }

    public void setNumAssurance(String numAssurance) {
	this.numAssurance = numAssurance;
    }

    @Column(name = "CodeApe", length = 25)
    public String getCodeApe() {
	return this.codeApe;
    }

    public void setCodeApe(String codeApe) {
	this.codeApe = codeApe;
    }

    @Column(name = "Solgan", length = 65535)
    public String getSolgan() {
	return this.solgan;
    }

    public void setSolgan(String solgan) {
	this.solgan = solgan;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "DateDeCreation", length = 10)
    public Date getDateDeCreation() {
	return this.dateDeCreation;
    }

    public void setDateDeCreation(Date dateDeCreation) {
	this.dateDeCreation = dateDeCreation;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "entreprises")
    public Set<Clients> getClientses() {
	return this.clientses;
    }

    public void setClientses(Set<Clients> clientses) {
	this.clientses = clientses;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "entreprises")
    public Set<Exercicecomptable> getExercicecomptables() {
	return this.exercicecomptables;
    }

    public void setExercicecomptables(Set<Exercicecomptable> exercicecomptables) {
	this.exercicecomptables = exercicecomptables;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "entreprisesByNumSiretEntreprises")
    public Set<Fournisseurs> getFournisseursesForNumSiretEntreprises() {
	return this.fournisseursesForNumSiretEntreprises;
    }

    public void setFournisseursesForNumSiretEntreprises(
	    Set<Fournisseurs> fournisseursesForNumSiretEntreprises) {
	this.fournisseursesForNumSiretEntreprises = fournisseursesForNumSiretEntreprises;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "entreprises")
    public Set<Entrepots> getEntrepotses() {
	return this.entrepotses;
    }

    public void setEntrepotses(Set<Entrepots> entrepotses) {
	this.entrepotses = entrepotses;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "entreprises")
    public Set<Categories> getCategorieses() {
	return this.categorieses;
    }

    public void setCategorieses(Set<Categories> categorieses) {
	this.categorieses = categorieses;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "entreprises")
    public Set<Commandes> getCommandeses() {
	return this.commandeses;
    }

    public void setCommandeses(Set<Commandes> commandeses) {
	this.commandeses = commandeses;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "entreprises")
    public Set<Utilisateurs> getUtilisateurses() {
	return this.utilisateurses;
    }

    public void setUtilisateurses(Set<Utilisateurs> utilisateurses) {
	this.utilisateurses = utilisateurses;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "entreprises")
    public Set<Produits> getProduitses() {
	return this.produitses;
    }

    public void setProduitses(Set<Produits> produitses) {
	this.produitses = produitses;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "entreprisesByNumSiret")
    public Set<Fournisseurs> getFournisseursesForNumSiret() {
	return this.fournisseursesForNumSiret;
    }

    public void setFournisseursesForNumSiret(
	    Set<Fournisseurs> fournisseursesForNumSiret) {
	this.fournisseursesForNumSiret = fournisseursesForNumSiret;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "entreprises")
    public Set<Devis> getDevises() {
	return this.devises;
    }

    public void setDevises(Set<Devis> devises) {
	this.devises = devises;
    }

}
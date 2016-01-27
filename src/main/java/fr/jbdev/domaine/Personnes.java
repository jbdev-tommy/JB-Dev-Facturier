package fr.jbdev.domaine;

// Generated 7 janv. 2016 21:47:57 by Hibernate Tools 4.0.0

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Personnes generated by hbm2java
 */
@Entity
@Table(name = "PERSONNES", catalog = "facturierDeux")
public class Personnes implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Integer numPersonne;
    private Adresses adresses;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String telephones;
    private Set<Utilisateurs> utilisateurses = new HashSet<Utilisateurs>(0);
    private Set<Clients> clientses = new HashSet<Clients>(0);

    public Personnes() {
    }

    public Personnes(Adresses adresses, String nom) {
	this.adresses = adresses;
	this.nom = nom;
    }

    public Personnes(Adresses adresses, String nom, String prenom,
	    Date dateNaissance, String telephones,
	    Set<Utilisateurs> utilisateurses, Set<Clients> clientses) {
	this.adresses = adresses;
	this.nom = nom;
	this.prenom = prenom;
	this.dateNaissance = dateNaissance;
	this.telephones = telephones;
	this.utilisateurses = utilisateurses;
	this.clientses = clientses;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "NumPersonne", unique = true, nullable = false)
    public Integer getNumPersonne() {
	return this.numPersonne;
    }

    public void setNumPersonne(Integer numPersonne) {
	this.numPersonne = numPersonne;
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

    @Column(name = "Prenom", length = 64)
    public String getPrenom() {
	return this.prenom;
    }

    public void setPrenom(String prenom) {
	this.prenom = prenom;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "DateNaissance", length = 10)
    public Date getDateNaissance() {
	return this.dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
	this.dateNaissance = dateNaissance;
    }

    @Column(name = "Telephones", length = 21)
    public String getTelephones() {
	return this.telephones;
    }

    public void setTelephones(String telephones) {
	this.telephones = telephones;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "personnes")
    public Set<Utilisateurs> getUtilisateurses() {
	return this.utilisateurses;
    }

    public void setUtilisateurses(Set<Utilisateurs> utilisateurses) {
	this.utilisateurses = utilisateurses;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "personnes")
    public Set<Clients> getClientses() {
	return this.clientses;
    }

    public void setClientses(Set<Clients> clientses) {
	this.clientses = clientses;
    }

}
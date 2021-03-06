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
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Regimesocial generated by hbm2java
 */
@Entity
@Table(name = "REGIMESOCIAL", catalog = "facturierDeux")
public class Regimesocial implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Integer numRegime;
    private String nom;
    private String description;
    private TauxCotisations tauxCotisations;
    private Set<FormesJuridiques> formesJuridiqueses = new HashSet<FormesJuridiques>(
	    0);
    private Set<FormesJuridiques> formesJuridiqueses_1 = new HashSet<FormesJuridiques>(
	    0);

    public Regimesocial() {
    }

    public Regimesocial(String nom, String description) {
	this.nom = nom;
	this.description = description;
    }

    public Regimesocial(String nom, String description,
	    TauxCotisations tauxCotisations,
	    Set<FormesJuridiques> formesJuridiqueses,
	    Set<FormesJuridiques> formesJuridiqueses_1) {
	this.nom = nom;
	this.description = description;
	this.tauxCotisations = tauxCotisations;
	this.formesJuridiqueses = formesJuridiqueses;
	this.formesJuridiqueses_1 = formesJuridiqueses_1;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "NumRegime", unique = true, nullable = false)
    public Integer getNumRegime() {
	return this.numRegime;
    }

    public void setNumRegime(Integer numRegime) {
	this.numRegime = numRegime;
    }

    @Column(name = "Nom", nullable = false, length = 25)
    public String getNom() {
	return this.nom;
    }

    public void setNom(String nom) {
	this.nom = nom;
    }

    @Column(name = "Description", nullable = false, length = 65535)
    public String getDescription() {
	return this.description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "regimesocial")
    public TauxCotisations getTauxCotisations() {
	return this.tauxCotisations;
    }

    public void setTauxCotisations(TauxCotisations tauxCotisations) {
	this.tauxCotisations = tauxCotisations;
    }

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "regimesocials")
    public Set<FormesJuridiques> getFormesJuridiqueses() {
	return this.formesJuridiqueses;
    }

    public void setFormesJuridiqueses(Set<FormesJuridiques> formesJuridiqueses) {
	this.formesJuridiqueses = formesJuridiqueses;
    }

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "regimesocials")
    public Set<FormesJuridiques> getFormesJuridiqueses_1() {
	return this.formesJuridiqueses_1;
    }

    public void setFormesJuridiqueses_1(
	    Set<FormesJuridiques> formesJuridiqueses_1) {
	this.formesJuridiqueses_1 = formesJuridiqueses_1;
    }

}

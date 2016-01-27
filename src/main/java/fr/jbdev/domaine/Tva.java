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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Tva generated by hbm2java
 */
@Entity
@Table(name = "TVA", catalog = "facturierDeux")
public class Tva implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Integer numTaxe;
    private String nom;
    private double taux;
    private String description;
    private Set<Contient> contients = new HashSet<Contient>(0);
    private Set<DevisProduits> devisProduitses = new HashSet<DevisProduits>(0);

    public Tva() {
    }

    public Tva(double taux, String description) {
	this.taux = taux;
	this.description = description;
    }

    public Tva(String nom, double taux, String description,
	    Set<Contient> contients, Set<DevisProduits> devisProduitses) {
	this.nom = nom;
	this.taux = taux;
	this.description = description;
	this.contients = contients;
	this.devisProduitses = devisProduitses;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "NumTaxe", unique = true, nullable = false)
    public Integer getNumTaxe() {
	return this.numTaxe;
    }

    public void setNumTaxe(Integer numTaxe) {
	this.numTaxe = numTaxe;
    }

    @Column(name = "Nom", length = 25)
    public String getNom() {
	return this.nom;
    }

    public void setNom(String nom) {
	this.nom = nom;
    }

    @Column(name = "taux", nullable = false, precision = 22, scale = 0)
    public double getTaux() {
	return this.taux;
    }

    public void setTaux(double taux) {
	this.taux = taux;
    }

    @Column(name = "Description", nullable = false, length = 65535)
    public String getDescription() {
	return this.description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tva")
    public Set<Contient> getContients() {
	return this.contients;
    }

    public void setContients(Set<Contient> contients) {
	this.contients = contients;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tva")
    public Set<DevisProduits> getDevisProduitses() {
	return this.devisProduitses;
    }

    public void setDevisProduitses(Set<DevisProduits> devisProduitses) {
	this.devisProduitses = devisProduitses;
    }

}
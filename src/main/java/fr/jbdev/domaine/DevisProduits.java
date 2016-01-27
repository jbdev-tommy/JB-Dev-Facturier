package fr.jbdev.domaine;

// Generated 7 janv. 2016 21:47:57 by Hibernate Tools 4.0.0

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * DevisProduits generated by hbm2java
 */
@Entity
@Table(name = "DEVIS_PRODUITS", catalog = "facturierDeux")
public class DevisProduits implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Integer numDevisProduits;
    private Devis devis;
    private Tva tva;
    private Categories categories;
    private double quantite;
    private double remise;

    public DevisProduits() {
    }

    public DevisProduits(Devis devis, Tva tva, Categories categories,
	    double quantite) {
	this.devis = devis;
	this.tva = tva;
	this.categories = categories;
	this.quantite = quantite;
    }

    public DevisProduits(Devis devis, Tva tva, Categories categories,
	    double quantite, Float remise) {
	this.devis = devis;
	this.tva = tva;
	this.categories = categories;
	this.quantite = quantite;
	this.remise = remise;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "NumDevisProduits", unique = true, nullable = false)
    public Integer getNumDevisProduits() {
	return this.numDevisProduits;
    }

    public void setNumDevisProduits(Integer numDevisProduits) {
	this.numDevisProduits = numDevisProduits;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NumDevis", nullable = false)
    public Devis getDevis() {
	return this.devis;
    }

    public void setDevis(Devis devis) {
	this.devis = devis;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "NumTva", nullable = false)
    public Tva getTva() {
	return this.tva;
    }

    public void setTva(Tva tva) {
	this.tva = tva;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "NumCategorie", nullable = false)
    public Categories getCategories() {
	return this.categories;
    }

    public void setCategories(Categories categories) {
	this.categories = categories;
    }

    @Column(name = "Quantite", nullable = false, precision = 22, scale = 0)
    public double getQuantite() {
	return this.quantite;
    }

    public void setQuantite(double quantite) {
	this.quantite = quantite;
    }

    @Column(name = "Remise", precision = 12, scale = 0)
    public double getRemise() {
	return this.remise;
    }

    public void setRemise(double remise) {
	this.remise = remise;
    }

}
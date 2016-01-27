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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * FormesJuridiques generated by hbm2java
 */
@Entity
@Table(name = "FORMES_JURIDIQUES", catalog = "facturierDeux")
public class FormesJuridiques implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Integer numForme;
    private String statuJuridique;
    private String description;
    private Set<Entreprises> entrepriseses = new HashSet<Entreprises>(0);
    private Set<Regimesocial> regimesocials = new HashSet<Regimesocial>(0);
    private Set<Regimesocial> regimesocials_1 = new HashSet<Regimesocial>(0);

    public FormesJuridiques() {
    }

    public FormesJuridiques(String statuJuridique, String description) {
	this.statuJuridique = statuJuridique;
	this.description = description;
    }

    public FormesJuridiques(String statuJuridique, String description,
	    Set<Entreprises> entrepriseses, Set<Regimesocial> regimesocials,
	    Set<Regimesocial> regimesocials_1) {
	this.statuJuridique = statuJuridique;
	this.description = description;
	this.entrepriseses = entrepriseses;
	this.regimesocials = regimesocials;
	this.regimesocials_1 = regimesocials_1;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "NumForme", unique = true, nullable = false)
    public Integer getNumForme() {
	return this.numForme;
    }

    public void setNumForme(Integer numForme) {
	this.numForme = numForme;
    }

    @Column(name = "StatuJuridique", nullable = false, length = 25)
    public String getStatuJuridique() {
	return this.statuJuridique;
    }

    public void setStatuJuridique(String statuJuridique) {
	this.statuJuridique = statuJuridique;
    }

    @Column(name = "Description", nullable = false, length = 65535)
    public String getDescription() {
	return this.description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "formesJuridiques")
    public Set<Entreprises> getEntrepriseses() {
	return this.entrepriseses;
    }

    public void setEntrepriseses(Set<Entreprises> entrepriseses) {
	this.entrepriseses = entrepriseses;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "NON_SALARIER", catalog = "facturierDeux", joinColumns = { @JoinColumn(name = "NumForme", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "NumRegime", nullable = false, updatable = false) })
    public Set<Regimesocial> getRegimesocials() {
	return this.regimesocials;
    }

    public void setRegimesocials(Set<Regimesocial> regimesocials) {
	this.regimesocials = regimesocials;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SALARIE", catalog = "facturierDeux", joinColumns = { @JoinColumn(name = "NumForme", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "NumRegime", nullable = false, updatable = false) })
    public Set<Regimesocial> getRegimesocials_1() {
	return this.regimesocials_1;
    }

    public void setRegimesocials_1(Set<Regimesocial> regimesocials_1) {
	this.regimesocials_1 = regimesocials_1;
    }

}

package fr.jbdev.domaine;

// Generated 7 janv. 2016 21:47:57 by Hibernate Tools 4.0.0

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Exercicecomptable generated by hbm2java
 */
@Entity
@Table(name = "EXERCICECOMPTABLE", catalog = "facturierDeux")
public class Exercicecomptable implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private ExercicecomptableId id;
    private Entreprises entreprises;
    private int numero;
    private Date dateDebut;
    private Date dateFin;

    public Exercicecomptable() {
    }

    public Exercicecomptable(ExercicecomptableId id, Entreprises entreprises,
	    int numero, Date dateDebut, Date dateFin) {
	this.id = id;
	this.entreprises = entreprises;
	this.numero = numero;
	this.dateDebut = dateDebut;
	this.dateFin = dateFin;
    }

    @EmbeddedId
    @AttributeOverrides({
	    @AttributeOverride(name = "numId", column = @Column(name = "NumId", nullable = false)),
	    @AttributeOverride(name = "numSiret", column = @Column(name = "NumSiret", nullable = false, length = 32)) })
    public ExercicecomptableId getId() {
	return this.id;
    }

    public void setId(ExercicecomptableId id) {
	this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NumSiret", nullable = false, insertable = false, updatable = false)
    public Entreprises getEntreprises() {
	return this.entreprises;
    }

    public void setEntreprises(Entreprises entreprises) {
	this.entreprises = entreprises;
    }

    @Column(name = "Numero", nullable = false)
    public int getNumero() {
	return this.numero;
    }

    public void setNumero(int numero) {
	this.numero = numero;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "DateDebut", nullable = false, length = 10)
    public Date getDateDebut() {
	return this.dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
	this.dateDebut = dateDebut;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "DateFin", nullable = false, length = 10)
    public Date getDateFin() {
	return this.dateFin;
    }

    public void setDateFin(Date dateFin) {
	this.dateFin = dateFin;
    }

}

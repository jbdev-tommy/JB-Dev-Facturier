package fr.jbdev.facturier.controller.rapports;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;

import fr.jbdev.domaine.DevisProduits;
import fr.jbdev.domaine.Vente;
import fr.jbdev.facturier.controller.facture.FactureListBean;
import fr.jbdev.facturier.controller.ventes.VenteListBean;

@ViewScoped
@ManagedBean(name = "caBean", eager = false)
public class CaBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{venteListBean}")
    private VenteListBean venteListBean;

    @ManagedProperty(value = "#{factureListBean}")
    private FactureListBean factureListBean;

    private LineChartModel caPayeModel;
    private PieChartModel pieCa;
    private double ca;
    private double caImpV;
    private boolean view;

    @PostConstruct
    public void init() {
	view = false;

	if (!venteListBean.getList().isEmpty()) {
	    createAreaModel();
	    initPieModel();
	    view = true;
	} else {
	    view = false;
	}

    }

    private void createAreaModel() {

	final DateFormat sd = new SimpleDateFormat("yyyy-MM-dd"); //$NON-NLS-1$

	
	caPayeModel = new LineChartModel();

	/* CA paye */
	Map<Object, Number> mapVente = new HashMap<Object, Number>();
	for (Vente vente : venteListBean.getList()) {
	    ca += vente.getAccompte();
	     mapVente.put(sd.format(vente.getDateVente()), ca);
	}

	// init graph
	final LineChartSeries caPaye = new LineChartSeries();
	caPaye.setLabel(Messages.getString("CaBean.1")); //$NON-NLS-1$
	caPaye.setData(mapVente);


	// Ajout des valeur au model
	caPayeModel.addSeries(caPaye);


	// Caracteristiques du model
	caPayeModel
		.setTitle(Messages.getString("CaBean.3") + Calendar.getInstance().get(Calendar.YEAR)); //$NON-NLS-1$
	caPayeModel.setLegendPosition("ne"); //$NON-NLS-1$

	caPayeModel.setAnimate(true);
	caPayeModel.setStacked(true);
	caPayeModel.setShowPointLabels(true);

	DateAxis axisX = new DateAxis(Messages.getString("CaBean.0")); //$NON-NLS-1$
	axisX.setTickFormat("%b %#d, %y"); //$NON-NLS-1$
	
	Axis axisY = caPayeModel.getAxis(AxisType.Y);
	axisY.setMin(0);
	

	caPayeModel.getAxes().put(AxisType.X, axisX);

    }

    public VenteListBean getVenteListBean() {
	return venteListBean;
    }

    public void setVenteListBean(VenteListBean venteListBean) {
	this.venteListBean = venteListBean;
    }

    public FactureListBean getFactureListBean() {
	return factureListBean;
    }

    public void setFactureListBean(FactureListBean factureListBean) {
	this.factureListBean = factureListBean;
    }

    public LineChartModel getCaPayeModel() {
	return caPayeModel;
    }

    public void setCaPayeModel(LineChartModel caPayeModel) {
	this.caPayeModel = caPayeModel;
    }

    public PieChartModel getPieCa() {
	return pieCa;
    }

    public void setPieCa(PieChartModel pieCa) {
	this.pieCa = pieCa;
    }

    public double getCa() {
	return ca;
    }

    public void setCa(double ca) {
	this.ca = ca;
    }

    public double getCaImpV() {
	return caImpV;
    }

    public void setCaImpV(double caImpV) {
	this.caImpV = caImpV;
    }

    public boolean isView() {
	return view;
    }

    public void setView(boolean view) {
	this.view = view;
    }

     private void initPieModel() {
     final PieChartModel pieModel = new PieChartModel();
       Map<String, Number> datas = new HashMap<String, Number>();
     double somme = 0;
     
     for(Vente vente : venteListBean.getList()) {
	
	 for(DevisProduits devisPdt : vente.getFactures().getDevis().getDevisProduitses()) {
	     somme += vente.getAccompte();
	     datas.put(devisPdt.getCategories().getNom(), somme);
	 }
     }
	 if( !datas.isEmpty()) {
	     
	view = true;
     pieModel.setData(datas);
    
     pieModel.setTitle(Messages.getString("CaBean.7")); //$NON-NLS-1$
     pieModel.setLegendPosition("e"); //$NON-NLS-1$
     pieModel.setFill(true);
     pieModel.setShowDataLabels(true);
     pieModel.setDiameter(200);
     } else {
     view = false;
     }
     this.pieCa = pieModel;
     }

}

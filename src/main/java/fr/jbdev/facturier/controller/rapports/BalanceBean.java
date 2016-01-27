package fr.jbdev.facturier.controller.rapports;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

import fr.jbdev.facturier.controller.achats.AchatsListBean;
import fr.jbdev.facturier.controller.ventes.VenteListBean;

@ViewScoped
@ManagedBean(name = "balanceBean", eager = false)
public class BalanceBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{venteListBean}")
    private VenteListBean venteListBean;

    @ManagedProperty(value = "#{achatsListBean}")
    private AchatsListBean achatListBean;

    private Map<Object, Number> mapV;
    private Map<Object, Number> mapA;
    private LineChartModel model;

    private double zero = 0;
    private double tresorerie;
    private boolean view;
    private double tva;
    
    @PostConstruct
    public void init() {

	tresorerie = 0;
	model = new LineChartModel();
	view = false;
	mapV = new HashMap<Object, Number>();
	mapA = new HashMap<Object, Number>();
	
	
	double totalVente = 0;
	double totalAchat = 0;
	double totalTvaCum = 0;
	double totalTvapercu=0;	
	
	final DateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
	
	// Liste des ventes
	for (int i = 0; i < venteListBean.getList().size(); ++i) {
	    Date current = venteListBean.getList().get(i).getDateVente();
	    boolean egale = false;
	    double somme = 0;
	    double tvaCumule = 0;
	    int index = i;
	    do {
		egale = true;
		if (current.compareTo(venteListBean.getList().get(index)
			.getDateVente()) == 0) {
		   
		    somme += venteListBean.getList().get(index).getAccompte();
		    tvaCumule += venteListBean.getList().get(index).getTvaCumule();
		    
		} else
		    egale = false;

		if (index < i)
		    ++index;
		else
		    break;

	    } while (egale);
	    mapV.put(sd.format(current), somme); 
	   totalTvaCum += tvaCumule;
	    totalVente += somme;
	}
	
	// Liste des achats
	for (int i = 0; i < achatListBean.getList().size(); ++i) {
	    Date current = achatListBean.getList().get(i).getDateAchats();
	    boolean egale = false;
	    double somme = 0;
	    double tvaPercu = 0;
	    int index = i;
	    do {
		egale = true;
		if (current.compareTo(achatListBean.getList().get(index)
			.getDateAchats()) == 0) {
		    somme += achatListBean.getList().get(index).getAccomte();
		    tvaPercu += achatListBean.getList().get(index).getTvaRembourssable();
		    
		} else
		    egale = false;

		if (index < i)
		    ++index;
		else
		    break;

	    } while (egale);
	    mapA.put(sd.format(current), somme);
	   totalTvapercu += tvaPercu;
	    totalAchat += somme;
	}

      
	tresorerie = totalVente - totalAchat;
	tva =  totalVente - totalTvapercu;
    }

    @SuppressWarnings("unused")
    private void createAreaModel() {
	
	model = new LineChartModel();

	// init graph entre
	final LineChartSeries entre = new LineChartSeries();
	entre.setLabel(Messages.getString("BalanceBean.0")); //$NON-NLS-1$
	entre.setData(mapV);

	// init graph sortie
	final LineChartSeries sortie = new LineChartSeries();
	sortie.setYaxis(AxisType.Y2);
	sortie.setLabel(Messages.getString("BalanceBean.1")); //$NON-NLS-1$
	sortie.setData(mapA);

	// Caracteristiques du model
	model.setTitle(Messages.getString("BalanceBean.3") + Calendar.getInstance().get(Calendar.YEAR)); //$NON-NLS-1$
	model.setLegendPosition("ne"); //$NON-NLS-1$
	model.setAnimate(true);
	model.setStacked(true);
	model.setShowPointLabels(true);

	DateAxis axis = new DateAxis(Messages.getString("BalanceBean.2")); //$NON-NLS-1$
	//axis.setTickAngle(-50);
	// axis.setMax("2014-02-01");
	axis.setTickFormat("%b %#d, %y"); //$NON-NLS-1$

	final Axis yAxis = model.getAxis(AxisType.Y);
	yAxis.setLabel(Messages.getString("BalanceBean.6")); //$NON-NLS-1$
	yAxis.setMin(0);
	
	model.getAxes().put(AxisType.X, axis);
	model.getAxes().put(AxisType.Y2, yAxis);
	model.addSeries(entre);
	model.addSeries(sortie);

    }

    public VenteListBean getVenteListBean() {
	return venteListBean;
    }

    public void setVenteListBean(VenteListBean venteListBean) {
	this.venteListBean = venteListBean;
    }

    public AchatsListBean getAchatListBean() {
	return achatListBean;
    }

    public void setAchatListBean(AchatsListBean achatListBean) {
	this.achatListBean = achatListBean;
    }

    public Map<Object, Number> getMapV() {
	return mapV;
    }

    public void setMapV(Map<Object, Number> mapV) {
	this.mapV = mapV;
    }

    public Map<Object, Number> getMapA() {
	return mapA;
    }

    public void setMapA(Map<Object, Number> mapA) {
	this.mapA = mapA;
    }

    public LineChartModel getModel() {
	return model;
    }

    public void setModel(LineChartModel model) {
	this.model = model;
    }

    public double getTresorerie() {
	return tresorerie;
    }

    public void setTresorerie(double tresorerie) {
	this.tresorerie = tresorerie;
    }

    public double getTva() {
	return tva;
    }

    public void setTva(double tva) {
	this.tva = tva;
    }

    public boolean isView() {
	return view;
    }

    public void setView(boolean view) {
	this.view = view;
    }

    public double getZero() {
	return zero;
    }

    public void setZero(double zero) {
	this.zero = zero;
    }

    
}

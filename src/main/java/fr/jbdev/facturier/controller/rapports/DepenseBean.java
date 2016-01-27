package fr.jbdev.facturier.controller.rapports;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.DonutChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import fr.jbdev.domaine.Achats;
import fr.jbdev.domaine.Contient;
import fr.jbdev.facturier.controller.achats.AchatsListBean;

@SessionScoped
@ManagedBean(name = "depenseBean", eager = false)
public class DepenseBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{achatsListBean}")
    private AchatsListBean achatListBean;

    private DonutChartModel depenseModel;
    private LineChartModel lineModel;
    private boolean view;
    private double totalAchats;

    @PostConstruct
    public void init() {
	view = true;
	 createDonutModel();
	createLineModel();
    }

    public void createLineModel() {
	lineModel = initLineModel();
	lineModel.setAnimate(true);
	lineModel.setTitle(Messages.getString("DepenseBean.0")); //$NON-NLS-1$
	lineModel.setLegendPosition("ne"); //$NON-NLS-1$
	lineModel.setStacked(true);
	lineModel.setShowPointLabels(true);

	DateAxis axis = new DateAxis(Messages.getString("DepenseBean.1")); //$NON-NLS-1$
	axis.setTickFormat("%b %#d, %y"); //$NON-NLS-1$

	lineModel.getAxes().put(AxisType.X, axis);

    }

     public void createDonutModel() {
	 depenseModel = initDonutModel();
    	depenseModel.setTitle(Messages.getString("DepenseBean.2")); //$NON-NLS-1$
    	depenseModel.setLegendPosition("ne"); //$NON-NLS-1$
     depenseModel.setSliceMargin(5);
     depenseModel.setShowDataLabels(true);
     depenseModel.setMouseoverHighlight(true);
     depenseModel.setShadow(true);
    	depenseModel.setDataFormat("value"); //$NON-NLS-1$
     depenseModel.setShadow(true);
    
     }

    public LineChartModel initLineModel() {

	final DateFormat sd = new SimpleDateFormat("yyyy-MM-dd"); //$NON-NLS-1$

	final LineChartModel model = new LineChartModel();
	final Map<Object, Number> mapA = new HashMap<Object, Number>();

	if (!achatListBean.getList().isEmpty()) {
	    // Liste des achats
	    for (int i = 0; i < achatListBean.getList().size(); ++i) {
		Date current = achatListBean.getList().get(i).getDateAchats();
		boolean egale = false;
		double somme = 0;
		int index = i;
		do {
		    egale = true;
		    if (current.compareTo(achatListBean.getList().get(index)
			    .getDateAchats()) == 0) {
			somme += achatListBean.getList().get(index)
				.getAccomte();
		    } else
			egale = false;

		    if (index < i)
			++index;
		    else
			break;

		} while (egale);
		mapA.put(sd.format(current), somme);
		totalAchats += somme;
	    }

	    final LineChartSeries serie = new LineChartSeries();
	    serie.setLabel(Messages.getString("DepenseBean.6")); //$NON-NLS-1$
	    // serie.setFill(true);
	    serie.setData(mapA);

	    model.addSeries(serie);
	} else {
	    view = false;
	}
	return model;

    }

    public AchatsListBean getAchatListBean() {
	return achatListBean;
    }

    public void setAchatListBean(AchatsListBean achatListBean) {
	this.achatListBean = achatListBean;
    }

    public LineChartModel getLineModel() {
	return lineModel;
    }

    public void setLineModel(LineChartModel lineModel) {
	this.lineModel = lineModel;
    }

    public boolean isView() {
	return view;
    }

    public void setView(boolean view) {
	this.view = view;
    }

    public double getTotalAchats() {
	return totalAchats;
    }

    public void setTotalAchats(double totalAchats) {
	this.totalAchats = totalAchats;
    }

     public DonutChartModel initDonutModel() {
     final DonutChartModel model = new DonutChartModel();
     List<Achats> list = achatListBean.getList();
     final Map<String, Number> circle1 = new LinkedHashMap<String, Number>();
     final Map<String, Number> circle2 = new LinkedHashMap<String, Number>();
     for(Achats achat : list ) {
	 double somme = 0;
	 String nom = null;
	    double sommeCat = 0; 
	    String nomCat = null;
	for(Contient contient :  achat.getCommandes().getContients()) {
	    nom = contient.getProduits().getNom();
	    somme += contient.getQuantite() * contient.getProduits().getPrixHt();

	   for(Contient tmp :  achat.getCommandes().getContients()) {
	       if(tmp.getProduits().getCategories().equals(contient.getProduits().getCategories())) {
		   nomCat = tmp.getProduits().getCategories().getNom();
		   sommeCat += contient.getQuantite() * contient.getProduits().getPrixHt();
	       }
	   }
	}
	circle2.put(nom, somme);
	circle1.put(nomCat, sommeCat);
     }
  
     model.addCircle(circle1);
     model.addCircle(circle2);
  
     return model;
     }

    public DonutChartModel getDepenseModel() {
        return depenseModel;
    }

    public void setDepenseModel(DonutChartModel depenseModel) {
        this.depenseModel = depenseModel;
    }

}

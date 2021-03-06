package appli;

import model.CoreFunctions;
import model.Periode;
import model.PeriodeReader;
import views.PeriodeView;
import controllers.Control;
import controllers.Tool;


public class Appli {

	
	
	public static void main(String[] args) throws Exception {
		Periode periode = new Periode();
		
		Tool.setPeriode(periode);
		Control.setPeriode(periode);
		
		PeriodeView view = new PeriodeView();
		
		Control.setPeriodeView(view);
		
		view.observe(periode);
		
		
		periode.dessiner(CoreFunctions.sinusoide());
		periode.duree(1.0 / 440.0);
		view.selectTool(Tool.SNAP);
		PeriodeReader.prepare(periode);
	}

}

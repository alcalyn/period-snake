package controllers;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;


import model.Periode;

import views.CourbePanel;

public abstract class Tool implements MouseListener, MouseMotionListener {
	
	
	public static final int
		SNAP = 5,
		WRITE = 6,
		CHUT = 7;
	
	
	private static HashMap<Integer, Tool> singletons = new HashMap<Integer, Tool>();
	
	
	protected static Periode periode;
	
	
	protected CourbePanel courbe_panel;
	protected Brush brush;
	
	
	
	public Tool(CourbePanel courbe_panel) {
		this.courbe_panel = courbe_panel;
		this.brush = new Brush();
	}
	
	public Brush getBrush() {
		return brush;
	}
	
	
	public String [] getBrushParams() {
		return new String[0];
	}
	
	public abstract String getName();
	
	
	public static Tool getTool(int tool, CourbePanel courbe_panel) {
		if(singletons.get(tool) == null) {
			switch (tool) {
			case SNAP:
				singletons.put(tool, new SnapTool(courbe_panel));
				break;
			
			case WRITE:
				singletons.put(tool, new WriteTool(courbe_panel));
				break;
			
			case CHUT:
				singletons.put(tool, new ChutTool(courbe_panel));
				break;
	
			default:
				System.out.println("Tool code undefined : "+tool);
				return null;
			}
		}
		
		return singletons.get(tool);
	}
	
	
	public static void setPeriode(Periode periode) {
		Tool.periode = periode;
	}
	
	
	public static int mod(int s) {
		int mod = s % periode.sampling();
		if(mod < 0) mod += periode.sampling();
		return mod;
	}
	

}

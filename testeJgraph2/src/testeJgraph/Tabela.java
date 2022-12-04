package testeJgraph;

import java.awt.Color;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.text.AttributeSet.ColorAttribute;

import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxUtils;
import com.mxgraph.view.mxStylesheet;

public class Tabela{
	private JButton tabelaButton;
	private JPanel tabelaPanel;
	
	public Tabela(mxStylesheet stylesheet) {
				
		tabelaButton = new JButton("Tabela");
		tabelaButton.setBounds(600, 300, 100, 50);
		tabelaPanel = new JPanel();
		tabelaPanel.add(tabelaButton);
	    stylesheet.putCellStyle("tabela",createTabelaStyle());
	       
	}

	
	public JPanel getPanel() {
		return tabelaPanel;
	}
	
	public JButton getButton() {
		return tabelaButton;
	}
	
	private Hashtable<String,Object> createTabelaStyle() {
	    Hashtable<String, Object> style = new Hashtable<String, Object>();
	    style.put(mxConstants.STYLE_FILLCOLOR, mxUtils.getHexColorString(Color.WHITE));
	    style.put(mxConstants.STYLE_STROKEWIDTH, 1.5);
	    style.put(mxConstants.STYLE_STROKECOLOR, mxUtils.getHexColorString(new Color(0, 0, 170)));
	    style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
	    style.put(mxConstants.STYLE_PERIMETER, mxConstants.PERIMETER_ELLIPSE);
	    return style;
	 }
	

}
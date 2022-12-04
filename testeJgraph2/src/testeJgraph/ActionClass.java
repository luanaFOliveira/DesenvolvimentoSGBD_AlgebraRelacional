package testeJgraph;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxUtils;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;
import java.util.ArrayList;
import java.util.List;
import sgbd.*;

public class ActionClass extends JFrame implements ActionListener {
	
	private mxGraph graph;
	private mxGraphComponent graphComponent;
	private Object newCellChild;
	private Object newCell;
	private Boolean createCell=false;
	private String style;
	private String name;
	private Object cell;
	
	private Projecao tipoProjecao;
	private Selecao tipoSelecao;
	private ProdutoCartesiano tipoProdutoCartesiano;
	private Uniao tipoUniao;
	private Diferenca tipoDiferenca;
	private Renomeacao tipoRenomeacao;
	private Tabela tipoTabela;
	
	private JButton edgeButton;
	private Boolean createEdge=false;
	private Object newParent;
	private JPanel edgePanel;
	
	private List<Object> listCells;
	
	private String inf;
	private Form form;
	
	
	public ActionClass() {
		super("Jgraph teste");
		initGUI();
	}
	
	private void initGUI() {
		setSize(800,600);
		setLocationRelativeTo(null);
		
		graph = new mxGraph();
		graphComponent = new mxGraphComponent(graph);
		graphComponent.setPreferredSize(new Dimension(400,400));
		getContentPane().add(graphComponent);
		
		
	    JPanel containerPanel = new JPanel(new GridLayout(8, 1));
	    mxStylesheet stylesheet = graph.getStylesheet();
	    
	    // criar classe que tenha todos os tipos dai passo soh ela pro de controlar input, criar o action listener na outra classe tbm
	    tipoProjecao = new Projecao(stylesheet);
	    tipoProjecao.getButton().addActionListener(this);
	    containerPanel.add(tipoProjecao.getPanel());
	  
	    tipoSelecao = new Selecao(stylesheet);
	    tipoSelecao.getButton().addActionListener(this);
	    containerPanel.add(tipoSelecao.getPanel());

	    tipoProdutoCartesiano = new ProdutoCartesiano(stylesheet);
	    tipoProdutoCartesiano.getButton().addActionListener(this);
	    containerPanel.add(tipoProdutoCartesiano.getPanel());
	    
	    tipoUniao = new Uniao(stylesheet);
	    tipoUniao.getButton().addActionListener(this);
	    containerPanel.add(tipoUniao.getPanel());

	    tipoDiferenca = new Diferenca(stylesheet);
	    tipoDiferenca.getButton().addActionListener(this);
	    containerPanel.add(tipoDiferenca.getPanel());
	    
	    tipoRenomeacao = new Renomeacao(stylesheet);
	    tipoRenomeacao.getButton().addActionListener(this);
	    containerPanel.add(tipoRenomeacao.getPanel());

	    tipoTabela = new Tabela(stylesheet);
	    tipoTabela.getButton().addActionListener(this);
	    containerPanel.add(tipoTabela.getPanel());
	    
	    edgeButton = new JButton("Edge");
	    edgeButton.setBounds(600, 300, 100, 50);
	    edgeButton.addActionListener(this);
	    edgePanel = new JPanel();
	    edgePanel.add(edgeButton);
	    containerPanel.add(edgePanel);
	    
	    	    
		this.add(containerPanel,BorderLayout.EAST);

		setVisible(true);
	
		
		Object parent = graph.getDefaultParent();
		graph.getModel().beginUpdate();
		
		
		
		mxHierarchicalLayout layout = new mxHierarchicalLayout(graph);
		layout.setUseBoundingBox(false);
		layout.execute(parent);
		
		this.listCells = new ArrayList<>();
		
		
		graphComponent.getGraphControl().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				cell = graphComponent.getCellAt(e.getX(), e.getY());
				if(createCell == true ) {
					newCell = graph.insertVertex(parent,null, name, e.getX(), e.getY(), 80, 30,style);
					listCells.add(newCell);
					createCell = false;
				}
				/*else if(cell == null && newCell != null) {
					newCellChild = graph.insertVertex(parent, null, "no", e.getX(), e.getY(), 80, 30,style);
					listCells.add(newCellChild);
					graph.insertEdge(parent, null, "", newCell, newCellChild);
				}
				
				if(cell != null) {
					newCell = cell;
					style = ((mxCell) cell).getStyle();
				}
				*/
				if(cell != null) {
					System.out.println(((mxCell) cell).getValue().toString());
					if(createEdge == true && newParent == null) {
						newParent = cell;
					}
					if( createEdge == true && cell != newParent) {
						graph.insertEdge(newParent, null,"", newParent, cell);
						((mxCell) cell).setParent((mxCell)newParent);
						newParent = null;
						createEdge = false;
						
					}
				}

				if(e.getButton() == MouseEvent.BUTTON3 && cell != null) {
					graph.getModel().remove(cell);	

				}
				
			}
			
		});
		
		
		graph.getModel().endUpdate();
		
		
			
	}

	private void assignVariables(String styleVar,String nameVar) {
		createCell = true;
		newCell = null;
		newCellChild = null;
		newParent = null;
		style = styleVar;
		name = nameVar;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
			if(e.getSource() == tipoProjecao.getButton()) {
				assignVariables("projecao","π  projecao");
				
			}else if(e.getSource() == tipoSelecao.getButton() ) {
				assignVariables("selecao","σ  selecao");
				//form = new Form(inf,"select");
				form = tipoSelecao.createForm();
				
			}else if(e.getSource() == tipoProdutoCartesiano.getButton()) {
				assignVariables("produtoCartesiano","✕  produto cartesiano");
				
				System.out.println(form.getPredicate());

			}else if(e.getSource() == tipoUniao.getButton()) {
				assignVariables("uniao","∪  uniao");
				
			}else if(e.getSource() == tipoDiferenca.getButton()) {
				assignVariables("diferenca","-  diferenca");
				
			}else if(e.getSource() == tipoRenomeacao.getButton()) {
				assignVariables("renomeacao","ρ  renomeacao");
				
			}else if(e.getSource() == tipoTabela.getButton()) {
				assignVariables("tabela","tabela");
			}else if(e.getSource() == edgeButton) {
				createEdge = true;
			}
			
	}
	
	
}
	





package testeJgraph;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;

import entities.Cell;
import entities.OperatorCell;
import entities.TableCell;
import sgbd.prototype.Prototype;
import sgbd.table.Table;
import util.ImportCSVFile;

@SuppressWarnings("serial")
public class ActionClass extends JFrame implements ActionListener {
	
	private mxGraph graph;
	private mxGraphComponent graphComponent;
	private Object newCell;
	private Boolean createCell=false;
	private String style;
	private String name;
	private Object cell;
	private boolean isOperation;
	
	private Projecao tipoProjecao;
	private Selecao tipoSelecao;
	private ProdutoCartesiano tipoProdutoCartesiano;
	private Uniao tipoUniao;
	private Diferenca tipoDiferenca;
	private Renomeacao tipoRenomeacao;
	private Juncao tipoJuncao;
	
	private JButton edgeButton;
	private Boolean createEdge=false;
	private Object newParent;
	private JPanel edgePanel;
	
	private JButton importButton;
	private JToolBar toolBar;
	
	private List<Cell> listCells;
	
	private Prototype currentPrototype = null;
	private Table currentTable = null;
	
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
	    
	    tipoJuncao = new Juncao(stylesheet);
	    tipoJuncao.getButton().addActionListener(this);
	    containerPanel.add(tipoJuncao.getPanel());

	    edgeButton = new JButton("Edge");
	    edgeButton.setBounds(600, 300, 100, 50);
	    edgeButton.addActionListener(this);
	    edgePanel = new JPanel();
	    edgePanel.add(edgeButton);
	    containerPanel.add(edgePanel);
	    
		toolBar = new JToolBar();
		getContentPane().add(toolBar, BorderLayout.SOUTH);
	    
		importButton = new JButton("Importar tabela");
		toolBar.add(importButton);
		importButton.setHorizontalAlignment(SwingConstants.LEFT);
		importButton.addActionListener(this);
	    
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

				cell = graphComponent.getCellAt(e.getX(), e.getY());
				
				if(createCell == true ) {

					newCell = graph.insertVertex(parent,null, name, e.getX(), e.getY(), 80, 30,style);
					
					listCells.add(isOperation ? new OperatorCell(name, style, newCell) :
												new TableCell(name, style, newCell, currentTable, currentPrototype));
					
					createCell = false;
					
				}
				
				if(cell != null) {
					
					if(createEdge == true && newParent == null) {
						newParent = cell;
					}
					if( createEdge == true && cell != newParent) {
						
						graph.insertEdge(newParent, null,"", newParent, cell);
						((mxCell) cell).setParent((mxCell)newParent);
						
						if(style == "projecao") {
							
							new FormFrameProjecao(cell, listCells);
							
						}else if(style == "selecao") {
							
							new FormFrameSelecao(cell, listCells);
							
						}else if(style == "juncao") {
							
							//formFrameJuncao = new FormFrameJuncao(ps.get(0),ps.get(1),tables.get(0),tables.get(1));
							
						}
						
						newParent = null;
						createEdge = false;
						
					}
					//Cell entityCell = listCells.stream().filter(x -> x.getCell().equals((mxCell)cell)).findFirst().orElse(null);
					//OperatorToTable.converter(new ProjectionOperator(entityCell.getTable(), entityCell.getColumns()), Arrays.asList(1));
				}

				if(e.getButton() == MouseEvent.BUTTON3 && cell != null) {
					
					graph.getModel().remove(cell);	

				}else if(e.getButton() == MouseEvent.BUTTON2 && cell != null) {
					
					Cell entityCell = listCells.stream().filter(x -> x.getCell().equals((mxCell)cell)).findFirst().orElse(null);
					new ResultFrame(entityCell.getContent());
					
				}
				
			}
			
		});
		
		graph.getModel().endUpdate();
			
	}

	private void assignVariables(String styleVar, String nameVar, boolean isOperation) {
		
		createCell = true;
		newCell = null;
		newParent = null;
		style = styleVar;
		name = nameVar;
		this.isOperation = isOperation;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
			if(e.getSource() == tipoProjecao.getButton()) {
				assignVariables("projecao","π  projecao", true);
				
			}else if(e.getSource() == tipoSelecao.getButton() ) {
				assignVariables("selecao","σ  selecao", true);
				
			}else if(e.getSource() == tipoProdutoCartesiano.getButton()) {
				assignVariables("produtoCartesiano","✕  produto cartesiano", true);
				
			}else if(e.getSource() == tipoUniao.getButton()) {
				assignVariables("uniao","∪  uniao", true);
				
			}else if(e.getSource() == tipoDiferenca.getButton()) {
				assignVariables("diferenca","-  diferenca", true);
				
			}else if(e.getSource() == tipoRenomeacao.getButton()) {
				assignVariables("renomeacao","ρ  renomeacao", true);
				
			}else if(e.getSource() == edgeButton) {
				createEdge = true;
				
			}else if(e.getSource() == importButton) {
				ImportCSVFile.importCSVFile();
				String name = ImportCSVFile.getFileName();
				currentTable = ImportCSVFile.getTable();
				currentPrototype = ImportCSVFile.getPrototype();
				assignVariables("tabela", name, false);
				
			}else if(e.getSource() == tipoJuncao.getButton()) {
				assignVariables("juncao","|X| juncao", true);

			}
			
	}
	
}

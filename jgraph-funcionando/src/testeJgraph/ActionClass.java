package testeJgraph;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;

import sgbd.prototype.Column;
import sgbd.prototype.Prototype;
import sgbd.prototype.RowData;
import sgbd.table.SimpleTable;
import sgbd.table.Table;
import util.FindType;

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
	
	private JButton edgeButton;
	private Boolean createEdge=false;
	private Object newParent;
	private JPanel edgePanel;
	
	private JButton importButton;
	private JToolBar toolBar;
	
	private List<Object> listCells;
	
	private List<Table> tables = new ArrayList<>();
	private List<Prototype> ps = new ArrayList<>();
	
	//private String inf;
	private FormFrameProjecao formFrameProjecao;
	private FormFrameSelecao formFrameSelecao;
	
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
				// TODO Auto-generated method stub
				cell = graphComponent.getCellAt(e.getX(), e.getY());
				if(createCell == true ) {
					newCell = graph.insertVertex(parent,null, name, e.getX(), e.getY(), 80, 30,style);
					listCells.add(newCell);
					createCell = false;
				}
				
				if(cell != null) {
					System.out.println(((mxCell) cell).getValue().toString());
					if(createEdge == true && newParent == null) {
						newParent = cell;
					}
					if( createEdge == true && cell != newParent) {
						graph.insertEdge(newParent, null,"", newParent, cell);
						((mxCell) cell).setParent((mxCell)newParent);
						if(style == "projecao") {
							formFrameProjecao.main(ps.get(0), tables.get(0));
							//operator = formFrameProjecao.getOperator();
						}else if(style == "selecao") {
							formFrameSelecao.main(ps.get(0), tables.get(0));
							//operator = formFrameSelecao.getOperator();
						}
						
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
	
	private String importFile() {
		
		JFileChooser fileUpload = new JFileChooser();
		//fileUpload.showOpenDialog(null);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV files", "csv");
		fileUpload.setFileFilter(filter);
		
		int res = fileUpload.showOpenDialog(null);
		
		if(res == JFileChooser.APPROVE_OPTION) {
			
			try(BufferedReader br = new BufferedReader(new FileReader(fileUpload.getSelectedFile().getAbsolutePath()))){
				
				String columnsName[] = br.readLine().replace("\"", "").replace(" ", "").split(",");
				String fileName = fileUpload.getSelectedFile().getName().toUpperCase().substring(0, fileUpload.getSelectedFile().getName().indexOf("."));
				
				List<RowData> rows = new ArrayList<>();
				
				String line = br.readLine();
				while(line != null) {
					
					String columns[] = line.split(",");
					
					RowData data = new RowData();
					
					int j = 0;
					
					for(String i : columns) {
						
						if(FindType.isInt(i)) {
							
							data.setInt(columnsName[j], Integer.parseInt(i));
							
						}else if(FindType.isFloat(i)) {
							
							data.setFloat(columnsName[j], Float.parseFloat(i));
							
						}else {
							
							data.setString(columnsName[j], i);
							
						}
						
						j++;
						
					}
					
					rows.add(data);
					
					line = br.readLine();
				}
				
				List<String> columnsNameList1 = Arrays.asList(columnsName);
				List<String> columnsNameList2 = new ArrayList<>(columnsNameList1);
				
				createTable(fileName, columnsNameList2, rows);
				return fileName;
				
			}catch(IOException e) {
				
				e.printStackTrace();
			
			}
			
		}
		
		return null;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
			if(e.getSource() == tipoProjecao.getButton()) {
				assignVariables("projecao","π  projecao");
				
			}else if(e.getSource() == tipoSelecao.getButton() ) {
				assignVariables("selecao","σ  selecao");
				
			}else if(e.getSource() == tipoProdutoCartesiano.getButton()) {
				assignVariables("produtoCartesiano","✕  produto cartesiano");
				
			}else if(e.getSource() == tipoUniao.getButton()) {
				assignVariables("uniao","∪  uniao");
				
			}else if(e.getSource() == tipoDiferenca.getButton()) {
				assignVariables("diferenca","-  diferenca");
				
			}else if(e.getSource() == tipoRenomeacao.getButton()) {
				assignVariables("renomeacao","ρ  renomeacao");
				
			}else if(e.getSource() == edgeButton) {
				createEdge = true;
				
			}else if(e.getSource() == importButton) {
				String name = importFile();
				assignVariables("tabela", name);
				
			}
			
	}
	
	public void createTable(String fileName, List<String> columnsName, List<RowData> rows) {
		Prototype p = new Prototype();
		
		p.addColumn(columnsName.get(0), 4, Column.PRIMARY_KEY);
		columnsName.remove(0);
		
		// adiciona todas as colunas
		columnsName.forEach(x -> {p.addColumn(x, 100, Column.NONE);});
		
        Table table = SimpleTable.openTable(fileName, p);
        table.open();
        rows.stream().forEach(x -> {table.insert(x);});
        
        tables.add(table);
        ps.add(p);

        table.open();
        
        
	}
	
	
}

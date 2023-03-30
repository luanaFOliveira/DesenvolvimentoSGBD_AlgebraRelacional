package gui.frames.forms.operations;

import java.util.List;
import java.util.Map;

import com.mxgraph.model.mxCell;
import com.mxgraph.view.mxGraph;

import entities.Cell;
import entities.OperatorCell;
import sgbd.query.Operator;
import sgbd.query.binaryop.joins.BlockNestedLoopJoin;

public class CartesianProduct {

	private Cell cell;
	private Cell parentCell1;
	private Cell parentCell2;
	private Object jCell;
	private mxGraph graph;

	public CartesianProduct(Object jCell, Map<mxCell, Cell> cells, mxGraph graph) {

		this.cell = cells.get(jCell);
		this.parentCell1 = this.cell.getParents().get(0);
		this.parentCell2 = this.cell.getParents().get(1);
		this.jCell = jCell;
		this.graph = graph;
		executeOperation();

	}

	public void executeOperation() {

		Operator table1 = parentCell1.getData();
		Operator table2 = parentCell2.getData();

		
		Operator operator = new BlockNestedLoopJoin(table1, table2, (t1, t2) -> {
			return true;
		});

		((OperatorCell) cell).setColumns(List.of(parentCell1.getColumns(), parentCell2.getColumns()),
				operator.getContentInfo().values());
		((OperatorCell) cell).setOperator(operator);
		cell.setName(parentCell1.getName() + " X " + parentCell2.getName());

		graph.getModel().setValue(jCell, "X");

	}
}

package entities;

import sgbd.prototype.Prototype;
import sgbd.query.Operator;
import sgbd.table.Table;

public class Cell {
	
	private String style;
	private String name;
	private Object cell;
	private Operator operator;
	private Prototype prototype;
	private Table table;

	public Cell(String name, String style, Object cell, Table table, Prototype prototype) {
		
		this.style = style;
		this.name = name;
		this.cell = cell;
		this.table = table;
		this.prototype = prototype;
		operator = null;
		
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getCell() {
		return cell;
	}

	public void setCell(Object cell) {
		this.cell = cell;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public Prototype getPrototype() {
		return prototype;
	}

	public void setPrototype(Prototype prototype) {
		this.prototype = prototype;
	}
	
}

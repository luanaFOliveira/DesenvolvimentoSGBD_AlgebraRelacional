package entities;

import java.util.List;

import sgbd.query.Operator;
import sgbd.table.Table;

public abstract class Cell {
	
	private String style;
	private String name;
	private Object cell;
	
	public Cell(String name, String style, Object cell) {
		
		this.style = style;
		this.name = name;
		this.cell = cell;
		
	}

	public String getStyle() {
		return style;
	}

	public String getName() {
		return name;
	}

	public Object getCell() {
		return cell;
	}

	public abstract Operator getData();
	public abstract List<String> getColumns();
	public abstract String toString();
	public abstract Table getTable();
	
}

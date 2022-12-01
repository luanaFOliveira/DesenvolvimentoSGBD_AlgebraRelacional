package antlr.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import antlr.expressions.AntlrToProgram;
import antlr.expressions.ExpressionProcessor;
import antlr.expressions.Program;
import antlr.tools.ExprLexer;
import antlr.tools.ExprParser;
import sgbd.prototype.Column;
import sgbd.prototype.ComplexRowData;
import sgbd.prototype.Prototype;
import sgbd.query.Operator;
import sgbd.query.Tuple;
import sgbd.query.sourceop.TableScan;
import sgbd.query.unaryop.FilterOperator;
import sgbd.table.SimpleTable;
import sgbd.table.Table;

public class Main {

	public static void main(String[] args) {
		
		List<String[]> commands = new ArrayList<>();
		
		if(args.length != 1) {
			System.err.print("Coloque o arquivo com as entradas.");
		}else {
			String fileName = args[0];
			ExprParser parser = getParser(fileName);
			
			ParseTree antlrAST = parser.prog();
			
			AntlrToProgram progVisitor = new AntlrToProgram();
			Program prog = progVisitor.visit(antlrAST);
			
			ExpressionProcessor ep = new ExpressionProcessor(prog.expressions);
			
			for(String evaluation : ep.getEvaluationResults()) {
				commands.add(evaluation.split(";"));
			}
				
		}
		
		Prototype p1 = new Prototype();
        p1.addColumn("id",4,Column.PRIMARY_KEY);
        p1.addColumn("nome",255,Column.DINAMIC_COLUMN_SIZE);
        p1.addColumn("anoNascimento",4,Column.NONE);
        p1.addColumn("email",120,Column.NONE);
        p1.addColumn("idade",4,Column.CAM_NULL_COLUMN);
        p1.addColumn("salario",4,Column.NONE);
        p1.addColumn("idCidade",4,Column.NONE);
        
        Table users = SimpleTable.openTable("users",p1);
		
        users.open();
        
        Operator selectSomeUsers = new TableScan(users, Arrays.asList("id","idade","nome", "idCidade", "salario"));
        
        Operator executor = null;
        
        System.out.println(commands.get(0)[1].substring(0, commands.get(0)[1].indexOf(">")));
        
        if(commands.get(0)[0].equals("Select")) {
        	
	        Operator where = new FilterOperator(selectSomeUsers,(Tuple t)->{
	            return t.getContent("users").getInt(commands.get(0)[1].substring(0, commands.get(0)[1].indexOf(">"))) >
	            	   Integer.parseInt(commands.get(0)[1].substring(commands.get(0)[1].indexOf(">") + 1));
	        });
        
        	executor = where;
        	executor.open();
        
        }
        
        while(executor.hasNext()){
            Tuple t = executor.next();
            String str = "";
            for (Map.Entry<String, ComplexRowData> row: t){
                for(Map.Entry<String,byte[]> data:row.getValue()) {
                    switch(data.getKey()){
                        case "idade":
                        case "anoNascimento":
                        case "id":
                        case "idCidade":
                        case "size":
                            str+=row.getKey()+"."+data.getKey()+"="+row.getValue().getInt(data.getKey());
                            break;
                        case "salario":
                            str+=row.getKey()+"."+data.getKey()+"="+row.getValue().getFloat(data.getKey());
                            break;
                        case "name":
                        	str+=row.getKey()+"."+data.getKey()+"="+row.getValue().getFloat(data.getKey());
                        case "nome":
                        default:
                            str+=row.getKey()+"."+data.getKey()+"="+row.getValue().getString(data.getKey());
                            break;
                    }
                    str+=" | ";
                }
            }
            System.out.println(str);
        }
	}
	
	public static ExprParser getParser(String fileName) {
		ExprParser parser = null;
		
		try {
			CharStream input = CharStreams.fromFileName(fileName);
			ExprLexer lexer = new ExprLexer(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			parser = new ExprParser(tokens);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return parser;
	}
}

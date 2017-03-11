package br.com.persistencia;

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement;


public class GerenteConexao {
	//metodo est�tico gerador de conex�es 
	public static Connection getConexao(){
	Connection conexao = null;
	String url = "jdbc:mysql://localhost:3306/trabalhoBiblioteca";
	String usuario = "root";
	String senha = "1982cleber";
	try{
		Class.forName("org.gjt.mm.mysql.Driver");
		conexao = DriverManager.getConnection(url, usuario, senha);
	}catch(ClassNotFoundException cnfe){
		System.out.println("Erro ao carregar o driver");
		cnfe.printStackTrace();
	}
	catch(SQLException sqle){
		System.out.println("N�o foi poss�vel conectar com o BD");
		sqle.printStackTrace();
	}
	return conexao;
	}
}
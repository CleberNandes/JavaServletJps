package br.com.teste;

import br.com.modelo.Empregado;
import br.com.modelo.EmpregadoDAO;

public class TestaEmpregadoDAO {
	public static void main(String args[]){
		/*Empregado empregado = new Empregado();
		empregado.setNome("Luiz");
		empregado.setAge(45);
		EmpregadoDAO.grava(empregado);*/
		
		// testa altera��o
		
		/*Empregado empregado = new Empregado();
		empregado.setId(2);
		empregado.setNome("Luiz");
		empregado.setAge(45);
		EmpregadoDAO.altera(empregado);*/
		
		// testa exclus�o
		
		//EmpregadoDAO.exclui(1);
		
		// lista empregados
		
		EmpregadoDAO.lista();
		
		// le um �nico empregado
		
		//EmpregadoDAO.le(3);
	}
}
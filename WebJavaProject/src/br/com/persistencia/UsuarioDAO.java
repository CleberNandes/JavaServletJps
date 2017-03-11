package br.com.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.modelo.Usuario;
import br.com.modelo.Tipo_Usuario;

public class UsuarioDAO {
	
	public static int grava(Usuario usuario) {
		Connection con = GerenteConexao.getConexao();
		PreparedStatement pst = null;
		int ret = 0;
		try {
			String sql = "INSERT INTO usuarios(nome, email, tipo_usuario_id, senha) VALUES(?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, usuario.getNome());
			pst.setString(2, usuario.getEmail());
			pst.setInt(3, usuario.getTipo().getId());
			pst.setString(4, usuario.getSenha());
			ret = pst.executeUpdate();
			System.out.println(usuario);
			System.out.println("Dados inseridos com sucesso!!!");
		} catch (SQLException sqle) {
			System.out.println("N�o foi poss�vel inserir os dados!!");
			System.out.println(sqle);
		}
		return ret;
	}
	
	public static int altera(Usuario usuario) {
		Connection con = GerenteConexao.getConexao();
		PreparedStatement pst = null;
		int ret = 0;
		try {
			String sql = "UPDATE usuarios SET nome = ?, email = ?, "
				+ "tipo_usuario_id = ?, senha = ? WHERE id = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, usuario.getNome());
			pst.setString(2, usuario.getEmail());
			pst.setInt(3, usuario.getTipo().getId());
			pst.setString(4, usuario.getSenha());
			pst.setInt(5, usuario.getId());
			ret = pst.executeUpdate();
			System.out.println("Dados atualizados com sucesso!!!");
		} catch (SQLException sqle) {
			System.out.println("N�o foi poss�vel atualizar os dados!!");
			System.out.println(sqle);
			sqle.printStackTrace();
		}
		return ret;
	}
	
	public static int exclui(int id) {
		Connection con = GerenteConexao.getConexao();
		PreparedStatement pst = null;
		int ret = 0; 
		try {
			String sql = "DELETE FROM usuarios WHERE id = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			ret = pst.executeUpdate();
			System.out.println("Dados exclu�do com sucesso!!!");
		} catch (SQLException sqle) {
			System.out.println("N�o foi poss�vel excluir os dados!!");
			sqle.printStackTrace();
		}
		return ret;
	}

	
	public static Usuario le(int id) {
		Connection con = GerenteConexao.getConexao();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Usuario usuario = null;
		Tipo_Usuario tipo = null;
		try {
			String sql = "SELECT * FROM usuarios WHERE id = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()){
				
				tipo = escolheTipoUsuario(rs.getInt("tipo_usuario_id"));
				
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setTipo(tipo);
				System.out.println(usuario);
			}
			System.out.println("Dados obtidos com sucesso!!!");
		} catch (SQLException sqle) {
			System.out.println("N�o foi poss�vel obter os dados!!");
			sqle.printStackTrace();
			System.out.println(sqle);
		}
		return usuario;
	}
	
	
	public static List<Usuario> listaUsuarios() {
		Connection con = GerenteConexao.getConexao();
		PreparedStatement pst = null;
		ResultSet rs = null;
		List <Usuario> usuarios = new ArrayList();
		Usuario usuario = null;
		Tipo_Usuario tipo = null;
		try {
			String sql = "SELECT * FROM usuarios";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()){
				do {
					
					tipo = escolheTipoUsuario(rs.getInt("tipo_usuario_id"));	
					usuario = new Usuario();
					usuario.setId(rs.getInt("id"));
					usuario.setNome(rs.getString("nome"));
					usuario.setEmail(rs.getString("email"));
					usuario.setTipo(tipo);
					usuarios.add(usuario);
					System.out.println(usuario);
				} while(rs.next());
			}
			System.out.println("Dados Listados com sucesso!!!");
		} catch (SQLException sqle) {
			System.out.println("N�o foi poss�vel listar os dados!!");
			System.out.println(sqle);
			sqle.printStackTrace();
		}
		return usuarios;
	}
	

	public static List<Tipo_Usuario> listaTipos() {
		Connection con = GerenteConexao.getConexao();
		PreparedStatement pst = null;
		ResultSet rs = null;
		List <Tipo_Usuario> tipos = new ArrayList();
		Tipo_Usuario tipo = null;
		try {
			String sql = "SELECT * FROM tipo_usuario";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()){
				do {
					
					tipo = new Tipo_Usuario();
					tipo.setId(rs.getInt("id"));
					tipo.setNome(rs.getString("nome"));
					tipos.add(tipo);
					System.out.println(tipo);
				} while(rs.next());	
			}
		} catch (SQLException sqle) {
			System.out.println("N�o foi poss�vel listar os tipos de usuarios!!");
			sqle.printStackTrace();
		}
		return tipos;
	}
	
	public static Tipo_Usuario escolheTipoUsuario(int id) {
		Connection con = GerenteConexao.getConexao();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Tipo_Usuario tipo = null;
		try {
			String sql = "SELECT * FROM tipo_usuario where id = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()){
					
				tipo = new Tipo_Usuario();
				tipo.setId(rs.getInt("id"));
				tipo.setNome(rs.getString("nome"));	
			}
			System.out.println("Tipo "+tipo+" escolhido com sucesso!!!");
		} catch (SQLException sqle) {
			System.out.println("N�o foi poss�vel escolher o tipo!!");
			sqle.printStackTrace();
		}
		return tipo;
	}
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) {

        conn = new conectaDAO().connectDB();
        prep = null;
        try {
            prep = conn.prepareStatement("INSERT INTO produtos(nome, valor) VALUES(?, ?)");
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
        } finally {
            if (prep != null) {
                try {
                    prep.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar PreparedStatement: " + e.getMessage());
                }
            }
        }

    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        conn = new conectaDAO().connectDB();
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        String sql = "SELECT * FROM produtos";
        prep = null;
        try {
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery(sql);
            

            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                listagem.add(produto);
            }
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
        return listagem;
    }

}

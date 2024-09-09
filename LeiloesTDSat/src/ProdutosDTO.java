/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adm
 */
public class ProdutosDTO {
    private Integer id;
    private String nome;
    private Integer valor;
    private String status;

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getValor() {
        return valor;
    }

    public String getStatus() {
        return status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}

public void venderProduto(int produtoId) {
    String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
    try (Connection conn = this.connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, produtoId);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}

public List<Produto> listarProdutosVendidos() {
    List<Produto> produtosVendidos = new ArrayList<>();
    String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
    try (Connection conn = this.connect();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            Produto produto = new Produto();
            while (rs.next()) {
                Produto produto = new Produto();
            
                produto.setId(rs.getInt("id"));           
                produto.setNome(rs.getString("nome"));  
                produto.setPreco(rs.getDouble("preco"));
                produto.setStatus(rs.getString("status"));
            
                produtosVendidos.add(produto);
            }
            
            produtosVendidos.add(produto);
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    return produtosVendidos;
}


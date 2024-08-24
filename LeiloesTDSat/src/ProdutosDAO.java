import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProdutosDAO {

    // Método para cadastrar produtos (já existente)
    public boolean cadastrarProduto(ProdutosDTO produto) {
        Connection conn = null;
        PreparedStatement stmt = null;
    
        try {
            conn = ConexaoDAO.getConnection();
            String sql = "INSERT INTO uc11 (nome, valor, status) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getValor());
            stmt.setString(3, produto.getStatus());
    
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0; // Retorna verdadeiro se o produto foi inserido com sucesso
    
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Retorna falso se ocorreu um erro
        } finally {
            ConexaoDAO.closeConnection(conn, stmt);
        }
    }
    

    // Método para listar produtos
    public List<ProdutosDTO> listarProdutos() {
        List<ProdutosDTO> produtos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = conectaDAO.connectDB(); // Pega a conexão com o banco
            String sql = "SELECT * FROM uc11"; // SQL para listar todos os produtos
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));

                produtos.add(produto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConectaDAO.closeConnection(conn, stmt, rs); // Fechar conexão
        }

        return produtos;
    }
}

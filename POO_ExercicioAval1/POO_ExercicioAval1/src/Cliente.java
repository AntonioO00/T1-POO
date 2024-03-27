import java.util.ArrayList;

public class Cliente {

      // Atributos da classe Cliente
    private String nome;
    private String email;
    private String endereco;

    private ArrayList<Entrega> entregas;

    // Construtor dos atributos
    public Cliente (String nome, String email, String endereco){
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(){
        this.endereco = endereco;
    }

    // Metodos de classe Modelo

    public boolean adicionaEntregas(Entrega entrega) {
      return entregas.add(entrega);

    }

    public ArrayList<Entrega> pesquisaEntrega() {
        return entregas;
    }

}

public class Entrega {
    // Atributos da Classe Entregas
    private int codigo;
    private double valor;
    private String descricao;
    private  Cliente cliente;

   // Construtor Entregas
    public Entrega(int codigo,double valor,String descricao, Cliente cliente){

        this.codigo = codigo;
        this.valor = valor;
        this.descricao = descricao;
        this.cliente = cliente;


    }

    // getters e Setters
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    //
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    //
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Cliente getCliente() {
        return cliente;
    }



}

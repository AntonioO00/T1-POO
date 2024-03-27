import java.util.ArrayList;

public class CadastroEntregas {

    private ArrayList<Entrega> cadastroEntregases;
    public ArrayList<Entrega> getCadastroEntregases() {
        return cadastroEntregases;
    }

    public CadastroEntregas() {
        cadastroEntregases = new ArrayList<Entrega>();
    }


    public boolean cadastraEntregas(Entrega entrega) {
        int codigo = entrega.getCodigo();
        if (pesquisaEntrega(codigo)== null){
            return cadastroEntregases.add(entrega);
        }
        return false;
    }


    public Entrega pesquisaEntrega(int codigo) {
        for (Entrega e: cadastroEntregases) {
            if (e.getCodigo()==codigo) {
                return e;
            }
        }
        return null;
    }


     public ArrayList<Entrega> pesquisaEntrega(String email) {
        for (Entrega e: cadastroEntregases) {
            if (e.getCliente().getEmail().equals(email)) {
                return cadastroEntregases;
            }
        }
        return null;
    }

    public ArrayList<Entrega> devolveEntregas(){
        return cadastroEntregases;

    }





}
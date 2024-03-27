import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Locale;

public class ACMEDelivery {

    private CadastroEntregas cadastroentregas;
    private Clientela clientela;
    private Cliente clientes;
     private Scanner entrada = null;
     private PrintStream saidaPadrao = System.out;

    public ACMEDelivery() {
        try {
            BufferedReader streamEntrada = new BufferedReader(new FileReader("arquivoentrada.txt"));
        //  BufferedReader streamEntrada = new BufferedReader(new FileReader("dadosin-extra.txt"));
            entrada = new Scanner(streamEntrada);   // Usa como entrada um arquivo
            PrintStream streamSaida = new PrintStream(new File("arqivosaida.txt"), Charset.forName("UTF-8"));
            System.setOut(streamSaida);             // Usa como saida um arquivo
        } catch (Exception e) {
            System.out.println(e);
        }
        Locale.setDefault(Locale.ENGLISH);   // Ajusta para ponto decimal
        entrada.useLocale(Locale.ENGLISH);
        entrada.useLocale(Locale.ENGLISH);

        cadastroentregas= new CadastroEntregas();
        clientela = new Clientela();
    }

    public void executar(){

        cadastraCliente();
        cadastraEntregas();
        quantClientes();
        quantEntregas();
        mostrardadosCliente();
        mostrardadosEntrega();
        dadosentregaCliente();
        maiorValor();
        enderecoEntrega();
        somatorioCliente();
        restauraES();
        pontoExtra();

            }



    private void cadastraCliente(){
        String email = "";

        do {
            email = entrada.nextLine();
            if (!email.equals("-1")) {
                String nome = entrada.nextLine();
                String endereco = entrada.nextLine();
                Cliente c = new Cliente(nome, email, endereco);
                if (clientela.cadastraCliente(c)) {
                    System.out.println(" ________________________________________________");
                    System.out.println("Cliente cadastrado com sucesso");
                    System.out.println(" o nome do cliente é : "+ c.getNome());
                    System.out.println("O email do cliente é:  "+ c.getEmail());
                    System.out.println("O endereço do cliente é: " +c.getEndereco());
                } else
                    System.out.println("Erro: Cliente nao cadastrado");
            }

            }while(!email.equals("-1"));
    }
    private void cadastraEntregas(){
        int codigo = 0;

         do {
             codigo = entrada.nextInt();
            if (codigo != -1) {
                double valor = entrada.nextDouble();
                String limpa = entrada.nextLine();
                String descricao = entrada.nextLine();
                String email = entrada.nextLine();

                   if (clientela.pesquisaCliente(email) == null) {
                       System.out.println("Não foi possivel fazer o Cadastro");
                   }
                   if (clientela.pesquisaCliente(email) != null) {

                       Cliente cliente = clientela.pesquisaCliente(email);
                       Entrega e = new Entrega(codigo, valor, descricao, cliente);
                       if (cadastroentregas.cadastraEntregas(e)) {
                           System.out.println("Entrega cadastrada com sucesso !!!");

                           System.out.println(" ________________________________________________");
                           System.out.println(" O codigo da entrega é : "+e.getCodigo() );
                           System.out.println(" O preço da descriçao :  "+e.getValor() );
                           System.out.println(" O Descriçao é : " +e.getDescricao());
                           System.out.println("O email do cliente cadastrado nessa entrega é:"+e.getCliente().getEmail());

                       } else
                           System.out.println("Erro:   Entrega não cadastrada");

                   }
                 }

               } while (codigo != -1);

    }


     public void quantClientes(){
         ArrayList<Cliente> clientes = clientela.devolveClientes();
         int quantidade = clientes.size();
         System.out.println(" ________________________________________________");
         System.out.println("A quanridade de clientes cadastrados é : "+quantidade);

     }

    public void quantEntregas(){
        ArrayList<Entrega> entrega = cadastroentregas.devolveEntregas();
        int quantidade = entrega.size();
        System.out.println(" ________________________________________________");
        System.out.println("A quanridade de entregas Cadastradas é : "+quantidade);

    }


    public void mostrardadosCliente() {
        String limpa = entrada.nextLine();
        String email = entrada.nextLine();
        Cliente cliente = clientela.pesquisaCliente(email);

        if (cliente == null) {
            System.out.println("Cliente inexistente");
        }
        else
            System.out.println(" ________________________________________________");
            System.out.println("O Email do cliente é :" + cliente.getEmail());
            System.out.println("O Nome do cliente é :" + cliente.getNome());
            System.out.println("O Endereço  do cliente é :" + cliente.getEndereco());

    }


    public void mostrardadosEntrega() {
        int codigo = entrada.nextInt();
        Entrega entrega = cadastroentregas.pesquisaEntrega(codigo);

        if (entrega != null) {
            System.out.println(" ________________________________________________");
            System.out.println(" O codigo da entrega é:" +entrega.getCodigo());
            System.out.println(" O preço da entrega é:" + entrega.getValor());
            System.out.println(" A descriçao dessa entrega é:" + entrega.getDescricao());
            System.out.println(" O email do cliente desta entrega é:" +entrega.getCliente().getEmail());
            System.out.println(" O nome do cliente é :" + entrega.getCliente().getNome());
            System.out.println(" O endereço desta entrega é:" +entrega.getCliente().getEndereco());
        }
        else{

        System.out.println("Cliente inexistente");

        }
    }

    public void dadosentregaCliente() {
        String limpa = entrada.nextLine();
        String email = entrada.nextLine();
        ArrayList<Entrega> entrega = cadastroentregas.pesquisaEntrega(email);
        if (entrega != null) {
            for (Entrega e : entrega) {
                if (e.getCliente().getEmail().equals(email)) {
                    System.out.println(" ________________________________________________");
                    System.out.println(" O email do cliente desta entrega é:" + e.getCliente().getEmail());
                    System.out.println(" O codigo da entrega é:" + e.getCodigo());
                    System.out.println(" O preço da entrega é:" + e.getValor());
                    System.out.println(" A descriçao dessa entrega é:" + e.getDescricao());
                    System.out.println("\n");
                }
            }

        } else

             System.out.println("O Cliente nao possui entregas ");
    }

    public void maiorValor(){
        ArrayList<Entrega> entrega = cadastroentregas.devolveEntregas();
         Entrega maiorvalor = null;

         if (entrega == null){
             System.out.println("Cliente Inexistente");
         }
         else

         for (Entrega e: entrega){
             if ( maiorvalor == null || e.getValor() > maiorvalor.getValor()){
                 maiorvalor = e;
             }
         }
        System.out.println(" ________________________________________________");
        System.out.println(" O codigo da entrega é:" + maiorvalor.getCodigo() );
        System.out.println(" O preço da entrega é:" + maiorvalor.getValor());
        System.out.println(" A descriçao dessa entrega é:" + maiorvalor.getDescricao());
    }


    public void enderecoEntrega(){

         int codigo = entrada.nextInt();

          Entrega entrega = cadastroentregas.pesquisaEntrega(codigo);

          if (entrega == null){
              System.out.println("Entrega Inexistente");
          } else
              System.out.println(" ________________________________________________");
              System.out.println(" O codigo da entrega é:" +entrega.getCodigo());
              System.out.println(" O preço da entrega é:" + entrega.getValor());
              System.out.println(" A descriçao dessa entrega é :" + entrega.getDescricao());
              System.out.println(" O endereço dessa entrega é :" + entrega.getCliente().getEndereco());

    }



    public void somatorioCliente() {

        String limpa = entrada.nextLine();
        String email = entrada.nextLine();
        Cliente cliente = clientela.pesquisaCliente(email);
        ArrayList<Entrega> entrega = cadastroentregas.pesquisaEntrega(email);
        double somatorio = 0.0;

        if (cliente == null) {
            System.out.println("Cliente Inexistente");
        }
        else if (entrega == null) {
            System.out.println("Entrega inexistente");
        }
        else {

            for (Entrega e : entrega) {
                somatorio+= e.getValor();

            }
            System.out.println(" ________________________________________________");
            System.out.println(" O email do cliente desta entrega é:" + cliente.getEmail());
            System.out.println(" O nome é:" + cliente.getNome());
            System.out.println(" O preço da entrega é:" + somatorio);

        }
    }


    private void restauraES() {
        System.setOut(saidaPadrao);
        entrada = new Scanner(System.in);
    }









   public void pontoExtra() {

      int opcao = 0;
        do {
            menu();
            System.out.print("Digite a opcao desejada: ");
              opcao = entrada.nextInt();
            entrada.nextLine();
            switch (opcao) {
                case 0:
                    break;
                case 1:
                    primeiraOpcao();
                    break;
                case 2:
                    segundaOpcao();
                    break;
                default:
                    System.out.println("Opcao invalida! Redigite!");
            }
        } while (opcao != 0);


        }

    private void menu() {
        System.out.println("=====================================");
        System.out.println("Menu de opcoes: ");
        System.out.println("[0] Sair do sistema");
        System.out.println("[1] Cadastrar um novo cliente e uma entrega correspondente");
        System.out.println("[2] Mostrar todos os clientes cadastrados suas entregas correspondentes.");
        System.out.println("=====================================");
    }

    public void primeiraOpcao(){
              System.out.println("Digite o nome do cliente que deseja cadastrar:");
              String nome = entrada.nextLine();
              System.out.println("Digite o email do cliente :");
              String email = entrada.nextLine();
              System.out.println("Digite o endereço do cliente:");
                String endereco = entrada.nextLine();

                Cliente c = new Cliente(nome, email, endereco);
                if (clientela.cadastraCliente(c)) {

                    System.out.println("Cliente cadastrado com sucesso");
                    System.out.println(" ________________________________________________");
                    System.out.println("Cadastre uma entrega para esse cliente");
                    System.out.println("Digite o codigo da Entrega:");
                    int codigo = entrada.nextInt();
                    System.out.println("Digite o valor da Entrega:");
                    double valor = entrada.nextDouble();
                    System.out.println("Digite a desciçao da Entrega");
                    String descricao = entrada.nextLine();

                    if (clientela.pesquisaCliente(email) == null) {
                        System.out.println("Não foi possivel fazer o Cadastro");
                    }
                    if (clientela.pesquisaCliente(email) != null) {

                        Cliente cliente = clientela.pesquisaCliente(email);
                        Entrega e = new Entrega(codigo, valor, descricao, cliente);
                        if (cadastroentregas.cadastraEntregas(e)) {
                            System.out.println("Entrega cadastrada com sucesso !!!");

                            System.out.println(" ________________________________________________");

                        } else
                            System.out.println("Erro:   Entrega não cadastrada");

                    }
                }

    }

    public void segundaOpcao() {
        ArrayList<Entrega> tudo = cadastroentregas.devolveEntregas();
        if (tudo != null) {
            for (Entrega e : tudo) {
                System.out.println(" ________________________________________________");
                System.out.println(" O nome do cliente é :" + e.getCliente().getNome());
                System.out.println(" O codigo da entrega é:" + e.getCodigo());
                System.out.println(" O preço da entrega é:" + e.getValor());
                System.out.println(" A descriçao dessa entrega é:" + e.getDescricao());
                System.out.println("\n");
            }
        } else

                System.out.println("NAO EXISTE CLIENTES !!!! ");
        }







    }


















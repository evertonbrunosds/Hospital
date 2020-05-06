package view;

/**
 * Classe responsável controlar os elementos visuais que surgem na tela.
 * @author Everton Bruno Silva dos Santos.
 */
public class Screen {
    
    /**
     * Construtor responsável por impedir o instanciamento da classe.
     */
    private Screen() {}
    
    /**
     * Método responsável limpar a tela.
     */
    public static void clear(){
        for(int i = 0; i < 30; i++) {
            System.out.println("\n");
        }
    }
    
    /**
     * Método responsável por exibir o menú principal na tela.
     */
    public static void showMenu(){
        clear();
        System.out.println("|-----------------------------------------------------|");
        System.out.println("|                   Menu Principal                    |");
        System.out.println("|-----------------------------------------------------|");
        System.out.println("|   1)  Inclusão de novo médico                       |");
        System.out.println("|   2)  Editar nome do médico                         |");
        System.out.println("|   3)  Encaminhamento de paciente                    |");
        System.out.println("|   4)  Listar fila de pacientes                      |");
        System.out.println("|   5)  Realizar atendimento                          |");
        System.out.println("|   6)  Listagem de exames                            |");
        System.out.println("|   7)  Listar atendimentos realizados por um médico  |");
        System.out.println("|   8)  Listar exames solicitados                     |");
        System.out.println("|   9)  Realizar exames                               |");
        System.out.println("|   10) Listar todos os médicos cadastrados           |");
        System.out.println("|   11) Sair                                          |");
        System.out.println("|-----------------------------------------------------|");
        System.out.println("");        
    }
    
    /**
     * Método responsável por exibir mensagens na tela.
     * @param msg Refere-se a mensagem a ser exibida.
     */
    public static void showMessage(final String msg) {
        Keyboard.inputStr(msg + "\nPressione [ENTER] para continuar...");
    }

    /**
     * Método responsável por exibir mensagens na tela com o auxílio de uma
     * estrutura de desvio coordenada através de um valor boleano.
     * @param msgTrue  Refere-se a mensagem que será exibida caso o valor seja verdadeiro.
     * @param msgFalse Refere-se a mensagem que será exibida caso o valor seja falso.
     * @param result   Refere-se ao valor boleano.
     */
    public static void showBooleanMessage(final String msgTrue, final String msgFalse, final boolean result) {
        showMenu();
        if(result){
            showMessage(msgTrue);            
        } else {
            showMessage(msgFalse);
        }
    }
    
    /**
     * Método responsável por exibir todos os elemetos de um vetor.
     * @param arrayString Refere-se ao vetor.
     */
    public static void showList(final String[] arrayString) {
        if (arrayString == null) {
            showMenu();
            showMessage("Não há informações a exibir...");
        } else {
            clear();
            for (final String string : arrayString) {
                System.out.println(string);
            }
            showMessage("");
        }
    }
    
}
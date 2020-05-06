package model;

/**
 * Classe responsável por comportar-se como um exame.
 * @author Everton Bruno Silva dos Santos.
 */
public class Exam {
    private final String name;

    /**
     * Construtor responsável por inicializar a classe bem como seus atributos.
     * @param name Refere-se ao nome do exame.
     */
    public Exam(final String name) {
        this.name = name;
    }

    /**
     * Método responsável por retornar o nome do exame.
     * @return Retorna o nome do exame.
     */
    public String getName() {
        return name;
    }
    
    
}

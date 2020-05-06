package model;

/**
 * Classe responsável por comportar-se como pessoa.
 * @author Everton Bruno Silva dos Santos.
 */
public abstract class Person {
    private String name;

    /**
     * Construtor responsável por inicializar a classe, bem como seus atributos.
     * @param nome Refere-se ao nome da pessoa.
     */
    public Person(final String nome) {
        this.name = nome;
    }

    /**
     * Método responsável por retornar o nome da pessoa.
     * @return Retorna o nome da pessoa.
     */
    public String getName() {
        return name;
    }

    /**
     * Método responsável por alterar o nome da pessoa.
     * @param name Refere-se ao novo nome da pessoa.
     */
    public void setName(final String name) {
        this.name = name;
    }
    
    
}

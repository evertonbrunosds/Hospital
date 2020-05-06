package util;

/**
 * Interface responsável apresentar as assinaturas dos métodos de um iterador.
 * @param <E> Refere-se ao tipo de dados com o qual essa estrutura trabalhar.
 * @author Everton Bruno Silva dos Santos.
 */
public interface IIterator<E> {
    
    /**
     * Método responsável por retornar todos os elementos um por vez até que não se tenha mais elementos no iterador.
     * @return Retorna os elementos armazenados.
     */
    public E getNext();
    
    /**
     * Método responsável por retornar dado boleano que indica se ainda existem elemetos na lista.
     * @return Retorna dado boleano.
     */
    public boolean hasNext();
    
    
}

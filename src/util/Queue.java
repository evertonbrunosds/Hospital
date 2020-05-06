package util;

/**
 * Classe responsável por comportar-se como uma fila.
 * @param <K> Refere-se ao tipo de dados que será usado como chave de acesso aos elementos.
 * @param <E> Refere-se ao tipo de dados que será usado como elemento.
 * @author Everton Bruno Silva dos Santos.
 */
public class Queue<K, E> extends NodeList<K, E> {
    
    /**
     * Método responsável por remover os elementos contidos no início da estrutura.
     * @return Retorna o primeiro elemento da estrutura.
     */
    public E remove() {
        if (isEmpty()) {
            return null;
        } else {
            final E element = root.getElement(); //armazena o elemento do nó raiz
            root = root.getNextNode(); //altera a referência do nó raiz para a referência do próximo nó.
            return element; //retorna elemento.
        }
    }

}

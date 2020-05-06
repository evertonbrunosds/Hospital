package util;

/**
 * Classe responsável por comportar-se como um nó capaz de compor a lista de nós.
 * @param <K> Refere-se ao tipo de chave de identificação do nó.
 * @param <E>  Refere-se ao tipo de elemento do nó.
 * @author Everton Bruno Silva dos Santos.
 */
public class Node<K,E> {
    private final K key;
    private final E element;
    private final boolean priority;
    private Node<K,E> nextNode;

    /**
     * Construtor responsável por inicializar a classe, bem como seus principais atributos.
     * @param key Refere-se a chave de identificação do nó.
     * @param element Refere-se ao elemento contido nó.
     */
    public Node(final K key, final E element) {
        this.key = key;
        this.element = element;
        this.priority = false;
        this.nextNode = null;
        
    }
    
    /**
     * Construtor responsável por inicializar a classe, bem como seus principais atributos.
     * @param key Refere-se a chave de identificação do nó.
     * @param element Refere-se ao elemento contido no nó.
     * @param priority Refere-se a situação de prioridade do nó em relação dos demais.
     */
    public Node(final K key, final E element, final boolean priority) {
        this.key = key;
        this.element = element;
        this.priority = priority;
        this.nextNode = null;
    }

    /**
     * Método responsável por retornar a chave de identificação do nó.
     * @return Retorna chave de identificação.
     */
    public K getKey() {
        return key;
    }

    /**
     * Método responsável por retornar o elemento contido no nó.
     * @return Retorna elemento.
     */
    public E getElement() {
        return element;
    }
    
    /**
     * Método responsável por retornar a situação de prioridade do nó em relação aos demais.
     * @return 
     */
    public boolean isPriority() {
        return priority;
    }

    /**
     * Método responsável por retornar o próximo nó.
     * @return 
     */
    public Node<K,E> getNextNode() {
        return nextNode;
    }

    /**
     * Método responsável por alterar a referência do próximo nó.
     * @param nextNode 
     */
    public void setNextNode(final Node<K,E> nextNode) {
        this.nextNode = nextNode;
    }
    

}

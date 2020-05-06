package util;

/**
 * Classe abstrata responsável por ser base de estruturas que armazenam dados.
 * @param <K> Refere-se ao tipo de chave armazenado na estrutura e em seus nós.
 * @param <E>  Refere-se ao tipo de elemento armazenado na estrutura e em seus nós.
 * @author Everton Bruno Silva dos Santos.
 */
public abstract class NodeList<K, E> {
    protected Node<K, E> root;

    /**
     * Construtor responsável por inicializar a classe, bem como eus atributos.
     */
    public NodeList() {
        this.root = null;
    }

    /**
     * Método responsável por retornar dado boleano que indica se a estrutura está vazia.
     * @return Retorna dado boleano.
     */
    public boolean isEmpty() {
        return this.root == null;
    }

    /**
     * Método responsável por ordenar a iserção de novos elementos na estrutura.
     * @param key Refere-se a chave de acesso ao elemento.
     * @param element Refere-se ao elemento.
     * @return Retorna resultado da operação.
     */
    public boolean insert(final K key, final E element) {
        if(!this.isContained(key)) { //verifica se a chave não está está em uso.
            final Node<K,E> newNode = new Node<>(key, element); //cria novo nó com os dados passados por parâmetro.
            this.root = insert(newNode, root); //ordena a inserção no método responsável por efetuar inserções.
            return true; //retorna true, indicando sucesso na operação.
        } else {
            return false; //retorna false, indicando falha na operação.
        }
    }

    /**
     * Método responsável por ordenar a iserção de novos elementos prioritários na estrutura.
     * @param key Refere-se a chave de acesso ao elemento.
     * @param element Refere-se ao elemento possívelmente prioritário.
     * @param priority Refere-se a prioritáridade do elemento.
     * @return Retorna resultado da operação.
     */
    public boolean insert(final K key, final E element, final boolean priority) {
        if(!this.isContained(key)) { //verifica se a chave não está está em uso.
            final Node<K,E> newNode = new Node<>(key, element, priority); //cria novo nó com os dados passados por parâmetro.
            this.root = insert(newNode, root); //ordena a inserção no método responsável por efetuar inserções.
            return true; //retorna true, indicando sucesso na operação.
        } else {
            return false; //retorna false, indicando falha na operação.
        }
    }

    /**
     * Método responsável por realizar as inserções de novos nós na estrutura.
     * @param newNode Refere-se ao novo nó.
     * @param currentNode Refere-se ao nó atual da lista.
     * @return Retorna o novo nó raiz da lista.
     */
    private Node<K, E> insert(final Node<K, E> newNode, final Node<K, E> currentNode) {
        if (currentNode == null) { //Verifica se chegamos o fim da lista.
            return newNode; //retorna o novo nó.
        } else {
            if (newNode.isPriority()) { //verifica se o novo nó é prioritário.
                if (currentNode.isPriority()) { //verifica se o nó atual é prioritário.
                    currentNode.setNextNode(insert(newNode, currentNode.getNextNode())); //busca a posição em que o novo nó será inserido em outra recursão.
                } else {
                    newNode.setNextNode(currentNode); //o novo nó prioritário irá apontar ao primeiro não prioritário.
                    return newNode; //retorna pedaço da lista com novo nó inserido.
                }
            } else {
                currentNode.setNextNode(insert(newNode, currentNode.getNextNode())); //busca a posição em que o novo nó será inserido.
            }
        }
        return currentNode; //retorna ao fim de todas as recurções o nó raiz da lista reconstruida.
    }
    
    /**
     * Método responsável por retornar o primeiro elemento da lista.
     * @return Retorna o primeiro elemento.
     */
    public E getElementTheFirst() {
        if(isEmpty()) {
            return null;
        } else {
            return root.getElement();
        }
    }
    
    /**
     * Método responsável por retornar dado boleano que indica se na estrutura determinado elemento está contido.
     * @param key Refere-se a chave de acesso ao dado.
     * @return Retorna dado boleano.
     */
    public boolean isContained(final K key) {
        return null != search(key);
    }

    /**
     * Método responsável por realizar buscas.
     * @param key Refere-se a chave do elemento buscado.
     * @return Retorna elemento encontrado.
     */
    public E search(final K key) {
        final Node<K, E> node = search(key, root); //armazena nó que contém o dado buscado
        if (node == null) {
            return null;
        } else {
            return node.getElement();
        }
    }

    /**
     * Método responsável por realizar buscas na estrutura.
     * @param key Refere-se a chave de busca do nó que contém o elemento desejado.
     * @param currentNode Refere-se ao nó atual da busca recursiva.
     * @return Retorna nó que contém o elemento buscado.
     */
    private Node<K, E> search(final K key, final Node<K, E> currentNode) {
        if (currentNode == null) { //verifica se atingiu o fim da estrutura.
            return null; //retorna nulo afim de indicar que o elemento não foi encontrado.
        } else {
            if (currentNode.getKey().equals(key)) { //verifica se o elemento foi encontrado.
                return currentNode; //retorna o nó onde há o elemento afim de indicar que o elemento foi encontrado.
            } else {
                return search(key, currentNode.getNextNode()); //permanece nas iterações com o próximo nó.
            }
        }
    }

    /**
     * Método responsável por retornar a quantidade de elementos que estão na estrutura.
     * @return Retorna quantidade de elementos na estrutura.
     */
    public int size() {
        return size(root); //repassa a busca ao método auxiliar recursivo.
    }

    /**
     * Método responsável por contar a quantidade de elemetos que estão na estrutura.
     * @param currentNode Refere-se ao nó atual da contagem.
     * @return Retorna quantidade de elementos.
     */
    private int size(final Node<K, E> currentNode) {
        if (currentNode == null) { //verifica se atingiu o fim da lista.
            return 0;
        } else {
            return 1 + size(currentNode.getNextNode()); //permanece nas recursões.
        }
    }

    /**
     * Método responsável por retornar uma estrutura capaz de iterar entre os elementos da estrutura.
     * @return Retorna iterador.
     */
    public Iterator<E> getIterator() {
        return new Iterator<>(root);
    }

    /**
     * Classe responsável por iterar entre os elementos da estrutura.
     * @param <E> Refere-se ao tipo de dados com o qual essa estrutura trabalha.
     * @author Silas Silva da Costa - 19111762.
     * @author Everton Bruno Silva dos Santos - 19111746.
     * @author Lucas Gabriel da Silva Lima Reis - 19111773
     */
    private class Iterator<E> implements IIterator<E> {
        private Node<K, E> root;

        /**
         * Construtor responsável por inicializar a classe, bem como seus atributos.
         * @param root Refere-se ao nó raiz.
         */
        private Iterator(final Node<K, E> root) {
            this.root = root;
        }

        /**
         * Método responsável por retornar todos os elementos um por vez até que não se tenha mais elementos no iterador.
         * @return Retorna os elementos armazenados.
         */
        @Override
        public E getNext() {
            Node<K, E> aux; //variável auxiliar que armazena as referência dos nós até que não se tenha mais nenhum.
            if(this.hasNext()) {
                aux = this.root; //variável auxiliar recebe a referência no nó raiz atual.
                root = this.root.getNextNode(); //o nó raiz recebe o próximo nó.
                return aux.getElement(); //retorna a referência contida no nó auxiliar.
            } else {
                return null;
            }
        }

       /**
        * Método responsável por retornar dado boleano que indica se ainda existem elemetos na lista.
        * @return Retorna dado boleano.
        */
        @Override
        public boolean hasNext() {
            return root != null;
        }
    }

    
}

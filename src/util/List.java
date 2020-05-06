package util;

/**
 * Classe responsável por comportar-se como uma lista.
 * @param <K> Refere-se ao tipo de dados que será usado como chave de acesso aos elementos.
 * @param <E> Refere-se ao tipo de dados que será usado como elemento.
 * @author Everton Bruno Silva dos Santos.
 */
public class List<K, E> extends NodeList<K, E> {
    
    /**
     * Método responsável por remover elementos localizados em qualquer ponto da estrutura.
     * @param key Refere-se a chave de acesso ao elementos.
     * @return Retorna elemento removido.
     */
    public E remove(final K key) {
        final Container container = new Container(); //Cria estrutura recipiente de elementos.
        this.root = remove(key, root, container); //repassa a ordem de remoção a um método recursivo de remoção.
        return container.getElement(); //retorna o elemento contido na estrutura recipiente.
    }

    /**
     * Método responsável por remover um dado elemento da estrutura.
     * @param key Refere-se a chave de acesso ao elemento que se deseja remover.
     * @param currentNode Refere-se ao nó da buscas.
     * @param container Refere-se a estrutura que irá armazenar o nó removido.
     * @return Retorna estrutura sem o nó removido.
     */
    private Node<K, E> remove(final K key, final Node<K, E> currentNode, final Container container) {
        if (currentNode == null) { //verifica se atingiu o fim da estrutura
            return null; //retorna nulo para indicar que o valor buscado não foi encontrado.
        } else {
            if (currentNode.getKey().equals(key)) { //verifica se o elemento buscado se encontra no nó atual por meio de sua chave.
                container.setElement(currentNode.getElement()); //armazena o elemento do nó atual na estrutura recipiente.
                return currentNode.getNextNode(); //retorna próximo elemeto de modo que o atual passe a não fazer mais parte da estrutura.
            } else {
                currentNode.setNextNode(remove(key, currentNode.getNextNode(), container)); //permaece nas recurssões.
            }
        }
        return currentNode; //retorna novo nó raiz.
    }
    
    /**
     * Classe responsável por comportar-se como recipiente de dados.
     * @author Silas Silva da Costa - 19111762.
     * @author Everton Bruno Silva dos Santos - 19111746.
     * @author Lucas Gabriel da Silva Lima Reis - 19111773.
     */
    private class Container {
        private E element;

        /**
         * Construtor responsável por inicializar a classe, bem como seus valores.
         */
        public Container() {
            element = null;
        }

        /**
         * Método responsável por retornar o elemento contido na estrutura.
         * @return Retorna elemento.
         */
        public E getElement() {
            return element;
        }

        /**
         * Método responsável por alterar o elemento da estrutura.
         * @param element Refere-se ao novo elemento da estrutura.
         */
        public void setElement(final E element) {
            this.element = element;
        }
    }
    
}

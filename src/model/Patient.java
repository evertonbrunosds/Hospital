package model;

import util.List;

/**
 * Classe responsável por comportar-se como paciente.
 * @author Everton Bruno Silva dos Santos.
 */
public class Patient extends Person {
    private final String register;
    private final boolean priority;
    private final List<String, Exam> examsList;

    /**
     * Construtor responsável por inicializar a classe, bem como seus atributos.
     * @param name Refere-se ao nome do paciente.
     * @param register Refere-se ao seu número de registro.
     * @param priority Refere-se a sua prioridade.
     */
    public Patient(final String name, final String register, final boolean priority) {
        super(name);
        this.register = register;
        this.priority = priority;
        this.examsList = new List<>();
    }

    /**
     * Método responsável por retornar o número de registro de um paciente.
     * @return Retorna número de registro do paciente.
     */
    public String getRegister() {
        return register;
    }

    /**
     * Método responsável por retornar o estado de prioridade de um paciente.
     * @return Retorna estado de prioridade.
     */
    public boolean isPriority() {
        return priority;
    }
    
    /**
     * Método responsável por adicionar novos exames a lista de exames do paciente.
     * @param exam Refere-se ao exame.
     */
    public void addExam(final Exam exam) {
        examsList.insert(exam.getName(), exam);
    }
    
    /**
     * Método responsávle por verificar se o paciente está aguardando por um dado exame.
     * @param name Refere-se ao nome do exame.
     * @return Retorna dado boleano que indica se o paciente está aguardando por um dado exame.
     */
    public boolean waitingForTheExam(final String name) {
        return this.examsList.isContained(name);
    }
    
    /**
     * Método responsável por retornar a lista de exames do paciente.
     * @return Retorna lista de exames.
     */
    protected List<String, Exam> getExamsList() {
        return this.examsList;
    }
    
}

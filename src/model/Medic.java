package model;

import util.List;
import util.Queue;
import util.IIterator;

/**
 * Classe responsável por comportar-se como médico.
 * @author Everton Bruno Silva dos Santos.
 */
public class Medic extends Person {
    private final String crm;
    private final Queue<String, Patient> queueOfWait; // fila de pacientes a espera
    private final List<String, Patient> listOfAssisted; // lista de pacientes atendidos

    /**
     * Construtor responsável por inicializar a classe bem como seus valores.
     * @param nome Refere-se ao nome do médico.
     * @param crm Refere-se ao CRM do médico.
     */
    public Medic(final String nome, final String crm) {
        super(nome);
        this.crm = crm;
        this.queueOfWait = new Queue<>();
        this.listOfAssisted = new List<>();
    }

    /**
     * Método responsável por retornar lista de pacientes atendidos pelo médico.
     * @return Retorna lista de atendidos.
     */
    protected List<String, Patient> getListOfAssisted() {
        return listOfAssisted;
    }

    /**
     * Método responsável por retornar fila de pacientes que estão aguardando pelo atendimento do médico.
     * @return Retorna fila de pacientes aguardando pelo atendimento.
     */
    protected Queue<String, Patient> getQueueOfWait() {
        return queueOfWait;
    }

    /**
     * Método responsável por retornar o CRM do médico.
     * @return Retorna CRM.
     */
    public String getCrm() {
        return crm;
    }

    /**
     * Método responsável por adicionar pacientes a fila de espera do médico.
     * @param patient Refere-se ao paciente que será adicionado.
     * @return Retorna dado boleano que indica resultado da operação.
     */
    public boolean addPatient(final Patient patient) {
        return this.queueOfWait.insert(patient.getRegister(), patient, patient.isPriority());
    }

    /**
     * Método responsável por realizar atendimentos.
     * @return Retorna o paciente atendido.
     */
    public Patient makeAtendence() {
        if (!queueOfWait.isEmpty()) {   //verifica se a fila de pacientes não está vazia.
            final Patient patient = queueOfWait.remove(); //pega o primeiro paciente da fila.
            this.listOfAssisted.insert(patient.getRegister(), patient); //manda o paciente para a lista de atendimento.
            return patient; //retorna o paciente atendido.
        } else {
            return null; //retorna nulo para indicar que a fila de pacientes está vazia.
        }
    }

    /**
     * Método responsável por retornar os nomes dos pacientes atendidos.
     * @return Retorna vetor de nomes.
     */
    public String[] getNamesOfTheListOfAssisted() {
        if(this.listOfAssisted.isEmpty()) {
            return null;
        } else {
            final IIterator<Patient> iIterator = this.listOfAssisted.getIterator();
            int i = 0;
            final String[] dataNames = new String[this.listOfAssisted.size()];
            Patient currentPatient;
            while(iIterator.hasNext()) {
                currentPatient = iIterator.getNext();
                dataNames[i] = currentPatient.getName();
                i++;
            }
            return dataNames;
        }
    }
    

}

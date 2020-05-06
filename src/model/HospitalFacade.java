package model;

import util.IIterator;
import util.List;

/**
 * Classe responsável por comportar-se como um hospital, implementando os métodos da interface IFacade.
 * @author Everton Bruno Silva dos Santos.
 */
public class HospitalFacade implements IFacade {
    private static IFacade instance;
    private final List<String, Medic> listOfMedics; //refere-se a lista de médicos do hospital.
    private final List<String, Patient> generalListOfExamsAwaiting;//refere-se a lista geral de atendidos.

    /**
     * Construtor responsável por inicializar a classe, bem como seus atributos.
     */
    private HospitalFacade() {
        this.listOfMedics = new List<>();
        this.generalListOfExamsAwaiting = new List<>();
    }
    
    /**
     * Método responsável por gerar uma instância do HospitalFacade.
     * @return Retorna instência do HospitalFacade.
     */
    public static IFacade getInstance() {
        if (instance == null) {
            instance = new HospitalFacade();
        }
        return instance;
    }
   
    @Override
    public boolean addMedic(final Medic medic) {
        return this.listOfMedics.insert(medic.getCrm(), medic);
    }

    /**
     * Método responsável por editar o nome de um médico por meio de seu CRM.
     * @param name Refere-se ao novo nome do médico.
     * @param crm Refere-se ao seu CRM.
     * @return Retorna o resultado da operação.
     */
    @Override
    public boolean editMedicName(final String name, final String crm) {
        final Medic medic = listOfMedics.search(crm); //busca e armazena a referência de um médico.
        if (medic != null) { //verifica se o médico foi encontrdo.
            medic.setName(name); //altera nome do médico.
            return true; //retorna true, indicando sucesso na operação.
        } else {
            return false; //retorna false, indicando falha na operação.
        }
    }

    /**
     * Método responsável por encaminhar um novo paciente ao hospital.
     * @param patient Refere-se ao novo paciente.
     * @return Retorna resultado da operação.
     */
    @Override
    public boolean addPatient(final Patient patient) {
        if (this.listOfMedics.isEmpty()) { //verifica de não há médicos no hospital.
            return false; //retorna false, indicando falha na operação.
        } else {
            Medic medicWithTheSmallerQueue = this.listOfMedics.getElementTheFirst(); //supõem que o 1º médico possue a menor fila.
            int sizeOfTheSmallerQueue = medicWithTheSmallerQueue.getQueueOfWait().size(); //armazena o tamanho de sua fila.
            final IIterator<Medic> iIterator = this.listOfMedics.getIterator(); //cria um iterador da lista de médicos.
            Medic currentMedic;
            while(iIterator.hasNext()) {
                currentMedic = iIterator.getNext();
                if(currentMedic.getQueueOfWait().isContained(patient.getRegister())) { //verifica se o paciente já não está na fila de espera de algum médico alguma fila.
                    return false; //Retorna false, indicando o resultado da operação.
                } else if(currentMedic.getQueueOfWait().size() < sizeOfTheSmallerQueue) { //verifica se o médico atual não possue uma fila menor que o suposto de menor fila.
                    sizeOfTheSmallerQueue = currentMedic.getQueueOfWait().size(); //altera a definição de menor fila.
                    medicWithTheSmallerQueue = currentMedic; //altera a ferefência de médico com menor fila.
                }
            }
            medicWithTheSmallerQueue.addPatient(patient); //adiciona novo paciente na fila do médico de menor fila.
            return true; //retorna true, indicando sucesso na operação.
        }
    }

    /**
     * Método responsável por pegar os registros dos pacientes da fila de espera de um dado médico.
     * @param crm Refere-se ao CRM do médico.
     * @return Retorna registros dos pacientes da fila de espera.
     */
    @Override
    public String[] getRegistersOfPatientsOfQueueOfWait(final String crm) {
        final Medic medic = this.listOfMedics.search(crm); //busca e armazena a referência de um médico.
        if (medic == null) { //verifica se o médico foi encontrado.
            return null; //retorna nulo, indicando falha.
        } else {
            final IIterator<Patient> iIterator = medic.getQueueOfWait().getIterator(); //cria iterador da fila de espera.
            final String[] dataRegisters = new String[medic.getQueueOfWait().size()]; //cria um vetor do tamanho da fila de espera.
            int i = 0; //inicia contador de índices do vetor.
            Patient currentPatient;
            while (iIterator.hasNext()) {
                currentPatient = iIterator.getNext();
                dataRegisters[i] = currentPatient.getRegister(); //armazena os nomes dos pacientes um por um.
                i++; //faz contagem dos índices do vetor que já foram usados.
            }
            return dataRegisters; //retorna os dados de registros de todos os pacientes da fila de espera.
        }
    }
    
    /**
     * Método responsável por pegar os nomes dos pacientes que aguardam por um determinado exame.
     * @param examName Refere-se ao nome do exame.
     * @return Retorna nomes dos pacientes que aguardam por um determinado exame.
     */
    @Override
    public String[] getNamePatientsOfTheListByExam(final String examName) {
        if (this.generalListOfExamsAwaiting.isEmpty()) { //verifica se a lista geral de atendidos está vazia.
            return null; //retorna nulo, indicando que não há ninguém na fila.
        } else {
            final List<String, Patient> list = new List<>(); //cria lista de pacientes vazia.
            IIterator<Patient> iIterator = this.generalListOfExamsAwaiting.getIterator(); //cria iterador da lista geral de atendidos.
            Patient currentPatient;
            while(iIterator.hasNext()) {
                currentPatient = iIterator.getNext();
                if (currentPatient.waitingForTheExam(examName)) { //verifica se o paciente da posição atual da lista está aguardando pelo exame.
                    list.insert(currentPatient.getRegister(), currentPatient); //adciona na nova lista o paciente que aguarda pelo exame.
                }
            }
            if (list.isEmpty()) { //verifica se a nova lista ainda está vazia.
                return null; //retorna nulo, indicando que não há ninguém aguardando pelo tal exame.
            } else {
                final String[] dataNames = new String[list.size()]; //cria vetor de nomes de pacientes do tamanho da lista de pacientes que guardam pelo tal exame.
                int i = 0; //cria contador de índices do vetor de nomes.
                iIterator = list.getIterator(); //altera a referência da variável de iteração.
                while(iIterator.hasNext()) {
                    currentPatient = iIterator.getNext();
                    dataNames[i] = currentPatient.getName(); //armazena os nomes de cada um dos pacientes que aguardam pelo exame.
                    i++; //altera o valor do contador de índices do vetor.
                }
                return dataNames; //retorna vetor de nomes dos pacientes que aguardam por dado exame.
            }
        }
    }

    /**
     * Método responsável por pegar os nomes de todos os exames que o paciente aguarda realizar.
     * @param register Refere-se ao registro do paciente.
     * @return Retorna vetor de nomes de exames que o paciente aguarda fazer.
     */
    @Override
    public String[] getExamListOfPatient(final String register) {
        final Patient patient = this.generalListOfExamsAwaiting.search(register); //busca pelo paciente na lista geral de atendidos.
        if (patient == null) { //verifica se o paciente foi encontrado.
            return null; //retorna nulo, indicando que o paciente não foi encontrado.
        } else {
            final IIterator<Exam> iIterator = patient.getExamsList().getIterator(); //cria iterador da lista de exames do paciente.
            final String[] dataNames = new String[patient.getExamsList().size()]; //cria vetor com o tamanho da lista de exames do paciente.
            int i = 0; //cria contador de índices do vetor de nomes de exames.
            Exam  currentExam;
            while(iIterator.hasNext()) {
                currentExam = iIterator.getNext();
                dataNames[i] = currentExam.getName(); //armazena cada um dos nomes de exames da lista.
                i++; //efetua contagem de índices do vetor.
            }
            return dataNames; //retorna vetor de nomes de exames.
        }
    }

    /**
     * Método responsável por pegar o nome e CRM de todos os médicos da lista.
     * @return Retorna nome e CRM de todos os médicos.
     */
    @Override
    public String[] getNameAndCRMOfAllMedics() {
        if (this.listOfMedics.isEmpty()) { //verifica se existem médicos no hospital.
            return null; //retorna nulo, indicando falha na operação.
        }
        final IIterator<Medic> iIterator = this.listOfMedics.getIterator(); //cria iterador da lista de médicos do hospital.
        final String[] dadaMedics = new String[this.listOfMedics.size()]; //cria vetor de dados dos médicos do tamanho da lista de médicos.
        int i = 0; //cria contador de índices do vetor.
        Medic currentMedic;
        while(iIterator.hasNext()) {
            currentMedic = iIterator.getNext();
            dadaMedics[i] = currentMedic.getCrm() + " - " + currentMedic.getName(); //armazena o nome de CRM do médico atual.
            i++; //efetua contagem de índices do vetor de dados dos médicos.
        }
        return dadaMedics; //retorna vetor de dados dos médicos.
    }

    /**
     * Método responsável por fazer com que um médico realiza um atendimento.
     * @param crm Refere-se ao CRM do médico.
     * @return Retorna o paciente atendido.
     */
    @Override
    public Patient makeAtendence(final String crm) {
        final Medic medic = this.listOfMedics.search(crm); //busca pelo médico que realizará o atendimento.
        if (medic != null) { //verifica se o médico foi encontrado.
            return medic.makeAtendence(); //retorna paciente atendido.
        } else {
            return null; //retorna nulo, indicando falha.
        }
    }

    /**
     * Método responsável por solicitar exames a um dado paciente.
     * @param patient Refere-se ao paciente.
     * @param exams Refere-se ao vetor de exames que serão solicitados.
     */
    @Override
    public void requestExams(final Patient patient, final String[] exams) {
        if(exams != null) {
            this.generalListOfExamsAwaiting.insert(patient.getRegister(), patient); //adiciona o paciente na lista geral de atendidos.
        }
        for (final String exam : exams) { //percorre vetor de exames
            patient.addExam(new Exam(exam)); //adiciona cada um dos exames.
        }
    }

    /**
     * Método responsável por pegar os nomes dos pacientes que foram atendidos por um médico.
     * @param crm Refere-se ao CRM do médico.
     * @return Retorna vetor contendo os nomes dos pacientes atendidos por um médico.
     */
    @Override
    public String[] getMakedAtendenceBy(final String crm) {
        final Medic medic = this.listOfMedics.search(crm); //busca por um médico.
        if(medic != null) { //verifica se o médico foi encontrado.
            return medic.getNamesOfTheListOfAssisted(); //retorna os nomes dos pacientes contidos na lista de atendidos.
        } else {
            return null; //retorna nulo, indicnado que o médico buscado não existe.
        }
    }

    /**
     * Método responsável por realizar exames solicitados.
     * @param name Refere-se ao nome do exame.
     * @return Retorna o paciente que realizou o exame.
     */
    @Override
    public Patient makeExam(final String name) {
        final IIterator<Patient> iIterator = this.generalListOfExamsAwaiting.getIterator(); // cria um iterador da lista geral de pacientes que aguardam pela realização de um dado exame.
        Patient currentPatient;
        while(iIterator.hasNext()) {
            currentPatient = iIterator.getNext();
            if(currentPatient.getExamsList().remove(name) != null) { //remove o exame od paciente que estiver na lista.
                if(currentPatient.getExamsList().isEmpty()) { //verifica se o paciente possue mais algum exame.
                    this.generalListOfExamsAwaiting.remove(currentPatient.getRegister()); //se não houver, remove o pciente da lista geral de exames.
                }
                return currentPatient; //retorna o paciente indicando que alguém aguardava pelo exame.
            }
        }
        return null; //retorna nulo, indicando que ninguém aguardava pelo exame.
    }
    
    
}

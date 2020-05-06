package model;

/**
 * Interface responsável pela modelagem dos métodos a serem utilizados.
 * @author Everton Bruno Silva dos Santos.
 */
public interface IFacade {
    
    /**
     * Método responsável por adicionar um médico no hospital.
     * @param medic Refere-se ao novo médico.
     * @return Retorna resultado da operação.
     */
    public boolean addMedic(final Medic medic);

    
    /**
     * Método responsável por editar o nome de um médico por meio de seu CRM.
     * @param name Refere-se ao novo nome do médico.
     * @param crm Refere-se ao seu CRM.
     * @return Retorna o resultado da operação.
     */
    public boolean editMedicName(final String name, final String crm);
        

    /**
     * Método responsável por encaminhar um novo paciente ao hospital.
     * @param patient Refere-se ao novo paciente.
     * @return Retorna resultado da operação.
     */
    public boolean addPatient(final Patient patient); 
    

    /**
     * Método responsável por pegar os registros dos pacientes da fila de espera de um dado médico.
     * @param crm Refere-se ao CRM do médico.
     * @return Retorna registros dos pacientes da fila de espera.
     */
    public String[] getRegistersOfPatientsOfQueueOfWait(final String crm); 
    
    
    /**
     * Método responsável por pegar os nomes dos pacientes que aguardam por um determinado exame.
     * @param examName Refere-se ao nome do exame.
     * @return Retorna nomes dos pacientes que aguardam por um determinado exame.
     */
    public String[] getNamePatientsOfTheListByExam(final String examName); 
    

    /**
     * Método responsável por pegar os nomes de todos os exames que o paciente aguarda realizar.
     * @param register Refere-se ao registro do paciente.
     * @return Retorna vetor de nomes de exames que o paciente aguarda fazer.
     */
    public String[] getExamListOfPatient(final String register);
    

    /**
     * Método responsável por pegar o nome e CRM de todos os médicos da lista.
     * @return Retorna nome e CRM de todos os médicos.
     */
    public String[] getNameAndCRMOfAllMedics(); 
    

    /**
     * Método responsável por fazer com que um médico realiza um atendimento.
     * @param crm Refere-se ao CRM do médico.
     * @return Retorna o paciente atendido.
     */
    public Patient makeAtendence(final String crm); 
    

    /**
     * Método responsável por solicitar exames a um dado paciente.
     * @param patient Refere-se ao paciente.
     * @param exams Refere-se ao vetor de exames que serão solicitados.
     */
    public void requestExams(final Patient patient, final String[] exams); 

    
    /**
     * Método responsável por pegar os nomes dos pacientes que foram atendidos por um médico.
     * @param crm Refere-se ao CRM do médico.
     * @return Retorna vetor contendo os nomes dos pacientes atendidos por um médico.
     */
    public String[] getMakedAtendenceBy(final String crm); 
    
    
    /**
     * Método responsável por realizar exames solicitados.
     * @param name Refere-se ao nome do exame.
     * @return Retorna o paciente que realizou o exame.
     */
    public Patient makeExam(String name);
    
}

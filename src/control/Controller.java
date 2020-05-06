package control;

import model.HospitalFacade;
import model.Medic;
import model.Patient;

/**
 * Classe responsável por ser o controlador da estrutura hospital.
 * @author Everton Bruno Silva dos Santos.
 */
public class Controller implements IController {
    private static IController instance;
    
    /**
     * Construtor responsável por inicializar a classe bem como seus atributos.
     */
    private Controller() {}
    
    /**
     * Método responsável por gerar uma instência do controlador.
     * @return Retorna instância do controlador.
     */
    public static IController getInstance() {
        if(instance == null) {
            instance = new Controller();
        }
        return instance;
    }
    
    /**
     * Método responsável por adicionar um médico no hospital.
     * @param name Refere-se ao nome do médico.
     * @param crm Refere-se ao CRM do médico.
     * @return Retorna resultado da operação.
     */
    @Override
    public boolean addMedic(final String name, final String crm) {
        return HospitalFacade.getInstance().addMedic(new Medic(name, crm));
    }

    /**
     * Método responsável por editar o nome de um médico por meio de seu CRM.
     * @param name Refere-se ao novo nome do médico.
     * @param crm  Refere-se ao CRM do médico.
     * @return Retorna resultado da operação.
     */
    @Override
    public boolean editMedicName(final String name, final String crm) {
        return HospitalFacade.getInstance().editMedicName(name, crm);
    }

    /**
     * Método responsável por pegar o nome e CRM de todos os médicos do hospital.
     * @return Retorna nome e CRM de todos os médicos.
     */
    @Override
    public String[] getNameAndCRMOfAllMedics() {
        return HospitalFacade.getInstance().getNameAndCRMOfAllMedics();
    }

    /**
     * Método responsável por pegar os registros dos pacientes da fila de espera de um dado médico.
     * @param crm Refere-se ao CRM do médico.
     * @return Retorna registros dos pacientes encontrados na fila de espera do médico.
     */
    @Override
    public String[] getRegistersOfPatientsOfQueueOfWait(final String crm) {
        return HospitalFacade.getInstance().getRegistersOfPatientsOfQueueOfWait(crm);
    }

    /**
     * Método responsável por encaminhar um novo paciente ao hospital.
     * @param name     Refere-se ao nome do pciente.
     * @param register Refere-se ao registro do paciente.
     * @param priority Refere-se a prioridade do paciente.
     * @return Retorna resultado da operação.
     */
    @Override
    public boolean addPatient(final String name, final String register, final boolean priority) {
        return HospitalFacade.getInstance().addPatient(new Patient(name, register, priority));
    }

    /**
     * Método responsável por fazer com que um médico realize o atendimento.
     * @param crm Refere-se ao CRM do médico.
     * @return Retorna o paciente atendido.
     */
    @Override
    public Patient makeAtendence(final String crm) {
        return HospitalFacade.getInstance().makeAtendence(crm);
    }

    /**
     * Método responsável por solicitar exames a um dado paciente.
     * @param patient Refere-se ao paciente.
     * @param exams   Refere-se aos exames.
     */
    @Override
    public void requestExams(final Patient patient, final String[] exams) {
        HospitalFacade.getInstance().requestExams(patient, exams);
    }

    /**
     * Método responsável por pegar os nomes dos pacientes que foram atendidos por um médico.
     * @param crm Refere-se ao CRM do médico.
     * @return Retorna os nomes dos pacientes.
     */
    @Override
    public String[] makedAtendenceBy(final String crm) {
        return HospitalFacade.getInstance().getMakedAtendenceBy(crm);
    }

    /**
     * Método responsável por pegar os nomes dos pacientes que aguardam por um determinado exame.
     * @param exam Refere-se ao nome dos exames.
     * @return Retorna os nomes dos pacientes.
     */
    @Override
    public String[] getNamePatientsOfTheListByExam(final String exam) {
        return HospitalFacade.getInstance().getNamePatientsOfTheListByExam(exam);
    }

    /**
     * Método responsável por pegar os nomes de todos os exames que o paciente aguarda realizar.
     * @param register Refere-se ao registro do paciente.
     * @return Retorna os nomes dos exames que aguarda realizar.
     */
    @Override
    public String[] getExamListOfPatient(final String register) {
        return HospitalFacade.getInstance().getExamListOfPatient(register);
    }

    /**
     * Método responsável por realizar exames solicitados.
     * @param name Refere-se ao nome do exame.
     * @return Retorna o paciente que realizou o exame.
     */
    @Override
    public Patient makeExam(final String name) {
        return HospitalFacade.getInstance().makeExam(name);
    }


}

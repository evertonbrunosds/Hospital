package view;

import control.Controller;
import model.Patient;

/**
 * Classe responsável por proporcionar a interação entre o utilizador e a aplicação.
 * @author Everton Bruno Silva dos Santos.
 */
public class Options {

    /**
     * Método responsável por inicializar a interface da aplicação.
     */
    public void start(){
        boolean booleanReturn;
        Patient patient;
        String[] arrayString;
        while(true){
            Screen.showMenu();
            switch (Keyboard.inputStr("Digite uma opção válida: ")){
                case "1":
                    booleanReturn = Controller.getInstance().addMedic(
                            Keyboard.inputStr("Insira o nome do novo médico: "),
                            Keyboard.inputInt("Insira o CRM do novo médico: ")+""
                    );
                    Screen.showBooleanMessage(
                            "Sucesso! Novo médico incluido...",
                            "Falha! Médico já incluso...",
                            booleanReturn
                    );
                    break;
                case "2":
                    booleanReturn = Controller.getInstance().editMedicName(
                        Keyboard.inputStr("Insira o novo nome do médico: "),
                        Keyboard.inputInt("Insira o CRM do médico: ")+""
                    );
                    Screen.showBooleanMessage(
                            "Sucesso! Nome editado...",
                            "Falha! Médico inexistente...",
                            booleanReturn
                    );
                    break;
                case "3":
                    booleanReturn = Controller.getInstance().addPatient(
                        Keyboard.inputStr("Insira o nome do paciente: "),
                        Keyboard.inputInt("Insira a matrícula do paciente: ")+"",
                        Keyboard.inputBoolean("O paciente é prioridade [S/N]? ")
                    );
                    Screen.showBooleanMessage(
                            "Sucesso! Paciente encaminhado...",
                            "Falha! Possíveis Razões:"+
                            "\n    1- O paciente já consta na fila de espera!"+
                            "\n    2- Não há médicos registrados no sistema!",
                            booleanReturn
                    );
                    break;
                case "4":
                    Screen.showList(
                        Controller.getInstance().getRegistersOfPatientsOfQueueOfWait(
                            Keyboard.inputInt("Insira o CRM do médico terá seus pacientes listados: ")+""
                        )
                    );
                    break;
                case "5":
                    patient = Controller.getInstance().makeAtendence(
                        Keyboard.inputStr("Insira o CRM do médico que realizará o atendimento: ")
                    );
                    if(patient == null) {
                        Screen.showMenu();
                        Screen.showMessage(
                            "Falha! Possíves Razões:"+
                            "\n    1- O médico não possui pacientes!"+
                            "\n    2- Não há médicos registrados no sistema!");
                    } else {
                        arrayString = new String[
                            Keyboard.inputInt("Quantos exames serão solicitados ao paciente "+patient.getName()+"? ")
                        ];
                        for(int i = 0; i < arrayString.length; i++) {
                            arrayString[i] = Keyboard.inputStr("Informe o "+(i+1)+"º exame: ");
                        }
                        if(arrayString != null) {
                            Controller.getInstance().requestExams(patient, arrayString);
                        }
                        Screen.showMenu();
                        Screen.showMessage("Sucesso! Atendimento realizado...");
                    }
                    patient = null;
                    break;
                case "6":
                    Screen.showList(
                        Controller.getInstance().getNamePatientsOfTheListByExam(
                            Keyboard.inputStr("Informe o tipo de exame: ")
                        )
                    );
                    break;
                case "7":
                    Screen.showList(
                        Controller.getInstance().makedAtendenceBy(
                            Keyboard.inputInt("Informe o CRM do médico: ")+""
                        )
                    );
                    break;
                case "8":
                    Screen.showList(
                        Controller.getInstance().getExamListOfPatient(
                            Keyboard.inputInt("Informe a matrícula do paciente: ")+""
                        )
                    );
                    break;
                case "9":
                    patient = Controller.getInstance().makeExam(
                        Keyboard.inputStr("Informe um tipo de exame: ")
                    );
                    if(patient != null) {
                        Screen.showMenu();
                        Screen.showMessage("Sucesso!" + patient.getName() + " acaba de realizar o exame...");
                    } else {
                        Screen.showMenu();
                        Screen.showMessage("Falha! Não há pacientes aguardando por este exame...");
                    }
                    break;
                case "10":
                    Screen.showList(Controller.getInstance().getNameAndCRMOfAllMedics());
                    break;
                case "11":
                    Screen.clear();
                    System.out.println("Aplicação terminada pelo usuário.");
                    System.exit(0);
                    break;
                default:
                    break;                    
            }            
        }
    }
}

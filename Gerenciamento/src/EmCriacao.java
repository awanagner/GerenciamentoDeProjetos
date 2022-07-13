package main;
public class EmCriacao implements Status {
    Projeto proj;

    public EmCriacao(Projeto proj) {
        this.proj = proj;
    }

    public void iniciarProjeto() {
        if (!Laboratorio.verificarParticipanteProj(proj.getTitulo())) {
            System.out.println("\nO projeto não pode ser iniciado.\nDeve existir pelo menos 1 professor alocado ao projeto.");
        } else if (Laboratorio.verificarSituacaoAlunos(proj.getTitulo())) {
            System.out.println("\nO projeto não pode ser iniciado.\nAlgum aluno participante já participa de 2 ou mais projetos em andamento.");
        } else {
            proj.setStatus(proj.getIniciado());
            System.out.println("\nProjeto iniciado com sucesso.\nStatus atual: Iniciado");
        }
    }

    public void concluirProjeto() {
        System.out.println("\nO projeto não pode ser concluído.\nO projeto ainda está em em criação.");
    }

    public String toString() {
        return "Em criação";
    }
}

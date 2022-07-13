package main;
import java.util.ArrayList;
import java.util.Collections;

public class Colaborador {
    private final String nome;
    private final String ocupacao;
    private final String email;
    private final ArrayList<Projeto> listaMeusProjetos = new ArrayList<>();
    private final ArrayList<Atividade> listaMinhasAtividades = new ArrayList<>();

    public Colaborador(String nome, String email, String ocupacao) {
        this.nome = nome;
        this.ocupacao = ocupacao;
        this.email = email;
    }

    public ArrayList<Projeto> getListaMeusProjetos() {
        return listaMeusProjetos;
    }

    public ArrayList<Atividade> getListaMinhasAtividades() {
        return listaMinhasAtividades;
    }

    public void adicionarProjeto(Projeto proj){
        listaMeusProjetos.add(proj);
    }

    public void adicionarAtividade(Atividade pub){
        listaMinhasAtividades.add(pub);
        Collections.sort(listaMinhasAtividades);
    }

    public String getNome() {
        return nome;
    }

    public String getOcupacao() {
        return ocupacao;
    }

    public StringBuilder imprimirAtividades() {
        StringBuilder Atividades = new StringBuilder();
        if (getListaMinhasAtividades().isEmpty()) {
            Atividades = new StringBuilder(" Este colaborador não tem nenhuma atividade.");
        } else {
            for(Atividade pub : listaMinhasAtividades){
                Atividades.append("\n  ").append(pub.getTitulo1()).append("(").append(pub.getDataInicio1()).append("(").append(pub.getDataFim1()).append(")");
            }
        }
        return Atividades;
    }

    public StringBuilder imprimirProjeto() {
        StringBuilder projetos = new StringBuilder();
        if (getListaMeusProjetos().isEmpty()) {
            projetos = new StringBuilder(" Este colaborador não participa de nenhum projeto.");
        } else {
            for(Projeto proj : listaMeusProjetos){
                projetos.append("\n  ").append(proj.getTitulo()).append(" (").append(proj.getStatus()).append(")");
            }
        }
        return projetos;
    }

    public String imprimir(){
        return "- Nome: " + nome + "\n- Email: " + email + "\n- Ocupação: " + ocupacao
                + "\n- Projetos em que participa:" + imprimirProjeto() + "\n- Atividades:" + imprimirAtividades();
    }

    public String toString() {
        return imprimir();
    }
}

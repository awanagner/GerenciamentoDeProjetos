package main;
import java.util.ArrayList;

public class Participante extends Colaborador{
    private final ArrayList<Atividade> listaMinhasAtividades = new ArrayList<>();

    public Participante(String nome, String email, String ocupacao) {
        super(nome, email, ocupacao);
    }

    public ArrayList<Atividade> getListaMinhasAtividades() {
        return listaMinhasAtividades;
    }

    public void adicionarAtividade(Atividade ori) {
        listaMinhasAtividades.add(ori);
    }

    public StringBuilder imprimirAtividades() {
        StringBuilder Atividades = new StringBuilder();
        if (getListaMinhasAtividades().isEmpty()) {
            Atividades = new StringBuilder(" Este colaborador não possui Atividades.");
        } else {
            for(Atividade ori : listaMinhasAtividades) {
                if (ori.getTituloProj() != null) {
                    Atividades.append("\n  ").append(ori.getNomeColab()).append(" (").append(ori.getTituloProj()).append(")");
                } else {
                    Atividades.append("\n  ").append(ori.getNomeColab());
                }

            }
        }
        return Atividades;
    }

    @Override
    public String toString() {
        return super.toString() + "\n- Atividades:" + imprimirAtividades();
    }
}

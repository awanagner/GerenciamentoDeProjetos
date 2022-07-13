package main;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Projeto {
    private final String titulo;
    private final Date dataInicio;
    private final Date dataFim;
    private final String objetivo;
    private final String descricao;
    private final ArrayList<Colaborador> listaParticipantes = new ArrayList<>();
    private final ArrayList<Atividade> listaAtividadesAssociadas = new ArrayList<>();
    private final ArrayList<Atividade> listaAtividades2Associadas = new ArrayList<>();

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    Status EmCriacao;
    Status Iniciado;
    Status EmAndamento;
    Status Concluido;
    Status status;

    public Projeto(String titulo, Date dataInicio, Date dataFim,
                   String objetivo, String descricao) {
        this.titulo = titulo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.objetivo = objetivo;
        this.descricao = descricao;

        EmCriacao = new EmCriacao(this);
        Iniciado = new Iniciado(this);
        EmAndamento = new EmAndamento(this);
        Concluido = new Concluido(this);
        status = EmCriacao;
    }

    public ArrayList<Colaborador> getListaParticipantes() {
        return listaParticipantes;
    }

    public ArrayList<Atividade> getListaAtividadesAssociadas() {
        return listaAtividadesAssociadas;
    }

    public ArrayList<Atividade> getListaAtividades2Associadas() {
        return listaAtividades2Associadas;
    }

    public void adicionarParticipante(Colaborador colab){
        listaParticipantes.add(colab);
    }

    public void adicionarAtividade(Atividade pub) {
        listaAtividadesAssociadas.add(pub);
        Collections.sort(listaAtividadesAssociadas);
    }

    public String getTitulo() {
        return titulo;
    }

    public Date get() {
        return dataFim;
    }

    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    
    public Status getIniciado() {
    	return Iniciado;
    }

    public Status getEmAndamento() {
        return EmAndamento;
    }

    public Status getConcluido() {
        return Concluido;
    }

    public void iniciarProjeto() {
        status.iniciarProjeto();
    }

    public void concluirProjeto() {
        status.concluirProjeto();
    }

    public StringBuilder imprimirParticipantes() {
        StringBuilder participantes = new StringBuilder();
        if (getListaParticipantes().isEmpty()) {
            participantes = new StringBuilder(" Não há colaboradores alocados ao projeto.");
        } else {
            for(Colaborador colab : listaParticipantes) {
                participantes.append("\n  ").append(colab.getNome()).append(" (").append(colab.getOcupacao()).append(")");
            }
        }
        return participantes;
    }

    public StringBuilder imprimirAtividades() throws IOException {
        StringBuilder Atividades = new StringBuilder();
        if (getListaAtividadesAssociadas().isEmpty()) {
            Atividades = new StringBuilder(" Não há publicações associadas ao projeto.");
        } else {
            for(Atividade pub : listaAtividadesAssociadas) {
            	Atividades.append("\n  ").append(pub.getTitulo1()).append("(").append(pub.getDataInicio1()).append("(").append(((Appendable) pub.getDataFim1()).append("("));
            }
        }
        return Atividades;
    }

    public StringBuilder imprimirAtividades2() {
        StringBuilder Atividades2 = new StringBuilder();
        if (getListaAtividades2Associadas().isEmpty()) {
            Atividades2 = new StringBuilder(" Não há orientações associadas ao projeto.");
        } else {
            for(Atividade ori : listaAtividades2Associadas) {
                if (ori.getTituloProj() != null) {
                    Atividades2.append("\n  ").append(ori.getNomeProf()).append("/").append(ori.getNomeColab()).append(" (").append(ori.getTituloProj()).append(")");
                } else {
                    Atividades2.append("\n  ").append(ori.getNomeProf()).append("/").append(ori.getNomeColab());
                }

            }
        }
        return Atividades2;
    }

    public String imprimir() throws IOException {
        return "- Título do projeto: " + titulo + "\n- Data de início: " + sdf.format(dataInicio)
                + "\n- Data de término: " + sdf.format(dataFim) + "\n- Objetivo: " + objetivo + "\n- Descrição: " + descricao
                + "\n- Participantes:" + imprimirParticipantes() + "\n- Publicações:" + imprimirAtividades() + "\n- Orientações:" + imprimirAtividades2()
                + "\n- Status: " + status;
    }

    public String toString() {
        try {
			return imprimir();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return descricao;
    }


}


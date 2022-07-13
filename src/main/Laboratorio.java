package main;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Laboratorio {
    private static final ArrayList<Projeto> listaProjetos = new ArrayList<>();
    private static final ArrayList<Atividade> listaAtividades = new ArrayList<>();
    private static final ArrayList<Colaborador> listaColaboradores = new ArrayList<>();

    public static ArrayList<Projeto> getListaProjetos() {
        return listaProjetos;
    }
    
      public static ArrayList<Atividade> getListaAtividades() {
        return listaAtividades;
    }


    public static ArrayList<Colaborador> getListaColaboradores() {
        return listaColaboradores;
    }

    public static void adicionarProjeto(Projeto proj) {
        listaProjetos.add(proj);
    }

    public static void adicionarAtividade(Atividade pub) {
        listaAtividades.add(pub);
        Collections.sort(listaAtividades);
    }

    public static void adicionarAtividade(String titulo1, Date dataInicio1, Date dataFim1, String nomeColab,
			String nomeColab2, String tituloProj) {
        Atividade pub = new Atividade(titulo1, dataInicio1, dataFim1);
		listaAtividades.add(pub);
        for(Colaborador colab : listaColaboradores) {
            String nomeProf = null;
			if (colab.getNome().equalsIgnoreCase(nomeProf)) {
                ((Participante) colab).adicionarAtividade(pub);
                break;
            }
        }
        for (Projeto proj : listaProjetos) {
            if (proj.getTitulo().equalsIgnoreCase(tituloProj)) {
                proj.adicionarAtividade(pub);
                break;
            }
        }

    }

    public static void adicionarColaborador(Colaborador colab) {
        listaColaboradores.add(colab);
    }

    public static int qtdProjetos(String status) {
        int qtd = 0;
        for(Projeto proj : listaProjetos) {
            if (proj.status.toString().equals(status)) {
                qtd++;
            }
        }
        return qtd;
    }

    public static boolean verificarSituacaoAlunos(String titulo) {     //Verifica se cada aluno participante do projeto já participa de 2 ou mais projetos em andamento
        for (Projeto proj : listaProjetos) {
            if (proj.getTitulo().equalsIgnoreCase(titulo)) {
                for(Colaborador colab : proj.getListaParticipantes()){
                    if (colab.getOcupacao().contains("Aluno")) {
                        int qtd = 0;
                        for(Projeto proj2 : colab.getListaMeusProjetos()){
                            if (proj2.getStatus().equals(proj2.EmAndamento)) {
                                qtd += 1;
                            }
                        }
                        if (qtd >= 2) {
                            return true;
                        }
                    }
                }
                break;
            }
        }
        return false;
    }

    public static boolean verificarParticipanteProj(String titulo) {     //Verifica se existe pelo menos 1 professor alocado ao projeto
        for (Projeto proj : listaProjetos) {
            if (proj.getTitulo().equalsIgnoreCase(titulo)) {
                for(Colaborador colab : proj.getListaParticipantes()){
                    if(colab.getOcupacao().equals("Participante")){
                        return true;
                    }
                }
                break;
            }
        }
        return false;
    }

    public static boolean verificarStatusProj(String titulo, String status) {
        for(Projeto proj : listaProjetos) {
            if (proj.getTitulo().equalsIgnoreCase(titulo)) {
                return proj.status.toString().equals(status);
            }
        }
        return false;
    }

    public static boolean verificarPubProjeto(String titulo) {
        for(Projeto proj : listaProjetos) {
            if (proj.getTitulo().equalsIgnoreCase(titulo)) {
                if (proj.getListaAtividadesAssociadas().isEmpty()) {
                    return true;
                }
                break;
            }
        }
        return false;
    }

    public static Projeto pesquisarProjeto(String titulo) {
        for(Projeto proj : listaProjetos) {
            if (proj.getTitulo().equalsIgnoreCase(titulo)) {
                return proj;
            }
        }
        return null;
    }

    public static Atividade pesquisarAtividade(String titulo1) {
        for(Atividade pub : listaAtividades) {
            if (pub.getTitulo1().equalsIgnoreCase(titulo1)) {
                return pub;
            }
        }
        return null;
    }

    public static Colaborador pesquisarColaborador(String nome) {
        for(Colaborador colab : listaColaboradores) {
            if (colab.getNome().equalsIgnoreCase(nome)) {
                return colab;
            }
        }
        return null;
    }

    public static void associarColabProj(String titulo, String nome) {      //Cadastra o colaborador na lista de participantes do projeto
        Colaborador colab = pesquisarColaborador(nome);                      //e inclui o projeto na lista de projetos em que o colaborador participa
        Projeto proj = pesquisarProjeto(titulo);

        if (colab != null && proj != null) {
            colab.adicionarProjeto(proj);
            proj.adicionarParticipante(colab);
        }
    }

    public static void associarColabPub(String titulo1, String nome) { //Cadastra o colaborador na lista de participantes
        Colaborador colab = pesquisarColaborador(nome);
        Atividade pub = pesquisarAtividade(titulo1);

        if (colab != null && pub != null) {
            colab.adicionarAtividade(pub);
            pub.adicionarAutor(colab);
        }
    }

    public static void associarPubProj(String tituloProj, String titulo1) { //Inclui a publicação na lista de publicações do projeto
        Atividade pub = pesquisarAtividade(titulo1);
        Projeto proj = pesquisarProjeto(tituloProj);

        if (pub != null && proj != null) {
            pub.setProjetoAssociado(proj);
            proj.adicionarAtividade(pub);
        }
    }

    public static void iniciarProjeto(String titulo) {
        for(Projeto proj : listaProjetos) {
            if (proj.getTitulo().equalsIgnoreCase(titulo)) {
                proj.iniciarProjeto();
                break;
            }
        }
    }

    public static void concluirProjeto(String titulo) {
        for(Projeto proj : listaProjetos) {
            if (proj.getTitulo().equalsIgnoreCase(titulo)) {
                proj.concluirProjeto();
                break;
            }
        }
    }

}

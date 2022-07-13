package main;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Publicacao implements Comparable<Publicacao> {
    private final String titulo1;
    private final Date dataInicio1;
    private final Date dataFim1;
    private final ArrayList<Colaborador> listaAutores = new ArrayList<>();
    private Projeto projetoAssociado = null;
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Publicacao(String titulo1, Date dataInicio1, Date dataFim1) {
        this.titulo1 = titulo1;
        this.dataInicio1 = dataInicio1;
        this.dataFim1 = dataFim1;
    }

	public ArrayList<Colaborador> getListaAutores() {
        return listaAutores;
    }

    public void adicionarAutor(Colaborador colab){
        this.listaAutores.add(colab);
    }

    public String getTitulo1() {
        return titulo1;
    }

    public Date getDataInicio1() {
        return dataInicio1;
    }
    
    public Date getFim1() {
        return dataFim1;
    }

    public void setProjetoAssociado(Projeto projetoAssociado) {
        this.projetoAssociado = projetoAssociado;
    }

   public StringBuilder imprimirAutores() {
        StringBuilder autores = new StringBuilder();
        if (getListaAutores().isEmpty()) {
            autores = new StringBuilder(" Não há responsáveis associados à publicação.");
        } else {
            for(Colaborador colab : listaAutores){
                autores.append("\n  ").append(colab.getNome()).append(" (").append(colab.getOcupacao()).append(")");
            }
        }
        return autores;
    }

    public String imprimirProjetoAssociado() {
        String projeto;
        if (projetoAssociado == null) {
            projeto = "Não há projeto associado à publicação.";
        } else {
            projeto = projetoAssociado.getTitulo();
        }
        return projeto;
    }

    public String imprimir() {
        return "- Título da publicação: " + titulo1 + "\n- Data de início: " + dataInicio1 
        		+ "\n Data de fim: " + dataFim1 + "\n- Autores: " + imprimirAutores()
                + "\n- Projeto associado: " + imprimirProjetoAssociado();
    }

    public String toString() {
        return imprimir();
    }


	@Override
	public int compareTo(Publicacao o) {
		// TODO Auto-generated method stub
		return 0;
	}
}

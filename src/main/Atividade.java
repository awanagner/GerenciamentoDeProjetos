package main;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Atividade implements Comparable<Atividade> {
    private final String titulo1;
    private final Date dataInicio1;
    private final Date dataFim1;
    private final ArrayList<Colaborador> listaAutores = new ArrayList<>();
    private Projeto projetoAssociado = null;
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
  

    public Atividade(String titulo1, Date dataInicio1, Date dataFim1) {
        this.titulo1 = titulo1;
        this.dataInicio1 = dataInicio1;
        this.dataFim1 = dataFim1;
		this.tituloProj = "";
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
    
    public Date getDataFim1() {
        return dataFim1;
    }

    public void setProjetoAssociado(Projeto projetoAssociado) {
        this.projetoAssociado = projetoAssociado;
    }

   public StringBuilder imprimirAutores() {
        StringBuilder autores = new StringBuilder();
        if (getListaAutores().isEmpty()) {
            autores = new StringBuilder(" Não há responsáveis associados à atividade.");
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
            projeto = "Não há projeto associado à atividade.";
        } else {
            projeto = projetoAssociado.getTitulo();
        }
        return projeto;
    }

    public String imprimir() {
        return "- Título da atividade: " + titulo1 + "\n- Data de início: " + dataInicio1 
        		+ "\n Data de fim: " + dataFim1 + "\n- Responsável e participantes: " + imprimirAutores()
                + "\n- Projeto associado: " + imprimirProjetoAssociado();
    }

    public String toString() {
        return imprimir();
    }


	@Override
	public int compareTo(Atividade o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private String nomeProf = "";
    private String nomeColab = "";
    private final String tituloProj;

    public Atividade(String nomeProf, String nomeColab, String titulo1, Date dataInicio1, Date dataFim1, String tituloProj) {
        this.nomeProf = nomeProf;
        this.nomeColab = nomeColab;
		this.titulo1 = titulo1;
		this.dataInicio1 = dataInicio1;
		this.dataFim1 = dataFim1;
        this.tituloProj = tituloProj;
    }

    public String getNomeProf() {
        return nomeProf;
    }

    public String getNomeColab() {
        return nomeColab;
    }

    public String getTituloProj() {
        return tituloProj;
    }
	
}

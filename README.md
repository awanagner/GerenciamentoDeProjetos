
# GERENCIAMENTO DE PROJETOS 
   Padrões utilizados no refatoramento: * State:
                                          - Status.java, EmCriacao.java, Iniciado.java, EmAndamento.java, Concluido.java;
                                        * Extract Method:
                                          - Sistema.java: tentarNovamente();
                                          - Laboratorio.java: pesquisarProjeto(), pesquisarAtividade(), pesquisarColaborador().
                                        * Move Accumulation to Collecting Parameter:
                                          - Projeto.java: imprimirParticipantes(), imprimirAtividades(), imprimir(), toString();
                                          - Publicacao.java: imprimirAutores(), imprimirProjetoAssociado(), imprimir(), toString();
                                          - Colaborador.java: imprimirPublicaoes(), imprimir(), toString();
                                          - Participantes.java: imprimirAtividades(), toString().
 */

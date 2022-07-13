package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class Sistema {
	public static void main(String[] args) {
		try (Scanner entrada = new Scanner(System.in)) {
			try (Scanner entradaString = new Scanner(System.in)) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				int menu = 0, menu2 = 0;
				boolean concluido;
				String nomeColab, nomeColab2 = null;

				Projeto proj;
				String tituloProj = null;
				Date dataInicio = null;
				Date dataTermino = null;
				String objetivo;
				String descricao;
				
				Atividade pub;
				String titulo1;
				Date dataInicio1 = null;
				Date dataFim1 = null;
				do {
					concluido = false;
					do {
						try {
							exibirMenu();
							String aux = entrada.nextLine();
							menu = Integer.parseInt(aux);
							concluido = true;
						} catch (NumberFormatException e) {
							System.out.println("\nInválido! Digite o número referente à opção que deseja!");
						}
					} while (!concluido);

					switch (menu) {
					case 1:     //1 - Criando um projeto
						System.out.println("\n====== CRIAR PROJETO ======");

						concluido = false;
						do {
							System.out.print("Título do projeto:");
							tituloProj = entradaString.nextLine();
							if (!tituloProj.equals("")) {
								if (Laboratorio.pesquisarProjeto(tituloProj) == null) {     //Vendo se ha um projeto com mesmo nome
									concluido = true;
								} else {
									System.out.println("Um projeto igual já existe.");
								}
							} else {
								System.out.println("O título não pode ficar em branco.");
							}
						} while (!concluido);

						concluido = false;
						do {
							try {
								System.out.print("Data de início(DD/MM/AAAA): ");
								dataInicio = sdf.parse(entradaString.nextLine());
								concluido = true;
							} catch (ParseException e) {
								System.out.println("Use o formato DD/MM/AAAA!");
							}
						} while (!concluido);

						concluido = false;
						do {
							try {
								System.out.print("Data de finalização(DD/MM/AAAA): ");
								dataTermino = sdf.parse(entradaString.nextLine());
								concluido = true;
							} catch (ParseException e) {
								System.out.println("Use o formato DD/MM/AAAA.");
							}
						} while (!concluido);

						concluido = false;
						do {
							System.out.print("Digite o objetivo do projeto: ");
							objetivo = entradaString.nextLine();
							if (!objetivo.equals("")) {
								concluido = true;
							} else {
								System.out.println("O objetivo do projeto precisa ser inserido.");
							}
						} while (!concluido);

						concluido = false;
						do {
							System.out.print("Digite a descrição do projeto: ");
							descricao = entradaString.nextLine();
							if (!descricao.equals("")) {
								concluido = true;
							} else {
								System.out.println("A descrição do projeto precisa ser inserida.");
							}
						} while (!concluido);

						proj = new Projeto(tituloProj, dataInicio, dataTermino, objetivo, descricao);

						Laboratorio.adicionarProjeto(proj);

						System.out.println("\nProjeto adicionado com sucesso.");
						System.out.println(proj);
						System.out.println("\nConsulte o projeto através do menu principal para acessar opções adicionais.");
						break;


					case 2:     //2 - Consultar projeto
						if (!Laboratorio.getListaProjetos().isEmpty()){
							System.out.println("\nDigite o título do projeto a ser consultado:");
							tituloProj = entradaString.nextLine();

							if (Laboratorio.pesquisarProjeto(tituloProj) != null) {     //Verifica se o projeto existe
								System.out.println("\nProjeto encontrado.\n" + Laboratorio.pesquisarProjeto(tituloProj));      //Imprime os dados do projeto
							} else {
								System.out.println("\nProjeto não encontrado.");
								break;
							}
							do {
								concluido = false;
								do {
									try {
										exibirMenu2();
										String aux = entrada.nextLine();
										menu = Integer.parseInt(aux);
										concluido = true;
									} catch (NumberFormatException e) {
										System.out.println("\nDigite o número correspondente à opção..");
									}
								} while (!concluido);

								switch (menu){
								case 1:     //1 - Add Colaboradores
									if (Laboratorio.verificarStatusProj(tituloProj, "Em criação")) {
										do {
											System.out.println("\nDigite o nome do colaborador:");
											nomeColab = entradaString.nextLine();

											if (Laboratorio.pesquisarColaborador(nomeColab) != null) {
												Laboratorio.associarColabProj(tituloProj, nomeColab);
												System.out.println("\nColaborador adicionado!");
												break;
											} else {
												System.out.println("\nNão encontrado! Faça o cadastro do colaborador antes de adicioná-lo!\n");
												menu = tentarNovamente();
											}
										} while (menu != 2);
									}  else {
										System.out.println("\nColaboradores só podem ser adicionados em projetos em criação.");
									}
									break;

								case 2:     //2 - Iniciar projeto
									Laboratorio.iniciarProjeto(tituloProj);
									break;

								case 3:     //3 - Concluir projeto
									Laboratorio.concluirProjeto(tituloProj);
									break;

								case 4: //4 - Exibir dados do projeto
									System.out.println("\n" + Laboratorio.pesquisarProjeto(tituloProj));
									break;

								case 5:     //Voltar
									break;

								default:
									System.out.println("\nOpção inválida.");
								} 
							} while (menu != 5);  } 
						else {
							System.out.println("\nNão há projetos cadastrados.");
						}
						break;

					case 3:     //3 - Cadastrar colaborador
						do {
							concluido = false;
							do {
								try {
									exibirMenu3();
									String aux = entrada.nextLine();
									menu = Integer.parseInt(aux);
									concluido = true;
								} catch (NumberFormatException e) {
									System.out.println("\nDigite o número da opção desejada.");
								}
							} while (!concluido);

							switch (menu) {
							case 1:
								do {
									concluido = false;
									do {
										try {
											System.out.println("\nQue tipo de aluno?");
											System.out.println("1 - Aluno de graduação");
											System.out.println("2 - Aluno de mestrado");
											System.out.println("3 - Aluno de doutorado");
											System.out.println("4 - Cancelar");
											System.out.print("=====> Escolha uma opção: ");
											String aux = entrada.nextLine();
											menu = Integer.parseInt(aux);
											concluido = true;
										} catch (NumberFormatException e) {
											System.out.println("\nDigite o número da opção desejada.");
										}
									} while (!concluido);

									switch (menu) {
									case 1:
										cadastrarColaborador("Aluno de graduação");
										menu = 4;
										break;
									case 2:
										cadastrarColaborador("Aluno de mestrado");
										menu = 4;
										break;
									case 3:
										cadastrarColaborador("Aluno de doutorado");
										menu = 4;
										break;
									case 4:
										break;
									default:
										System.out.println("\nOpção inválida.\n");
									} }
								while (menu != 4);
								break;

							case 2:
								cadastrarColaborador("Participante");
								menu = 4;
								break;
							case 3:
								cadastrarColaborador("Pesquisador");
								menu = 4;
								break;
							case 4:
								cadastrarColaborador("Outro");
								menu = 4;
								break;
							case 5:
								break;
							default:
								System.out.println("\nOpção inválida.\n");
							}

						} while (menu != 4);
						break;

					case 4:     //4 - Consultar colaborador
						if (!Laboratorio.getListaColaboradores().isEmpty()){
							do {
								System.out.println("\nQual colaborador deseja consultar?");
								nomeColab = entradaString.nextLine();

								if (Laboratorio.pesquisarColaborador(nomeColab) != null) {     //Verifica se o colaborador existe
									System.out.println("\n" + Laboratorio.pesquisarColaborador(nomeColab));     //Imprime os dados do colaborador
									break;
								} else {
									System.out.println("\nColaborador não encontrado.");
									menu = tentarNovamente();
								}
							} while (menu == 1);
						} else {
							System.out.println("\nNão existem colaboradores cadastrados.");
						}
						break;

					case 5:     //5 - Cadastrar atividade
						do {
							System.out.println("\n====== Cadastrar Atividade ======");

							concluido = false;
							do {
								System.out.print("Digite o título da atividade: ");
								titulo1 = entradaString.nextLine();
								if (!titulo1.equals("")) {
									if (Laboratorio.pesquisarAtividade(titulo1) == null) {     //Verifica se já existe uma atividade com o título digitado
										concluido = true;
									} else {
										System.out.println("Já existe uma atividade cadastrada com esse título.");
									}
								} else {
									System.out.println("O título da atividade precisa ser inserido.");
								}
							} while (!concluido);

							concluido = false;

							do {
								try {
									System.out.print("Data de início(DD/MM/AAAA): ");
									dataInicio = sdf.parse(entradaString.nextLine());
									concluido = true;
								} catch (ParseException e) {
									System.out.println("Use o formato DD/MM/AAAA!");
								}
							} while (!concluido);

							concluido = false;
							do {
								try {
									System.out.print("Data de finalização(DD/MM/AAAA): ");
									dataTermino = sdf.parse(entradaString.nextLine());
									concluido = true;
								} catch (ParseException e) {
									System.out.println("Use o formato (DD/MM/AAAA):");
								}
							} while (!concluido);

							concluido = false;

							do {
								System.out.println("Digite o nome do responsável pela atividade:");
								nomeColab = entradaString.nextLine();
								if (Laboratorio.pesquisarColaborador(nomeColab2) == null) {
									Laboratorio.adicionarAtividade(titulo1, dataInicio1, dataFim1, nomeColab, nomeColab2, tituloProj);
									System.out.println("\nAtividade adicionada com sucesso.\n");
									break;
								}
								
							} while (menu != 2);
							break;
						} while(!concluido);
						break;

					case 6:     //6 - Consultar atividade
						if (!Laboratorio.getListaAtividades().isEmpty()){
							System.out.println("\nDigite o título da atividade a ser consultada:");
							titulo1 = entradaString.nextLine();
							if (Laboratorio.pesquisarAtividade(titulo1) != null) {     //Verifica se a atividade existe
								System.out.println(Laboratorio.pesquisarAtividade(titulo1));     //Imprime os dados da atividade
							} else {
								System.out.println("\nPublicação não encontrada.");
								break;
							}
							do {
								concluido = false;
								do {
									try {
										exibirMenu6();
										String aux = entrada.nextLine();
										menu = Integer.parseInt(aux);
										concluido = true;
									} catch (NumberFormatException e) {
										System.out.println("\nDigite o número correspondente à opção");
									}
								} while (!concluido);

								switch (menu) {
								case 1:     //1 - Incluir autores
									do {
										System.out.println("\nDigite o nome do colaborador:");
										nomeColab = entradaString.nextLine();

										if (Laboratorio.pesquisarColaborador(nomeColab) != null) {
											Laboratorio.associarColabPub(titulo1, nomeColab);
											System.out.println("\nOperação feita com sucesso.");
											do {
												concluido = false;
												do {
													try {
														System.out.println("1 - Adicionar outro participante:");
														System.out.println("2 - Finalizar");
														System.out.print("=====> Escolha uma opção: ");
														String aux = entrada.nextLine();
														menu2 = Integer.parseInt(aux);
														concluido = true;
													} catch (NumberFormatException e) {
														System.out.println("\nDigite o número correspondente à opção.\n");
													}
												} while (!concluido);

												if (menu2 != 1 & menu2 != 2) {
													System.out.println("\nOpção inválida.\n");
												}
											} while (menu2 != 1 & menu2 != 2);
										} else {
											System.out.println("\nColaborador não encontrado.\nCadastre o colaborador no sistema antes de associá-lo a uma atividade.\n");
											menu2 = tentarNovamente();
										}
									} while (menu2 != 2);
									break;

								case 2:     //2 - Associar a um projeto
									do {
										System.out.println("\nDigite o título do projeto:");
										tituloProj = entradaString.nextLine();

										if (Laboratorio.pesquisarProjeto(tituloProj) != null) {
											if (Laboratorio.verificarStatusProj(tituloProj, "Em andamento")) {
												Laboratorio.associarPubProj(tituloProj, titulo1);
												System.out.println("\nProjeto associado com sucesso.");
												break;
											} else {
												System.out.println("\nAtividades só podem ser associadas a projetos em andamento.");
											}
										} else {
											System.out.println("\nProjeto não encontrado.");
											menu = tentarNovamente();
										}
									}while (menu2 != 2);
									break;
								case 3:
									break;

								default:
									System.out.println("\nOpção inválida.\n");

								}while (menu2 != 2);
								break;
							}while (menu2 != 2);
							break;
						}
						else {
							System.out.println("\nNão existem atividades cadastradas.");
						}

					case 7:     //7 - Relatório do projeto
						System.out.println("\n====== Dados Gerais dos Projetos ======");
						System.out.println("Colaboradores: " + Laboratorio.getListaColaboradores().size());
						System.out.println("Projetos em criação: " + Laboratorio.qtdProjetos("Em criação"));
						System.out.println("Projetos em andamento: " + Laboratorio.qtdProjetos("Em andamento"));
						System.out.println("Projetos concluídos: " + Laboratorio.qtdProjetos("Concluído"));
						System.out.println("Total de projetos: " + Laboratorio.getListaProjetos().size());
						System.out.println("Atividades: " + Laboratorio.getListaAtividades().size());
						break;

					case 8:     //8 - Encerrar
						System.out.println("\nSistema encerrado.");
						break;

					default:
						System.out.println("\nOpção inválida.");	
					} 
				} while(menu != 8); 

			}
		}
	}


	static void cadastrarColaborador(String ocupacao) {
		try (Scanner entradaString = new Scanner(System.in)) {
			boolean concluido;
			Colaborador colab;
			Participante prof;
			String nomeColab;
			String email;

			System.out.println("\n====== Cadastrar Colaborador ======");

			concluido = false;
			do {
				System.out.print("Digite o nome: ");
				nomeColab = entradaString.nextLine();
				if (!nomeColab.equals("")) {
					if (Laboratorio.pesquisarColaborador(nomeColab) == null) { // Verifica se já existe um colaborador
						// com o nome digitado
						concluido = true;
					} else {
						System.out.println("Já existe um colaborador cadastrado com esse nome.");
					}
				} else {
					System.out.println("O nome do colaborador precisa ser inserido.");
				}
			} while (!concluido);

			concluido = false;
			do {
				System.out.print("Digite o email: ");
				email = entradaString.nextLine();
				if (!email.equals("")) {
					concluido = true;
				} else {
					System.out.println("O email do colaborador precisa ser inserido.");
				}
			} while (!concluido);

			System.out.println("\nColaborador cadastrado com sucesso.");

			if (ocupacao.equals("Participante")) {
				prof = new Participante(nomeColab, email, ocupacao);
				Laboratorio.adicionarColaborador(prof);
				System.out.println(prof);
			} else {
				colab = new Colaborador(nomeColab, email, ocupacao);
				Laboratorio.adicionarColaborador(colab);
				System.out.println(colab);
			}
		}
	}

	static int tentarNovamente() {
		try (Scanner entrada = new Scanner(System.in)) {
			boolean concluido;
			int menu = 0;
			do {
				concluido = false;
				do {
					try {
						System.out.println("1 - Tentar novamente");
						System.out.println("2 - Voltar");
						System.out.print("=====> Escolha uma opção: ");
						String aux = entrada.nextLine();
						menu = Integer.parseInt(aux);
						concluido = true;
					} catch (NumberFormatException e) {
						System.out.println("\nDigite o número correspondente à opção..\n");
					}
				} while (!concluido);

				if (menu != 1 & menu != 2) {
					System.out.println("\nOpção inválida.\n");
				}
			} while (menu != 1 & menu != 2);
			return menu;
		}
	}

	static void exibirMenu() { // MENU 1
		System.out.println("\n====== SISTEMA DE GERENCIAMENTO ======");
		System.out.println("\nSeja bem-vindo!\nEscolha a opcao que deseja:\n");
		System.out.println("1 - Criar projeto");
		System.out.println("2 - Consultar projeto");
		System.out.println("3 - Cadastrar colaborador");
		System.out.println("4 - Consultar colaborador");
		System.out.println("5 - Cadastrar atividade");
		System.out.println("6 - Consultar atividade");
		System.out.println("7 - Relatório do projeto");
		System.out.println("8 - Encerrar");
		System.out.print("=====> Escolha uma opção: ");
	}

	static void exibirMenu2() { // Menu de "Consultar projeto"
		System.out.println("\n====== OPÇÕES DO PROJETO ======");
		System.out.println("1 - Adicionar participante");
		System.out.println("2 - Iniciar projeto");
		System.out.println("3 - Concluir projeto");
		System.out.println("4 - Exibir dados do projeto");
		System.out.println("5 - Voltar");
		System.out.print("=====> Escolha uma opção: ");
	}

	static void exibirMenu3() { // Menu de "Cadastrar colaborador"
		System.out.println("\nQuem você deseja cadastrar?");
		System.out.println("1 - Cadastrar um aluno");
		System.out.println("2 - Cadastrar um professor");
		System.out.println("3 - Cadastrar um pesquisador");
		System.out.println("4 - Cadastrar outro tipo");
		System.out.println("5 - Cancelar");
		System.out.print("=====> Escolha uma opção: ");
	}

	static void exibirMenu6() { // Menu de "Consultar produção acadêmica"
		System.out.println("\n====== OPÇÕES DA ATIVIDADE ======");
		System.out.println("1 - Incluir participantes das atividades:");
		System.out.println("2 - Associar a um projeto:");
		System.out.println("3 - Voltar:");
		System.out.print("=====> Escolha uma opção: ");
	}
}

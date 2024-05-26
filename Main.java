import java.util.Scanner;

/**
 * Main
 */

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int option;
    String filePath = "";

    // Menu das opções
    do {
      System.out.println("Menu de Opções:");
      System.out.println("1- Carregar dados de um arquivo ED2");
      System.out.println("2- Buscar uma chave/escopo na árvore");
      System.out.println("3- Inserir uma chave/escopo da árvore");
      System.out.println("4- Alterar uma chave da árvore");
      System.out.println("5- Remover uma chave da árvore");
      System.out.println("6- Salvar dados para um arquivo");
      System.out.println("7- Exibir conteúdo e as propriedades da árvore BST");
      System.out.println("8- Exibir conteúdo e as propriedades da árvore AVL");
      System.out.println("9- Encerrar o programa");
      System.out.println("Escolha uma opção: ");
      option = scanner.nextInt();
      scanner.nextLine();

      switch (option) {
        case 1:
          System.out.println("Informe o caminho e nome do arquivo ED2: ");
          filePath = scanner.nextLine();
          if (FilesMethods.validateFile(filePath)) {
            System.out.println("Arquivo ED2 válido");
          } else {
            System.out.println("Formato de arquivo inválido");
            filePath = ""; //Reseta o filePath se for inválido
          }
          break;

        case 2:
          // Buscar uma chave/escopo na árvore
          System.out.println("Informe a chave/escopo na árvore que deseja buscar: ");


        case 3:
          // Inserir uma chave/escopo da árvore
          System.out.println("Informe o que deseja inserir na árvore: ");


        case 4:
          // Alterar uma chave da árvore
          System.out.println("Informe qual chave/escopo na árvore deseja alterar: ");


        case 5:
          // Remover uma chave da árvore
          System.out.println("Informe qual chave/escopo na árvore deseja remover: ");


        case 6:
          // Salvar dados para um arquivo
          if(!filePath.isEmpty()){
            System.out.println("Informe o nome do novo arquivo para salvamento dos dados: ");
            String newFilePath = scanner.nextLine();
            if(FilesMethods.writeFile(filePath,newFilePath)){
              System.out.println("Dados salvos no arquivo: " + newFilePath);
            }else{
              System.out.println("Erro ao salvar os dados");
            }
          }else{
            System.out.println("Nenhum arquivo ED2 aberto.");
          }
          

        case 7:
          // Exibir conteúdo e as propriedades da árvore BST
        case 8:
          // Exibir conteúdo e as propriedades da árvore AVL
        case 9:
          System.out.println("Encerrando o programa, obrigado por utilizar :) ");
          break;
        default:
          System.out.println("Opção inválida. Utilize as opções de 1 à 9.");
      }
    } while (option != 9);
    scanner.close();
  }
}

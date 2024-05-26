import java.util.Scanner;

/**
 * Main
 */

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int option;

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
          String filePath = scanner.nextLine();
          if (ValidateFile.validateFile(filePath)) {
            System.out.println("Arquivo ED2 válido");
            System.out.println("Informe o nome do novo arquivo para salvar os dados: ");
            String newFilePath = scanner.nextLine();
            if (writeFile(filePath, newFilePath)) {
              System.out.println("Dados salvos no arquivo: " + newFilePath);
            } else {
              System.out.println("Erro ao salvar os dados no novo arquivo");
            }
          } else {
            System.out.println("Formato de arquivo inválido");
          }
          break;

        case 2:
          // Buscar uma chave/escopo na árvore
        case 3:
          // Inserir uma chave/escopo da árvore
        case 4:
          // Alterar uma chave da árvore
        case 5:
          // Remover uma chave da árvore
        case 6:
          // Salvar dados para um arquivo
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

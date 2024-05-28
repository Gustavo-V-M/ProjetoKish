// Guilherme Florio Vieira 10409698
// Arthur Ryuiti Sato Furukawa 10409106
// Gabriel Fuentes de Freitas Yamashita 10408876
// Gustavo Vilela Mitraud 10400866

//referências utilizadas: https://www.w3schools.com/java/java_files.asp, https://www.geeksforgeeks.org/file-handling-in-java/, aulas e slides anteriores.

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.nio.file.*;

public class Main {
  public static void main(String[] args) {
    String test = "xyz       =         123\nxyz ( \nxyz=abc\n)";
    BinaryTree bsttree = new BinaryTree();
    BinaryTree avltree = new BinaryTree();
    BST bst = new BST(bsttree);
    AVL avl = new AVL(avltree);
    Tokenizer tokenizerBST = new Tokenizer(bsttree, bst, avl);
    Tokenizer tokenizerAVL = new Tokenizer(avltree, bst, avl);//

    // Tokenization
    List<Node> tokensBST;
    List<Node> tokensAVL;
    Scanner scanner = new Scanner(System.in);
    int option;
    String filePath = "";
    String keySearch;
    String keyDelete;
    String keyInsert;
    String keyUpdate;
    Node found;
    Node node;
    int idToUpdate;

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

            Path filePathClass = Paths.get(filePath);
            String file;
            try {
              file = Files.readString(filePathClass);
            } catch (Exception e) {
              System.out.println("Erro lendo o arquivo parte 2, java é complicado");
              break;
            }
            tokensBST = tokenizerBST.tokenize(file);
            tokensAVL = tokenizerAVL.tokenize(file);
          } else {
            System.out.println("Formato de arquivo inválido");
            filePath = ""; // Reseta o filePath se for inválido
          }
          break;

        case 2:
          System.out.println("Informe a chave/escopo na árvore que deseja buscar: ");
          keySearch = scanner.nextLine();
          Token newSearch = new Token(keySearch, TokenType.IDENTIFIER);
          int comparisions = avltree.searchNodeComparisons(avltree.getRoot(), newSearch);
          found = avltree.searchNode(avltree.getRoot(), new Token(keySearch, TokenType.IDENTIFIER));
          if (found != null) {
            System.out.println("Chave '" + keySearch + "' encontrada na árvore.");
            System.out.println(comparisions);
            avltree.printNodeInfo(found);
          } else {
            System.out.println("Chave '" + keySearch + "' não encontrada na árvore.");
          }
          break;

        case 3:
          System.out.println("Informe o que deseja inserir na árvore: ");
          keyInsert = scanner.nextLine();
          Token newInsert = new Token(keyInsert, TokenType.IDENTIFIER);
          bst.insert(bsttree.getRoot(), new Node(newInsert));
          node = avl.insertNodeAVL(avltree.getRoot(), new Node(newInsert));
          avltree.balance(node);
          avltree.printNodeInfo(node);
          break;

        case 4:
          System.out.println("Informe qual chave/escopo na árvore deseja alterar: ");
          keyUpdate = scanner.nextLine();
          idToUpdate = avltree.getIdByKey(keyUpdate);
          found = avl.updateNodeValue(avltree.getRoot(), idToUpdate, keyUpdate);
          if (found != null) {
            System.out.println("Chave '" + keyUpdate + "' encontrada na árvore.");
            bst.updateBST(avltree.getRoot(), idToUpdate, keyUpdate);
            avltree.printNodeInfo(found);
          } else {
            System.out.println("Chave '" + keyUpdate + "' não encontrada na árvore.");
          }
          break;

        case 5:
          System.out.println("Informe qual chave/escopo na árvore deseja remover: ");
          keyDelete = scanner.nextLine();
          idToUpdate = avltree.getIdByKey(keyDelete);
          found = avl.deleteNode(avltree.getRoot(), idToUpdate);
          if (found != null) {
            System.out.println("Chave '" + keyDelete + "' encontrada na árvore e deletada.");
            bst.deleteNodeBST(avltree.getRoot(), idToUpdate);
            avltree.printNodeInfo(found);
          } else {
            System.out.println("Chave '" + keyDelete + "' não encontrada na árvore.");
          }
          break;

        case 6:
          // Salvar dados para um arquivo
          if (!filePath.isEmpty()) {
            System.out.println("Informe o nome do novo arquivo para salvamento dos dados: ");
            String newFilePath = scanner.nextLine();
            if (FilesMethods.writeFile(filePath, newFilePath)) {
              System.out.println("Dados salvos no arquivo: " + newFilePath);
            } else {
              System.out.println("Erro ao salvar os dados");
            }
          } else {
            System.out.println("Nenhum arquivo ED2 aberto.");
          }
          break;

        case 7:
          System.out.println("PreOrder:");
          bsttree.posOrderTraversal();
          System.out.println("InOrder:");
          bsttree.inOrderTraversal();
          System.out.println("PosOrder:");
          bsttree.posOrderTraversal();
          break;
        case 8:
          System.out.println("PreOrder:");
          avltree.posOrderTraversal();
          System.out.println("InOrder:");
          avltree.inOrderTraversal();
          System.out.println("PosOrder:");
          avltree.posOrderTraversal();
          break;
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

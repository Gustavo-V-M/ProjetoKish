import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Valida o formato do arquivo ED2
public class FilesMethods {
    public static boolean validateFile(String filePath){
        List<String> lines = new ArrayList();
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line = br.readLine()) != null){
                lines.add(line);
            }
        } catch(IOException e){
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return false;
        }
        for (String cur_line: lines){
            if (isScopeStart(cur_line)) {
                if (!validadeScope(lines)) {
                    return false;
                }
            }
            else if (!isValidLine(cur_line)){
                return false;
            }
        }
        return true;
    }

    // Valida a linha do arquivo ED2
    private static boolean isValidLine(String line){
        return line.matches("\\s*\\w+\\s*=\\s*\\S+")||
                line.matches("\\s*\\)\\s*") ||
                line.matches("\\s*\\w+\\s*");
    }

    private static boolean isScopeStart(String line) {
        return line.matches("\\s*\\w+\\s*\\(\\s*") || 
               line.matches("\\s*\\(");
    }
    private static boolean isScopeEnd(String line) {
        return line.matches("\\s*\\)\\s*");
    }

    private static boolean validadeScope(List<String> lines){
        int scopeStartCount = 0;
        for (String line: lines){
            if (isScopeEnd(line) && scopeStartCount == 1){
                return true;
            }
            else if (isScopeEnd(line) && scopeStartCount > 1){
                scopeStartCount--;
            }
            else if (isScopeStart(line)) {
                scopeStartCount++;
            }
        }
        return false;
    }


    public static boolean writeFile(String inputFilePath, String outputFilePath){
        try(BufferedReader br = new BufferedReader(new FileReader(inputFilePath));
        BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))){

            String line;
            while((line = br.readLine()) != null){
                bw.write(line);
                bw.newLine();
            }
        }catch(IOException e){
            System.out.println("Erro ao salvar o arquivo: " + e.getMessage());
            return false;
        }
        return true;
    }
}

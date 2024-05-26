import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

// Valida o formato do arquivo ED2
public class ValidateFile {
    public static boolean validateFile(String filePath){
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line = br.readLine()) != null){
                if(!isValidLine(line)) {
                    return false;
                }
            }
        } catch(IOException e){
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return false;
        }
        return true;
    }

    // Valida a linha do arquivo ED2
    private static boolean isValidLine(String line){
        return line.matches("\\w+\\s*=\\s*\\S+")||
                line.matches("\\w+\\s*\\(\\s*") ||
                line.matches("\\s*\\)");
    }
}

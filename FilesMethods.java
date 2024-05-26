import java.io.*;

// Valida o formato do arquivo ED2
public class FilesMethods {
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

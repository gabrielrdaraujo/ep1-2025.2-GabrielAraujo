package infra;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class CSV {

    public static List<String[]> read(String filePath) {
        List<String[]> linhas = new ArrayList<>();
        Path path = Paths.get(filePath);

        if (!Files.exists(path)) {
            return linhas; 
        }

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linhas.add(linha.split(";")); 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return linhas;
    }

    public static void write(String filePath, List<String[]> linhas) {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(filePath))) {
            for (String[] campos : linhas) {
                bw.write(String.join(";", campos));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

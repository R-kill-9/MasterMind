package main.persistence;
import java.io.*;
import java.util.*;

public class RankingPersistence {

    /*
     * Path donde se guardan los rankings
     */
    private String path = "";

    /**
     * Constructora
     */
    public RankingPersistence() {
    }

    /**
     * Comprovamos si existe la carpeta ranking, sino la creamos 
     */
    public void checkRanking() {
        File dataDir = new File("./DATA/");
        if (!dataDir.exists()) {
          //  dataDir.mkdir();
        }
        File rankingDir = new File(dataDir, "rankings");
        if (!rankingDir.exists()) {
            rankingDir.mkdir();
        }
        path = rankingDir.getPath();
    }

    /**
     * Guardamos el ranking segun la dificultad
     * en un fichero
     */
    public void saveRanking(int dificultad, TreeMap<String, Integer> ranking) {
        String fileName = "ranking" + Integer.toString(dificultad) + ".txt";
        File rankingFile = new File(path, fileName);
        try {
            FileWriter fw = new FileWriter(rankingFile);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Map.Entry<String, Integer> entry : ranking.entrySet()) {
                bw.write(entry.getKey() + "," + entry.getValue());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Error al guardar el ranking");
        }
    }

    /**
     * Cargamos el ranking segun la dificultad
     * de un fichero
     */
    public TreeMap<String, Integer> loadRanking(int dificultad) {
        TreeMap<String, Integer> ranking = new TreeMap<String, Integer>();
        String fileName = "ranking" + Integer.toString(dificultad) + ".txt";
        File rankingFile = new File(path, fileName);
        try {
            FileReader fr = new FileReader(rankingFile);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                ranking.put(parts[0], Integer.parseInt(parts[1]));
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error al cargar el ranking");
        }
        return ranking;
    }

    /**
     * Devuelve true si existe algun fichero de ranking1
     */
    public boolean existsRanking1() {
        String fileName = "ranking1.txt";
        File rankingFile = new File(path, fileName);
        return rankingFile.exists();
    }
    
    /**
     * Devuelve true si existe algun fichero de ranking2
     */
    public boolean existsRanking2() {
        String fileName = "ranking2.txt";
        File rankingFile = new File(path, fileName);
        return rankingFile.exists();
    }

    /*
     * Devuelve true si existe algun fichero de ranking3
     */
    public boolean existsRanking3() {
        String fileName = "ranking3.txt";
        File rankingFile = new File(path, fileName);
        return rankingFile.exists();
    }
    

    /**
     * Main para probar la clase
     */
    public static void main(String[] args) {
        RankingPersistence rp = new RankingPersistence();
        rp.checkRanking();
        TreeMap<String, Integer> ranking = new TreeMap<String, Integer>();
        ranking.put("pepe", 100);
        ranking.put("juan", 200);
        ranking.put("maria", 300);
        rp.saveRanking(2, ranking);
        TreeMap<String, Integer> ranking2 = rp.loadRanking(1);
        for (Map.Entry<String, Integer> entry : ranking2.entrySet()) {
            System.out.println(entry.getKey() + "," + entry.getValue());
        }
    }
    

}

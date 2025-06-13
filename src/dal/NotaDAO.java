package dal;

import java.io.*;
import java.util.ArrayList;
import model.Nota;
import util.Logger;

public class NotaDAO {
    // Caminho corrigido para o arquivo de dados
    private final String ARQUIVO = "dados/notas/notas.dat"; 

    public void salvar(ArrayList<Nota> notas) {
        try {
            File file = new File(ARQUIVO);
            // Garante que a pasta 'dados/notas' exista
            file.getParentFile().mkdirs(); 
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(notas);
            }
        } catch (IOException e) {
            Logger.registrar("Erro ao salvar notas: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Nota> carregar() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
            return (ArrayList<Nota>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            Logger.registrar("Erro ao carregar notas: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
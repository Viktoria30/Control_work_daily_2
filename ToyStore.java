import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ToyStore {
    private List<Toy> toys = new ArrayList<>();
    private static final String FILENAME = "toys.ser";

    public void addToy(Toy toy) {
        toys.add(toy);
    }

    public void changeToyWeight(int toyId, double newWeight) {
        for (Toy toy : toys) {
            if (toy.getId() == toyId) {
                toy.setWeight(newWeight);
                return;
            }
        }
    }

    public Toy drawPrize() {
        double totalWeight = toys.stream().mapToDouble(Toy::getWeight).sum();
        double randomNumber = Math.random() * totalWeight;
        double currentWeight = 0.0;

        for (Toy toy : toys) {
            currentWeight += toy.getWeight();
            if (randomNumber <= currentWeight) {
                if (toy.getQuantity() > 0) {
                    toy.setQuantity(toy.getQuantity() - 1);
                    return toy;
                }
            }
        }
        return null;
    }

    public void saveToys() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(toys);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public void loadToys() {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
        ois.readObject();
        
        Object obj = ois.readObject();
        if (obj instanceof List<?>) {
            toys = (List<Toy>) obj;
        }
    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
    }
}
}

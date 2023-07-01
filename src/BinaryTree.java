import java.util.TreeMap;

public class BinaryTree {
    private TreeMap<Integer, Medicament> tree;

    public void MedicamentTree() {
        tree = new TreeMap<>();
    }

    public void addMedicament(Medicament medicament) {
        if (!tree.containsKey(medicament.number)) {
            tree.put(medicament.number, medicament);
        }
    }

    public Medicament searchMedicament(int number) {
        return tree.get(number);
    }}
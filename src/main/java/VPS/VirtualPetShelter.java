package VPS;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class VirtualPetShelter {

    public Map<String, VirtualPet> pets = new HashMap<>();

   
    public Collection<VirtualPet> pets() {
        return pets.values();

    }

    public void add(VirtualPet pet) {
        pets.put(pet.getName(), pet);
    }

    public VirtualPet findPet(String petName) {
        return pets.get(petName);
    }

   
    public void intake(VirtualPet virtualPet) {
        String newName = virtualPet.getName();
        pets.put(newName, virtualPet);
    }

    
    public void feedAll() {
        for (VirtualPet pet : pets.values()) {
            pet.feedPet();
        }
    }

    public void waterAll() {
        for (VirtualPet pet : pets.values()) {
            pet.waterPet();
        }
    }

    public void play(String name) {
        pets.get(name).playPet();

    }

    public void tickAll() {
        for (VirtualPet pet : pets.values()) {
            pet.tick();
        }

    }

    public boolean adopt(String name) {
        pets.remove(name);
        return false;
    }

    public boolean doesPetRemain(String name) {
        return pets.containsKey(name);
    }

    public String petStatus() {
        String petStatus = "";
        for (Entry<String, VirtualPet> eachPet : pets.entrySet()) {
            petStatus += eachPet.getValue().getName() + "\t   " + eachPet.getValue().getHunger() + "\t   "
                    + eachPet.getValue().getWater() + "\t   " + eachPet.getValue().getBoredom() + "\n";
        }
        return petStatus;
    }

    public String option4Display() {
        String option4Display = "";
        for (Entry<String, VirtualPet> eachPet : pets.entrySet()) {
            option4Display += "Name: " + eachPet.getValue().getName() + "\t" + eachPet.getValue().getDescription()
                    + "\n";
        }
        return option4Display;
    }

}

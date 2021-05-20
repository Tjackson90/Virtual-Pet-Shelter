import VPS.VirtualPet;
import VPS.VirtualPetShelter;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class VirtualPetShelterTest {
    private static final String PET_NAME = "name";
    private static final String DESCRIPTION = "description";

    private VirtualPetShelter underTest;

    @Before
    public void setup() {
        underTest = new VirtualPetShelter();
    }

    @Test
    public void shouldRetrieveVirtualPetName() {
        VirtualPet check = new VirtualPet(PET_NAME, DESCRIPTION);
        underTest.intake(check);
        VirtualPet retrieved = underTest.findPet(PET_NAME);
        assertThat(retrieved, is(check));
    }

    @Test
    public void shouldIntakeMultipleVirtualPetNames() {
        String anotherName = "Gohan";
        VirtualPet pet = new VirtualPet("Gohan", DESCRIPTION);
        VirtualPet anotherPet = new VirtualPet(anotherName, DESCRIPTION);
        underTest.intake(pet);
        underTest.intake(anotherPet);
        Collection<VirtualPet> pets = underTest.pets();
        assertThat(pets, containsInAnyOrder(pet, anotherPet));
        assertTrue(pets.contains(pet));
        assertTrue(pets.contains(anotherPet));
        assertEquals(2, pets.size());
    }

    @Test
    public void shouldAdoptVirtualPets() {
        VirtualPet pet = new VirtualPet("Chill", DESCRIPTION);
        underTest.adopt(pet.getName());

        assertThat(underTest.doesPetRemain(pet.getName()), is(false));
    }


    @Test
    public void shouldShelterFeedAllPet() {
        VirtualPet pet = new VirtualPet("Chill", "Gohan");
        underTest.intake(pet);
        underTest.intake(new VirtualPet("Chill2", "Gohan2", 0, 0, 0)); 
        underTest.intake(new VirtualPet("Chill", "Gohan3"));
        underTest.feedAll();
        VirtualPet testPet = underTest.findPet("Chill2");
        int hunger = testPet.getHunger();
//         assertEquals("Chill2", testPet.getName());
        assertEquals(2, hunger);
        assertEquals(52, underTest.findPet("Chill").getHunger()); 
    }

    @Test
    public void shouldShelterWaterAllPet() {
        VirtualPet pet = new VirtualPet("Chill", "Gohan");
        underTest.intake(pet);
        underTest.intake(new VirtualPet("Chill2", "Gohan2", 0, 2, 0));
        underTest.intake(new VirtualPet("Chill3", "Gohan3"));
        underTest.waterAll();
        VirtualPet testPet = underTest.findPet("Chill2");
        int water = testPet.getWater();
        assertEquals(5, water);
        assertEquals(63, underTest.findPet("Chill3").getWater());
    }

    @Test
    public void shouldShelterPlayWithHold() {
        VirtualPet pet = new VirtualPet("Chill", DESCRIPTION);
        underTest.intake(pet);
        underTest.play("Chill");
        assertEquals(74, underTest.findPet("Chill").getBoredom());
    }

    @Test
    public void shouldTickAllPets() {
        VirtualPet pet = new VirtualPet("Chill", "Gohan");
        underTest.intake(pet);
        underTest.tickAll();
        assertEquals(49, underTest.findPet("Chill").getHunger());
        assertEquals(59, underTest.findPet("Chill").getWater());
        assertEquals(69, underTest.findPet("Chill").getBoredom());
    }
}

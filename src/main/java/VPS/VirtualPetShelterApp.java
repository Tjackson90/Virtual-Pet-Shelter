package VPS;

import java.util.Scanner;

public class VirtualPetShelterApp {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        VirtualPetShelter pets = new VirtualPetShelter();

        VirtualPet pet1 = new VirtualPet("Artor", "The Abysswalker.", 50, 50, 30);
        VirtualPet pet2 = new VirtualPet("Ansem", "Wise, lonely, needs a loving heart.", 20, 40, 60);
        VirtualPet pet3 = new VirtualPet("Cabba", "Is from Universe 6!", 60, 60, 60);
        VirtualPet pet4 = new VirtualPet("Doge", "Likes the moon!", 55, 60, 20);

        pets.add(pet1);
        pets.add(pet2);
        pets.add(pet3);
        pets.add(pet4);

        String decision = "";
        boolean gameRunning = true;

        while (gameRunning) {

            while (!decision.equalsIgnoreCase("Yes")) {

                System.out.println("Welcome to Helter Shelter.\n");

                System.out.println("Shelter Status \n");

                System.out.println("Name\t|Hunger\t|Thirst\t|Boredom");// the \t allows console to align output in columns
                System.out.println("--------|-------|-------|-------");
                System.out.println();
                System.out.println(pets.petStatus());
                System.out.println("\nMake a choice...\n");

                System.out.println("1. Feed pets");
                System.out.println("2. Water pets");
                System.out.println("3. Play with a pet");
                System.out.println("4. Adopt a pet.");
                System.out.println("5. Admit a pet.");
                System.out.println("6. Quit");

                String optionEntered = input.nextLine();
                
                if (optionEntered.equals("1")) {
                    pets.feedAll();
                    System.out.println("Pets enjoyed the food.\n");

                } else if (optionEntered.equals("2")) {
                    pets.waterAll();
                    System.out.println("Pets were all thirsty and drank water.\n");

                } else if (optionEntered.equals("3")) {
                    System.out.println("Choose the pet to interact with:");
                    System.out.println(pets.option4Display());
                    String petName = input.nextLine();

                    pets.play(petName);
                    System.out.println("You took " + petName + " on an exciting adventure nowhere.\n");

                } else if (optionEntered.equals("4")) {
                    System.out.println("Oh, you actually want one?");
                    System.out.println("Which one would you like to adopt?\n");
                    System.out.println(pets.option4Display());
                    String petName = input.nextLine();

                    if (!pets.doesPetRemain(petName)) {
                        System.out.println("This pet exists in memory, and the back of the shelter... ");
                    } else {
                        pets.adopt(petName);
                        System.out.println("You just adopted: \n" + petName + "!");
                    }

                } else if (optionEntered.equals("5")) {
                    System.out.println("Enter the name of the pet selling, er, admitting: ");
                    String newPetName = input.nextLine();

                    System.out.println("Give me a description of the pet: ");
                    String newPetDescription = input.nextLine();

                    pets.add(new VirtualPet(newPetName, newPetDescription));
                    System.out.println(newPetName + " is relieved to leave your clutches");
                    System.out.println("Thanks for selling your pet instead of abandoning it...\n");

                } else if (optionEntered.equals("6")) {
                    System.out.println("You going to quit on me?");
                    System.out.println("Enter Yes or No");
                    decision = input.nextLine();

                    if (decision.equalsIgnoreCase("Yes")) {
                        System.out.println("Well, see yourself out then...");

                    } else {
                        decision.equalsIgnoreCase("No");
                        System.out.println("Oh, don't scare me like that");
                        System.out.println("I can't pay someone less than free to work...\n");
                    }

                }

                pets.tickAll();
            }

            input.close();
        }

    }
}

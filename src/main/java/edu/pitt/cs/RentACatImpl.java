package edu.pitt.cs;

import java.util.ArrayList;
import java.util.Scanner;

public class RentACatImpl implements RentACat {

	private ArrayList<Cat> cats = new ArrayList<Cat>();

	/**
	 * Return a cat. This should call the .returnCat() method on the cat for the
	 * passed-in cat id. If the cat with the id exists in the list of cats and has
	 * been rented out, then this method should return true after calling
	 * .returnCat() on that cat. Otherwise, the method should return false.
	 * 
	 * @param id the ID of the cat to rent
	 * @return true if cat exists and was rented out, false otherwise
	 */

	@Override
	public boolean returnCat(int id) {
		Cat cat = getCat(id);
		if (cat != null && cat.getRented()) {
			cat.returnCat();
			System.out.println("Welcome back, " + cat.getName() + "!");
			return true;
		} else if (cat != null && !cat.getRented()) {
			System.out.println(cat.getName() + " is already here!");
			return false;
		} else {
			System.out.println("Invalid cat ID.");
			return false;
		}
	}

	/**
	 * Rent a cat. This should call the .rentCat() method on the cat for the
	 * passed-in cat id. If the cat with the id exists in the list of cats and has
	 * *not* been rented out, then this method should return true after calling
	 * .rentCat() on that cat. Otherwise, the method should return false.
	 * 
	 * @param id the ID of the cat to rent
	 * @return true if cat exists and was not rented out, false otherwise
	 */

	@Override
	public boolean rentCat(int id) {
		Cat cat = getCat(id);
		if (cat != null) {
			if (!cat.getRented()) {
				cat.rentCat();
				System.out.println(cat.getName() + " has been rented.");
				return true;
			} else {
				System.out.println("Sorry, " + cat.getName() + " is not here!");
				return false;
			}
		}
		System.out.println("Invalid cat ID.");
		return false;
	}

	/**
	 * Rename a cat. This calls the .renameCat(String) method on the cat for the
	 * passed-in cat id, if the cat exists, and then returns true. If the cat does
	 * not exist, the method returns false.
	 * 
	 * @param id the ID of the cat to rename
	 * @return true if cat exists, false otherwise
	 */

	@Override
	public boolean renameCat(int id, String name) {
		Cat cat = getCat(id);
		if (cat != null) {
			cat.renameCat(name);
			return true;
		}
		return false;
	}

	/**
	 * Create a String list from the list of cats using the .toString() method of
	 * each NON-RENTED Cat object in the list. That is, it should only add cats who
	 * are available to be rented. These cats should be separated by "\n" characters
	 * (line feeds). Example: ID 1. Jennyanydots ID 2. Old Deuteronomy ID 3.
	 * Mistoffelees
	 * 
	 * @return "\n"-delimited list of rentable cats
	 */

	public String listCats() {
		StringBuilder sb = new StringBuilder();

		if (cats.isEmpty()) {
			return (""); // Return an empty string or some indication of no cats
		} else {
			for (Cat cat : cats) {
				if (!cat.getRented()) {
					sb.append("ID ").append(cat.getId()).append(". ").append(cat.getName()).append("\n");
				}
			}
		}

		return sb.toString();
	}

	/**
	 * Given an id, return a reference to the specified cat if a cat with that ID
	 * exists. Return null if no cat of that ID exists in the list.
	 * 
	 * @param int id ID of cat to search for
	 * @return Cat searched for if exists, null otherwise
	 */

	public Cat getCat(int id) {
		if (cats == null) {
			System.out.println("Invalid cat ID.");
			return null;
		}

		for (Cat c : cats) {
			if (c.getId() == id) {
				return c;
			}
		}

		System.out.println("Invalid cat ID.");
		return null;
	}

	@Override
	public void addCat(Cat c) {
		cats.add(c);
	}

	public static void main(String[] args) {
		RentACat rc = new RentACatImpl();

		rc.addCat(new CatImpl(1, "Jennyanydots"));
		rc.addCat(new CatImpl(2, "Old Deuteronomy"));
		rc.addCat(new CatImpl(3, "Mistoffelees"));

		Scanner sc = new Scanner(System.in);

		int option;
		boolean keepGoing = true;

		while (keepGoing) {
			System.out.print("Option [1,2,3,4,5] > ");
			try {
				option = sc.nextInt();
				switch (option) {
					case 1:
						System.out.println("Cats for Rent");
						System.out.print(rc.listCats());
						break;
					case 2:
						System.out.print("Rent which cat? > ");
						try {
							int catIdToRent = sc.nextInt();
							if (rc.rentCat(catIdToRent)) {
								System.out.println("Cat was rented!");

							}

							else {
								System.out.println("Failed to rent cat");
							}
							rc.rentCat(catIdToRent);
						} catch (Exception ex) {
							System.out.println("Invalid cat ID.");
							sc.next();
							break;
						}
						break;
					case 3:
						System.out.print("Return which cat? > ");
						try {
							int catIdToReturn = sc.nextInt();
							if (rc.returnCat(catIdToReturn)) {
								// Retrieve the cat using the getCat method from RentACatImpl
								Cat returnedCat = ((RentACatImpl) rc).getCat(catIdToReturn);
								if (returnedCat != null) {
									System.out.println("Welcome back, " + returnedCat.getName() + "!");
								} else {
									System.out.println("Failed to return cat. Check cat ID.");
								}
							} else {
								System.out.println("Failed to return cat. Check cat ID.");
							}
						} catch (Exception ex) {
							System.out.println("Invalid cat ID.");
							sc.next(); // Clear the invalid input
						}
						break;
					case 4:
						System.out.print("Rename which cat? > ");
						try {
							int catIdToRename = sc.nextInt();
							sc.nextLine(); // Consume newline left-over
							System.out.print("What is the new name? > ");
							String newName = sc.nextLine();
							if (rc.renameCat(catIdToRename, newName)) {
								System.out.println("Cat renamed successfully!");
							} else {
								System.out.println("Failed to rename cat. Check cat ID.");
							}
						} catch (Exception ex) {
							System.out.println("Invalid cat ID.");
							sc.next(); // Clear the invalid input
						}
						break;

					case 5:
						keepGoing = false;
						break;
					default:
						throw new NumberFormatException();
				}
			} catch (Exception nfex) {
				System.err.println("Please enter 1, 2, 3, 4 or 5");
				System.err.println("1. See list of cats for rent");
				System.err.println("2. Rent a cat to a customer");
				System.err.println("3. Return a cat from a customer");
				System.err.println("4. Rename a cat");
				System.err.println("5. Quit");
				sc.next();
			}
		}

		System.out.println("Closing up shop for the day!");

		sc.close();
	}
}
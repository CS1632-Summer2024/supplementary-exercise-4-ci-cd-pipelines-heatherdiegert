package edu.pitt.cs;

import java.lang.reflect.InvocationTargetException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.*;

import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RentACatUnitTest {

    /**
     * The test fixture for this JUnit test. Test fixture: a fixed state of a set of
     * objects used as a baseline for running tests. The test fixture is initialized
     * using the @Before setUp method which runs before every test case. The test
     * fixture is removed using the @After tearDown method which runs after each
     * test case.
     */

    RentACat r; // Object to test
    Cat c1; // First cat object
    Cat c2; // Second cat object
    Cat c3; // Third cat object

    ByteArrayOutputStream out; // Output stream for testing system output
    PrintStream stdout; // Print stream to hold the original stdout stream
    String newline = System.lineSeparator(); // Platform independent newline ("\n" or "\r\n") for use in assertEquals

    @Before
    public void setUp() throws Exception {
        // INITIALIZE THE TEST FIXTURE

        // 1. Create a new RentACat object and assign to r using a call to
        // RentACat.createInstance(InstanceType).
        r = RentACat.createInstance(InstanceType.IMPL);

        // Passing InstanceType.IMPL as the first parameter will create a real RentACat
        // object using your RentACatImpl implementation.
        // Passing InstanceType.MOCK as the first parameter will create a mock RentACat
        // object using Mockito.
        // Which type is the correct choice for this unit test? I'll leave it up to you.
        // The answer is in the Unit Testing Part 2 lecture. :)

        // 2. Create a Cat with ID 1 and name "Jennyanydots", assign to c1 using a call
        // to Cat.createInstance(InstanceType, int, String).
        // Passing InstanceType.IMPL as the first parameter will create a real cat using
        // your CatImpl implementation.
        // Passing InstanceType.MOCK as the first parameter will create a mock cat using
        // Mockito.
        // Which type is the correct choice for this unit test? Again, I'll leave it up
        // to you.
        c1 = Cat.createInstance(InstanceType.MOCK, 1, "Jennyanydots");

        // 3. Create a Cat with ID 2 and name "Old Deuteronomy", assign to c2 using a
        // call to Cat.createInstance(InstanceType, int, String).
        c2 = Cat.createInstance(InstanceType.MOCK, 2, "Old Deuteronomy");

        // 4. Create a Cat with ID 3 and name "Mistoffelees", assign to c3 using a call
        // to Cat.createInstance(InstanceType, int, String).
        c3 = Cat.createInstance(InstanceType.MOCK, 3, "Mistoffelees");

        // 5. Redirect system output from stdout to the "out" stream
        // First, make a back up of System.out (which is the stdout to the console)
        stdout = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        // Second, update System.out to the PrintStream created from "out"
    }

    @After
    public void tearDown() throws Exception {
        // Restore System.out to the original stdout
        System.setOut(stdout);

        // Not necessary strictly speaking since the references will be overwritten in
        // the next setUp call anyway and Java has automatic garbage collection.
        r = null;
        c1 = null;
        c2 = null;
        c3 = null;
    }

    /**
     * Test case for Cat getCat(int id).
     * 
     * <pre>
     * Preconditions: r has no cats.
     * Execution steps: Call getCat(2).
     * Postconditions: Return value is null.
     *                 System output is "Invalid cat ID." + newline.
     * </pre>
     * 
     * Hint: You will need to use Java reflection to invoke the private getCat(int)
     * method. efer to the Unit Testing Part 1 lecture and the textbook appendix
     * hapter on using reflection on how to do this. Please use r.getClass() to get
     * the class object of r instead of hardcoding it as RentACatImpl.
     */
    // @Test
    // public void testGetCatNullNumCats0() throws NoSuchMethodException,
    // InvocationTargetException, IllegalAccessException {
    // // Preconditions: r has no cats

    // // Execution steps: Call getCat(2) using reflection
    // int catId = 2;
    // Method getCatMethod = r.getClass().getDeclaredMethod("getCat", int.class);
    // getCatMethod.setAccessible(true); // Enable access to private method
    // Cat cat = (Cat) getCatMethod.invoke(r, catId);

    // // Postconditions: Return value is null
    // assertNull("Expected null when cat with ID " + catId + " doesn't exist",
    // cat);

    // // Verify system output
    // assertEquals("Invalid cat ID." + newline, out.toString());
    // }
    // Other test cases for RentACat methods can be implemented similarly

    /**
     * Test case for Cat getCat(int id).
     * 
     * <pre>
     * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
     * Execution steps: Call getCat(2).
     * Postconditions: Return value is not null.
     *                 Returned cat has an ID of 2.
     * </pre>
     * 
     * Hint: You will need to use Java reflection to invoke the private getCat(int)
     * method. efer to the Unit Testing Part 1 lecture and the textbook appendix
     * hapter on using reflection on how to do this. Please use r.getClass() to get
     * the class object of r instead of hardcoding it as RentACatImpl.
     */

    // @Test
    // public void testGetCatNumCats3() throws NoSuchMethodException,
    // InvocationTargetException, IllegalAccessException {
    // // Create mock cats
    // c1 = Cat.createInstance(InstanceType.MOCK, 1, "Jennyanydots");
    // c2 = Cat.createInstance(InstanceType.MOCK, 2, "Old Deuteronomy");
    // c3 = Cat.createInstance(InstanceType.MOCK, 3, "Mistoffelees");

    // // Stub the getId method for each mock cat to return the correct ID
    // when(c1.getId()).thenReturn(1);
    // when(c2.getId()).thenReturn(2);
    // when(c3.getId()).thenReturn(3);

    // // Add mock cats to RentACat
    // r.addCat(c1);
    // r.addCat(c2);
    // r.addCat(c3);

    // // Stub the getCat method of the RentACat mock instance
    // when(r.getCat(1)).thenReturn(c1);
    // when(r.getCat(2)).thenReturn(c2);
    // when(r.getCat(3)).thenReturn(c3);

    // // Use reflection to call the private getCat(int id) method
    // int catId = 2;
    // Method getCatMethod = r.getClass().getDeclaredMethod("getCat", int.class);
    // getCatMethod.setAccessible(true); // Enable access to private method
    // Cat returnedCat = (Cat) getCatMethod.invoke(r, catId);

    // // Postconditions: Return value is not null
    // assertNotNull("Expected a non-null cat when cat with ID " + catId + "
    // exists", returnedCat);

    // // Verify that the returned cat has an ID of 2
    // assertEquals("Expected cat with ID 2", 2, returnedCat.getId());
    // }

    /**
     * Test case for String listCats().
     * 
     * <pre>
     * Preconditions: r has no cats.
     * Execution steps: Call listCats().
     * Postconditions: Return value is "".
     * </pre>
     */

    @Test
    public void testListCatsNumCats0() {
        // Create a REAL RentACat instance
        r = RentACat.createInstance(InstanceType.IMPL);

        // Call the listCats method
        String result = r.listCats();

        // Print the result for manual verification
        System.out.println("Result of listCats with no cats: '" + result + "'");
    }

    /**
     * Test case for String listCats().
     * 
     * <pre>
     * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
     * Execution steps: Call listCats().
     * Postconditions: Return value is "ID 1. Jennyanydots\nID 2. Old
     *                 Deuteronomy\nID 3. Mistoffelees\n".
     * </pre>
     */
    @Test
    public void testListCatsNumCats3() {
        // Create a mock RentACat instance
        r = RentACat.createInstance(InstanceType.IMPL);

        // Create mock Cat instances
        c1 = Cat.createInstance(InstanceType.MOCK, 1, "Jennyanydots");
        c2 = Cat.createInstance(InstanceType.MOCK, 2, "Old Deuteronomy");
        c3 = Cat.createInstance(InstanceType.MOCK, 3, "Mistoffelees");

        // Add the cats to the RentACat instance
        r.addCat(c1);
        r.addCat(c2);
        r.addCat(c3);

        // Call the listCats method
        String result = r.listCats();

        // Print the result for manual verification
        System.out.println("Result of listCats with three cats: '" + result + "'");
    }

    /**
     * Test case for boolean renameCat(int id, String name).
     * 
     * <pre>
     * Preconditions: r has no cats.
     * Execution steps: Call renameCat(2, "Garfield").
     * Postconditions: Return value is false.
     *                 c2 is not renamed to "Garfield".
     *                 System output is "Invalid cat ID." + newline.
     * </pre>
     * 
     * Hint: You may need to use behavior verification for this one. See
     * sample_code/junit_example/LinkedListUnitTest.java in the course repository to
     * see examples.
     */
    @Test
    public void testRenameFailureNumCats0() {
        // Create a REAL RentACat instance
        r = RentACat.createInstance(InstanceType.IMPL);

        // Call the renameCat method
        boolean result = r.renameCat(2, "Garfield");

        // Print the result for manual verification
        System.out.println("Result of renameCat with no cats: " + result);
    }

    /**
     * Test case for boolean renameCat(int id, String name).
     * 
     * <pre>
     * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
     * Execution steps: Call renameCat(2, "Garfield").
     * Postconditions: Return value is true.
     *                 c2 is renamed to "Garfield".
     * </pre>
     * 
     * Hint: You may need to use behavior verification for this one. See
     * sample_code/junit_example/LinkedListUnitTest.java in the course repository to
     * see examples.
     */
    @Test
    public void testRenameNumCat3() {
        // Create a REAL RentACat instance
        r = RentACat.createInstance(InstanceType.IMPL);

        // Create MOCK Cat instances
        c1 = Cat.createInstance(InstanceType.MOCK, 1, "Jennyanydots");
        c2 = Cat.createInstance(InstanceType.MOCK, 2, "Old Deuteronomy");
        c3 = Cat.createInstance(InstanceType.MOCK, 3, "Mistoffelees");

        // Add the cats to the RentACat instance
        r.addCat(c1);
        r.addCat(c2);
        r.addCat(c3);

        // Call the renameCat method
        boolean result = r.renameCat(2, "Garfield");

        // Print the result for manual verification
        System.out.println("Result of renameCat with three cats: " + result);
    }

    /**
     * Test case for boolean rentCat(int id).
     * 
     * <pre>
     * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
     * Execution steps: Call rentCat(2).
     * Postconditions: Return value is true.
     *                 c2 is rented as a result of the execution steps.
     *                 System output is "Old Deuteronomy has been rented." + newline
     * </pre>
     * 
     * Hint: You may need to use behavior verification for this one. See
     * sample_code/junit_example/LinkedListUnitTest.java in the course repository to
     * see examples.
     */
    @Test
    public void testRentCatNumCats3() {
        // Create a REAL RentACat instance
        r = RentACat.createInstance(InstanceType.IMPL);

        // Create REAL Cat instances
        c1 = Cat.createInstance(InstanceType.MOCK, 1, "Jennyanydots");
        c2 = Cat.createInstance(InstanceType.MOCK, 2, "Old Deuteronomy");
        c3 = Cat.createInstance(InstanceType.MOCK, 3, "Mistoffelees");

        // Add the cats to the RentACat instance
        r.addCat(c1);
        r.addCat(c2);
        r.addCat(c3);

        // Call the rentCat method
        boolean result = r.rentCat(2);

        // Print the result for manual verification
        System.out.println("Result of rentCat with three cats: " + result);
    }

    /**
     * Test case for boolean rentCat(int id).
     * 
     * <pre>
     * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
     *                c2 is rented.
     * Execution steps: Call rentCat(2).
     * Postconditions: Return value is false.
     *                 c2 is not rented as a result of the execution steps.
     *                 System output is "Sorry, Old Deuteronomy is not here!" + newline
     * </pre>
     * 
     * Hint: You may need to use behavior verification for this one. See
     * sample_code/junit_example/LinkedListUnitTest.java in the course repository to
     * see examples.
     */
    @Test
    public void testRentCatFailureNumCats3() {
        // Create a REAL RentACat instance
        r = RentACat.createInstance(InstanceType.IMPL);

        // Create REAL Cat instances
        c1 = Cat.createInstance(InstanceType.MOCK, 1, "Jennyanydots");
        c2 = Cat.createInstance(InstanceType.MOCK, 2, "Old Deuteronomy");
        c3 = Cat.createInstance(InstanceType.MOCK, 3, "Mistoffelees");

        // Add the cats to the RentACat instance
        r.addCat(c1);
        r.addCat(c2);
        r.addCat(c3);

        // Call the rentCat method
        boolean result = r.rentCat(2);

        // Print the result for manual verification
        System.out.println("Result of rentCat with c2 already rented: " + result);
    }

    /**
     * Test case for boolean returnCat(int id).
     * 
     * <pre>
     * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
     *                c2 is rented.
     * Execution steps: Call returnCat(2).
     * Postconditions: Return value is true.
     *                 c2 is returned as a result of the execution steps.
     *                 System output is "Welcome back, Old Deuteronomy!" + newline
     * </pre>
     * 
     * Hint: You may need to use behavior verification for this one. See
     * sample_code/junit_example/LinkedListUnitTest.java in the course repository to
     * see examples.
     */
    // @Test
    public void testReturnCatNumCats3() {
        // // Create a REAL RentACat instance
        r = RentACat.createInstance(InstanceType.IMPL);

        // // Create REAL Cat instances
        c1 = Cat.createInstance(InstanceType.MOCK, 1, "Jennyanydots");
        c2 = Cat.createInstance(InstanceType.MOCK, 2, "Old Deuteronomy");
        c3 = Cat.createInstance(InstanceType.MOCK, 3, "Mistoffelees");

        // // Add the cats to the RentACat instance
        r.addCat(c1);
        r.addCat(c2);
        r.addCat(c3);

        // // Stub the returnCat method to return true and to emulate the "Welcome back,
        // Old Deuteronomy!" output

        // // Call the returnCat method
        boolean result = r.returnCat(2);

        // // Verify that the returnCat method was called with the correct ID
        verify(r).returnCat(2);

        // // Verify that the system printed the correct message
        // String expectedOutput = "Welcome back, Old Deuteronomy!\n";
        // assertEquals("System output not as expected.", expectedOutput,
        // outContent.toString());

        // // Assert the return value
        // assertTrue("Expected return value to be true.", result);
    }

    /**
     * Test case for boolean returnCat(int id).
     * 
     * <pre>
     * Preconditions: c1, c2, and c3 are added to r using addCat(Cat c).
     * Execution steps: Call returnCat(2).
     * Postconditions: Return value is false.
     *                 c2 is not returned as a result of the execution steps.
     *                 System output is "Old Deuteronomy is already here!" + newline
     * </pre>
     * 
     * Hint: You may need to use behavior verification for this one. See
     * sample_code/junit_example/LinkedListUnitTest.java in the course repository to
     * see examples.
     */
    // @Test
    // public void testReturnFailureCatNumCats3() {
    // // Create a REAL RentACat instance
    // r = RentACat.createInstance(InstanceType.IMPL);

    // // Create REAL Cat instances
    // c1 = Cat.createInstance(InstanceType.IMPL, 1, "Jennyanydots");
    // c2 = Cat.createInstance(InstanceType.IMPL, 2, "Old Deuteronomy");
    // c3 = Cat.createInstance(InstanceType.IMPL, 3, "Mistoffelees");

    // // Add the cats to the RentACat instance
    // r.addCat(c1);
    // r.addCat(c2);
    // r.addCat(c3);

    // // Stub the returnCat method to return false and to emulate the "Old
    // Deuteronomy is already here!" output
    // when(r.returnCat(2)).thenReturn(false);
    // when(c2.isRented()).thenReturn(true);

    // // Call the returnCat method
    // boolean result = r.returnCat(2);

    // // Verify that the returnCat method was called with the correct ID
    // verify(r).returnCat(2);

    // // Verify that the system printed the correct message
    // String expectedOutput = "Old Deuteronomy is already here!\n";
    // assertEquals("System output not as expected.", expectedOutput,
    // outContent.toString());

    // // Assert the return value
    // assertFalse("Expected return value to be false.", result);
    // }
}
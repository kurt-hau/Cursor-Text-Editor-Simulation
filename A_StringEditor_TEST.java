package editor;

public class A_StringEditor_TEST {

    public static void main(String[] args) {
        //testInsert();
        //testMove();
        //testDelete();
        //testBackspace();
        //testLettersBeforeCursor();
        //testLettersAfterCursor();
    }

    
    public static void testInsert() {
        A_StringEditor editor = new A_StringEditor();
        editor.insert('A');
        editor.insert('B');
        editor.insert('C');
        System.out.println("After inserting 'A', 'B', 'C':\n" + editor + "\n");
    }

    
    public static void testMove() {
        A_StringEditor editor = new A_StringEditor();
        editor.insert('A');
        editor.insert('B');
        editor.insert('C');
        editor.insert('D');
        
        // Initial State
        System.out.println("Initial state:\n" + editor);
        System.out.println("----------------------------------------\n" );

        // Move forward twice
        editor.moveForward();
        editor.moveForward();
        System.out.println("After moving forward twice:\n" + editor);
        System.out.println("----------------------------------------\n" );

        // Move backward once
        editor.moveBackward();
        System.out.println("After moving backward once:\n" + editor);
        System.out.println("----------------------------------------\n" );
        
        // Move forward again to test at the end boundary
        editor.moveForward();
        editor.moveForward(); // Cursor should stay at the end after this move
        System.out.println("After attempting to move forward at end:\n" + editor);
        System.out.println("----------------------------------------\n" );
        
        // Move backward three times to test beginning boundary
        editor.moveBackward();
        editor.moveBackward();
        editor.moveBackward();
        editor.moveBackward(); // Cursor should stay at the beginning after this move
        System.out.println("After moving backward to the beginning:\n" + editor);
        System.out.println("----------------------------------------\n" );
    }


    
    public static void testDelete() {
        A_StringEditor editor = new A_StringEditor();
        editor.insert('H');
        editor.insert('I');
        editor.insert('J');
        editor.moveBackward();
        editor.delete();
        System.out.println("After deleting at cursor:\n" + editor + "\n");
    }

    public static void testBackspace() {
        A_StringEditor editor = new A_StringEditor();
        editor.insert('M');
        editor.insert('N');
        editor.moveForward();
        editor.backspace();
        System.out.println("After backspacing:\n" + editor + "\n");
    }
    
 // Tests the lettersBeforeCursor method by retrieving characters before the cursor
    public static void testLettersBeforeCursor() {
        A_StringEditor editor = new A_StringEditor();
        editor.insert('A');
        editor.insert('B');
        editor.insert('C');
        editor.insert('D');
        editor.insert('E');
        
        // Position cursor after 'C'
        editor.moveBackward(); 
        editor.moveBackward(); // Cursor is "between C and D" (right before d)

        // Test case 1: count is exactly the number of characters to the left
        System.out.println("Letters before cursor (3 chars): " + editor.lettersBeforeCursor(3)); // Expected: "ABC"
        System.out.println("----------------------------------------\n" );
        
        // Test case 2: count is more than the available characters to the left
        System.out.println("Letters before cursor (10 chars): " + editor.lettersBeforeCursor(10)); // Expected: "ABC"
        System.out.println("----------------------------------------\n" );

        // Test case 3: count is 0
        System.out.println("Letters before cursor (0 chars): " + editor.lettersBeforeCursor(0)); // Expected: ""
        System.out.println("----------------------------------------\n" );
    }

    public static void testLettersAfterCursor() {
        A_StringEditor editor = new A_StringEditor();
        editor.insert('A');
        editor.insert('B');
        editor.insert('C');
        editor.insert('D');
        editor.insert('E');

        // Position cursor between C and D 
        editor.moveBackward(); 
        editor.moveBackward();	// Cursor is between C and D (right before d)

        // Test case 1: count is exactly the number of characters to the right
        System.out.println("Letters after cursor (2 chars): " + editor.lettersAfterCursor(2)); // Expected: "DE"
        System.out.println("----------------------------------------\n" );
        
        // Test case 2: count is more than the available characters to the right
        System.out.println("Letters after cursor (10 chars): " + editor.lettersAfterCursor(10)); // Expected: "DE"
        System.out.println("----------------------------------------\n" );
        
        // Test case 3: count is 0
        System.out.println("Letters after cursor (0 chars): " + editor.lettersAfterCursor(0)); // Expected: ""
        System.out.println("----------------------------------------\n" );
    }

}

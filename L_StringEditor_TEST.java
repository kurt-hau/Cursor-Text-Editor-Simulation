package editor;

public class L_StringEditor_TEST {

    public static void main(String[] args) {
        //testInsert();
        //testMove();
        //testDelete();
        //testBackspace();
        //testLettersBeforeCursor();
        testLettersAfterCursor();
    }

    // Tests the insert method by adding characters and printing the editor state
    public static void testInsert() {
        L_StringEditor editor = new L_StringEditor();
        editor.insert('A');
        editor.insert('B');
        editor.insert('C');
        System.out.println("After inserting 'A', 'B', 'C':\n" + editor + "\n");
    }

    // Tests the moveForward and moveBackward methods by moving the cursor in both directions
    public static void testMove() {
        L_StringEditor editor = new L_StringEditor();
        editor.insert('A');
        editor.insert('B');
        editor.insert('C');
        editor.insert('D');
        
        // Initial state of the editor with cursor at the end
        System.out.println("Initial state:\n" + editor);
        System.out.println("----------------------------------------\n");

        // Move forward twice (if possible)
        editor.moveForward();
        editor.moveForward();
        System.out.println("After moving forward twice:\n" + editor);
        System.out.println("----------------------------------------\n");

        // Move backward once
        editor.moveBackward();
        System.out.println("After moving backward once:\n" + editor);
        System.out.println("----------------------------------------\n");

        // Move forward again, testing boundary at the end
        editor.moveForward();
        editor.moveForward(); // Cursor should remain at the end
        System.out.println("After attempting to move forward at end:\n" + editor);
        System.out.println("----------------------------------------\n");

        // Move backward multiple times to test the boundary at the start
        editor.moveBackward();
        editor.moveBackward();
        editor.moveBackward();
        editor.moveBackward();
        editor.moveBackward(); // Cursor should stay at the beginning
        System.out.println("After moving backward to the beginning:\n" + editor);
        System.out.println("----------------------------------------\n");
    }

    // Tests the delete method by removing a character immediately to the right of the cursor
    public static void testDelete() {
        L_StringEditor editor = new L_StringEditor();
        editor.insert('H');
        editor.insert('I');
        editor.insert('J');
        editor.moveBackward(); // Position cursor before 'J'
        editor.delete(); // Delete 'J'
        System.out.println("After deleting at cursor:\n" + editor + "\n");
        
     // Test Case 2: Delete when cursor is at the beginning (should delete the first character after the cursor)
        L_StringEditor editor2 = new L_StringEditor();
        editor2.insert('A');
        editor2.insert('B');
        editor2.insert('C');
        editor2.moveBackward(); // Move cursor to the start
        editor2.moveBackward();
        editor2.moveBackward(); // Cursor should be at the dummy
        editor2.delete(); // Should delete 'A'
        System.out.println("After deleting at the beginning (removing 'A'):\n" + editor2 + "\n");
        System.out.println("----------------------------------------\n");

        // Test Case 3: Delete at the end of the text (should do nothing)
        L_StringEditor editor3 = new L_StringEditor();
        editor3.insert('X');
        editor3.insert('Y');
        editor3.moveForward(); // Position cursor after 'Y' (end of the list)
        editor3.delete(); // No character to delete
        System.out.println("After attempting delete at the end:\n" + editor3 + "\n");
        System.out.println("----------------------------------------\n");

        // Test Case 4: Delete in an empty editor (should do nothing)
        L_StringEditor editor4 = new L_StringEditor();
        editor4.delete(); // No character to delete in an empty editor
        System.out.println("After attempting delete in an empty editor:\n" + editor4 + "\n");
        System.out.println("----------------------------------------\n");

        // Test Case 5: Delete the only character in the editor
        L_StringEditor editor5 = new L_StringEditor();
        editor5.insert('S');
        System.out.println("Initial state with one character:\n" + editor5);
        editor5.delete(); // Should delete 'S' and leave the editor empty
        System.out.println("After deleting the only character:\n" + editor5 + "\n");
        System.out.println("----------------------------------------\n");
    }

    // Tests the backspace method by removing a character immediately to the left of the cursor
    public static void testBackspace() {
        L_StringEditor editor = new L_StringEditor();
        editor.insert('M');
        editor.insert('N');
        editor.moveForward(); // Move cursor to the end
        editor.backspace(); // Remove 'N' from the left of the cursor
        System.out.println("After backspacing:\n" + editor + "\n");
        System.out.println("----------------------------------------\n");
        
        L_StringEditor editor2 = new L_StringEditor();
        editor2.insert('A');
        editor2.insert('B');
        editor2.insert('C');
        editor2.moveBackward(); // Move cursor to the start
        editor2.moveBackward();
        editor2.moveBackward();
        editor2.backspace(); // Should not remove anything
        System.out.println("After backspacing at the start:\n" + editor2 + "\n");
        System.out.println("----------------------------------------\n");

        // Test Case 3: Backspace in the middle of the text
        L_StringEditor editor3 = new L_StringEditor();
        editor3.insert('X');
        editor3.insert('Y');
        editor3.insert('Z');
        editor3.moveBackward(); // Move cursor to between 'Y' and 'Z'
        editor3.backspace(); // Remove 'Y'
        System.out.println("After backspacing in the middle:\n" + editor3 + "\n");
        System.out.println("----------------------------------------\n");
        
        // Test Case 4: Backspace in an empty editor (should do nothing)
        L_StringEditor editor4 = new L_StringEditor();
        editor4.backspace(); // No character to remove
        System.out.println("After backspacing in an empty editor:\n" + editor4 + "\n");
        System.out.println("----------------------------------------\n");

        // Test Case 5: Backspace on a single character in the editor
        L_StringEditor editor5 = new L_StringEditor();
        editor5.insert('S');
        System.out.println("Initial state with one character:\n" + editor5);
        editor5.backspace(); // Should remove 'S' and leave the editor empty
        System.out.println("After backspacing the only character:\n" + editor5 + "\n");
        System.out.println("----------------------------------------\n");
    }

    // Tests the lettersBeforeCursor method by retrieving characters to the left of the cursor
    public static void testLettersBeforeCursor() {
        L_StringEditor editor = new L_StringEditor();
        editor.insert('A');
        editor.insert('B');
        editor.insert('C');
        editor.insert('D');
        editor.insert('E');
        
        // Position cursor between 'C' and 'D' (right before d)
        editor.moveBackward(); 
        editor.moveBackward();

        // Test case 1: Retrieve exactly 3 characters to the left of the cursor
        System.out.println("Letters before cursor (3 chars): " + editor.lettersBeforeCursor(3)); // Expected: "ABC"
        System.out.println("----------------------------------------\n");

        // Test case 2: Request more characters than available to the left
        System.out.println("Letters before cursor (10 chars): " + editor.lettersBeforeCursor(10)); // Expected: "ABC"
        System.out.println("----------------------------------------\n");

        // Test case 3: Request 0 characters to the left
        System.out.println("Letters before cursor (0 chars): " + editor.lettersBeforeCursor(0)); // Expected: ""
        System.out.println("----------------------------------------\n");
    }

    // Tests the lettersAfterCursor method by retrieving characters to the right of the cursor
    public static void testLettersAfterCursor() {
        L_StringEditor editor = new L_StringEditor();
        editor.insert('A');
        editor.insert('B');
        editor.insert('C');
        editor.insert('D');
        editor.insert('E');

        // Position cursor between 'C' and 'D' (right before d)
        editor.moveBackward(); 
        editor.moveBackward();

        // Test case 1: Retrieve exactly 2 characters to the right of the cursor
        System.out.println("Letters after cursor (2 chars): " + editor.lettersAfterCursor(2)); // Expected: "DE"
        System.out.println("----------------------------------------\n");

        // Test case 2: Request more characters than available to the right
        System.out.println("Letters after cursor (10 chars): " + editor.lettersAfterCursor(10)); // Expected: "DE"
        System.out.println("----------------------------------------\n");

        // Test case 3: Request 0 characters to the right
        System.out.println("Letters after cursor (0 chars): " + editor.lettersAfterCursor(0)); // Expected: ""
        System.out.println("----------------------------------------\n");
    }
}

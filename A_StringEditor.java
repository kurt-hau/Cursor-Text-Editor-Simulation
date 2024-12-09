package editor;

import java.util.Arrays;

public class A_StringEditor {
	private static final int INITIAL_CAPACITY = 1000;  // The initial size of the data array
	
	private char[] data;		// contents of string
	private int stringSize;		// size of string
	private int cursorPos;		// index of cursor position
	

	// CONSTRUCTOR
	public A_StringEditor() {
		data = new char[INITIAL_CAPACITY];
		stringSize = 0;                    // No characters in the string initially
        cursorPos = 0;                     // Cursor starts at the beginning, at 0th index
	}
	

	public void insert(char c) {
	    // Check if the data array is full; if so, expand it by doubling its current length
	    if (stringSize >= data.length) {
	        data = Arrays.copyOf(data, data.length * 2); // Double array size to accommodate more characters
	    }

	    // Shift characters to the right to make space for the new character at cursorPos
	    System.arraycopy(data, cursorPos, data, cursorPos + 1, stringSize - cursorPos);

	    // Insert the character at the cursor position
	    data[cursorPos] = c;

	    // Update cursor position and string size
	    cursorPos++;       // Move cursor to the right of the inserted character
	    stringSize++;      // Increase the string size to reflect the addition of a new character
	}
	
	public void moveForward() {
	    // Check if the cursor is not at the end of the string, won't go past end
	    if (cursorPos < stringSize) {
	        cursorPos++; // Move the cursor one position to the right
	    }
	}
	
	public void moveBackward() {
	    // Check if the cursor is not at the beginning of the string, won't go before the beginning
	    if (cursorPos > 0) {
	        cursorPos--; // Move the cursor one position to the left
	    }
	}
	
	public void delete() {
	    // Check if there is a character to delete (cursor is not at the end)
	    if (cursorPos < stringSize) {
	        // Shift characters to the left to remove the character at cursorPos
	    		/*Breakdown of args of the arraycopy() method
	    		 * data: Copies from our existing array
	    		 * 
	    		 * cursorPos + 1: starting position within the data array from which we want to begin copying, right of the cursor +1
	    		 * 
	    		 * data: array where the elements will be copied to, the same data array, re-writing same array
	    		 * 
	    		 * cursorPos: Starting place for cursor in the "new" array, cuts out the element that was at cursorPos
	    		 * 
	    		 * stringSize - cursorPos - 1: Then number of elements to copy, total characters from the position just after the cursor to the end of the array
	    		 * 			
	    		 * 
	    		 * 		
	    		 * 
	    		 */
	        System.arraycopy(data, cursorPos + 1, data, cursorPos, stringSize - cursorPos - 1);
	        stringSize--; // Decrease the string size to reflect deletion
	    }
	}
	
	public void backspace() {
		// Check if there is a character to delete, not at beginning of array
	    if (cursorPos > 0) {
	        // Shift characters to the left to remove the character at cursorPos
	    		/*Breakdown of args of the arraycopy() method
	    		 * data: Copies from our existing array
	    		 * 
	    		 * cursorPos: starting position within the data array from which we want to begin copying, right of the cursor
	    		 * 
	    		 * data: array where the elements will be copied to, the same data array, re-writing same array
	    		 * 
	    		 * cursorPos-1: Starting place for cursor in the "new" array, cuts out the element that was before CursorPos
	    		 * 
	    		 * stringSize - cursorPos: Then number of elements to copy, total characters from the cursorPos to the end of the array
	    		 * 			
	    		 */
	        System.arraycopy(data, cursorPos, data, cursorPos-1, stringSize - cursorPos);
	        stringSize--; // Decrease the string size to reflect deletion
	        cursorPos--; //Move the cursorPos to the left one space to be in front of the new character at this position
	    }
	}
	
	public String lettersBeforeCursor(int count) {
		//Will assign this value in the following if/else loop
	    int start;

	    // Determine the starting position for the substring
	    	// If there are enough characters to the left of the cursor, start 'count' characters back
	    if (cursorPos - count >= 0) {
	        start = cursorPos - count;
	        
	    // If fewer characters are available, start at the beginning of the string
	    } else {
	        start = 0;
	    }

	    // Create and return a new String from 'start' to 'cursorPos' in the data array
	    	//Use the String method
	    
	    	//Uses the characters from the char[] data array
	    	//start: start getting characters from the chosen start position
	    	//cursorPos - start: gets this many characters, will stop at cursorPos
	    return new String(data, start, cursorPos - start);
	}
	
	public String lettersAfterCursor(int count) {
	    int end;

	    // Determine the ending position for the substring
	    	// If there are enough characters to the right of the cursor, end after 'count' characters
	    if (cursorPos + count <= stringSize) {
	        end = cursorPos + count;
	        
	    // If count is too big, end at the end of the string
	    } else {
	        end = stringSize;
	    }

	    // Create and return a new String from 'cursorPos + 1' to 'end' in the data array
	    	//start at the position to the right of the cursor
	    	//get numbers from the start all the way until the "end", as many numbers is the difference of the two
	    return new String(data, cursorPos, end - (cursorPos ));
	}
	
	
	// Will be called in the testing doc to help view the outputs
	@Override
    public String toString() {
        StringBuilder result = new StringBuilder("Text: ");
        result.append(new String(data, 0, stringSize))
              .append("\nCursor Position: ")
              .append(cursorPos)
              .append("\nString Size: ")
              .append(stringSize);
        
        return result.toString();
    }
}

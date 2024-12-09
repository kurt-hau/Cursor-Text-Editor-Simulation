package editor;

public class L_StringEditor {
	
	//Node structure for each character in the doubly linked list
	private class Node {
		private char c;		// Character stored in the node
		private Node next;	//Reference to the next node
		private Node prev;	//Reference to the previous node
		
		 // Constructor for Node, initializing with character data
        Node(char data) {
            this.c = data;
            this.next = null;
            this.prev = null;
        }
	}
	
	private Node dummy;       // Dummy node to mark the start of the list
	private Node cursorPos;		// The position of the cursor in the list
									//cursorPos will essentially act like our "first" node
	
	
	// Constructor for L_StringEditor initializes an empty editor with a dummy node
    public L_StringEditor() {
        dummy = new Node('\0');  // Dummy node (using '\0' to indicate it's a placeholder)
        cursorPos = dummy;       // Start cursor at the dummy node
    }
	
	public void insert(char c) {
	    // Create a new node with the character to insert
	    Node newNode = new Node(c);

        // Insert the first node after the dummy if cursor is at the beginning
	    if (cursorPos == dummy) {
            newNode.next = dummy.next;	//Connect TO THE RIGHT
            newNode.prev = dummy;		//Connect TO THE LEFT
            
            //If the list is not empty, meaning there is something after dummy
            if (dummy.next != null) {		
                dummy.next.prev = newNode; 	//Connect FROM THE RIGHT
            }
            dummy.next = newNode;	//Connect FROM THE LEFT
            cursorPos = newNode;  // Move cursor to the newly inserted node
	      
            
	    // If you're inserting anywhere other than the front, Insert newNode right after the cursor position
	    } else {
	        newNode.prev = cursorPos;		//Connect TO THE LEFT
	        newNode.next = cursorPos.next;	//Connect TO THE RIGHT

	        //Connect FROM THE RIGHT
	        // If there’s a node after cursorPos (meaning cursorPos isn't at the end), update its prev reference to the new node
	        if (cursorPos.next != null) {
	            cursorPos.next.prev = newNode;
	        }

	        //Connect FROM THE LEFT
	        // Link cursorPos's next to the new node
	        cursorPos.next = newNode;

	        // Move cursorPos to the new node, placing it immediately after the inserted character
	        cursorPos = newNode;
	    }
	}

	
	public void moveForward() {
	    // Check if there is a node to the right of the cursor, and that there is a node at the cursorPos
	    if (cursorPos != null && cursorPos.next != null) {
	        // Move the cursor one position to the right
	        cursorPos = cursorPos.next;
	    }
	}
	
	public void moveBackward() {
	    // Check if there is a node to the left of the cursor
	    if (cursorPos != null && cursorPos.prev != null) {
	        // Move the cursor one position to the left
	        cursorPos = cursorPos.prev;
	    }
	}
	
	public void delete() {
	    // Check if there is a node to the right of the cursor (meaning cursor is not at the end)
	    if (cursorPos == null || cursorPos.next == null) {
	        return; // Do nothing if at the end
	    }

	    Node toDelete = cursorPos.next; // The node to delete is immediately to the right of cursorPos

	    // Adjust links to remove toDelete from the list
	    if (toDelete.next != null) {
	        toDelete.next.prev = cursorPos; // Disconnect FROM THE RIGHT
	    }
	    cursorPos.next = toDelete.next; // Disconnect FROM THE LEFT

	    // Clear references for garbage collection
	    toDelete.next = null;
	    toDelete.prev = null;
	}

	
	public void backspace() {
	    // Check if the cursor is at the beginning of the list (at the dummy node)
	    if (cursorPos == null || cursorPos == dummy) {
	        return; // Do nothing if at the beginning
	    }

	    Node toDelete = cursorPos; // Deleting the node at the current cursorPos

	    // Move cursorPos one node to the right, if possible, or to the left if at the end
	     cursorPos = cursorPos.prev;

	    // Remove the toDelete node by adjusting the links on either side
	    if (toDelete.prev != null) {
	        toDelete.prev.next = toDelete.next; // Disconnect FROM THE LEFT
	    }
	    if (toDelete.next != null) {
	        toDelete.next.prev = toDelete.prev; // Disconnect FROM THE RIGHT
	    }

	    // Clear references for garbage collection
	    toDelete.next = null;
	    toDelete.prev = null;
	}

	
	public String lettersBeforeCursor(int count) {
	    StringBuilder result = new StringBuilder();	//initialize a StringBuilder to accumulate the characters
	    Node current = cursorPos.prev; 	//Start from the node immediately to the left of the cursor
	    int charsCollected = 0;	//
	    
	    //Manually add the first character before looping
	    	//Accounts for the character "at" cursorPos
	    if(cursorPos != null && cursorPos != dummy && charsCollected < count){
	    	result.insert(0, cursorPos.c);
	    	charsCollected ++;
	    }
	    

	    // GO LEFT, collecting characters until reaching the dummy node, or collecting 'count' characters
	    while (current != null && current != dummy && charsCollected < count) {
	        result.insert(0, current.c); // Insert character at the beginning of the result, shifts all prior characters up one index
	        									//needed since we are collecting in reverse order
	        current = current.prev;      // Move to the previous node
	        charsCollected++;       // Increment the count of collected characters
	    }

	    // Result contains up to 'count' characters or all available characters if fewer than 'count'
	    	//Call toString on the StringBuilder once we get what we need
	    return result.toString();
	}

	
	public String lettersAfterCursor(int count) {
		StringBuilder result = new StringBuilder();	//initialize a StringBuilder to accumulate the characters
	    Node current = cursorPos.next; 	//Start from the node immediately to the left of the cursor
	    int charsCollected = 0;	//

	    // GO RIGHT, collecting characters until reaching the end of the list, or collecting 'count' characters
	    while (current != null && charsCollected < count) {
	        result.append(current.c); // Insert character at the end, since we are collecting in straightforward order
	        current = current.next;      // Move to the next node
	        charsCollected++;       // Increment the count of collected characters
	    }

	    // Result contains up to 'count' characters or all available characters if fewer than 'count'
	    	//Call toString on the StringBuilder once we get what we need
	    return result.toString();
	}
	
	@Override
	//Needed to print out my tests
	public String toString() {
	    StringBuilder result = new StringBuilder("Text: ");
	    Node current = cursorPos;

	    // Move cursorPos back to the beginning of the list
	    while (current != null && current.prev != null) {
	        current = current.prev;
	    }

	    // Append all characters in the list to the result
	    while (current != null) {
	        
	    	//Keep adding the characters
	        result.append(current.c); 
	        current = current.next;
	    }

	  
	    // Show cursor position at the final cursor node, if it exists
	    if (cursorPos != null) {
	        result.insert(result.indexOf(String.valueOf(cursorPos.c)) + 1, '|');
	    }

	    return result.toString();
	}

}

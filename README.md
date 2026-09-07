# Mini Hospital Emergency Management System

## Project Description
This project is a Java-based console application developed for a Data Structures and Algorithms assignment. It demonstrates the use of four fundamental data structures:

- Binary Search Tree (BST) for patient records
- Queue for emergency patient management
- Stack for treatment history
- Singly Linked List for patient visit history

## Objectives
- Manage patient registration and record lookup using a custom BST
- Handle emergency patients using FIFO queue operations
- Record completed treatments using a custom LIFO stack
- Maintain each patient's visit history with a singly linked list
- Build a user-friendly hospital workflow in a console application

## Technologies Used
- Java
- VS Code
- Standard Input/Output (Scanner)

## Data Structures Used
1. BST - Patient records are stored by unique Patient ID
2. Queue - Emergency patients are serviced in arrival order
3. Stack - Treatment records are stored in reverse chronological order
4. Linked List - Each patient keeps a history of previous visits

## System Features
- Register, search, and delete patients
- Display all patients in ascending Patient ID order
- Add emergency patients to queue
- Dequeue patients for treatment
- Push completed treatments into stack
- View and pop treatment records
- Add, remove, search, and display patient visits
- Input validation for invalid menu inputs and data

## Class Structure
- Main.java
- Patient.java
- PatientNode.java
- PatientBST.java
- QueueNode.java
- EmergencyQueue.java
- TreatmentRecord.java
- StackNode.java
- TreatmentStack.java
- Visit.java
- VisitNode.java
- VisitHistory.java

## How Each Data Structure Works in This Project
### BST (Patient Records)
The BST stores patients using Patient ID as the key. Insertions follow BST rules so all IDs are placed in order. Search and delete operations use comparisons with the current node value.

### Queue (Emergency Management)
The queue stores patients waiting for treatment in order of arrival. The first patient inserted is the first patient served, which follows FIFO behavior.

### Stack (Treatment History)
The treatment stack stores completed cases. The latest completed treatment is removed first, demonstrating LIFO behavior.

### Singly Linked List (Visit History)
Each patient has their own linked list of visits. Visits are appended to the end and can be deleted or searched by visit ID.

## How to Compile and Run
Open a terminal in the project folder and run:

```bash
javac -d out src/*.java
java -cp out Main
```

## Sample Operations
- Register patients with unique IDs
- Search for a patient by ID
- Add patients to the emergency queue
- Call the next patient for treatment
- Complete treatment and push record to stack
- Add a patient visit and view history

## Testing
This project includes realistic testing scenarios such as:
- Duplicate patient ID prevention
- Searching for missing patients
- Displaying patients in ascending order
- Empty queue and empty stack handling
- Searching and removing visit records

## Author
Student Name: [Your Name]
Course: CIT300 - Data Structures and Algorithms

## Suggested GitHub Commit Sequence
1. Initial project structure
2. Added Patient class
3. Implemented Patient BST insertion
4. Added BST search and traversal
5. Added BST deletion
6. Implemented emergency queue
7. Implemented treatment stack
8. Implemented patient visit linked list
9. Added input validation
10. Added testing
11. Updated README

## Notes
This project was designed to be simple, educational, and easy to understand for demonstrations and assignment explanations.

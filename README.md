# DatabaseDev

This repository contains two main projects:

1. **File Editing Project**: A simple text editor built in Java to familiarize with file systems and concurrency in Java.
2. **Custom Database Development**: The long-term goal of building a custom database engine in Java.

## Project Structure

- **File Editing Project**: Implements basic file operations (open, edit, save), concurrency management for file I/O, undo/redo, and other text editing features.
- **Custom Database Development**: The end goal is to build a custom database engine in Java, leveraging the concurrency features in the language.

## File Editing Project

### Features

- **Open, Edit, and Save Text Files**

  - Read files from disk and display them in the editor.
  - Save files to disk after editing.

- **Undo/Redo Functionality**

  - Track edits and support undo/redo operations to revert back to previous text states.

- **Search and Replace**

  - Search for specific text and replace it throughout the document.

- **Auto-Save**

  - Automatically save the document periodically in the background.

- **Syntax Highlighting** (Optional)

  - Syntax highlighting for specific file types (e.g., Java, HTML, or Markdown).

- **Concurrency Support**
  - File operations (open, save, etc.) are handled asynchronously to avoid blocking the UI, ensuring smooth user experience.

### Technologies Used

- **Java**: The programming language used to develop the text editor.
- **Swing/JavaFX**: Used for building the graphical user interface (GUI).
- **ExecutorService**: Java’s concurrency library to manage background tasks like file I/O.
- **BufferedReader & BufferedWriter**: For efficient file reading and writing.

### Getting Started

#### Prerequisites

Make sure you have the following installed on your system:

- Java 8 or later
- A Java IDE or text editor (e.g., IntelliJ IDEA, Eclipse, Visual Studio Code)

#### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/HusseinBitambuka/DatabaseDev.git
   ```

````

2. Navigate to the project directory:

   ```bash
   cd DatabaseDev
   ```

3. Open the project in your IDE or build using Maven/Gradle if applicable.

### Running the Application

1. Compile and run the main class `TextEditor.java`:

   ```bash
   javac TextEditor.java
   java TextEditor
   ```

2. The text editor will open, allowing you to open a text file, edit it, and save changes back to the disk.

### Usage

* **Open File**: Click on the "Open" menu item to open a file and load its contents into the editor.
* **Edit Text**: Type into the editor window to make changes.
* **Save File**: Click on the "Save" menu item to save changes to the file.
* **Undo/Redo**: Use the undo and redo options in the edit menu to revert or reapply recent changes.
* **Search/Replace**: Use the "Find" and "Replace" options from the edit menu to search for specific text and replace it.

### Future Features

* **Multiple File Support**: Allow opening and editing multiple files in tabs.
* **Improved Syntax Highlighting**: Extend the syntax highlighting to support more file types and custom themes.
* **File Versioning**: Implement version history to track changes made to documents.
* **Plugins/Extensions**: Add the ability for users to install custom plugins to extend the editor's functionality.

---

## Custom Database Development

### Project Overview

The ultimate goal of this project is to design and build a custom database engine in Java. This database engine will utilize Java's concurrency features for efficient data management and implement core database functionalities, including:

* **Data Storage**: Implement mechanisms for storing and retrieving data from files or in-memory storage.
* **Query Execution**: Support basic SQL-like queries.
* **Concurrency**: Use Java's `ExecutorService` and other concurrency tools to handle multiple database operations simultaneously.
* **Indexing**: Implement simple indexing techniques like B-trees or hash indexes for fast data retrieval.
* **Transactions**: Support basic ACID properties (Atomicity, Consistency, Isolation, Durability).

### Technologies Used

* **Java**: The programming language used to build the database engine.
* **Concurrency**: Leveraging Java's concurrency libraries to handle database operations efficiently.
* **File Handling**: Using Java's file I/O libraries to manage persistent storage.
* **Indexes**: Implementing B-trees or hash indexes to optimize query performance.

### Roadmap

1. **Basic File Storage**: Implement simple mechanisms for saving and loading data from files.
2. **Query Execution**: Design a query language (SQL or custom) and implement a query engine.
3. **Indexing**: Add indexing techniques for faster search and retrieval.
4. **Concurrency and Transactions**: Implement multi-threaded handling and basic transaction support.
5. **Optimization**: Optimize the database for large datasets and real-time performance.

---
### File structure

DatabaseDev/
│
├── file-editor/                     # File editing project (Java text editor)
│   ├── src/                         # Source code for the text editor
│   │   ├── com/                     # Java package for organizing classes
│   │   │   ├── editor/              # Text editor core functionality (open, save, undo, etc.)
│   │   │   ├── ui/                  # GUI-related classes (Swing/JavaFX components)
│   │   │   ├── concurrency/         # Concurrency-related classes (ExecutorService, Background Tasks)
│   │   ├── TextEditor.java          # Main class for running the text editor
│   ├── resources/                   # Non-code resources (icons, themes, etc.)
│   ├── README.md                    # Documentation for the file editor project
│   ├── build.gradle or pom.xml      # Build configuration (Gradle or Maven)
│
├── custom-database/                 # Custom database development project
│   ├── src/                         # Source code for the custom database engine
│   │   ├── com/                     # Java package for organizing classes
│   │   │   ├── db/                  # Core database logic (storage, indexing, query execution)
│   │   │   ├── concurrency/         # Concurrency-related classes for database operations
│   │   │   ├── filemanagement/      # File handling for database storage
│   │   ├── DatabaseEngine.java      # Main class for running the database engine
│   ├── resources/                   # Non-code resources for the database (e.g., config files)
│   ├── README.md                    # Documentation for the custom database project
│   ├── build.gradle or pom.xml      # Build configuration (Gradle or Maven)
│
├── .gitignore                       # Git ignore file to exclude unnecessary files
├── LICENSE.md                       # License file for the repository
├── README.md                        # Main documentation for the entire repository
└── docs/                            # Additional project documentation (e.g., design, architecture)
    ├── architecture.md              # Document for high-level project architecture
    ├── database_design.md           # Design details for the custom database
    └── future_features.md           # Roadmap and feature suggestions


````

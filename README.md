![Banner Image](./banner.png)

# Clojure Testing Project

## Overview
This project is a demonstration of Clojure's unique capabilities in handling various software testing scenarios. It showcases the distinct syntax and semantics of Clojure, a modern Lisp dialect known for its functional programming features and robust concurrency support. Through a series of small, focused tests, this project explores different aspects of Clojure programming, emphasizing its application in testing various edge cases and functionalities.

## Objective
The primary goal is to understand and utilize Clojure's testing frameworks and idiomatic practices. The tests cover a range of scenarios from basic functionality checks to more complex edge case validations, providing insights into both Clojure's language features and practical testing strategies.

## Project Structure
## Project Structure

The project is organized as follows:

- `src/`: Contains the source code for the project.
  - `problems/`: Holds Clojure files for individual problem statements and solutions.
    - `problem_1.clj`: Solution to problem 1.
    - `problem_2.clj`: Solution to problem 2.
    - `problem_3.clj`: Solution to problem 3 and associated unit tests.
  - `utils/`: Utilities and helpers used across the project.
    - `utils.clj`: General utility functions.
    - `invoice_item.clj`: Functions related to invoice item processing.
    - `invoice_spec.clj`: Specifications for invoice validation.
- `deps.edn`: A Clojure CLI file that specifies dependencies and other configuration options.
- `invoice.edn`: EDN file that likely contains example invoice data.
- `invoice.json`: JSON file that likely contains example invoice data in JSON format.
- `problems.README.md`: Documentation for the problems being solved in the project.

## Key Features
- **Functional Programming**: Clojure's functional programming paradigm is at the heart of this project, promoting the use of immutable data structures and pure functions.
- **Problem Solving**: The `problems` directory contains solutions to various challenges, each highlighting different aspects of Clojure's capabilities.
- **Specification and Validation**: Utilizes `clojure.spec` to define robust specifications for data validation, as seen in `invoice_spec.clj`.
- **Test-Driven Development**: Demonstrates Clojure's built-in testing framework with unit tests in `problem_3.clj`, ensuring code correctness and reliability.
- **Utility Functions**: The `utils` namespace provides reusable functions that support the application's core functionality.
- **Data Handling**: Showcases Clojure's handling of different data formats with EDN and JSON files, facilitating data interchange and parsing.
- **Documentation**: Includes a detailed `problems.README.md` for an in-depth understanding of the individual problems and their solutions.
- **Extensibility**: The modular structure of the project allows for easy expansion and addition of new problems and utility functions.

## Running the Tests
Ensure you have Clojure and Leiningen installed. To run the tests, navigate to the project root run your REPL client.

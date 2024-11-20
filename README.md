# Text Interface

While GUIs are the most popular form of User Interface, there are many applications that are better suited for running purely in the terminal in a text-based format.
Possible reasons for this are:
- The program is simple and doesn't require a GUI
- The program is designed to run in an ecosystem that doesn't support GUIs
- The program requires efficient runtime that would be hindered by a GUI

However, building a text interface in native Java often leads to expansive, unscalable designs that slow down development.

This TextInterface library is designed to streamline the interface development process by providing a system that handles many of the repetitive tasks required by text interfaces.

This library focuses on:
- Acclerating devlopment by providing tools to quickly create text interfaces
- Improving the readability and maintainability of the codebase by providing a clean, modular approach to building text interfacces
- Allowing developers to design the way that is best suited for their system

The core unit of the library is the Scene. All scenes are built up using a Builder Design pattern. Every scene can also have child scenes, which are then attatched to the scene. Scenes can also have functions attatched to them.

A [Demo Application](https://github.com/Caleb-Leavell/TextInterface/blob/main/src/main/java/com/calebleavell/textinterface/DemoApp.java) has been provided to show the recommended development style.
This Demo Application implements a simple random number generator that continuously takes a maximum number from the user, then displays the generated number.
When run, the Demo App works as follows:

![image](https://github.com/user-attachments/assets/098fda3e-3c05-4b27-8b6d-0b124ecfb720)

This functionality, which includes gathering user input, generating the number, displaying the output, and choosing a scene, is all achieved in around 100 lines of code, while utilizing only 3 
declared variables in the entire program.

# v1.2
This release add the following features:

Input Listeners: This is a similar concept to Event Handlers. Input Listeners get updated every time an Inputtable (or any scene that takes an input) and then updates itself to match or to do something specific based off of it.
Runtime Scene Selection List Generation: the NumberedSceneSelectorScene class is enhanced with its Builder's sceneList method being overloaded to be able to take in scene names, which are converted into the actual list of scenes when the object is run.
Recursive Termination: Terminating a scene also terminates its children, and their children, etc.
These features improve the ability to keep things attached to scenes and not having to rely on disjointed development. It also reduces the need for use of reflective methods, which improves safety.

# v1.1
Adjusts the getChild methods to all be overloaded. Adds 2 new overloaded versions, one for name and one for ID, that can return the actual class of the scene selected, rather than a polymorphed Scene type. This significantly reduces the amount of reflection needed.

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

To make the design as modular as possible, it is recommended to utilize reflection to access scene methods inside functions.
Note that this is optional, and it is possible to do develop without reflection. Reflection simply improves the readability of the code.

A [Demo Application](https://github.com/Caleb-Leavell/TextInterface/blob/main/src/main/java/com/calebleavell/textinterface/DemoApp.java) has been provided to show the recommended development style.


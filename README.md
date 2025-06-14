# Eco-Friendly Smart Home Energy Consumption & Generation Simulator (Java & GUI) with Database Integration

This repository features a **Java Object-Oriented Programming (OOP) project** that simulates a smart home environment. It incorporates various smart devices that consume energy and renewable energy sources that generate it. The simulator dynamically calculates energy consumption, renewable energy generation, and the overall cost based on a configurable electricity price. This version of the simulator provides both a **command-line interface (CLI)** and a **graphical user interface (GUI)** for interactive simulation.



## Project Structure

The project is thoughtfully organized into the following Java classes, demonstrating key OOP principles:

* **`EnergyConsumer` (Abstract Class):**
    Represents a generic energy-consuming device. It includes common attributes like `name`, `power consumption`, and `status (on/off)`. It provides methods to turn the device on/off, calculate energy consumption, and an abstract `displayStatus()` method for concrete device implementations.

* **`RenewableEnergySource` (Interface):**
    Defines the contract for all renewable energy sources, requiring a `generateEnergy()` method.

* **`SmartLight` (Class):**
    Extends `EnergyConsumer` and models a smart light with brightness control and an optional occupancy sensor. It implements methods to dim the light and overrides `turnOn()` to account for the occupancy sensor. `displayStatus()` is also implemented to show specific light attributes.

* **`SmartHVAC` (Class):**
    Extends `EnergyConsumer` and represents a smart HVAC (Heating, Ventilation, and Air Conditioning) system. It features target temperature and fan speed control, dynamically adjusting power consumption. `displayStatus()` displays the HVAC's specific attributes, including current temperature.

* **`SmartRefrigerator` (Class):**
    Extends `EnergyConsumer` and simulates a smart refrigerator with internal temperature control. It implements methods to set the internal temperature and overrides `displayStatus()` to show the refrigerator's internal temperature.

* **`SolarPanel` (Class):**
    Implements `RenewableEnergySource` and represents a solar panel. It generates energy based on its surface area, efficiency, and sunlight intensity. Includes a method to set the current sunlight intensity.

* **`WindTurbine` (Class):**
    Implements `RenewableEnergySource` and represents a wind turbine. It generates energy based on its blade diameter, efficiency, and wind speed. Includes a method to set the current wind speed.

* **`SmartHomeSimulator` (Class):**
    The core logic class that manages collections of `EnergyConsumer` devices and `RenewableEnergySource` objects. It allows adding devices and renewable sources. The `simulate()` method calculates total energy consumption, renewable energy generation, and overall cost based on the provided electricity price. This class serves as the backend for both CLI and GUI interactions.

* **`SmartHomeGUI` (Class):**
    Provides a **graphical user interface** for the `SmartHomeSimulator`. Users can add and control devices, monitor energy consumption and generation, and set simulation parameters. It's built using **Java Swing** (or JavaFX, depending on implementation specifics).

* **`SmartHomeApp` (Class):**
    The main entry point of the application, responsible for launching the GUI.



## How to Run the Simulation

### Graphical User Interface (GUI)

To run the GUI version of the Smart Home Simulator:

1.  **Ensure you have a Java Development Kit (JDK) installed.**
2.  **Clone the repository** to your local machine:
    ```bash
    git clone <repository_url>
    ```
    (Replace `<repository_url>` with the actual URL of your GitHub repository.)
3.  **Navigate to the project directory**:
    ```bash
    cd smart-home-simulator
    ```
4.  **Compile all Java files**, including the GUI-related classes:
    ```bash
    javac SmartHomeSimulator.java EnergyConsumer.java RenewableEnergySource.java SmartLight.java SmartHVAC.java SmartRefrigerator.java SolarPanel.java WindTurbine.java SmartHomeGUI.java SmartHomeApp.java
    ```
5.  **Run the GUI application**:
    ```bash
    java SmartHomeApp
    ```
    This command will launch the graphical user interface of the Smart Home Simulator.



## GUI Screenshots

Here are some screenshots showcasing the Smart Home Simulator's GUI:

### Login Screen
![Login Screen](https://github.com/iamdurjoybarua/Eco-Friendly-Smart-Home-Simulator/blob/007c159284b4b5839a05d5af62af1552cae0bb62/Images/Login%20Screen.png)

*The initial login screen of the Smart Home Controller.*

### Main Controller
![Main Controller](https://github.com/iamdurjoybarua/Eco-Friendly-Smart-Home-Simulator/blob/007c159284b4b5839a05d5af62af1552cae0bb62/Images/Main%20Controller.png)

*The main interface for controlling smart devices.*

### Setting Brightness (80%)
![Setting Brightness 80%](https://github.com/iamdurjoybarua/Eco-Friendly-Smart-Home-Simulator/blob/007c159284b4b5839a05d5af62af1552cae0bb62/Images/Setting%20Brightness%20(80%25).png)

*Input dialog for setting the brightness of a light to 80%.*

### Setting Brightness (30%)
![Setting Brightness 30%](https://github.com/iamdurjoybarua/Eco-Friendly-Smart-Home-Simulator/blob/007c159284b4b5839a05d5af62af1552cae0bb62/Images/Setting%20Brightness%20(30%25).png)

*Input dialog for setting the brightness of a light to 30%.*

### Setting Target Temperature
![Setting Target Temperature](https://github.com/iamdurjoybarua/Eco-Friendly-Smart-Home-Simulator/blob/007c159284b4b5839a05d5af62af1552cae0bb62/Images/Setting%20Target%20Temperature.png)

*Input dialog for setting the target temperature of the HVAC system.*

### Setting Fan Speed
![Setting Fan Speed](https://github.com/iamdurjoybarua/Eco-Friendly-Smart-Home-Simulator/blob/007c159284b4b5839a05d5af62af1552cae0bb62/Images/Setting%20Fan%20Speed.png)

*Input dialog for setting the fan speed of the HVAC system.*

### Main Controller with Updated Status
![Main Controller Updated Status](https://github.com/iamdurjoybarua/Eco-Friendly-Smart-Home-Simulator/blob/007c159284b4b5839a05d5af62af1552cae0bb62/Images/Main%20Controller%20with%20Updated%20Status.png)

*The main controller after some devices have been turned on and their settings adjusted.*

### Terminal Output (GUI)
![Terminal Output GUI](https://github.com/iamdurjoybarua/Eco-Friendly-Smart-Home-Simulator/blob/007c159284b4b5839a05d5af62af1552cae0bb62/Images/Terminal%20Output.png)

*Example of the terminal output showing device status changes from the GUI with Database.*

### Sample Output (Core)
![Sample Output Core](https://github.com/iamdurjoybarua/Eco-Friendly-Smart-Home-Simulator/blob/007c159284b4b5839a05d5af62af1552cae0bb62/Images/Sample%20Output.png)

*Example of the core simulation output showing device status and energy calculations.*



## Features Demonstrated

This project effectively demonstrates several core **Object-Oriented Programming (OOP) concepts** and GUI development practices:

* **Abstraction:** The `EnergyConsumer` abstract class establishes a common interface and behavior for all energy-consuming devices, hiding their internal complexities.
* **Inheritance:** Concrete device classes (`SmartLight`, `SmartHVAC`, `SmartRefrigerator`) inherit properties and behaviors from `EnergyConsumer`, then extend them with their unique functionalities.
* **Polymorphism:** The `displayStatus()` method is overridden in each concrete device class to provide device-specific status information. The `SmartHomeSimulator` can interact with various device types uniformly through their common interfaces.
* **Interfaces:** The `RenewableEnergySource` interface defines a clear contract for all renewable energy generators, promoting loose coupling.
* **Encapsulation:** Class members (attributes) are typically kept `private`, with access controlled via `public` methods (getters and setters), ensuring data integrity and maintainability.
* **Lists and Iteration:** The `SmartHomeSimulator` leverages `ArrayList` (or similar collections) to efficiently manage and iterate through lists of devices and renewable sources for simulation logic.
* **GUI Development:** Showcases the practical application of **Java Swing** (or JavaFX) for building an interactive and user-friendly interface.
* **Event Handling:** The GUI effectively uses event listeners to respond to user interactions (e.g., button clicks, menu selections), enabling dynamic control over the simulation.
* **Visual Representation:** The GUI provides an intuitive visual way to interact with and monitor the smart home environment, enhancing user experience.



## Potential Enhancements

This project offers a solid foundation and can be significantly expanded with the following enhancements:

* More sophisticated energy consumption models for different devices, considering factors like ambient temperature, usage patterns, etc.
* A wider variety of smart devices (e.g., smart plugs, security cameras, robotic vacuum cleaners).
* More complex and realistic renewable energy models (e.g., historical weather data integration).
* Real-time simulation updates and live data visualization within the GUI.
* Data persistence using databases (like **SQLite**, as updated with `sqlite-jdbc-3.50.1.0`) or file I/O to save and load simulation states and GUI configurations.
* Event-driven behavior where devices automatically react to changes in the environment or other devices (e.g., lights turning on when occupancy is detected).
* Integration of energy storage solutions (e.g., batteries) to manage excess generated energy.
* User customization options for the smart home layout within the GUI (e.g., drag-and-drop device placement).
* Network capabilities to simulate remote control of devices or communication between homes.



## References and Resources

This project was developed with inspiration and knowledge drawn from the following resources:

* **"Head First Java"** by Kathy Sierra and Bert Bates (O'Reilly Media)
    * A highly recommended beginner-friendly book for understanding Java and core OOP concepts like encapsulation, inheritance, and polymorphism.
* **"Effective Java"** by Joshua Bloch (Addison-Wesley)
    * An advanced guide on Java best practices, crucial for writing robust, reusable, and maintainable code. The Third Edition (2018) is particularly relevant.
* **Oracle Java Documentation**
    * URL: [https://docs.oracle.com/en/java/](https://docs.oracle.com/en/java/)
    * The official documentation for Java SE, providing comprehensive API details for built-in Java features, including collections (like `ArrayList` and `List`) and GUI frameworks (Swing/JavaFX).
* **Energy Consumption Data (General Reference)**
    * Source: U.S. Energy Information Administration (EIA)
    * URL: [https://www.eia.gov/energyexplained/use-of-energy/](https://www.eia.gov/energyexplained/use-of-energy/)
    * Provides real-world data on energy usage that influenced the approximated consumption values in the simulator (e.g., 60W for lights, 1.5 kWh for HVAC).
* **"Carbon Footprint of Electricity Generation" (Simplified Metrics)**
    * Source: International Energy Agency (IEA)
    * URL: [https://www.iea.org/](https://www.iea.org/)
    * The simulator uses a simplified 0.5 kg CO2 per kWh for carbon footprint calculations, informed by average global estimates from such sources.
* **"The Internet of Things"** by Samuel Greengard (MIT Press)
    * Explores foundational IoT concepts, including smart devices and energy management, directly relevant to the simulator's functionality.
* **"Design Patterns: Elements of Reusable Object-Oriented Software"** by Erich Gamma et al. ("Gang of Four" book) (Addison-Wesley)
    * A classic in OOP design, covering patterns like Singleton (potentially useful for `SmartHomeController` if implemented) and Factory (a suggested enhancement).
* **JavaPoint Tutorials**
    * URL: [https://www.javatpoint.com/java-oops-concepts](https://www.javatpoint.com/java-oops-concepts)
    * A free online resource offering practical examples of OOP in Java, including inheritance and abstraction, which are central to this project's device hierarchy.
* **GeeksforGeeks: Java Examples**
    * URL: [https://www.geeksforgeeks.org/java/](https://www.geeksforgeeks.org/java/)
    * Provides numerous code snippets for various Java concepts, including abstract classes and collections.
* **W3Schools Java Tutorial**
    * URL: [https://www.w3schools.com/java/](https://www.w3schools.com/java/)
    * Free tutorials on Java programming, covering syntax, OOP concepts (classes, objects, inheritance, abstraction), and collections (`ArrayList`).
* **Google Gemini**
    * URL: [https://gemini.google.com/share/968f7aae37d2](https://gemini.google.com/share/968f7aae37d2)
* **X Grok**
    * URL: [https://x.com/i/grok/share/i1pbBUaa8kz05cPZu6EOtLWgi](https://x.com/i/grok/share/i1pbBUaa8kz05cPZu6EOtLWgi)
* **Graphviz Online**
    * URL: [https://dreampuf.github.io/GraphvizOnline/](https://dreampuf.github.io/GraphvizOnline/)


## Author

Durjoy Barua (https://github.com/iamdurjoybarua)

Feel free to contribute to this project by submitting pull requests with bug fixes or new features, especially for the GUI & Database!

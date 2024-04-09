Restaurant Management System 
by Alex Petmecky (AlexPetmecky), Cohen Smith (cssmith8), Caleb Patton (cap39)

Our program is implemented in the latest version of Java (JRE-21). It is a Maven project; import it as such in your Java IDE of your choice.
The repo is separated into two projects, with the top level directories "Selected" and "Unselected"
The "Selected" project contains the blackboard architecture. Import and run this project and the GUI should pop up.
The "Unselected" project contains the event-based architecture.

Example steps for Eclipse IDE:
Download latest version of Java.
Download and install Eclipse IDE.
Go to File > Import > Maven > Existing maven projects 
Select "Selected" or "Unselected" as the root directories.
Once imported, press the green run button at the top.

The Blackboard style of software architecture emphasizes flexibility in managing changing information, making it well-suited for dynamic environments where data is constantly evolving. Its design allows for easy implementation of parallel processing, enabling efficient utilization of computing resources and improved performance. Additionally, the Blackboard architecture fosters modularity, facilitating the development of complex systems by breaking them down into smaller, more manageable components. On the other hand, Event-Based architecture operates on a different principle, where changes in one component do not necessitate changes in another. This decoupling enables greater resilience, as failures or updates in one part of the system do not cascade into failures elsewhere, enhancing overall robustness. Real-time processing is a key feature of Event-Based architecture, enabling immediate responses to events as they occur, making it suitable for applications where timely reactions are crucial. Overall, while both architectures offer advantages in different contexts, the Blackboard style prioritizes flexibility and modularity, whereas Event-Based architecture emphasizes resilience and real-time processing.
Given this, we decided to choose Blackboard architecture as our final canidate. The modular nature lines up well for a system which may need new data elements added or removed in the future, and may encounter sudden expansion of it's requirements. Additionally, implementing it in a distributed manner would be easier, with centralized data stores and components to access them and the potential for multiple concurrent, decentralized end-user GUIs to access the central components easily. The advantages of Blackboard seemed to line up with our non-functional requirements well while the advantages of Event-Based did not outweight the downsides.


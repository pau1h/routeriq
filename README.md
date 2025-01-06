
# IoT Router Enhancer

🚧 **Work in Progress** 🚧  
This project transforms your router into an IoT-enabled device. With this tool, you can trigger automated rules and actions based on who joins or leaves your network.

---

## ✨ Features

- **Device Detection**: Identify and track devices connecting or disconnecting from your network.
- **Automated Actions**: Trigger custom rules based on network activity.
- **IoT Integration**: Expand your router’s functionality into a smart home hub.

---

## 📂 Project Structure

### Core Files

- **`Database.java`**  
  Handles data storage and management for tracking devices and network events.

- **`Device.java`**  
  Represents a connected device and its associated metadata.

- **`Scraper.java`**  
  Handles network scraping to detect device presence and activity in real-time.


---

## 🛠️ How It Works

1. **Network Monitoring**:  
   Scraper scans the network periodically to detect device presence.  

2. **Device Management**:  
   Detected devices are stored and tracked in a database for real-time updates.

3. **Trigger Automation**:  
   Custom rules can be defined to execute specific actions when devices join or leave.

---

## 🚀 Getting Started

### Prerequisites

- A compatible router with administrative access. Currently the only compatible router is the HUMAX BGW320-500.
- Java Development Kit (JDK) installed.
- Basic knowledge of Java and networking.

### Installation

1. Clone this repository:  
   ```bash
   git clone https://github.com/pau1h/routeriq.git 
   ```
2. Navigate to the project directory:  
   ```bash
   cd routeriq
   ```
3. Build and run the project:  
   ```bash
   javac *.java
   java Spring
   ```

---

## 📋 Roadmap

- [ ] Add support for more router models.
- [ ] Enhance rule creation interface.
- [ ] Integrate with popular IoT platforms (e.g., Home Assistant, Google Home).
- [ ] Implement a web-based management dashboard.

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository.
2. Create a new branch:  
   ```bash
   git checkout -b feature/YourFeature
   ```
3. Make your changes and commit them:  
   ```bash
   git commit -m "Add YourFeature"
   ```
4. Push to the branch:  
   ```bash
   git push origin feature/YourFeature
   ```
5. Open a pull request.

---

## 📝 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## 📧 Contact

For questions or suggestions, feel free to open an issue. 

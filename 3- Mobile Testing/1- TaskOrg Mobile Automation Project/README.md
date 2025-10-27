# Mobile-Testing-Project
# 📱 Mobile Testing Project

This repository contains automated test scripts for different mobile applications using **Appium**, **Android Studio**, **Selenium**, and **TestNG**.  
It is part of a QA bootcamp/team project focused on building a complete mobile automation testing framework.

---

## 🚀 Tech Stack
- Java (JDK 8/11/17 supported)  
- Appium Java Client 7.6  
- Selenium WebDriver  
- TestNG (test execution & reporting)  
- Maven (dependency management)  
- Android Studio Emulator (Pixel 7 / Pixel 7 Pro)  
- Appium Inspector (element inspection)  

---

## 📂 Project Structure
```
src
 ├── main
 │    └── java
 │         └── base        # BaseTest and utilities
 │
 └── test
      └── java
           └── testcases   # Test scripts (Login, Delete Task, Workflows, etc.)
```

- **BaseTest.java** → Handles setup/teardown of Appium driver  
- **Page Object Classes** → Organized locators & reusable methods  
- **Test Classes** → Actual test cases (with TestNG annotations)  

---

## ⚙️ Setup Instructions

### 1️⃣ Prerequisites
- Install [Java JDK](https://adoptium.net/)  
- Install [Maven](https://maven.apache.org/)  
- Install [Node.js](https://nodejs.org/)  
- Install [Appium Server](https://appium.io/)  
- Install [Android Studio](https://developer.android.com/studio) + SDK tools  

### 2️⃣ Clone the Repository
```bash
git clone https://github.com/<your-username>/<your-repo-name>.git
cd <your-repo-name>
```

### 3️⃣ Install Dependencies
```bash
mvn clean install
```

### 4️⃣ Start Appium Server
```bash
appium --address 127.0.0.1 --port 4723
```

### 5️⃣ Run Tests
```bash
mvn test
```

---

## 🧪 Workflows Tested
- **Tasks.org App**
  - Add a Task  
  - Edit a Task  
  - Delete a Task  
  - Swipe & Gestures  

- **Duolingo App**
  - Login & Invalid Login Scenarios  
  - Signup  
  - Lesson navigation  

- **Wikipedia App**
  - Search Articles  
  - Open Random Article  
  - Save to Reading List  

---

## 📊 Test Reports
After execution, TestNG generates reports in:
```
/test-output/index.html
```
Open it in your browser to view test results.

---

## 👨‍💻 Contributors
- QA Team (Hala, Ahmad, Saif, Badawi)

---

## 📌 Notes
- Default Appium server URL: `http://127.0.0.1:4723`  
- Some older versions of Appium may require `/wd/hub` at the end of the server URL.  
- Pixel 7 emulator is recommended for consistency.

---

## 📜 License
This project is for educational purposes (QA Bootcamp).  

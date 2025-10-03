# 🐾 ShihabVet.com – Software Quality Assurance Project

This repository contains the **Software Quality Assurance (SQA) deliverables** for the **ShihabVet.com** e-commerce platform.  
The purpose of this project is to validate the system’s **functionality, usability, and reliability** before launch through structured manual testing.

---

## 📌 Project Overview
**ShihabVet.com** is an online store for pet-related products.  
This QA project ensures that core features such as registration, login, product purchasing, cart management, address handling, account management, and admin functionalities are tested and verified.  

**Key workflows tested:**
- User Sign Up & Login  
- Buying Products  
- Shopping Cart Operations  
- Shipping Address Validation  
- Account Settings & Profile Management  
- Admin Panel – Order Search & Filters:contentReference[oaicite:6]{index=6}

---

## 📂 Deliverables
All documentation and artifacts are included:

1. **[Software Requirements Specification (SRS)](1-%20ShihabVet%20%20-%20SRS.docx.pdf)**:contentReference[oaicite:7]{index=7}  
2. **[Test Plan](2-%20ShihabVet.com%20Test%20Plan.pdf)**:contentReference[oaicite:8]{index=8}  
3. **[Test Cases](3-%20ShihabVet%20Manual%20Testing%20-%20Test%20Cases.pdf)**:contentReference[oaicite:9]{index=9}  
4. **[Bug Report](4-%20ShihabVet%20Manual%20Testing-%20Bug%20Report.pdf)**:contentReference[oaicite:10]{index=10}  
5. **[Requirement Traceability Matrix (RTM)](5-%20ShihabVet%20Manual%20Testing%20-%20RTM.pdf)**:contentReference[oaicite:11]{index=11}  
6. **[Test Summary Report](6-%20ShihabVet%20Manual%20Testing%20-%20Test%20Summary%20Report.pdf)**:contentReference[oaicite:12]{index=12}

---

## 🖥️ Test Environment
- **Device:** Asus ExpertBook 14"  
- **OS:** Windows 11 Pro  
- **Processor:** Intel i5 12th Gen  
- **RAM:** 24GB  
- **Browsers:** Chrome, Firefox  
- **Mobile:** Android Emulator (Pixel 7, Android 14)  
- **Network:** Stable Internet Connection:contentReference[oaicite:13]{index=13}

---

## 🛠️ Tools Used
- Microsoft Excel / Google Sheets – Test Cases, RTM  
- Microsoft Word / PDF – SRS, Test Plan, Reports  
- Manual Testing (Functional & Usability)  
- Selenium Java (Automation Testing – planned for future)  
- JMeter (Performance Testing – planned for future)

---

## ✅ Test Execution Summary
From the **Test Summary Report**:contentReference[oaicite:14]{index=14}:

- **Total Test Cases Executed:** 64  
- **Passed:** 47  
- **Failed:** 17  
- **Pending / Blocked:** 0  

**Pass Rate:** 73.4%  
**Fail Rate:** 26.6%  

**Module-wise Results:**  
- **Sign Up** → 82% passed  
- **Login** → 100% passed  
- **Buy Product** → 100% passed  
- **Product Cart** → 88% passed  
- **Shipping Address** → 42% passed  
- **Account Settings** → 68% passed  
- **Admin Panel (Order Filters)** → 0% passed

---

## ⚠️ Key Defects Found
From the **Bug Report**:contentReference[oaicite:15]{index=15}:  
- **Sign Up Issues:** Weak validation (too long/short passwords, simple-only passwords accepted).  
- **Cart Issues:** Missing **discount code field** during checkout.  
- **Shipping Address Issues:** Invalid names, numbers, and phone numbers accepted.  
- **Account Settings Issues:** Can’t update name or email properly.  
- **Reorder Malfunction:** Cancelation not possible → items auto-added to cart.  
- **Print Page Issues:** Layout broken and inconsistent.  
- **Admin Panel Issues:** **Order search filter malfunctioning**.  

---

## 📊 Requirement Traceability
The **RTM** ensures each requirement is mapped to test cases:contentReference[oaicite:16]{index=16}:

- **REQ-001:** User Sign Up → TC-SU-001 … TC-SU-011  
- **REQ-002:** User Login → TC-LI-001 … TC-LI-005  
- **REQ-003:** Product Purchase → TC-BP-001 … TC-BP-008  
- **REQ-004:** Cart Functionality → TC-PC-001 … TC-PC-008  
- **REQ-005:** Shipping Addresses → TC-SA-001 … TC-SA-012  
- **REQ-006:** Account Settings → TC-AS-001 … TC-AS-019  
- **REQ-007:** Admin Order Filters → TC-AP-001  

---

## 👤 Author
**Mohammad Badawi**  
📧 [mohammadadbadawi@gmail.com](mailto:mohammadadbadawi@gmail.com)  
*QA Engineer – Project Lead*

---

## 📜 License
This project is part of a **QA portfolio** and is intended for academic, training, and professional showcase purposes only.

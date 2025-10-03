# 🐾 ShihabVet.com – Software Quality Assurance Project

This repository contains the **manual Software Quality Assurance (SQA) deliverables** for the **ShihabVet.com** e-commerce platform. The goal of this project was to ensure the quality, reliability, and usability of the website before launch by performing structured testing activities.

---

## 📌 Project Overview
**ShihabVet.com** is an online store for pet-related products. This SQA project validates its **core functionalities** to ensure a smooth and bug-free user experience.  

The main workflows tested include:
- User Registration & Login  
- Buying Products  
- Shopping Cart Operations  
- Shipping Address Management  
- Account Settings  
- Admin Panel (Order search & filters)

---

## 📂 Deliverables
All project documentation is included in this folder:

1. **[Software Requirements Specification (SRS)](1-%20ShihabVet%20%20-%20SRS.docx.pdf)**:contentReference[oaicite:0]{index=0}  
2. **[Test Plan](2-%20ShihabVet.com%20Test%20Plan.pdf)**:contentReference[oaicite:1]{index=1}  
3. **[Test Cases & Documentation Book](3-%20ShihabVet%20Manual%20Testing-Documentation%20Book.xlsx)**  
4. **[Bug Report](4-%20ShihabVet%20Manual%20Testing%20-%20Bug%20Report.pdf)**:contentReference[oaicite:2]{index=2}  
5. **[Requirement Traceability Matrix (RTM)](5-%20ShihabVet%20Manual%20Testing-Documentation%20Book%20-%20RTM.pdf)**:contentReference[oaicite:3]{index=3}  
6. **[Test Summary Report](6-%20ShihabVet%20Manual%20Testing-Documentation%20Book%20-%20Test%20Summary%20Report.pdf)**:contentReference[oaicite:4]{index=4}

---

## 🖥️ Test Environment
- **Device:** Asus ExpertBook 14"  
- **OS:** Windows 11 Pro  
- **Browser:** Chrome, Firefox  
- **Mobile:** Android Emulator (Pixel 7, Android 14)  
- **Internet:** Stable broadband connection  

---

## 🛠️ Tools Used
- Microsoft Excel (Test Cases, RTM)  
- Microsoft Word / PDF (SRS, Test Plan, Reports)  
- Manual Testing (Functional & Usability Testing)  

---

## ✅ Test Execution Summary
Based on the **Test Summary Report**:contentReference[oaicite:5]{index=5}:

- **Total Test Cases Executed:** 60  
- **Passed:** 44  
- **Failed:** 16  
- **Pending / Blocked:** 0  

**Module-wise Pass Rate:**  
- Sign Up → 75%  
- Login → 100%  
- Buy Product → 100%  
- Cart → 88%  
- Shipping Address → 16.7%  
- Account Settings → 73.4%  
- Admin Panel (Order Filters) → 0%  

---

## ⚠️ Key Defects Found
From the **Bug Report**:contentReference[oaicite:6]{index=6}:
- Invalid sign-up accepted (e.g., long passwords, simple-only passwords).  
- Missing **discount code option** during checkout.  
- Address validation issues (invalid names, phone numbers, etc. accepted).  
- Reorder & Print functionality bugs.  
- Admin **order search filter malfunction**.  

---

## 📊 Requirement Traceability
The **RTM** ensures every requirement is mapped to corresponding test cases:contentReference[oaicite:7]{index=7}.  
Example mappings:
- **REQ-001:** User Sign Up → TC-SU-001 to TC-SU-008  
- **REQ-002:** Login → TC-LI-001 to TC-LI-004  
- **REQ-003:** Buying Product → TC-BP-001 to TC-BP-008  
- **REQ-007:** Admin Order Filters → TC-AP-001  

---

## 👤 Author
**Mohammad Badawi**  
📧 [mohammadadbadawi@gmail.com](mailto:mohammadadbadawi@gmail.com)  
*QA Engineer – Manual Testing Project Lead*  

---

## 📜 License
This project is for **academic and portfolio purposes**. Not intended for commercial use.


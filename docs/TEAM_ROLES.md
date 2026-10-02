# ImpactLoop — Team Roles & Responsibilities

## 1. Project

Project Name:
GreenPulse — ImpactLoop

Hackathon Track:
Heat & Water

MVP Problem:
Urban Waterlogging

MVP Intervention:
Drain Cleaning

Core Product Question:
Did the environmental intervention produce an observed improvement under comparable environmental conditions?

---

## 2. Team Structure

ImpactLoop is divided into four main development areas:

1. Citizen & Field Application
2. Backend & Data
3. Intelligence & Outcome Engine
4. Municipal Dashboard & AWS

The Team Leader coordinates the complete project, integration, quality assurance, and final release.

---

# 3. Member 1 — Citizen & Field Application

## Role

Frontend Developer — Citizen and Field Experience

## Main Responsibility

Build the interfaces used by citizens to report waterlogging and by field workers to record intervention evidence.

## Main Work

### Citizen Flow

Citizen
→ Report Waterlogging
→ Add Photo
→ Capture Location
→ Add Description
→ Submit Report
→ Receive Issue ID

### Field Worker Flow

Field Worker
→ View Assigned Issue
→ Start Intervention
→ Capture BEFORE Evidence
→ Perform Drain Cleaning
→ Capture AFTER Evidence
→ Complete Intervention

## Main Screens

### Citizen

- Report screen
- Waterlogging form
- Photo selection
- Location capture
- Submit confirmation
- Report details

### Field Worker

- Assigned issues
- Issue details
- Start intervention
- BEFORE evidence
- Intervention status
- AFTER evidence
- Completion confirmation

## Main APIs Used

```text
POST /api/v1/issues
GET /api/v1/issues/{issueId}

POST /api/v1/interventions
GET /api/v1/interventions/{interventionId}
PATCH /api/v1/interventions/{interventionId}

POST /api/v1/uploads/presign
POST /api/v1/evidence
GET /api/v1/interventions/{interventionId}/evidence

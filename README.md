# GreenPulse — ImpactLoop

ImpactLoop is an **Environmental Intervention Outcome Intelligence Platform** for the Heat and Water track.

This repository currently contains the **shared project foundation only** for parallel development by a 4-member team. It does **not** implement the full application yet.

## MVP Scope (Locked)
- Problem: **Urban waterlogging**
- Intervention: **Drain cleaning**
- Primary user: **Municipal authority**
- Secondary user: **Field worker**
- Data contributor: **Citizen**

## Core Workflow
Citizen reports waterlogging  
→ Issue is created  
→ Municipality creates an intervention  
→ Field worker captures BEFORE evidence  
→ Drain cleaning is performed  
→ Field worker captures AFTER evidence  
→ Environmental/rain event data is collected  
→ Outcome Engine compares comparable events  
→ Calculates observed improvement  
→ Calculates confidence  
→ Detects recurrence  
→ Recommends next action  
→ Municipal dashboard displays the result

## Repository Foundation Structure
```text
greenpulse-impactloop/
├── frontend/
│   ├── citizen/
│   └── municipal/
├── backend/
├── intelligence/
├── docs/
└── demo/
```

## Documentation Index
- `/docs/PROJECT_SCOPE.md` — goals, boundaries, workflow, and non-goals
- `/docs/TEAM_ROLES.md` — ownership and interface contracts for each member
- `/docs/DATA_MODEL.md` — core entities, required fields, relationships, and status values
- `/docs/API_CONTRACT.md` — stable API endpoints, payload schemas, and error format
- `/docs/TASK_BOARD.md` — parallel work plan and dependency-safe execution

## Guardrails for This Phase
- No hardware/IoT components
- No advanced ML
- No native mobile app
- No authentication yet
- No scope expansion beyond MVP
- Keep API and data contracts stable for parallel development

# ImpactLoop — Project Scope

## 1. Project Name

GreenPulse — ImpactLoop

## 2. Hackathon Track

Heat & Water

## 3. MVP Problem

Urban Waterlogging

## 4. Primary User

Municipal Authority

## 5. Secondary User

Field Worker

## 6. Data Contributor

Citizen

## 7. MVP Intervention

Drain Cleaning

## 8. Main Problem

Cities and civic teams may record that an environmental intervention was completed, but the system does not necessarily close the loop by measuring whether the intervention produced an observed improvement under comparable environmental conditions.

ImpactLoop is designed to connect the original problem, the intervention, evidence, environmental events, and observed outcome in one workflow.

## 9. Main Goal

Measure whether an intervention produced an observed improvement under comparable environmental conditions.

## 10. Core Workflow

Citizen Report
→ Issue Created
→ Municipal Intervention
→ Before Evidence
→ Action Performed
→ After Evidence
→ Environmental / Rain Event
→ Outcome Calculation
→ Confidence
→ Recurrence Detection
→ Next Action
→ Municipal Dashboard

## 11. Simple Real-World Example

1. A citizen reports waterlogging near a roadside drain.
2. The report contains location, description, and a photo.
3. A municipal officer reviews the issue.
4. A drain-cleaning intervention is assigned to a field team.
5. The worker captures before evidence.
6. The drain is cleaned.
7. The worker captures after evidence.
8. Later comparable rainfall events are observed.
9. ImpactLoop compares relevant before and after observations.
10. The system calculates an observed outcome.
11. The system reports confidence in that outcome.
12. The system checks whether the problem recurs.
13. The system recommends the next action.

## 12. Core Product Question

Did the intervention actually improve the environmental problem?

The system should move beyond:

"Task completed"

to:

"Observed outcome after the intervention"

## 13. MVP Scope

The hackathon MVP will focus on:

- One environmental problem: Urban Waterlogging
- One intervention: Drain Cleaning
- Citizen reporting
- Field evidence collection
- Rain/environmental event data
- Outcome calculation
- Confidence
- Recurrence detection
- Next-action recommendation
- Municipal dashboard

## 14. MVP Exclusions

The first MVP will NOT include:

- Hardware or IoT sensors
- Native Android application
- Multiple environmental problems
- Multi-city production support
- Advanced machine-learning training
- Complex authentication
- Full municipal system integration
- Unnecessary chatbot features
- Large-scale production infrastructure

## 15. AI Role

AI is not the final decision maker.

AI may be used for:

- Evidence understanding
- Description summarization
- Information extraction
- Human-readable explanations

Deterministic rules and application data will calculate:

- Observed improvement
- Confidence
- Recurrence
- Next action

## 16. Core Data Entities

ImpactLoop uses five primary entities:

1. Issue
2. Intervention
3. Evidence
4. RainEvent
5. Outcome

## 17. Main System Flow

Citizen / Worker
→ Frontend
→ Backend APIs
→ Data Storage
→ Outcome Intelligence
→ Municipal Dashboard

## 18. Technology Direction

Frontend:
- Web application
- Mobile-first interface

Backend:
- API Gateway
- AWS Lambda

Data:
- DynamoDB

Evidence Storage:
- Amazon S3

Notifications:
- Amazon SNS

Monitoring:
- Amazon CloudWatch

Mapping:
- Leaflet + OpenStreetMap

## 19. Team Roles

### Member 1
Citizen + Field UI

### Member 2
Backend + Database + APIs

### Member 3
Intelligence + Outcome Engine

### Member 4
Municipal Dashboard + AWS + Release

### Team Leader

The Team Leader owns:

- Product scope
- Common contracts
- Integration
- Quality assurance
- Final demo
- Release coordination

## 20. First Integration Milestone

The first working integration must be:

Citizen
→ Report Form
→ POST /issues
→ Database
→ GET /issues
→ Municipal Dashboard

Only after this flow works should the team continue to the intervention, evidence, and outcome workflow.

## 21. Definition of Success for the MVP

A judge should be able to see one complete working story:

Report
→ Intervention
→ Before Evidence
→ After Evidence
→ Environmental Event
→ Outcome
→ Confidence
→ Recurrence
→ Next Action

## 22. Scope Freeze Rule

No member should add or remove an MVP feature, rename core data fields, or change API contracts without team agreement.

The goal is to build one complete, reliable workflow rather than many incomplete features.

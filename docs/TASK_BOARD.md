# ImpactLoop — Task Board

## 1. Project Goal

Build an MVP that can answer:

> Did the environmental intervention actually improve the waterlogging problem?

MVP:

- Problem: Urban Waterlogging
- Intervention: Drain Cleaning
- Location: One demo city/location
- Evidence: BEFORE and AFTER
- Environmental context: Rain events
- Output: Observed Outcome + Confidence + Recurrence + Next Action

---

# 2. Member 1 — Citizen & Field Application

Branch:

`feature/member1-ui`

## Phase 1 — Citizen Reporting

- [ ] Create citizen report page
- [ ] Add waterlogging description field
- [ ] Add photo selection
- [ ] Add location capture
- [ ] Add submit button
- [ ] Connect `POST /api/v1/issues`
- [ ] Display generated Issue ID
- [ ] Create issue details page

## Phase 2 — Field Worker

- [ ] Create assigned issues screen
- [ ] Display issue details
- [ ] Add Start Intervention action
- [ ] Connect `POST /api/v1/interventions`
- [ ] Add BEFORE evidence upload
- [ ] Add intervention completion flow
- [ ] Add AFTER evidence upload
- [ ] Connect evidence APIs
- [ ] Display intervention status

## Definition of Done

A user can:

Report waterlogging
→ receive Issue ID
→ field worker opens issue
→ starts intervention
→ uploads BEFORE evidence
→ completes drain cleaning
→ uploads AFTER evidence.

---

# 3. Member 2 — Backend & Data

Branch:

`feature/member2-backend`

## Phase 1 — Backend Foundation

- [ ] Create backend project structure
- [ ] Configure AWS Lambda
- [ ] Configure API Gateway
- [ ] Configure DynamoDB
- [ ] Configure S3
- [ ] Create API error format
- [ ] Add request validation
- [ ] Add logging

## Phase 2 — Issue APIs

- [ ] `POST /api/v1/issues`
- [ ] `GET /api/v1/issues`
- [ ] `GET /api/v1/issues/{issueId}`

## Phase 3 — Intervention APIs

- [ ] `POST /api/v1/interventions`
- [ ] `GET /api/v1/interventions/{interventionId}`
- [ ] `PATCH /api/v1/interventions/{interventionId}`

## Phase 4 — Evidence

- [ ] `POST /api/v1/uploads/presign`
- [ ] `POST /api/v1/evidence`
- [ ] `GET /api/v1/interventions/{interventionId}/evidence`

## Phase 5 — Environmental Data

- [ ] Create RainEvent storage
- [ ] `POST /api/v1/rain-events`
- [ ] `GET /api/v1/rain-events`

## Phase 6 — Outcome Storage

- [ ] Create Outcome storage
- [ ] `POST /api/v1/outcomes/calculate`
- [ ] `GET /api/v1/outcomes/{interventionId}`

## Definition of Done

All APIs required by the frontend and intelligence engine work with real AWS data.

---

# 4. Member 3 — Intelligence & Outcome Engine

Branch:

`feature/member3-intelligence`

## Main Goal

Build the decision engine that determines whether an intervention produced an observed improvement.

## Phase 1 — Comparable Events

- [ ] Read intervention date/location
- [ ] Read historical waterlogging incidents
- [ ] Read rain events
- [ ] Identify comparable rain events
- [ ] Define comparison rules
- [ ] Record comparison inputs

## Phase 2 — Outcome Calculation

- [ ] Count comparable BEFORE incidents
- [ ] Count comparable AFTER incidents
- [ ] Calculate observed incident reduction
- [ ] Calculate duration improvement when data exists
- [ ] Determine outcome status
- [ ] Determine confidence level

Example:

```text
Before comparable events = 4
Before incidents = 4

After comparable events = 4
After incidents = 1

Observed reduction = 75%

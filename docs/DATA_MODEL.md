# ImpactLoop — Data Model

## 1. Purpose

This document defines the common data structures used by all ImpactLoop modules.

All team members must follow these structures.

Do not rename fields, remove fields, or change field types without team approval.

---

## 2. Main Entities

ImpactLoop MVP contains five primary entities:

1. Issue
2. Intervention
3. Evidence
4. RainEvent
5. Outcome

---

## 3. Entity Relationship

Issue
↓
Intervention
↓
Evidence
↓
Outcome

RainEvent
↓
Used by the Outcome Engine to compare environmental conditions.

---

# 4. Issue

An Issue represents a citizen-reported urban waterlogging problem.

### Fields

| Field | Type | Required | Description |
|---|---|---|---|
| issueId | String | Yes | Unique issue identifier |
| type | String | Yes | Type of environmental issue |
| latitude | Number | Yes | Geographic latitude |
| longitude | Number | Yes | Geographic longitude |
| description | String | Yes | Citizen description |
| photoUrl | String | No | URL/key of the original report photo |
| status | String | Yes | Current issue status |
| createdAt | String | Yes | ISO-8601 timestamp |

### MVP Type

```text
WATERLOGGING

### Status Values

OPEN
IN_REVIEW
ACTION_ASSIGNED
RESOLVED

### Example

{
  "issueId": "WL-1007",
  "type": "WATERLOGGING",
  "latitude": 21.1702,
  "longitude": 72.8311,
  "description": "Water accumulated near roadside drain",
  "photoUrl": "s3://impactloop-evidence/reports/WL-1007.jpg",
  "status": "OPEN",
  "createdAt": "2026-10-08T10:15:00Z"
}

---

# 5. Intervention

An Intervention represents the action taken to address an Issue.

### Fields

| Field | Type | Required | Description |
|---|---|---|---|
| interventionId | String | Yes | Unique intervention identifier |
| issueId | String | Yes | Related Issue |
| actionType | String | Yes | Type of intervention |
| assignedTeam | String | No | Assigned field team |
| status | String | Yes | Current intervention status |
| startedAt | String | No | Work start timestamp |
| completedAt | String | No | Work completion timestamp |

### MVP Action Type

DRAIN_CLEANING

### Status Values

ASSIGNED
IN_PROGRESS
COMPLETED

### Example

{
  "interventionId": "INT-221",
  "issueId": "WL-1007",
  "actionType": "DRAIN_CLEANING",
  "assignedTeam": "TEAM-B",
  "status": "ASSIGNED",
  "startedAt": null,
  "completedAt": null
}

---

# 6. Evidence

Evidence represents before/after proof connected to an intervention.

### Fields

| Field | Type | Required | Description |
|---|---|---|---|
| evidenceId | String | Yes | Unique evidence identifier |
| interventionId | String | Yes | Related Intervention |
| type | String | Yes | BEFORE or AFTER |
| photoUrl | String | Yes | S3 object URL/key |
| latitude | Number | Yes | Evidence latitude |
| longitude | Number | Yes | Evidence longitude |
| timestamp | String | Yes | Evidence capture timestamp |

### Allowed Types

BEFORE
AFTER

---

# 7. RainEvent

RainEvent represents an observed rainfall event used to compare environmental conditions.

### Fields

| Field | Type | Required | Description |
|---|---|---|---|
| eventId | String | Yes | Unique rainfall event identifier |
| location | String | Yes | Ward or location identifier |
| startTime | String | Yes | Event start timestamp |
| endTime | String | Yes | Event end timestamp |
| rainfallMm | Number | Yes | Total rainfall in millimetres |

---

# 8. Outcome

Outcome represents the observed result associated with an intervention.

### Fields

| Field | Type | Required | Description |
|---|---|---|---|
| outcomeId | String | Yes | Unique outcome identifier |
| interventionId | String | Yes | Related Intervention |
| beforeIncidents | Number | Yes | Comparable incidents before intervention |
| afterIncidents | Number | Yes | Comparable incidents after intervention |
| observedReduction | Number | Yes | Observed percentage reduction |
| durationReduction | Number | No | Observed duration reduction percentage |
| confidence | String | Yes | LOW, MEDIUM, or HIGH |
| recurring | Boolean | Yes | Whether the problem recurs |
| nextAction | String | Yes | Recommended next action |
| createdAt | String | Yes | Outcome calculation timestamp |

---

# 9. Relationship Details

Issue → Intervention

Intervention → Evidence

Intervention → Outcome

RainEvent → Outcome Engine

---

# 10. Data Rules

1. All IDs must be unique.
2. All timestamps must use ISO-8601 format.
3. Latitude and longitude must be numeric decimal values.
4. MVP Issue type is WATERLOGGING.
5. MVP Intervention type is DRAIN_CLEANING.
6. Evidence type is only BEFORE or AFTER.
7. Outcome values must be calculated by the Intelligence Engine.
8. AI must not directly generate the final observedReduction.
9. Confidence must reflect the available comparison data.
10. A result with insufficient data must not be presented as highly reliable.
11. Core field names must not be changed without team approval.

---

# 11. Data Flow

Citizen
↓
Issue
↓
Intervention
↓
Before Evidence
↓
Drain Cleaning
↓
After Evidence
↓
Rain Events
↓
Outcome Engine
↓
Outcome

---

# 12. Ownership

### Member 1
Uses:
- Issue
- Evidence

### Member 2
Creates and stores:
- Issue
- Intervention
- Evidence
- RainEvent
- Outcome

### Member 3
Reads:
- Issue
- Intervention
- Evidence
- RainEvent

Produces:
- Outcome

### Member 4
Reads:
- Issue
- Intervention
- Outcome

Displays them in the Municipal Dashboard.

---

# 13. Contract Freeze

This document is the common data contract for the team.

Any change to field names, field types, status values, or entity relationships must be discussed and approved before implementation.

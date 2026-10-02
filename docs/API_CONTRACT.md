# API_CONTRACT

Base path (draft): `/api/v1`

## Conventions
- JSON request/response
- IDs are strings
- Timestamps are ISO-8601 UTC
- Stable field names; additive changes only unless version bump

## Error Response Structure (common)
```json
{
  "error": {
    "code": "VALIDATION_ERROR",
    "message": "Required field missing: location.latitude",
    "details": [
      {
        "field": "location.latitude",
        "issue": "required"
      }
    ],
    "requestId": "req_12345"
  }
}
```

## 1) Create Issue
`POST /api/v1/issues`

Request:
```json
{
  "title": "Waterlogging near Market Road",
  "description": "Road submerged for 2 hours after rainfall",
  "location": {
    "latitude": 23.0225,
    "longitude": 72.5714,
    "addressText": "Market Road, Ward 11"
  },
  "severity": "high"
}
```

Response `201`:
```json
{
  "issueId": "iss_001",
  "status": "open",
  "reportedAt": "2026-10-02T06:00:00Z"
}
```

## 2) Create Intervention
`POST /api/v1/interventions`

Request:
```json
{
  "issueId": "iss_001",
  "type": "drain_cleaning",
  "scheduledAt": "2026-10-03T09:00:00Z",
  "assignedFieldWorkerId": "fw_21"
}
```

Response `201`:
```json
{
  "interventionId": "int_001",
  "issueId": "iss_001",
  "status": "planned"
}
```

## 3) Capture Evidence
`POST /api/v1/evidence`

Request:
```json
{
  "interventionId": "int_001",
  "issueId": "iss_001",
  "phase": "before",
  "capturedAt": "2026-10-03T08:45:00Z",
  "capturedBy": "fw_21",
  "mediaUrl": "https://example.local/evidence/before_001.jpg",
  "waterDepthCm": 18,
  "notes": "Heavy stagnation"
}
```

Response `201`:
```json
{
  "evidenceId": "ev_001",
  "phase": "before"
}
```

## 4) Record Rain Event
`POST /api/v1/rain-events`

Request:
```json
{
  "location": {
    "latitude": 23.0225,
    "longitude": 72.5714
  },
  "startTime": "2026-10-03T06:00:00Z",
  "endTime": "2026-10-03T09:00:00Z",
  "rainfallMm": 22.4,
  "source": "api"
}
```

Response `201`:
```json
{
  "rainEventId": "rain_011"
}
```

## 5) Compute Outcome
`POST /api/v1/outcomes/compute`

Request:
```json
{
  "issueId": "iss_001",
  "interventionId": "int_001"
}
```

Response `200`:
```json
{
  "outcomeId": "out_001",
  "issueId": "iss_001",
  "interventionId": "int_001",
  "observedImprovementPct": 41.7,
  "confidenceScore": 0.83,
  "recurrenceDetected": false,
  "recommendedAction": "monitor",
  "summary": "Post-cleaning water depth reduced under comparable rainfall"
}
```

## 6) Municipal Dashboard Data
`GET /api/v1/municipal/dashboard?ward=11`

Response `200`:
```json
{
  "openIssues": 12,
  "activeInterventions": 4,
  "recentOutcomes": [
    {
      "issueId": "iss_001",
      "interventionId": "int_001",
      "observedImprovementPct": 41.7,
      "confidenceScore": 0.83,
      "recurrenceDetected": false,
      "recommendedAction": "monitor"
    }
  ]
}
```

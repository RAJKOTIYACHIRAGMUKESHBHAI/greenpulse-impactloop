# TEAM_ROLES

## Ownership Map

### Member 1 — Citizen + Field UI
- Owns `frontend/citizen/`
- Builds citizen reporting UI and field worker evidence capture UI
- Integrates with stable backend API contracts only
- Must not change API contracts unilaterally

### Member 2 — Backend + Database + APIs
- Owns `backend/`
- Implements persistence for Issue, Intervention, Evidence, RainEvent, Outcome
- Implements and maintains API endpoints and request/response schema stability

### Member 3 — Intelligence + Outcome Engine
- Owns `intelligence/`
- Implements comparable-event logic, improvement calculation, confidence scoring, recurrence detection, and recommendations
- Consumes backend data contracts and writes `Outcome`

### Member 4 — Municipal Dashboard + AWS + Release
- Owns `frontend/municipal/` and deployment/release setup
- Builds municipal dashboard views for issue/intervention/outcome monitoring
- Manages integration packaging and release readiness

## Cross-Team Interface Rules
1. API paths, payload fields, and enums are contract-first and must stay stable.
2. Any contract change requires team agreement and changelog update before merge.
3. Use mock/stub responses derived from `/docs/API_CONTRACT.md` to unblock parallel development.
4. Keep module ownership boundaries clear; avoid cross-module code edits unless coordinated.

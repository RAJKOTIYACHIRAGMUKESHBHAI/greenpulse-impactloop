# TASK_BOARD

## Parallel Development Plan

## Member 1 — Citizen + Field UI
- Build issue reporting form using `POST /api/v1/issues`
- Build before/after evidence capture UI using `POST /api/v1/evidence`
- Use static API mocks aligned with `API_CONTRACT.md`

## Member 2 — Backend + Database + APIs
- Create data schema for Issue, Intervention, Evidence, RainEvent, Outcome
- Implement endpoints in `API_CONTRACT.md`
- Enforce shared error response structure

## Member 3 — Intelligence + Outcome Engine
- Implement comparable-event selection rules
- Implement improvement and confidence calculations
- Implement recurrence detection and recommendation mapping
- Expose compute flow via `POST /api/v1/outcomes/compute`

## Member 4 — Municipal Dashboard + AWS + Release
- Build dashboard views using `GET /api/v1/municipal/dashboard`
- Prepare AWS deployment baseline (non-functional scaffolding only)
- Own release integration across modules

## Dependency Rules
- UI can proceed with mocks immediately; do not block on backend runtime.
- Backend and intelligence can proceed in parallel with stable data contracts.
- Dashboard depends on response shapes, not final engine implementation.
- Any breaking API/data change requires explicit team agreement before merge.

## Definition of Done for Foundation Phase
- Folder structure exists
- All six foundation docs exist and are internally consistent
- Entity fields and status values are defined
- API examples and error schema are defined
- Ownership boundaries and parallel workflow are clear

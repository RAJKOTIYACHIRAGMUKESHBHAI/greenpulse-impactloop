# PROJECT_SCOPE

## Purpose
Build a municipal decision-support platform that measures whether drain-cleaning interventions reduce waterlogging impact under comparable rain conditions.

## MVP Focus
- Single problem class: urban waterlogging
- Single intervention class: drain cleaning
- Single outcome domain: observable post-intervention improvement and recurrence monitoring

## In Scope
1. Citizen issue reporting (basic submission)
2. Intervention lifecycle tracking
3. BEFORE/AFTER evidence capture
4. Rain event ingestion/recording
5. Outcome Engine (rule-based comparisons, confidence scoring)
6. Municipal dashboard-ready output model

## Out of Scope (for now)
- Hardware sensors/IoT integration
- Advanced ML prediction models
- Native mobile application
- Authentication/authorization
- Multi-intervention optimization features

## End-to-End Workflow
1. Citizen reports waterlogging.
2. System creates `Issue`.
3. Municipality creates `Intervention` linked to that issue.
4. Field worker captures BEFORE `Evidence`.
5. Drain cleaning is performed.
6. Field worker captures AFTER `Evidence`.
7. System records nearby-period `RainEvent` data.
8. Outcome Engine compares comparable rain events (before vs after windows).
9. Engine computes observed improvement and confidence.
10. Engine flags recurrence if waterlogging is seen again.
11. Engine proposes next action.
12. Municipal dashboard consumes and displays `Outcome`.

## Stable Contract Principle
During parallel build, changes to entity names, required fields, status enums, and API paths must be coordinated and versioned to avoid breaking other modules.

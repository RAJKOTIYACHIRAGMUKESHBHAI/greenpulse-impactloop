# DATA_MODEL

## Core Entities

## 1) Issue
Represents a citizen-reported waterlogging problem.

Required fields:
- `issueId` (string, unique)
- `title` (string)
- `description` (string)
- `location` (object: `latitude`, `longitude`, `addressText`)
- `reportedAt` (ISO datetime)
- `reportedByType` (enum: `citizen`)
- `severity` (enum: `low`, `medium`, `high`)
- `status` (enum: `open`, `validated`, `intervention_created`, `resolved`, `recurred`, `closed`)

## 2) Intervention
Represents drain-cleaning work planned/performed for an issue.

Required fields:
- `interventionId` (string, unique)
- `issueId` (string, FK → Issue)
- `type` (enum: `drain_cleaning`)
- `createdAt` (ISO datetime)
- `scheduledAt` (ISO datetime)
- `completedAt` (ISO datetime, nullable until complete)
- `assignedFieldWorkerId` (string)
- `status` (enum: `planned`, `in_progress`, `before_captured`, `after_captured`, `completed`, `cancelled`)

## 3) Evidence
Represents before/after field evidence.

Required fields:
- `evidenceId` (string, unique)
- `interventionId` (string, FK → Intervention)
- `issueId` (string, FK → Issue)
- `phase` (enum: `before`, `after`)
- `capturedAt` (ISO datetime)
- `capturedBy` (string)
- `mediaUrl` (string)
- `waterDepthCm` (number, nullable)
- `notes` (string, nullable)

## 4) RainEvent
Represents observed rainfall context for comparison windows.

Required fields:
- `rainEventId` (string, unique)
- `location` (object: `latitude`, `longitude`)
- `startTime` (ISO datetime)
- `endTime` (ISO datetime)
- `rainfallMm` (number)
- `source` (enum: `manual`, `api`)

## 5) Outcome
Represents computed impact results for an intervention under comparable rain contexts.

Required fields:
- `outcomeId` (string, unique)
- `issueId` (string, FK → Issue)
- `interventionId` (string, FK → Intervention)
- `computedAt` (ISO datetime)
- `observedImprovementPct` (number)
- `confidenceScore` (number, 0 to 1)
- `recurrenceDetected` (boolean)
- `recommendedAction` (enum: `monitor`, `reclean`, `escalate_engineering_review`)
- `summary` (string)

## Entity Relationships
- Issue 1 → many Interventions
- Intervention 1 → many Evidence records
- Issue/Intervention → many RainEvents (contextual, location-window based)
- Intervention 1 → 1 latest Outcome (versionable over time if recomputed)

## Status Flow
- Issue: `open` → `validated` → `intervention_created` → `resolved` → (`recurred` optional) → `closed`
- Intervention: `planned` → `in_progress` → `before_captured` → `after_captured` → `completed`

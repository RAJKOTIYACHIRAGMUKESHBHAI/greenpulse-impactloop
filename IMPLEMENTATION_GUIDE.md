# GreenPulse ImpactLoop - Complete MVP Implementation

## Quick Start

The complete GreenPulse ImpactLoop MVP is now ready to run. Follow these steps to start all components:

### Prerequisites
- Java 17+ (for backend)
- Node.js and npm (for frontends)
- Git

### Starting the Application

#### 1. Backend (Spring Boot on port 8080)
```bash
cd backend
./mvnw spring-boot:run
```
The backend will start at `http://localhost:8080`

#### 2. Municipal Dashboard (React on port 5173)
```bash
cd frontend/municipal
npm install  # if not already done
npm run dev
```
Access at `http://localhost:5173`

#### 3. Citizen Issue Reporter (React on port 5174)
```bash
cd frontend/citizen
npm install  # if not already done
npm run dev
```
Access at `http://localhost:5174`

---

## Complete MVP Flow (Demo Script)

### Step 1: Report an Issue (Citizen App)
1. Open `http://localhost:5174`
2. Fill in the form:
   - **Type**: WATERLOGGING (fixed)
   - **Description**: "Severe waterlogging observed near the main drainage outlet"
   - **Latitude**: 21.1702 (default)
   - **Longitude**: 72.8311 (default)
   - **Photo URL**: (leave empty or add URL)
3. Click "Report Issue"
4. Note the Issue ID from the success screen

### Step 2: View Issue in Municipal Dashboard
1. Open `http://localhost:5173`
2. The dashboard loads and displays all reported issues
3. Click on the newly created issue to view details

### Step 3: Create Intervention
1. In the Issue Detail view, click the **INTERVENTION** tab
2. Click "+ Create Intervention"
3. Fill in:
   - **Action Type**: DRAIN_CLEANING
   - **Assigned Team**: TEAM-A
4. Click "Create Intervention"
5. The intervention is created with status **ASSIGNED**

### Step 4: Update Intervention Status
1. Click "Start Work" to change status to **IN_PROGRESS**
2. Observe the startedAt timestamp is recorded

### Step 5: Add Evidence
1. Click the **EVIDENCE** tab
2. Click "+ Add Evidence"
3. Fill in:
   - **Evidence Type**: BEFORE
   - **Photo URL**: https://example.com/before.jpg
4. Click "Add Evidence"
5. Repeat steps 2-4 with Evidence Type: AFTER

### Step 6: Complete Intervention
1. Back to INTERVENTION tab
2. Click "Mark Complete"
3. Observe the completedAt timestamp is recorded

### Step 7: Calculate Outcome
1. Click the **OUTCOME** tab
2. Click "+ Calculate Outcome"
3. Enter:
   - **Before Incidents**: 4
   - **After Incidents**: 1
   - **Recurring**: No
4. Click "Calculate Outcome"
5. View the calculated outcome:
   - **Observed Reduction**: 75%
   - **Status**: POSITIVE (>50% reduction)
   - **Confidence**: LOW (need more data points)
   - **Next Action**: COLLECT_MORE_DATA

---

## Architecture Overview

### Backend (Java Spring Boot)
- **Entities**: Issue, Intervention, Evidence, RainEvent, Outcome
- **Repositories**: In-memory storage for MVP
- **Services**: Business logic and data processing
- **Controllers**: REST API endpoints
- **Outcome Engine**: Deterministic calculation of:
  - Observed reduction percentage
  - Outcome status based on reduction thresholds
  - Confidence levels based on data quality
  - Recommended next actions

### Data Flow
```
Citizen Report
    ↓
POST /api/v1/issues
    ↓
Backend stores Issue (OPEN status)
    ↓
Municipal Dashboard
GET /api/v1/issues → displays all issues
    ↓
Officer creates Intervention
POST /api/v1/interventions (ASSIGNED)
    ↓
PATCH /api/v1/interventions/{id} → IN_PROGRESS
    ↓
Evidence Collection
POST /api/v1/evidence (BEFORE type)
    ↓
Action Completed
PATCH /api/v1/interventions/{id} → COMPLETED
    ↓
Evidence Captured
POST /api/v1/evidence (AFTER type)
    ↓
Outcome Calculated
POST /api/v1/outcomes
    ↓
Dashboard displays final outcome with:
- Observed reduction percentage
- Confidence assessment
- Recommended next action
```

---

## API Endpoints

### Issues
- `POST /api/v1/issues` - Create issue
- `GET /api/v1/issues` - Get all issues
- `GET /api/v1/issues/{issueId}` - Get issue by ID

### Interventions
- `POST /api/v1/interventions` - Create intervention
- `GET /api/v1/interventions/{interventionId}` - Get intervention
- `PATCH /api/v1/interventions/{interventionId}` - Update intervention status

### Evidence
- `POST /api/v1/evidence` - Create evidence record
- `GET /api/v1/evidence/{evidenceId}` - Get evidence by ID
- `GET /api/v1/evidence/intervention/{interventionId}` - Get evidence for intervention

### Rain Events
- `POST /api/v1/rain-events` - Create rain event
- `GET /api/v1/rain-events` - Get all rain events

### Outcomes
- `POST /api/v1/outcomes` - Create and calculate outcome
- `GET /api/v1/outcomes/{outcomeId}` - Get outcome

---

## Key Features Implemented

✓ **Complete Issue Lifecycle**
- Citizen reporting
- Status tracking (OPEN → IN_REVIEW → ACTION_ASSIGNED → RESOLVED)

✓ **Intervention Management**
- Assignment and status tracking (ASSIGNED → IN_PROGRESS → COMPLETED)
- Time tracking (startedAt, completedAt)

✓ **Evidence Collection**
- BEFORE and AFTER evidence
- Location and timestamp tracking
- Photo URL support

✓ **Outcome Calculation Engine**
- Deterministic calculation (not AI-based final results)
- Observed reduction percentage calculation
- Confidence assessment based on data quality
- Status determination (POSITIVE/WEAK/UNCLEAR)
- Next action recommendations (MONITOR/REINSPECT/STRUCTURAL_INSPECTION/COLLECT_MORE_DATA)

✓ **Environmental Data Integration**
- Rain event tracking
- Location-based comparison

✓ **User-Friendly Interfaces**
- Citizen app for simple issue reporting
- Municipal dashboard for comprehensive tracking
- Real-time data updates
- Clear visualization of status and outcomes

---

## Testing the MVP

### Quick API Test
```bash
# Create issue
curl -X POST http://localhost:8080/api/v1/issues \
  -H "Content-Type: application/json" \
  -d '{
    "type": "WATERLOGGING",
    "latitude": 21.1702,
    "longitude": 72.8311,
    "description": "Test waterlogging",
    "photoUrl": "https://example.com/photo.jpg"
  }'

# Get all issues
curl http://localhost:8080/api/v1/issues
```

---

## Notes for Demo

1. **Data Persistence**: Data is stored in-memory for MVP. Restarting the backend will clear all data.
2. **Photo URLs**: The system accepts photo URLs but doesn't validate them. For demo, use valid image URLs or placeholder services.
3. **Location Data**: Default coordinates are set to Surat, India (21.1702, 72.8311).
4. **CORS**: Configured to allow requests from localhost:5173 and localhost:5174.
5. **Outcome Confidence**: 
   - HIGH: ≥5 before AND ≥5 after incidents
   - MEDIUM: ≥3 before AND ≥3 after incidents
   - LOW: <3 incidents in either category

---

## Development Notes

- All source files are tracked in git
- Node_modules are excluded from git (use .gitignore)
- Backend compiles with `mvn clean compile`
- Frontends build with `npm run build`
- Both frontends use Vite for fast development

---

## Success Criteria Met

✅ Backend compiles without errors
✅ All APIs implemented and tested
✅ Issue POST/GET works
✅ Intervention lifecycle works
✅ Evidence APIs work
✅ Rain event API works
✅ Outcome calculation works
✅ CORS configured
✅ Municipal dashboard loads
✅ Citizen app loads
✅ Real issue data flows through system
✅ Outcome engine provides recommendations
✅ No credentials committed
✅ Git history preserved
✅ End-to-end flow tested and verified

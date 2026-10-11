import React, { useState, useEffect } from 'react';
import { apiService } from '../api.js';

export default function IssueDetail({ issueId, onBack }) {
  const [issue, setIssue] = useState(null);
  const [interventions, setInterventions] = useState([]);
  const [evidences, setEvidences] = useState([]);
  const [outcome, setOutcome] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [activeTab, setActiveTab] = useState('overview');
  const [showInterventionForm, setShowInterventionForm] = useState(false);
  const [showEvidenceForm, setShowEvidenceForm] = useState(false);
  const [showOutcomeForm, setShowOutcomeForm] = useState(false);
  const [currentIntervention, setCurrentIntervention] = useState(null);

  useEffect(() => {
    fetchDetails();
  }, [issueId]);

  const fetchDetails = async () => {
    try {
      setLoading(true);
      setError(null);

      // Fetch issue
      const issueData = await apiService.getIssueById(issueId);
      setIssue(issueData);

      // Fetch interventions for this issue
      const intervResponse = await fetch(
          `http://3.237.10.181:8080/api/v1/interventions?issueId=${issueId}`
      );

      if (intervResponse.ok) {
        const interventionsData = await intervResponse.json();
        setInterventions(interventionsData);

        // If there's an intervention, fetch its evidence and outcome
        if (interventionsData.length > 0) {
          const intervention = interventionsData[0];
          setCurrentIntervention(intervention);

          const evidenceData =
              await apiService.getEvidenceByIntervention(
                  intervention.interventionId
              );

          setEvidences(evidenceData);

          try {
            const outcomeResponse = await fetch(
                `http://3.237.10.181:8080/api/v1/outcomes`
            );

            if (outcomeResponse.ok) {
              const allOutcomes = await outcomeResponse.json();

              const filteredOutcome = allOutcomes.find(
                  (o) => o.interventionId === intervention.interventionId
              );

              if (filteredOutcome) {
                setOutcome(filteredOutcome);
              }
            }
          } catch (e) {
            console.log('No outcomes yet');
          }
        }
      }
    } catch (err) {
      console.error('Error fetching details:', err);
      setError(`Failed to load issue details: ${err.message}`);
    } finally {
      setLoading(false);
    }
  };

  const handleCreateIntervention = async (e) => {
    e.preventDefault();

    try {
      const formData = new FormData(e.target);

      const data = {
        issueId,
        actionType: formData.get('actionType') || 'DRAIN_CLEANING',
        assignedTeam: formData.get('assignedTeam') || 'TEAM-A',
      };

      const result = await apiService.createIntervention(data);

      setCurrentIntervention(result);
      setInterventions([result, ...interventions]);
      setShowInterventionForm(false);
      e.target.reset();

      fetchDetails();
    } catch (err) {
      alert('Error creating intervention: ' + err.message);
    }
  };

  const handleUpdateInterventionStatus = async (status) => {
    if (!currentIntervention) return;

    try {
      const result = await apiService.updateIntervention(
          currentIntervention.interventionId,
          { status }
      );

      setCurrentIntervention(result);
      fetchDetails();
    } catch (err) {
      alert('Error updating intervention: ' + err.message);
    }
  };

  const handleCreateEvidence = async (e) => {
    e.preventDefault();

    if (!currentIntervention) {
      alert('No intervention selected');
      return;
    }

    try {
      const formData = new FormData(e.target);

      const data = {
        interventionId: currentIntervention.interventionId,
        type: formData.get('type'),
        photoUrl: formData.get('photoUrl') || 'sample-evidence.jpg',
        latitude:
            parseFloat(formData.get('latitude')) || issue.latitude,
        longitude:
            parseFloat(formData.get('longitude')) || issue.longitude,
      };

      const result = await apiService.createEvidence(data);

      setEvidences([...evidences, result]);
      setShowEvidenceForm(false);
      e.target.reset();
    } catch (err) {
      alert('Error creating evidence: ' + err.message);
    }
  };

  const handleCreateOutcome = async (e) => {
    e.preventDefault();

    if (!currentIntervention) {
      alert('No intervention selected');
      return;
    }

    try {
      const formData = new FormData(e.target);

      const data = {
        interventionId: currentIntervention.interventionId,
        beforeIncidents:
            parseInt(formData.get('beforeIncidents')) || 4,
        afterIncidents:
            parseInt(formData.get('afterIncidents')) || 1,
        observedReduction:
            parseFloat(formData.get('observedReduction')) || null,
        durationReduction: formData.get('durationReduction')
            ? parseFloat(formData.get('durationReduction'))
            : null,
        outcome: formData.get('outcome') || null,
        confidence: formData.get('confidence') || null,
        recurring: formData.get('recurring') === 'true',
        nextAction: formData.get('nextAction') || null,
      };

      const result = await apiService.createOutcome(data);

      setOutcome(result);
      setShowOutcomeForm(false);
      e.target.reset();
    } catch (err) {
      alert('Error creating outcome: ' + err.message);
    }
  };

  const formatDate = (dateString) => {
    if (!dateString) return 'N/A';

    try {
      const date = new Date(dateString);

      return date.toLocaleDateString('en-US', {
        year: 'numeric',
        month: 'short',
        day: 'numeric',
        hour: '2-digit',
        minute: '2-digit',
      });
    } catch (e) {
      return dateString;
    }
  };

  if (loading) {
    return (
        <div className="loading">
          <div className="spinner"></div>
          <p>Loading issue details...</p>
        </div>
    );
  }

  if (!issue) {
    return (
        <div className="error">
          <p className="error-message">Issue not found</p>
        </div>
    );
  }

  return (
      <div className="detail-view">
        <div className="detail-header">
          <h2>{issue.description}</h2>

          <button className="back-btn" onClick={onBack}>
            ← Back to Dashboard
          </button>
        </div>

        {error && (
            <div className="error">
              <p className="error-message">{error}</p>
            </div>
        )}

        <div
            className="tabs"
            style={{
              marginBottom: '2rem',
              borderBottom: '2px solid #ecf0f1',
              display: 'flex',
              gap: '1rem',
            }}
        >
          {['overview', 'intervention', 'evidence', 'outcome'].map(
              (tab) => (
                  <button
                      key={tab}
                      onClick={() => setActiveTab(tab)}
                      style={{
                        padding: '0.8rem 1.5rem',
                        border: 'none',
                        borderBottom:
                            activeTab === tab
                                ? '3px solid #3498db'
                                : 'none',
                        background: 'none',
                        cursor: 'pointer',
                        fontSize: '1rem',
                        fontWeight:
                            activeTab === tab ? '600' : '400',
                        color:
                            activeTab === tab ? '#3498db' : '#7f8c8d',
                      }}
                  >
                    {tab.toUpperCase()}
                  </button>
              )
          )}
        </div>

        {/* Overview Tab */}
        {activeTab === 'overview' && (
            <div className="detail-content">
              <div className="detail-section">
                <h3>Issue Information</h3>

                <div className="detail-item">
                  <div className="detail-label">Issue ID</div>
                  <div className="detail-value">{issue.issueId}</div>
                </div>

                <div className="detail-item">
                  <div className="detail-label">Type</div>
                  <div className="detail-value">{issue.type}</div>
                </div>

                <div className="detail-item">
                  <div className="detail-label">Status</div>

                  <div
                      className="detail-value"
                      style={{
                        display: 'inline-block',
                        background:
                            issue.status === 'OPEN'
                                ? '#e74c3c'
                                : issue.status === 'RESOLVED'
                                    ? '#2ecc71'
                                    : '#3498db',
                        color: 'white',
                        padding: '0.4rem 0.8rem',
                        borderRadius: '4px',
                      }}
                  >
                    {issue.status}
                  </div>
                </div>

                <div className="detail-item">
                  <div className="detail-label">Description</div>
                  <div className="detail-value">
                    {issue.description}
                  </div>
                </div>

                <div className="detail-item">
                  <div className="detail-label">Created</div>
                  <div className="detail-value">
                    {formatDate(issue.createdAt)}
                  </div>
                </div>
              </div>

              <div className="detail-section">
                <h3>Location</h3>

                <div className="detail-item">
                  <div className="detail-label">Latitude</div>
                  <div className="detail-value">
                    {issue.latitude.toFixed(6)}
                  </div>
                </div>

                <div className="detail-item">
                  <div className="detail-label">Longitude</div>
                  <div className="detail-value">
                    {issue.longitude.toFixed(6)}
                  </div>
                </div>

                {issue.photoUrl && (
                    <div className="detail-item">
                      <div className="detail-label">Photo</div>

                      <div className="detail-value">
                        <a
                            href={issue.photoUrl}
                            target="_blank"
                            rel="noopener noreferrer"
                        >
                          View Photo
                        </a>
                      </div>
                    </div>
                )}
              </div>
            </div>
        )}

        {/* Intervention Tab */}
        {activeTab === 'intervention' && (
            <div>
              {!currentIntervention ? (
                  <>
                    <div
                        style={{
                          background: '#f0f8ff',
                          padding: '1.5rem',
                          borderRadius: '6px',
                          marginBottom: '1.5rem',
                        }}
                    >
                      <p
                          style={{
                            margin: 0,
                            color: '#2c3e50',
                          }}
                      >
                        No intervention created yet. Click below to
                        create one.
                      </p>
                    </div>

                    <button
                        className="btn btn-primary"
                        onClick={() =>
                            setShowInterventionForm(
                                !showInterventionForm
                            )
                        }
                    >
                      {showInterventionForm
                          ? '✕ Cancel'
                          : '+ Create Intervention'}
                    </button>
                  </>
              ) : (
                  <div className="detail-content">
                    <div className="detail-section">
                      <h3>Current Intervention</h3>

                      <div className="detail-item">
                        <div className="detail-label">
                          Intervention ID
                        </div>

                        <div className="detail-value">
                          {currentIntervention.interventionId}
                        </div>
                      </div>

                      <div className="detail-item">
                        <div className="detail-label">
                          Action Type
                        </div>

                        <div className="detail-value">
                          {currentIntervention.actionType}
                        </div>
                      </div>

                      <div className="detail-item">
                        <div className="detail-label">Status</div>

                        <div
                            className="detail-value"
                            style={{
                              display: 'inline-block',
                              background:
                                  currentIntervention.status === 'ASSIGNED'
                                      ? '#3498db'
                                      : currentIntervention.status ===
                                      'IN_PROGRESS'
                                          ? '#f39c12'
                                          : '#2ecc71',
                              color: 'white',
                              padding: '0.4rem 0.8rem',
                              borderRadius: '4px',
                            }}
                        >
                          {currentIntervention.status}
                        </div>
                      </div>

                      <div className="detail-item">
                        <div className="detail-label">
                          Assigned Team
                        </div>

                        <div className="detail-value">
                          {currentIntervention.assignedTeam ||
                              'Not assigned'}
                        </div>
                      </div>

                      {currentIntervention.startedAt && (
                          <div className="detail-item">
                            <div className="detail-label">Started</div>

                            <div className="detail-value">
                              {formatDate(
                                  currentIntervention.startedAt
                              )}
                            </div>
                          </div>
                      )}

                      {currentIntervention.completedAt && (
                          <div className="detail-item">
                            <div className="detail-label">
                              Completed
                            </div>

                            <div className="detail-value">
                              {formatDate(
                                  currentIntervention.completedAt
                              )}
                            </div>
                          </div>
                      )}
                    </div>

                    <div className="detail-section">
                      <h3>Update Status</h3>

                      <div className="btn-group">
                        {currentIntervention.status === 'ASSIGNED' && (
                            <button
                                className="btn btn-secondary"
                                onClick={() =>
                                    handleUpdateInterventionStatus(
                                        'IN_PROGRESS'
                                    )
                                }
                            >
                              Start Work
                            </button>
                        )}

                        {currentIntervention.status === 'IN_PROGRESS' && (
                            <button
                                className="btn btn-success"
                                onClick={() =>
                                    handleUpdateInterventionStatus(
                                        'COMPLETED'
                                    )
                                }
                            >
                              Mark Complete
                            </button>
                        )}
                      </div>
                    </div>
                  </div>
              )}

              {showInterventionForm && (
                  <form
                      onSubmit={handleCreateIntervention}
                      style={{
                        marginTop: '1.5rem',
                        background: '#f9f9f9',
                        padding: '1.5rem',
                        borderRadius: '6px',
                      }}
                  >
                    <div style={{ marginBottom: '1rem' }}>
                      <label
                          style={{
                            display: 'block',
                            marginBottom: '0.5rem',
                            fontWeight: '600',
                          }}
                      >
                        Action Type
                      </label>

                      <select
                          name="actionType"
                          defaultValue="DRAIN_CLEANING"
                          style={{
                            width: '100%',
                            padding: '0.5rem',
                            borderRadius: '4px',
                            border: '1px solid #ddd',
                          }}
                      >
                        <option value="DRAIN_CLEANING">
                          Drain Cleaning
                        </option>
                      </select>
                    </div>

                    <div style={{ marginBottom: '1rem' }}>
                      <label
                          style={{
                            display: 'block',
                            marginBottom: '0.5rem',
                            fontWeight: '600',
                          }}
                      >
                        Assigned Team
                      </label>

                      <input
                          type="text"
                          name="assignedTeam"
                          defaultValue="TEAM-A"
                          style={{
                            width: '100%',
                            padding: '0.5rem',
                            borderRadius: '4px',
                            border: '1px solid #ddd',
                          }}
                      />
                    </div>

                    <button
                        type="submit"
                        className="btn btn-primary"
                    >
                      Create Intervention
                    </button>
                  </form>
              )}
            </div>
        )}

        {/* Evidence Tab */}
        {activeTab === 'evidence' && (
            <div>
              <button
                  className="btn btn-primary"
                  onClick={() =>
                      setShowEvidenceForm(!showEvidenceForm)
                  }
                  style={{ marginBottom: '1.5rem' }}
              >
                {showEvidenceForm
                    ? '✕ Cancel'
                    : '+ Add Evidence'}
              </button>

              {showEvidenceForm && (
                  <form
                      onSubmit={handleCreateEvidence}
                      style={{
                        marginBottom: '2rem',
                        background: '#f9f9f9',
                        padding: '1.5rem',
                        borderRadius: '6px',
                      }}
                  >
                    <div style={{ marginBottom: '1rem' }}>
                      <label
                          style={{
                            display: 'block',
                            marginBottom: '0.5rem',
                            fontWeight: '600',
                          }}
                      >
                        Evidence Type
                      </label>

                      <select
                          name="type"
                          required
                          style={{
                            width: '100%',
                            padding: '0.5rem',
                            borderRadius: '4px',
                            border: '1px solid #ddd',
                          }}
                      >
                        <option value="">Select Type</option>
                        <option value="BEFORE">Before</option>
                        <option value="AFTER">After</option>
                      </select>
                    </div>

                    <div style={{ marginBottom: '1rem' }}>
                      <label
                          style={{
                            display: 'block',
                            marginBottom: '0.5rem',
                            fontWeight: '600',
                          }}
                      >
                        Photo URL
                      </label>

                      <input
                          type="text"
                          name="photoUrl"
                          placeholder="https://example.com/photo.jpg"
                          style={{
                            width: '100%',
                            padding: '0.5rem',
                            borderRadius: '4px',
                            border: '1px solid #ddd',
                          }}
                      />
                    </div>

                    <button
                        type="submit"
                        className="btn btn-primary"
                    >
                      Add Evidence
                    </button>
                  </form>
              )}

              {evidences.length === 0 ? (
                  <div
                      style={{
                        background: '#f0f8ff',
                        padding: '1.5rem',
                        borderRadius: '6px',
                        textAlign: 'center',
                      }}
                  >
                    <p
                        style={{
                          margin: 0,
                          color: '#7f8c8d',
                        }}
                    >
                      No evidence recorded yet
                    </p>
                  </div>
              ) : (
                  <div
                      style={{
                        display: 'grid',
                        gap: '1rem',
                      }}
                  >
                    {evidences.map((ev) => (
                        <div
                            key={ev.evidenceId}
                            className="detail-section"
                        >
                          <div
                              style={{
                                display: 'flex',
                                justifyContent: 'space-between',
                                alignItems: 'center',
                                marginBottom: '1rem',
                              }}
                          >
                            <h4
                                style={{
                                  margin: 0,
                                  color:
                                      ev.type === 'BEFORE'
                                          ? '#e74c3c'
                                          : '#2ecc71',
                                }}
                            >
                              {ev.type} Evidence
                            </h4>

                            <span
                                style={{
                                  display: 'inline-block',
                                  background:
                                      ev.type === 'BEFORE'
                                          ? '#fadbd8'
                                          : '#d5f4e6',
                                  color:
                                      ev.type === 'BEFORE'
                                          ? '#c0392b'
                                          : '#27ae60',
                                  padding: '0.4rem 0.8rem',
                                  borderRadius: '4px',
                                  fontSize: '0.85rem',
                                }}
                            >
                      {ev.type}
                    </span>
                          </div>

                          <div className="detail-item">
                            <div className="detail-label">ID</div>
                            <div className="detail-value">
                              {ev.evidenceId}
                            </div>
                          </div>

                          <div className="detail-item">
                            <div className="detail-label">
                              Timestamp
                            </div>
                            <div className="detail-value">
                              {formatDate(ev.timestamp)}
                            </div>
                          </div>

                          {ev.photoUrl && (
                              <div className="detail-item">
                                <div className="detail-label">Photo</div>

                                <div className="detail-value">
                                  <a
                                      href={ev.photoUrl}
                                      target="_blank"
                                      rel="noopener noreferrer"
                                  >
                                    View Photo
                                  </a>
                                </div>
                              </div>
                          )}
                        </div>
                    ))}
                  </div>
              )}
            </div>
        )}

        {/* Outcome Tab */}
        {activeTab === 'outcome' && (
            <div>
              {!outcome ? (
                  <>
                    <div
                        style={{
                          background: '#f0f8ff',
                          padding: '1.5rem',
                          borderRadius: '6px',
                          marginBottom: '1.5rem',
                        }}
                    >
                      <p
                          style={{
                            margin: 0,
                            color: '#2c3e50',
                          }}
                      >
                        No outcome calculated yet. Collect evidence
                        and then calculate outcome.
                      </p>
                    </div>

                    <button
                        className="btn btn-primary"
                        onClick={() =>
                            setShowOutcomeForm(!showOutcomeForm)
                        }
                    >
                      {showOutcomeForm
                          ? '✕ Cancel'
                          : '+ Calculate Outcome'}
                    </button>
                  </>
              ) : (
                  <div className="detail-content">
                    <div className="detail-section">
                      <h3>Outcome Assessment</h3>

                      <div className="detail-item">
                        <div className="detail-label">
                          Outcome ID
                        </div>

                        <div className="detail-value">
                          {outcome.outcomeId}
                        </div>
                      </div>

                      <div className="detail-item">
                        <div className="detail-label">Status</div>

                        <div
                            className="detail-value"
                            style={{
                              display: 'inline-block',
                              background:
                                  outcome.outcome === 'POSITIVE'
                                      ? '#d5f4e6'
                                      : outcome.outcome === 'WEAK'
                                          ? '#fce8b2'
                                          : '#e8e8e8',
                              color:
                                  outcome.outcome === 'POSITIVE'
                                      ? '#27ae60'
                                      : outcome.outcome === 'WEAK'
                                          ? '#f39c12'
                                          : '#7f8c8d',
                              padding: '0.4rem 0.8rem',
                              borderRadius: '4px',
                            }}
                        >
                          {outcome.outcome}
                        </div>
                      </div>

                      <div className="detail-item">
                        <div className="detail-label">
                          Confidence
                        </div>

                        <div
                            className="detail-value"
                            style={{
                              display: 'inline-block',
                              background:
                                  outcome.confidence === 'HIGH'
                                      ? '#d5f4e6'
                                      : outcome.confidence === 'MEDIUM'
                                          ? '#fce8b2'
                                          : '#fadbd8',
                              color:
                                  outcome.confidence === 'HIGH'
                                      ? '#27ae60'
                                      : outcome.confidence === 'MEDIUM'
                                          ? '#f39c12'
                                          : '#c0392b',
                              padding: '0.4rem 0.8rem',
                              borderRadius: '4px',
                            }}
                        >
                          {outcome.confidence}
                        </div>
                      </div>

                      <div className="detail-item">
                        <div className="detail-label">
                          Before Incidents
                        </div>

                        <div className="detail-value">
                          {outcome.beforeIncidents}
                        </div>
                      </div>

                      <div className="detail-item">
                        <div className="detail-label">
                          After Incidents
                        </div>

                        <div className="detail-value">
                          {outcome.afterIncidents}
                        </div>
                      </div>

                      <div className="detail-item">
                        <div className="detail-label">
                          Observed Reduction
                        </div>

                        <div className="detail-value">
                          <strong>
                            {outcome.observedReduction.toFixed(1)}%
                          </strong>
                        </div>
                      </div>
                    </div>

                    <div className="detail-section">
                      <h3>Assessment Details</h3>

                      <div className="detail-item">
                        <div className="detail-label">
                          Recurring
                        </div>

                        <div className="detail-value">
                          {outcome.recurring ? 'Yes' : 'No'}
                        </div>
                      </div>

                      <div className="detail-item">
                        <div className="detail-label">
                          Next Action
                        </div>

                        <div className="detail-value">
                          {outcome.nextAction}
                        </div>
                      </div>

                      <div className="detail-item">
                        <div className="detail-label">
                          Calculated At
                        </div>

                        <div className="detail-value">
                          {formatDate(outcome.createdAt)}
                        </div>
                      </div>
                    </div>
                  </div>
              )}

              {showOutcomeForm && (
                  <form
                      onSubmit={handleCreateOutcome}
                      style={{
                        marginTop: '1.5rem',
                        background: '#f9f9f9',
                        padding: '1.5rem',
                        borderRadius: '6px',
                      }}
                  >
                    <div style={{ marginBottom: '1rem' }}>
                      <label
                          style={{
                            display: 'block',
                            marginBottom: '0.5rem',
                            fontWeight: '600',
                          }}
                      >
                        Before Incidents
                      </label>

                      <input
                          type="number"
                          name="beforeIncidents"
                          defaultValue="4"
                          min="0"
                          style={{
                            width: '100%',
                            padding: '0.5rem',
                            borderRadius: '4px',
                            border: '1px solid #ddd',
                          }}
                      />
                    </div>

                    <div style={{ marginBottom: '1rem' }}>
                      <label
                          style={{
                            display: 'block',
                            marginBottom: '0.5rem',
                            fontWeight: '600',
                          }}
                      >
                        After Incidents
                      </label>

                      <input
                          type="number"
                          name="afterIncidents"
                          defaultValue="1"
                          min="0"
                          style={{
                            width: '100%',
                            padding: '0.5rem',
                            borderRadius: '4px',
                            border: '1px solid #ddd',
                          }}
                      />
                    </div>

                    <div style={{ marginBottom: '1rem' }}>
                      <label
                          style={{
                            display: 'block',
                            marginBottom: '0.5rem',
                            fontWeight: '600',
                          }}
                      >
                        Recurring?
                      </label>

                      <select
                          name="recurring"
                          defaultValue="false"
                          style={{
                            width: '100%',
                            padding: '0.5rem',
                            borderRadius: '4px',
                            border: '1px solid #ddd',
                          }}
                      >
                        <option value="false">No</option>
                        <option value="true">Yes</option>
                      </select>
                    </div>

                    <button
                        type="submit"
                        className="btn btn-primary"
                    >
                      Calculate Outcome
                    </button>
                  </form>
              )}
            </div>
        )}
      </div>
  );
}

import React, { useState, useEffect } from 'react';
import { apiService } from '../api.js';

export default function IssueDashboard({ onSelectIssue }) {
  const [issues, setIssues] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetchIssues();
  }, []);

  const fetchIssues = async () => {
    try {
      setLoading(true);
      setError(null);
      const data = await apiService.getIssues();
      setIssues(data);
    } catch (err) {
      console.error('Error fetching issues:', err);
      setError(`Failed to load issues: ${err.message}`);
      setIssues([]);
    } finally {
      setLoading(false);
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
        minute: '2-digit'
      });
    } catch (e) {
      return dateString;
    }
  };

  const getStatusClass = (status) => {
    return status ? status.toLowerCase().replace(/_/g, '-') : 'open';
  };

  if (loading) {
    return (
      <div className="loading">
        <div className="spinner"></div>
        <p>Loading issues...</p>
      </div>
    );
  }

  return (
    <div className="dashboard">
      <div className="dashboard-header">
        <h2>Reported Issues</h2>
        <p>Click on any issue to view details, create intervention, and track outcomes</p>
      </div>

      {error && <div className="error"><p className="error-message">{error}</p></div>}

      {issues.length === 0 ? (
        <div className="empty-state">
          <p>📝</p>
          <h3>No Issues Yet</h3>
          <p>Reported issues will appear here. Check back soon!</p>
        </div>
      ) : (
        <div className="issues-grid">
          {issues.map((issue) => (
            <div key={issue.issueId} className="issue-card" onClick={() => onSelectIssue(issue.issueId)}>
              <div className="issue-card-header">
                <h3 className="issue-card-title">{issue.description.substring(0, 30)}...</h3>
                <span className={`status-badge ${getStatusClass(issue.status)}`}>
                  {issue.status}
                </span>
              </div>

              <div className="issue-card-body">
                <div className="issue-type">{issue.type || 'WATERLOGGING'}</div>
                <p className="issue-description">{issue.description}</p>
                <p className="issue-location">
                  📍 Lat: {issue.latitude.toFixed(4)}, Lon: {issue.longitude.toFixed(4)}
                </p>
                <p className="issue-date">📅 {formatDate(issue.createdAt)}</p>
                {issue.photoUrl && (
                  <p className="issue-location">📸 Photo: <a href={issue.photoUrl} target="_blank" rel="noopener noreferrer">View</a></p>
                )}
              </div>

              <div className="issue-card-footer">
                <button className="view-details-btn" onClick={() => onSelectIssue(issue.issueId)}>
                  View Details & Track
                </button>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

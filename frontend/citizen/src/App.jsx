import React, { useState } from 'react';
import { citizenApi } from './api.js';
import './App.css';

export default function App() {
  const [formData, setFormData] = useState({
    type: 'WATERLOGGING',
    description: '',
    latitude: 21.1702,
    longitude: 72.8311,
    photoUrl: '',
  });
  
  const [submitted, setSubmitted] = useState(false);
  const [error, setError] = useState(null);
  const [issueId, setIssueId] = useState(null);
  const [loading, setLoading] = useState(false);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: name === 'latitude' || name === 'longitude' ? parseFloat(value) : value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    if (!formData.description.trim()) {
      setError('Please describe the issue');
      return;
    }

    try {
      setLoading(true);
      setError(null);
      
      const result = await citizenApi.reportIssue(formData);
      setIssueId(result.issueId);
      setSubmitted(true);
      
      // Reset form
      setFormData({
        type: 'WATERLOGGING',
        description: '',
        latitude: 21.1702,
        longitude: 72.8311,
        photoUrl: '',
      });
    } catch (err) {
      console.error('Error reporting issue:', err);
      setError(`Error: ${err.message}`);
    } finally {
      setLoading(false);
    }
  };

  const handleReportAnother = () => {
    setSubmitted(false);
    setIssueId(null);
  };

  if (submitted && issueId) {
    return (
      <div className="app-container">
        <div className="form-card success-card">
          <div className="success-icon">✓</div>
          <h2>Thank You!</h2>
          <p>Your issue has been reported successfully.</p>
          <div className="issue-id">Issue ID: <strong>{issueId}</strong></div>
          <p className="info-text">Our municipal team will review your report and take appropriate action. You can track the status of your issue using the ID above.</p>
          <button className="btn btn-primary" onClick={handleReportAnother}>
            Report Another Issue
          </button>
        </div>
      </div>
    );
  }

  return (
    <div className="app-container">
      <div className="form-card">
        <div className="form-header">
          <h1>🌱 Report Urban Issue</h1>
          <p>Help us improve your city</p>
        </div>

        {error && <div className="error-message">{error}</div>}

        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="type">Issue Type *</label>
            <select
              id="type"
              name="type"
              value={formData.type}
              onChange={handleChange}
              disabled
            >
              <option value="WATERLOGGING">Urban Waterlogging</option>
            </select>
            <small>For this MVP, we're focused on waterlogging issues</small>
          </div>

          <div className="form-group">
            <label htmlFor="description">Description *</label>
            <textarea
              id="description"
              name="description"
              value={formData.description}
              onChange={handleChange}
              placeholder="Describe the waterlogging problem in detail. Include location details, severity, and any other relevant information."
              rows="5"
              required
            />
          </div>

          <div className="form-row">
            <div className="form-group">
              <label htmlFor="latitude">Latitude *</label>
              <input
                type="number"
                id="latitude"
                name="latitude"
                value={formData.latitude}
                onChange={handleChange}
                step="0.0001"
                placeholder="21.1702"
                required
              />
              <small>Default: Surat, India</small>
            </div>

            <div className="form-group">
              <label htmlFor="longitude">Longitude *</label>
              <input
                type="number"
                id="longitude"
                name="longitude"
                value={formData.longitude}
                onChange={handleChange}
                step="0.0001"
                placeholder="72.8311"
                required
              />
              <small>Default: Surat, India</small>
            </div>
          </div>

          <div className="form-group">
            <label htmlFor="photoUrl">Photo URL (Optional)</label>
            <input
              type="url"
              id="photoUrl"
              name="photoUrl"
              value={formData.photoUrl}
              onChange={handleChange}
              placeholder="https://example.com/photo.jpg"
            />
            <small>Link to a photo of the issue (optional)</small>
          </div>

          <button
            type="submit"
            className="btn btn-primary btn-large"
            disabled={loading}
          >
            {loading ? 'Submitting...' : 'Report Issue'}
          </button>
        </form>

        <div className="form-footer">
          <p>📍 Your reports help the municipal team understand and address local issues.</p>
        </div>
      </div>
    </div>
  );
}

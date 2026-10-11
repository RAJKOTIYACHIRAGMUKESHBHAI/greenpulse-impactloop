import React, { useState, useEffect } from 'react';
import { apiService } from './api.js';
import './App.css';
import IssueDashboard from './components/IssueDashboard';
import IssueDetail from './components/IssueDetail';

export default function App() {
  const [currentView, setCurrentView] = useState('dashboard');
  const [selectedIssueId, setSelectedIssueId] = useState(null);

  const handleSelectIssue = (issueId) => {
    setSelectedIssueId(issueId);
    setCurrentView('detail');
  };

  const handleBackToDashboard = () => {
    setCurrentView('dashboard');
    setSelectedIssueId(null);
  };

  return (
    <div className="app">
      <header className="app-header">
        <h1>🌱 GreenPulse ImpactLoop</h1>
        <p>Municipal Dashboard - Urban Waterlogging Impact Assessment</p>
      </header>
      
      <main className="app-main">
        {currentView === 'dashboard' ? (
          <IssueDashboard onSelectIssue={handleSelectIssue} />
        ) : (
          <IssueDetail
            issueId={selectedIssueId}
            onBack={handleBackToDashboard}
          />
        )}
      </main>
    </div>
  );
}

const API_BASE_URL = 'http://localhost:8080/api/v1';

export const apiService = {
  // Issues
  async getIssues() {
    const response = await fetch(`${API_BASE_URL}/issues`);
    if (!response.ok) throw new Error('Failed to fetch issues');
    return response.json();
  },

  async getIssueById(issueId) {
    const response = await fetch(`${API_BASE_URL}/issues/${issueId}`);
    if (!response.ok) throw new Error('Failed to fetch issue');
    return response.json();
  },

  // Interventions
  async createIntervention(data) {
    const response = await fetch(`${API_BASE_URL}/interventions`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data),
    });
    if (!response.ok) throw new Error('Failed to create intervention');
    return response.json();
  },

  async getIntervention(interventionId) {
    const response = await fetch(`${API_BASE_URL}/interventions/${interventionId}`);
    if (!response.ok) throw new Error('Failed to fetch intervention');
    return response.json();
  },

  async updateIntervention(interventionId, data) {
    const response = await fetch(`${API_BASE_URL}/interventions/${interventionId}`, {
      method: 'PATCH',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data),
    });
    if (!response.ok) throw new Error('Failed to update intervention');
    return response.json();
  },

  // Evidence
  async createEvidence(data) {
    const response = await fetch(`${API_BASE_URL}/evidence`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data),
    });
    if (!response.ok) throw new Error('Failed to create evidence');
    return response.json();
  },

  async getEvidenceByIntervention(interventionId) {
    const response = await fetch(`${API_BASE_URL}/evidence/intervention/${interventionId}`);
    if (!response.ok) throw new Error('Failed to fetch evidence');
    return response.json();
  },

  // Rain Events
  async createRainEvent(data) {
    const response = await fetch(`${API_BASE_URL}/rain-events`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data),
    });
    if (!response.ok) throw new Error('Failed to create rain event');
    return response.json();
  },

  async getRainEvents() {
    const response = await fetch(`${API_BASE_URL}/rain-events`);
    if (!response.ok) throw new Error('Failed to fetch rain events');
    return response.json();
  },

  // Outcomes
  async createOutcome(data) {
    const response = await fetch(`${API_BASE_URL}/outcomes`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data),
    });
    if (!response.ok) throw new Error('Failed to create outcome');
    return response.json();
  },

  async getOutcome(outcomeId) {
    const response = await fetch(`${API_BASE_URL}/outcomes/${outcomeId}`);
    if (!response.ok) throw new Error('Failed to fetch outcome');
    return response.json();
  },
};

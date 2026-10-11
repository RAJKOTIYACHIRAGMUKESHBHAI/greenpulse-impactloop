const API_BASE_URL = 'http://3.237.10.181:8080/api/v1';

export const citizenApi = {
  async reportIssue(data) {
    const response = await fetch(`${API_BASE_URL}/issues`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data),
    });
    if (!response.ok) throw new Error('Failed to report issue');
    return response.json();
  },
};

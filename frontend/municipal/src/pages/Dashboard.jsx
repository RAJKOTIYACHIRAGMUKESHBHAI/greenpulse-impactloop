import React, { useState } from "react";

function Dashboard() {
  const [activeSection, setActiveSection] = useState("dashboard");

  const complaints = [
    {
      id: "CMP001",
      citizen: "Rahul Patel",
      location: "Ward 1",
      description: "Heavy waterlogging near main road",
      status: "Pending",
    },
    {
      id: "CMP002",
      citizen: "Kajal Shah",
      location: "Ward 3",
      description: "Drain overflow after rainfall",
      status: "Resolved",
    },
    {
      id: "CMP003",
      citizen: "Amit Joshi",
      location: "Ward 5",
      description: "Blocked drainage near residential area",
      status: "Pending",
    },
    {
      id: "CMP004",
      citizen: "Neha Patel",
      location: "Ward 2",
      description: "Water accumulated near school",
      status: "Resolved",
    },
  ];

  const wards = [
    {
      id: "W001",
      name: "Ward 1",
      complaints: 32,
      resolved: 25,
      pending: 7,
    },
    {
      id: "W002",
      name: "Ward 2",
      complaints: 28,
      resolved: 24,
      pending: 4,
    },
    {
      id: "W003",
      name: "Ward 3",
      complaints: 25,
      resolved: 20,
      pending: 5,
    },
    {
      id: "W004",
      name: "Ward 4",
      complaints: 18,
      resolved: 15,
      pending: 3,
    },
    {
      id: "W005",
      name: "Ward 5",
      complaints: 22,
      resolved: 14,
      pending: 8,
    },
  ];

  const citizens = [
    {
      id: "CIT001",
      name: "Rahul Patel",
      ward: "Ward 1",
      complaints: 4,
      status: "Active",
    },
    {
      id: "CIT002",
      name: "Kajal Shah",
      ward: "Ward 3",
      complaints: 3,
      status: "Active",
    },
    {
      id: "CIT003",
      name: "Amit Joshi",
      ward: "Ward 5",
      complaints: 5,
      status: "Active",
    },
    {
      id: "CIT004",
      name: "Neha Patel",
      ward: "Ward 2",
      complaints: 2,
      status: "Active",
    },
    {
      id: "CIT005",
      name: "Priya Mehta",
      ward: "Ward 4",
      complaints: 1,
      status: "Active",
    },
  ];

  const reports = [
    {
      id: "REP001",
      title: "Waterlogging Complaints",
      value: 125,
      description: "Total citizen complaints",
    },
    {
      id: "REP002",
      title: "Resolved Complaints",
      value: 98,
      description: "Complaints successfully resolved",
    },
    {
      id: "REP003",
      title: "Pending Complaints",
      value: 27,
      description: "Complaints requiring action",
    },
    {
      id: "REP004",
      title: "Completed Interventions",
      value: 82,
      description: "Drain cleaning interventions completed",
    },
    {
      id: "REP005",
      title: "Recurring Issues",
      value: 12,
      description: "Locations with repeated waterlogging",
    },
    {
      id: "REP006",
      title: "Next Actions",
      value: 15,
      description: "Locations requiring follow-up",
    },
  ];

  return (
    <div style={styles.container}>

      {/* SIDEBAR */}
      <div style={styles.sidebar}>
        <h2 style={styles.logo}>GreenPulse</h2>

        <div
          style={{
            ...styles.menuItem,
            ...(activeSection === "dashboard"
              ? styles.activeMenu
              : {}),
          }}
          onClick={() => setActiveSection("dashboard")}
        >
          🏠 Dashboard
        </div>

        <div
          style={{
            ...styles.menuItem,
            ...(activeSection === "complaints"
              ? styles.activeMenu
              : {}),
          }}
          onClick={() => setActiveSection("complaints")}
        >
          📋 Complaints
        </div>

        <div
          style={{
            ...styles.menuItem,
            ...(activeSection === "wards"
              ? styles.activeMenu
              : {}),
          }}
          onClick={() => setActiveSection("wards")}
        >
          🏙️ Wards
        </div>

        <div
          style={{
            ...styles.menuItem,
            ...(activeSection === "citizens"
              ? styles.activeMenu
              : {}),
          }}
          onClick={() => setActiveSection("citizens")}
        >
          👥 Citizens
        </div>

        <div
          style={{
            ...styles.menuItem,
            ...(activeSection === "reports"
              ? styles.activeMenu
              : {}),
          }}
          onClick={() => setActiveSection("reports")}
        >
          📊 Reports
        </div>
      </div>

      {/* MAIN CONTENT */}
      <div style={styles.mainContent}>

        {/* DASHBOARD */}
        {activeSection === "dashboard" && (
          <>
            <h1 style={styles.heading}>
              Municipal Dashboard
            </h1>

            <p style={styles.subtitle}>
              GreenPulse – ImpactLoop monitoring dashboard
            </p>

            <div style={styles.cardContainer}>

              <div style={styles.card}>
                <h3>Total Complaints</h3>
                <p style={styles.number}>125</p>
              </div>

              <div style={styles.card}>
                <h3>Resolved</h3>
                <p style={styles.number}>98</p>
              </div>

              <div style={styles.card}>
                <h3>Pending</h3>
                <p style={styles.number}>27</p>
              </div>

              <div style={styles.card}>
                <h3>Active Wards</h3>
                <p style={styles.number}>12</p>
              </div>

            </div>
          </>
        )}

        {/* COMPLAINTS */}
        {activeSection === "complaints" && (
          <>
            <h1 style={styles.heading}>
              Complaints
            </h1>

            <p style={styles.subtitle}>
              Waterlogging complaints reported by citizens
            </p>

            <div style={styles.tableContainer}>
              <table style={styles.table}>
                <thead>
                  <tr>
                    <th style={styles.th}>Complaint ID</th>
                    <th style={styles.th}>Citizen</th>
                    <th style={styles.th}>Location</th>
                    <th style={styles.th}>Description</th>
                    <th style={styles.th}>Status</th>
                  </tr>
                </thead>

                <tbody>
                  {complaints.map((complaint) => (
                    <tr key={complaint.id}>
                      <td style={styles.td}>
                        {complaint.id}
                      </td>

                      <td style={styles.td}>
                        {complaint.citizen}
                      </td>

                      <td style={styles.td}>
                        {complaint.location}
                      </td>

                      <td style={styles.td}>
                        {complaint.description}
                      </td>

                      <td style={styles.td}>
                        <span
                          style={{
                            ...styles.status,
                            backgroundColor:
                              complaint.status === "Resolved"
                                ? "#d4edda"
                                : "#fff3cd",
                            color:
                              complaint.status === "Resolved"
                                ? "#155724"
                                : "#856404",
                          }}
                        >
                          {complaint.status}
                        </span>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          </>
        )}

        {/* WARDS */}
        {activeSection === "wards" && (
          <>
            <h1 style={styles.heading}>
              Wards
            </h1>

            <p style={styles.subtitle}>
              Ward-wise waterlogging complaint monitoring
            </p>

            <div style={styles.tableContainer}>
              <table style={styles.table}>
                <thead>
                  <tr>
                    <th style={styles.th}>Ward ID</th>
                    <th style={styles.th}>Ward Name</th>
                    <th style={styles.th}>Total Complaints</th>
                    <th style={styles.th}>Resolved</th>
                    <th style={styles.th}>Pending</th>
                  </tr>
                </thead>

                <tbody>
                  {wards.map((ward) => (
                    <tr key={ward.id}>
                      <td style={styles.td}>
                        {ward.id}
                      </td>

                      <td style={styles.td}>
                        {ward.name}
                      </td>

                      <td style={styles.td}>
                        {ward.complaints}
                      </td>

                      <td style={styles.td}>
                        {ward.resolved}
                      </td>

                      <td style={styles.td}>
                        <span style={styles.pendingBadge}>
                          {ward.pending}
                        </span>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          </>
        )}

        {/* CITIZENS */}
        {activeSection === "citizens" && (
          <>
            <h1 style={styles.heading}>
              Citizens
            </h1>

            <p style={styles.subtitle}>
              Citizen information and complaint activity
            </p>

            <div style={styles.tableContainer}>
              <table style={styles.table}>
                <thead>
                  <tr>
                    <th style={styles.th}>Citizen ID</th>
                    <th style={styles.th}>Citizen Name</th>
                    <th style={styles.th}>Ward</th>
                    <th style={styles.th}>Complaints</th>
                    <th style={styles.th}>Status</th>
                  </tr>
                </thead>

                <tbody>
                  {citizens.map((citizen) => (
                    <tr key={citizen.id}>
                      <td style={styles.td}>
                        {citizen.id}
                      </td>

                      <td style={styles.td}>
                        {citizen.name}
                      </td>

                      <td style={styles.td}>
                        {citizen.ward}
                      </td>

                      <td style={styles.td}>
                        {citizen.complaints}
                      </td>

                      <td style={styles.td}>
                        <span style={styles.activeBadge}>
                          {citizen.status}
                        </span>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          </>
        )}

        {/* REPORTS */}
        {activeSection === "reports" && (
          <>
            <h1 style={styles.heading}>
              Reports
            </h1>

            <p style={styles.subtitle}>
              Municipal waterlogging and intervention reports
            </p>

            <div style={styles.reportGrid}>
              {reports.map((report) => (
                <div
                  key={report.id}
                  style={styles.reportCard}
                >
                  <h3 style={styles.reportTitle}>
                    {report.title}
                  </h3>

                  <p style={styles.reportNumber}>
                    {report.value}
                  </p>

                  <p style={styles.reportDescription}>
                    {report.description}
                  </p>
                </div>
              ))}
            </div>

            <div style={styles.infoBox}>
              <h3>ImpactLoop Observation</h3>

              <p>
                Reports currently show demo data for the
                municipal dashboard. Actual intervention
                outcomes will be connected after the backend
                and outcome engine are integrated.
              </p>
            </div>
          </>
        )}

      </div>
    </div>
  );
}

/* STYLES */

const styles = {
  container: {
    display: "flex",
    minHeight: "100vh",
    fontFamily: "Arial, sans-serif",
    backgroundColor: "#f4f6f8",
  },

  sidebar: {
    width: "230px",
    backgroundColor: "#1b4332",
    color: "white",
    padding: "20px",
    boxSizing: "border-box",
  },

  logo: {
    marginBottom: "30px",
    textAlign: "center",
  },

  menuItem: {
    padding: "14px 12px",
    marginBottom: "8px",
    borderRadius: "6px",
    cursor: "pointer",
    fontSize: "16px",
  },

  activeMenu: {
    backgroundColor: "#40916c",
  },

  mainContent: {
    flex: 1,
    padding: "35px",
  },

  heading: {
    marginBottom: "8px",
    color: "#1b4332",
  },

  subtitle: {
    color: "#666",
    marginBottom: "30px",
  },

  cardContainer: {
    display: "grid",
    gridTemplateColumns: "repeat(4, 1fr)",
    gap: "20px",
  },

  card: {
    backgroundColor: "white",
    padding: "25px",
    borderRadius: "10px",
    boxShadow: "0 2px 8px rgba(0,0,0,0.1)",
  },

  number: {
    fontSize: "32px",
    fontWeight: "bold",
    color: "#2d6a4f",
  },

  tableContainer: {
    backgroundColor: "white",
    padding: "20px",
    borderRadius: "10px",
    boxShadow: "0 2px 8px rgba(0,0,0,0.1)",
    overflowX: "auto",
  },

  table: {
    width: "100%",
    borderCollapse: "collapse",
  },

  th: {
    textAlign: "left",
    padding: "14px",
    backgroundColor: "#2d6a4f",
    color: "white",
  },

  td: {
    padding: "14px",
    borderBottom: "1px solid #ddd",
  },

  status: {
    padding: "6px 12px",
    borderRadius: "15px",
    fontWeight: "bold",
    fontSize: "13px",
  },

  pendingBadge: {
    backgroundColor: "#fff3cd",
    color: "#856404",
    padding: "6px 12px",
    borderRadius: "15px",
    fontWeight: "bold",
  },

  activeBadge: {
    backgroundColor: "#d4edda",
    color: "#155724",
    padding: "6px 12px",
    borderRadius: "15px",
    fontWeight: "bold",
  },

  reportGrid: {
    display: "grid",
    gridTemplateColumns: "repeat(3, 1fr)",
    gap: "20px",
  },

  reportCard: {
    backgroundColor: "white",
    padding: "25px",
    borderRadius: "10px",
    boxShadow: "0 2px 8px rgba(0,0,0,0.1)",
  },

  reportTitle: {
    color: "#1b4332",
    marginBottom: "10px",
  },

  reportNumber: {
    fontSize: "32px",
    fontWeight: "bold",
    color: "#2d6a4f",
    margin: "10px 0",
  },

  reportDescription: {
    color: "#666",
  },

  infoBox: {
    backgroundColor: "white",
    marginTop: "25px",
    padding: "20px",
    borderRadius: "10px",
    borderLeft: "5px solid #40916c",
  },
};

export default Dashboard;





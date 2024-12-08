import { useEffect, useState } from "react";
import ConfigurationForm from "./components/ConfigurationForm";
import ControlPanel from "./components/ControlPanel";
import LogDisplay from "./components/LogDisplay";
import TicketDisplay from "./components/TicketDisplay";
import "./style/App.css";

function App() {
  const [config, setConfig] = useState(null); // Configuration settings
  const [tickets, setTickets] = useState([]); // Current tickets
  const [logs, setLogs] = useState([]); // Logs from backend
  const [systemRunning, setSystemRunning] = useState(false); // System running status

  // Polling to fetch ticket availability and logs
  useEffect(() => {
    if (systemRunning) {
      const interval = setInterval(() => {
        fetchTicketData();
        fetchLogData();
      }, 2000); // Poll every 2 seconds
      return () => clearInterval(interval); // Cleanup interval on unmount
    }
  }, [systemRunning]);

  const fetchTicketData = async () => {
    try {
      const response = await fetch("http://localhost:8080/tickets");
      const data = await response.json();
      setTickets(data);
    } catch (error) {
      console.error("Error fetching ticket data:", error);
    }
  };

  const fetchLogData = async () => {
    try {
      const response = await fetch("http://localhost:8080/logs");
      const data = await response.text();
      setLogs(data.split("\n"));
    } catch (error) {
      console.error("Error fetching log data:", error);
    }
  };

  // Handle configuration submission
  const handleConfigurationSubmit = (configData) => {
    setConfig(configData);
    setSystemRunning(true); // Start the system once configuration is set
  };

  // Control system start/stop
  const handleControlPanel = (action) => {
    if (action === "start") {
      setSystemRunning(true);
    } else if (action === "stop") {
      setSystemRunning(false);
    }
  };

  return (
    <div className="App">
      <h1>Real-Time Ticketing System</h1>
      {!config ? (
        <ConfigurationForm onSubmit={handleConfigurationSubmit} />
      ) : (
        <>
          <TicketDisplay tickets={tickets} />
          <ControlPanel onControl={handleControlPanel} />
          <LogDisplay logs={logs} />
        </>
      )}
    </div>
  );
}

export default App;

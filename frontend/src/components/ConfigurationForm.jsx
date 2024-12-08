import { useState } from "react";

const ConfigurationForm = ({ onSubmit }) => {
  const [totalTickets, setTotalTickets] = useState(100);
  const [ticketReleaseRate, setTicketReleaseRate] = useState(1000);
  const [customerRetrievalRate, setCustomerRetrievalRate] = useState(1500);
  const [maxCapacity, setMaxCapacity] = useState(50);

  const handleSubmit = (e) => {
    e.preventDefault();
    const configData = {
      totalTickets,
      ticketReleaseRate,
      customerRetrievalRate,
      maxCapacity,
    };
    onSubmit(configData);
  };

  return (
    <form onSubmit={handleSubmit}>
      <label>
        Total Tickets:
        <input
          type="number"
          value={totalTickets}
          onChange={(e) => setTotalTickets(Number(e.target.value))}
        />
      </label>
      <label>
        Ticket Release Rate (ms):
        <input
          type="number"
          value={ticketReleaseRate}
          onChange={(e) => setTicketReleaseRate(Number(e.target.value))}
        />
      </label>
      <label>
        Customer Retrieval Rate (ms):
        <input
          type="number"
          value={customerRetrievalRate}
          onChange={(e) => setCustomerRetrievalRate(Number(e.target.value))}
        />
      </label>
      <label>
        Maximum Ticket Capacity:
        <input
          type="number"
          value={maxCapacity}
          onChange={(e) => setMaxCapacity(Number(e.target.value))}
        />
      </label>
      <button type="submit">Start</button>
    </form>
  );
};

export default ConfigurationForm;

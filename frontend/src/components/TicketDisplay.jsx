const TicketDisplay = ({ tickets = [] }) => {
  return (
    <div>
      <h3>Available Tickets</h3>
      <ul>
        {tickets.map((ticket, index) => (
          <li key={index}>{ticket}</li>
        ))}
      </ul>
    </div>
  );
};

export default TicketDisplay;

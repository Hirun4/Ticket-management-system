const ControlPanel = ({ onControl }) => {
  return (
    <div>
      <button onClick={() => onControl("start")}>Start System</button>
      <button onClick={() => onControl("stop")}>Stop System</button>
    </div>
  );
};

export default ControlPanel;

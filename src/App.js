import './App.css';
import React from 'react';
import VotingPieChart from './component/VotingPieChart';

const App = () => {
  return (
    <div className="App">
      <header className="App-header">
        <h1 className="title">Voting Details</h1>
      </header>
      <main>
        <VotingPieChart />
      </main>
    </div>
  );
};

export default App;

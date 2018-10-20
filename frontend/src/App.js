import React, { Component } from 'react';
import { BrowserRouter as Router, Route } from "react-router-dom";
import './App.css';
import Home from './Home/home'

class App extends Component {
  render() {
    return (
      <Router>
        <div className="App">
          <header className="App-header">
          <div>
            <Route exact path="/" component={Home} />
          </div>
          </header>
        </div>
      </Router>
    );
  }
}

export default App;

import React from 'react';
import './css/App.css';
import User from './pages/User';
import Drug from './pages/Drug';
import {BrowserRouter,Route,Link,Switch} from 'react-router-dom'
function App() {
  return (
    <div className="App">
        <BrowserRouter>
        <div className="nav">
        <h2 class="title">管理员界面</h2>
          <ul>
            <li><Link to="/User">用户管理</Link></li>
            <li><Link to="/Drug">药品管理</Link></li>
          </ul>
        </div>
        <div className="content">
          <Switch>
            <Route path="/Drug" component={Drug}/>
            <Route path="/User" component={User}/>
          </Switch>
        </div>
      </BrowserRouter>
    </div>
  );
}

export default App;

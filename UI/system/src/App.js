import React from 'react';
import {BrowserRouter,Route,Link,Switch} from 'react-router-dom'
import './App.css';
import User from './pages/User'
import Drug from './pages/Drug'


function App() {
  return (
    <div className="App">
      <BrowserRouter>
      {/* 左侧导航 */}
      <div className="nav_left">
        <div className="title">
          医药管理系统
        </div>
        <ul>
          <li>
            <Link to="/user">用户管理</Link>
          </li>
          <li>
            <Link to="/drug">药品管理</Link>
          </li>
        </ul>
      </div>
      {/* 右侧内容 */}
      <div className="content_right">
      
        {/* 路由 */}
        <Switch>
          <Route path="/user" component={User}/>
          <Route path="/drug" component={Drug}/>
     
        </Switch>
      </div>
      </BrowserRouter>
    </div>
  );
}

export default App;

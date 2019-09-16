import React, { Component } from 'react';
import logo from './logo.svg';
import './css/Loginstyle.css';
import Login from './pages/Login';
import {BrowserRouter,Route,Link,Switch} from 'react-router-dom';

function App(){
    return(
      <center>
        <form>
          <div class="inner">
            <h2 医  药 管 理 系 统 />
          </div>
          <div class="text">
        <BrowserRouter>
        <div className="nav">
          <ul>
            <li><Link to="/login">点击登录</Link></li>
        
          </ul>
        </div>
        <div className="content">
          <Switch>
            <Route path="/login" component={Login}/>
          
          </Switch>
        </div>
      </BrowserRouter>
          </div>
        <div class="under">
          <div class="div1">
            <div class="div2">
              <p>给我一份信任</p >
              <p>还您一身健康</p >
              <p>细致入微</p >
              <p>关注每位患者的一点一滴</p >
              <p>用技术治疗患者病</p >
              <p>用温情治疗患者痛</p >
              <p>多一点理解</p >
              <p>多一点沟通</p >
              <p>改善服务态度</p >
              <p>提高服务质量</p >
              <p>优化服务流程</p >
              <p>创新服务模式</p >
              <p>努力使患者放心满意</p >
              <p>全心全意为患者服务</p >   
            </div>
          </div>
        </div>
      </form> 
      </center>
    );
  }
export default App;

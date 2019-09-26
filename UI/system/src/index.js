import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import Admin from './pages/Admin'
import Doctor from './pages/Doctor'
import Pharmacist from './pages/Pharmacist'
import MyRoute from '../src/route/index'
import * as serviceWorker from './serviceWorker';

ReactDOM.render(<MyRoute />, document.getElementById('root'));

//ReactDOM.render(<App />, document.getElementById('root'));
//ReactDOM.render(<Admin />, document.getElementById('root'));
//ReactDOM.render(<Doctor />, document.getElementById('root'));
//ReactDOM.render(<Pharmacist />, document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();

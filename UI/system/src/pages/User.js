import React from 'react';
import $ from 'jquery';
import './style.css';
class User extends React.Component{

    constructor(){
        super();
        //局部状态
        this.state={
            users:[],
            form:{
                name:"",
                password:"",
                sex:"",
                id:"",
                type:"",
                status:""
            }
        }
    }
    componentDidMount(){
        this.loadUsers();
        
    }
    loadUsers(){
        //加载用户信息
        $.get("http://127.0.0.1:8888/user/findAll",({status,message,date})=>{
           if(status===200){
               //将查询数据库设置到state中
               this.setState({
                   "users":date
               })
           }else{
               alert(message)
           }
       })
   }

}
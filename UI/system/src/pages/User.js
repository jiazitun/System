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
   changeHandler=(event)=>{
    let name=event.target.name;
    let value=event.target.value;
    this.setState({
        form:{...this.state.form,...{[name]:value}}
    })
    }
    submitHandler=(event)=>{
        let url="http://127.0.0.1:8888/user/saveOrUpdate";
        $.post(url,this.state.form,({message})=>{
            alert(message)
            //刷新页面
            this.loadUsers();
        })
        event.preventDefault();
    }
    toUpdate=(id)=>{
        //1,通过id查找课程信息
        //2，将返回结果设置到this.state.form中
        //state->form
        $.get("http://127.0.0.1:8888/user/findById?id="+id,({status,message,date})=>{
            if(status===200){
                //将查询数据库设置到state中
                this.setState({
                    "form":date
                })
            }else{
                alert(message)
            }
        })
    }


}
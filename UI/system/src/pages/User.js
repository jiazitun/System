import React from 'react';
import $ from 'jquery';
import '../css/style.css';

class User extends React.Component{

    constructor(){
        super();
        //局部状态
        this.state={
            flag:false,
            users:[],
            form:{
                name:"",
                sex:"",
                password:"",
                type:"",
                status:""
            }
        }
    }

    componentDidMount(){
        //加载用户信息
        this.loadUsers();
        console.log(this.state.users);
    }
    
    loadUsers(){
    //查询所有用户信息
    $.get("http://127.0.0.1:8888/user/findAll",({status,message,data})=>{
        console.log(data);
    if(status===200){
       
        //将查询数据库设置到state中
            this.setState({      
            users:data  
        })
        console.log(this.state.users);
        console.log(data);
        }else{
            alert(message)
        }
       });
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
        //1,通过id查找用户
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
    toAdd=()=>{
        this.setState({
            flag:!this.state.flag,
            form:{
                name:"",
                sex:"",
                password:"",
                type:"",
                status:""
            }
        })
    }
    toDelete=(id)=>{
         //1,通过id查找用户
        //2，将返回结果设置到this.state.form中
        //state->form
        $.get("http://127.0.0.1:8888/user/deleteById?id="+id,({status,message,date})=>{
            alert(message)
            //刷新页面
            this.loadUsers();
        })
    }
    render(){
        let {users,form,flag}=this.state;
        //let users = this.state;
        let $form;
        if(flag){
            $form=(
                <form onSubmit={this.submitHandler}>
                姓名
                <input type="text" name="name" value={form.name} onChange={this.changeHandler}/>
                性别
                <input type="text" name="sex" value={form.sex} onChange={this.changeHandler}/>
                密码
                <input type="text" name="password" value={form.password} onChange={this.changeHandler}/>
                类型
                <input type="text" name="type" value={form.type} onChange={this.changeHandler}/>
                状态
                <input type="text" name="status" value={form.status} onChange={this.changeHandler}/>
                <input type="submit" value="提交"/>
                </form>
            )
        }

        return(
            <div>
                <h2>用户管理</h2>
                <button onClick={this.toAdd} class="btn btn-primary">添加</button>
                {/* 表单 */}
                {JSON.stringify(form)}
                {$form}
            <table class="table">
                <thead>
                    <tr>
                    <th>编号</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>密码</th>
                    <th>类型</th>
                    <th>状态</th>
                    <th>操作</th>
                    </tr>
                </thead>

                <tbody>
                    {
                    users.map((item)=>{
                    return (
                    <tr key={item.id}>
                        <td><input type='checkbox' value={item.id}/></td>

                        <td>{item.name}</td>
                        <td>{item.sex}</td>
                        <td>{item.password}</td>
                        <td>{item.type}</td>
                        <td>{item.status}</td>
                        <td>

                        <span onClick={this.toDelete.bind(this,item.id)}>删除</span>
                        <span onClick={this.toUpdate.bind(this,item.id)}>更新</span>
                        </td>
                    </tr>);
                    })
                    }
                </tbody>
        </table>
            </div>
        )
    }
}
export default User;
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
    toAdd=()=>{
        this.setState({
            flag:!this.state.flag,
            form:{
                name:"",
                credit:"",
                description:"",
                teacherId:""
            }
        })
    }


    toDelete=(id)=>{
        //1,通过id查找课程信息
        //2，将返回结果设置到this.state.form中
        //state->form
        $.get("http://127.0.0.1:8888/course/delete?id="+id,({status,message,date})=>{
            alert(message)
            //刷新页面
            this.loadCourses();
        })
    }
    render(){
        let {teachers,courses,form,flag}=this.state;

        let $form;
        if(flag){
            $form=(
                <form onSubmit={this.submitHandler}>
                课程名称
                <input type="text" name="name" value={form.name} onChange={this.changeHandler}/>
                课程学分
                <input type="text" name="credit" value={form.credit} onChange={this.changeHandler}/>
                课程简介
                <textarea name="description" value={form.description} onChange={this.changeHandler}></textarea>
                任课老师
                <select name="teacherId" value={form.teacherId} onChange={this.changeHandler}>
                    {
                    teachers.map((item)=>{
                        return <option value={item.id} key={item.id}>{item.realname}</option>
                    })
                    }
                </select>
                <input type="submit" value="提交"/>
                </form>
            )
        }

        return(
            <div>
                <h2>课程管理</h2>
                <button onClick={this.toAdd} class="btn btn-primary">添加</button>
                {/* {JSON.stringify(form)} */}
                {$form}
            <table class="table">
                <thead>
                    <tr>
                    <th>编号</th>
                    <th>课程名称</th>
                    <th>课程学分</th>
                    <th>课程介绍</th>
                    <th>任课老师</th>
                    <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    {
                    courses.map((item)=>{
                    return (<tr key={item.id}>
                        <td><input type='checkbox' value={item.id}/></td>
                        <td>{item.name}</td>
                        <td>{item.credit}</td>
                        <td>{item.description}</td>
                        <td>{item.teacher.realname}</td>
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
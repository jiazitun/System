import React from 'react';
import $ from 'jquery';
import '../css/style.css';
import { Button } from 'antd';

class Drug extends React.Component{
    
    constructor(){
        super();
        //局部状态
        this.state={
            flag:false,
            drugs:[],
            form:{
                name:"",
                norms:"",
                price:"",
                firm:"",
                category:""
            }
        }
    }

    componentDidMount(){
        //加载用户信息
        this.loadDrugs();
        //console.log(this.state.drugs);
    }
    
    loadDrugs(){
    //查询所有用户信息
    $.get("http://127.0.0.1:8888/drug/findAll",({status,message,data})=>{
        //console.log(data);
    if(status===200){
       
        //将查询数据库设置到state中
            this.setState({      
            drugs:data  
        })
        console.log(this.state.drugs);
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
        let url="http://127.0.0.1:8888/drug/saveOrUpdate";
        $.post(url,this.state.form,({message})=>{
            alert(message)
            //刷新页面
            this.loadDrugs();
        })
        event.preventDefault();
    }
    toUpdate=(id)=>{
        //1,通过id查找用户
        //2，将返回结果设置到this.state.form中
        //state->form
        // flag:!this.state.flag,
        $.get("http://127.0.0.1:8888/drug/findById?id="+id,({status,message,data})=>{
            if(status===200){
                //将查询数据库设置到state中
                this.setState({
                    flag:!this.state.flag,
                    "form":data
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
                norms:"",
                price:"",
                firm:"",
                category:""
            }
        })
    }
    toDelete=(id)=>{
         //1,通过id查找用户
        //2，将返回结果设置到this.state.form中
        //state->form
        $.get("http://127.0.0.1:8888/drug/deleteById?id="+id,({status,message,data})=>{
            alert(message)
            //刷新页面
            this.loadDrugs();
        })
    }
    render(){
        let {drugs,form,flag}=this.state;
        //let drugs = this.state;
        let $form;
        if(flag){
            $form=(
                <form onSubmit={this.submitHandler}>
				名称
                <input firm="text" name="name" value={form.name} onChange={this.changeHandler}/>
                剂量
                <input firm="text" name="norms" value={form.norms} onChange={this.changeHandler}/>
                价格
                <input firm="text" name="price" value={form.price} onChange={this.changeHandler}/>
                厂家
                <input firm="text" name="firm" value={form.firm} onChange={this.changeHandler}/>
                种类
                <input firm="text" name="category" value={form.category} onChange={this.changeHandler}/>
                
                {/* <input firm="submit" value="提交" content="提交"/> */}
                <button firm="submit" value="提交">提交</button>
                </form>
            )
        }

        return(
            <div>
                <h2>药品管理</h2>
                <button onClick={this.toAdd} class="btn btn-primary">添加</button>
                {/* 表单 */}
                {/* {JSON.stringify(form)} */}
                {$form}
            <table class="table">
                <thead>
                    <tr>
                    {/* <th>编号</th> */}
                    <th>名称</th>
                    <th>剂量</th>
                    <th>价格</th>
                    <th>厂家</th>
                    <th>种类</th>
                    <th>操作</th>
                    </tr>
                </thead>

                <tbody>
                    {
                    drugs.map((item)=>{
                    return (
                    <tr key={item.id}>
                        {/* <td><input firm='checkbox' value={item.id}/></td> */}
                        <td>{item.name}</td>
                        <td>{item.norms}</td>
                        <td>{item.price}</td>
                        <td>{item.firm}</td>
                        <td>{item.category}</td>
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
export default Drug;
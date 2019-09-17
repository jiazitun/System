import React from "react";
import { Card, Form, Input, Button, message, Icon, Checkbox } from "antd";
import '../css/Login.css';
import { Redirect } from "react-router-dom";
//const FormItem = Form.Item;
class FormLogin extends React.Component{
 
    // handleSubmit = ()=>{//提交函数，在此函数中你可以通过getFieldsValue方法拿到表单数据
    //     let userInfo = this.props.form.getFieldsValue();
    //     this.props.form.validateFields((err,values)=>{
    //         if(!err){
    //             message.success(`${userInfo.userName}欢迎您 ，当前密码为：${userInfo.userPwd}`)
    //         }
    //     })
    // }
    constructor(props){
        super(props);
        this.state={
            login:false
        }
    }
    
        doLogin=()=>{
            let username=document.querySelector("input[type=text]").value;
            let password=document.querySelector("input[type=password]").value;
            if(username==="admin"&&password==="123456"){
                this.setState({
                    login :true
                })
            }
        }
 
    render(){
        if(this.state.login){
            return  <Redirect to="/home"/>
         }else{
             alert("登录失败");
         }
        // const { getFieldDecorator } = this.props.form;//es6语法解构赋值，getFieldDecorator 此方法可以帮助你获取表单数据，初始化表单数据，校验表单数据，具体用法如下代码所示
        return (
             
            <div class="formDiv">
                <h1>医药管理系统</h1>
                <div>

                <form onSubmit={this.doLogin}>
                    账号：<input type="text"/><br/>
                    密码：<input type="password"/><br/>
                    <input type="submit" value="登录"/>
                </form>

                </div>


                    {/* <Form>
                        <FormItem>
                            {
                                getFieldDecorator('userName',{//userName实际上就是你获取整个表单数据对象之后，此输入框的名字
                                    initialValue:'',//这是用来初始化表单数据的
                                    rules:[//这是用来校验表单数据的，具体用法请看文档
                                        {
                                            required:true,
                                            message:'用户名不能为空'
                                        },
                                        {
                                            min:1,max:255,
                                            message:'长度不在范围内'
                                        },
                                        {
                                            pattern:new RegExp('^\\w+$','g'),
                                            message:'用户名必须为字母或者数字'
                                        }
                                    ]
                                })(
                                    <Input prefix={<Icon type="user"/>} placeholder="请输入用户名" />
                                )
                            }
                        </FormItem>
                        <FormItem>
                            {
                                getFieldDecorator('userPwd', {
                                    initialValue: '',
                                    rules: []
                                })(
                                    <Input prefix={<Icon type="lock" />} type="password" placeholder="请输入密码" />
                                )
                            }
                        </FormItem>
                        <FormItem>
                            {
                                getFieldDecorator('remember', {
                                    valuePropName:'checked',
                                    initialValue: true
                                })(
                                    <Checkbox>记住密码</Checkbox>
                                )
                            }
                            <a href="#" style={{float:'right'}}>忘记密码</a>
                        </FormItem>
                        <FormItem>
                            <Button type="primary" onClick={this.handleSubmit}>登录</Button>
                        </FormItem>
                    </Form> */}
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
            </div>
            
        );
    }
}
export default Form.create()(FormLogin);
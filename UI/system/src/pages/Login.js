import React from "react";
import { Card, Form, Input, Button, message, Icon, Checkbox } from "antd";
const FormItem = Form.Item;
class FormLogin extends React.Component{
 
    handleSubmit = ()=>{//提交函数，在此函数中你可以通过getFieldsValue方法拿到表单数据
        let userInfo = this.props.form.getFieldsValue();
        this.props.form.validateFields((err,values)=>{
            if(!err){
                message.success(`${userInfo.userName}欢迎您 ，当前密码为：${userInfo.userPwd}`)
            }
        })
    }
 
    render(){
        const { getFieldDecorator } = this.props.form;//es6语法解构赋值，getFieldDecorator 此方法可以帮助你获取表单数据，初始化表单数据，校验表单数据，具体用法如下代码所示
        return (
            <div>
                    <Form style={{width:300}}>
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
                                            min:5,max:10,
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
                    </Form>
            </div>
        );
    }
}
export default Form.create()(FormLogin);
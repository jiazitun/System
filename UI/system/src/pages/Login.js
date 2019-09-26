import React from 'react'
import {Form,Input,Icon, Button} from 'antd'
// import {render} from 'react-dom'
import axios from 'axios'
import '../css/Login.css'
// import Img from "../img/headimg.jpg"

class LoginFrom extends React.Component{
  constructor(){
      super();
  }

  handleSubmit = (e) => {
    //提交之前判断输入的字段是否有错误
    e.preventDefault();
    let history = this.props.history;
    this.props.form.validateFields((errors,values)=>{
      if (!errors) {
        axios({
          url: "http://127.0.0.1:8888/user/login",
          method: "post",
          params: {
            userName: values.userName,
            password: values.passWord
          }
        })
        .then(function(response) {
          console.log(response)
          if(null == response.data.data){
            console.log(response.data.message)
          }else{
            if(response.data.data.type == '管理员'){
              history.push('/Admin');
             }else if(response.data.data.type == '医师'){
              history.push('/Doctor');
             }else{
              history.push('/Pharmacist');
             }
          }
          })
          
        console.log('Received values of form: ', values);
      }
    })
  }

  render(){
    //Form.create 包装的组件会自带this.props.form属性，该属性提供了一系列API，包括以下4个
    //getFieldDecorator用于和表单进行双向绑定
    //isFieldTouched判断一个输入控件是否经历过 getFieldDecorator 的值收集时机 options.trigger(收集子节点的值的时机，默认时onChange)
    //getFieldError获取某个输入控件的 Error
    //获取一组输入控件的 Error ，如不传入参数，则获取全部组件的 Error
    const { getFieldDecorator, getFieldError, isFieldTouched } = this.props.form;
    const userNameError = isFieldTouched('userName') && getFieldError('userName');
    const passWordError = isFieldTouched('password') && getFieldError('password');
    return (
      <div className="login">
        {/* <div className="headimg">
          <img src={Img} alt="图片找不到了"/>
        </div> */}
        <div className="login-form">
          <div className="login-logo">
            <div className="login-name"></div>
          </div>
          <Form onSubmit={this.handleSubmit}>
            {/* 一个FromItem中放一个被 getFieldDecorator 装饰过的 child */}
            <Form.Item
              validateStatus={userNameError ? 'error' : ''}//validateStatus为校验状态，如不设置，则会根据校验规则自动生成，可选：'success' 'warning' 'error' 'validating'
            >
            {
              getFieldDecorator('userName',{
                rules:[{required:true,message:"Please input your username!"}]
              })(
                <Input prefix={<Icon type="user" style={{ color: 'rgba(0,0,0,.25)' }}/>} placeholder="Username"/>
              )
            }
            </Form.Item>
            <Form.Item
              validateStatus={passWordError ? "error" : ''}
            >
            {
              getFieldDecorator('passWord',{
                rules:[{required:true,message:"Please input your Password!"}]
              })(
                <Input prefix={<Icon type="lock" style={{ color: 'rgba(0,0,0,.25)' }}/>} placeholder="Password"/>
              )
            }
            </Form.Item>
            <Form.Item>
              <Button
                type="primary"
                htmlType="submit"
              >登录
              </Button>
            </Form.Item>
          </Form>
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
      </div>
    )
  }
}
let LoginForm = Form.create()(LoginFrom);
export default LoginForm;
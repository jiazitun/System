import React from 'react';
import $ from 'jquery';
import '../App.css';
import UserForm from './UserForm'
import {
  Button,
  Table,
  Icon,
  Popconfirm,
  Modal,
  Pagination,
  message
} from 'antd'
// ajax的全局配置
$.ajaxSetup({
  error:function(error){
    message.error('服务器异常');
  }
})

class User extends React.Component{
  constructor(props){
    super(props);
    this.state = {
      visible:false,
      list:[],
      ids:[],
      users:{}
    }
  }
    componentDidMount(){
        //加载用户信息
        this.loadUsers();
        //console.log(this.state.users);
    }
    loadUsers(){
    //查询所有用户信息
    $.get("http://127.0.0.1:8888/user/findAll",({status,message,data})=>{
        //console.log(data);
    if(status===200){
        //将查询数据库设置到state中
            this.setState({      
            list:data  
        })
        }else{
            alert(message)
        }
       });
   }
     // 显示模态框进行数据的添加
  toAdd = ()=>{
    this.setState({ 
      visible: true, 
      users:{}
    });
  }
  //单个删除
  delete(record){
    let url = "http://localhost:8888/user/deleteById?id="+record.id;
    $.get(url,(result)=>{
      if(result.status=== 200){
        this.loadUsers();
        message.success(result.message)
      } else {
        message.error(result.message);
      }
    });
  }
  //显示模态框
  toEdit(record){
    console.log(record);
    this.setState({
       visible: true, //  显示模态框   
       users:record //  当前要修改的值

    });
  }
  //批量删除
  batchDelete =()=>{
    Modal.confirm({
      title: '确定要删除这些用户吗?',
      content: 'Some descriptions',
      onOk:()=> {
        console.log('OK');
        let url = "http://localhost:8888/user/batchDelete";
        $.ajax({
          url,
          method:'POST',
          data:JSON.stringify(this.state.ids),
          contentType:'application/json',
          success:(result)=>{
            message.success(result.message);
            this.loadUsers();
          }
        })
      },
      onCancel() {
        console.log('Cancel');
      },
    });
}
handleOk = e => {
  // 提交表单
  e.preventDefault();
  this.state.form.validateFields((err, values) => {
    if (!err) {
      console.log('Received values of form: ', values);
      let url = "http://localhost:8888/user/saveOrUpdate"
      $.post(url,values,(result)=>{
        // 提示成功
        message.success(result.message);
        // 关闭模态框
        this.setState({ visible: false, });
        // 刷新数据
        this.loadUsers();
      })
    }
  });
};
//表单取消
  handleCancel = e => {
    console.log(e);
    this.setState({
      visible: false,
    });
  };

  toDetails(record){
    this.props.history.push({
      pathname:'/Usedetils',
      payload:record
    });
  }

  //表单修改,ref是父组件触发子组件的事件
  saveFormRef = (form) => {
    console.log(form);
    this.setState({
      form
    })
  }
   render(){
     //定义列
    const columns = [
      {
        title: '用户名',
        dataIndex: 'username',
      },
      {
        title: '姓名',
        dataIndex: 'name',
      },
      {
        title: '性别',
        dataIndex: 'sex',
      },
      {
        title: '密码',
        dataIndex: 'password',
      },
      {
        title: '职业',
        dataIndex: 'type',
      },
      {
        title: '状态',
        dataIndex: 'status',
      },

      {
        title: '操作',
        dataIndex: '',
        render: (text,record) => {
          return (
            <div>
              <Popconfirm title="确定删除用户？" onConfirm={this.delete.bind(this,record)} okText="是" cancelText="否">
                <Button type="link" >
                    <Icon type="delete" /></Button>
              </Popconfirm>
              <Button type="link" onClick={this.toEdit.bind(this,record)}>
                    <Icon type="edit" /></Button>
            </div>
          )
        }
        
        
      },
    ];
    //定义行
    const rowSelection = {
      onChange: (selectedRowKeys) => {
        //每次改变的时候,参数值改变
        this.setState({
          ids:selectedRowKeys
        })
      },
      getCheckboxProps: record => ({
        disabled: record.name === 'Disabled User', // Column configuration not to be checked
        name: record.name,
      }),
    };


    return (
      <div className="user">
        <h2>用户管理</h2>
        <div className="btns">
          <Button onClick={this.toAdd}>添加</Button> &nbsp;
          <Button type="danger" onClick={this.batchDelete}>批量删除</Button>
        </div>
        {/* {JSON.stringify(this.state.list)} */}
         {/* 表格 */}
         <Table 
          size="small"
          //选中行相当于选中id
          rowKey="id" 
          rowSelection={rowSelection} 
          columns={columns} 
          dataSource={this.state.list} />
          {/* 模态框 */}
        <Modal
          title="新增或修改用户信息"
          visible={this.state.visible}
          onOk={this.handleOk}
          onCancel={this.handleCancel}
        >
        <UserForm ref={this.saveFormRef} users={this.state.users} />
        </Modal>
      </div>
    )

   }
}
export default User;
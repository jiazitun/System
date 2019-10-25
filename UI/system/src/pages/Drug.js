import React from 'react';
import $ from 'jquery';
import '../App.css';
import DrugForm from './DrugForm'
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

class Drug extends React.Component{
  constructor(props){
    super(props);
    this.state = {
      visible:false,
      list:[],
      ids:[],
      drugs:{}
    }
  }
    componentDidMount(){
        //加载用户信息
        this.loadDrugs();
        //console.log(this.state.users);
    }
    loadDrugs(){
    //查询所有用户信息
    $.get("http://127.0.0.1:8888/drug/findAll",({status,message,data})=>{
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
      drugs:{}
    });
  }
  
  //单个删除
  delete(record){
    let url = "http://localhost:8888/drug/deleteById?id="+record.id;
    $.get(url,(result)=>{
      if(result.status=== 200){
        this.loadDrugs();
        message.success(result.message)
      } else {
        message.error(result.message);
      }
    });
  }

  //toRFID
  toRFID= ()=>{
    let url = "http://127.0.0.1:8888/card/openPort";
    $.get(url,(result)=>{
      if(result.status=== 200){
        this.loadDrugs();
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
       drugs:record //  当前要修改的值

    });
  }
  //批量出库
  batchUpdate =()=>{
    Modal.confirm({
      title: '确定要出库这些药品吗?',
     
      onOk:()=> {
        console.log('OK');
        let url = "http://localhost:8888/drug/batchUpdate";
        $.ajax({
          url,
          method:'POST',
          data:JSON.stringify(this.state.ids),
          contentType:'application/json',
          success:(result)=>{
            message.success(result.message);
            this.loadDrugs();
          }
        })
      },
      onCancel() {
        this.loadDrugs();
        }
      },
      
    );
  }

  //批量删除
  batchDelete =()=>{
    Modal.confirm({
      title: '确定要删除这些用户吗?',
      content: 'Some descriptions',
      onOk:()=> {
        console.log('OK');
        let url = "http://localhost:8888/drug/batchDelete";
        $.ajax({
          url,
          method:'POST',
          data:JSON.stringify(this.state.ids),
          contentType:'application/json',
          success:(result)=>{
            message.success(result.message);
            this.loadDrugs();
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
      let url = "http://localhost:8888/drug/saveOrUpdate"
      $.post(url,values,(result)=>{
        // 提示成功
        message.success(result.message);
        // 关闭模态框
        this.setState({ visible: false, });
        // 刷新数据
        this.loadDrugs();
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
  run = e =>{
    e.preventDefault();
    this.state.form.validateFields((err, values) => {
      if (!err) {
        console.log('Received values of form: ', values);
        let url = "http://127.0.0.1:8888/Hello/Run"
        $.post(url,values,(result)=>{
          // 提示成功
          if(!values){
            $.post('',)
          }
          message.success(result.message);
          // 关闭模态框
          this.setState({ visible: false, });
          // 刷新数据
          this.loadDrugs();
        })
      }
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
        title: '名称',
        dataIndex: 'name',
      },
      {
        title: '剂量',
        dataIndex: 'norms',
      },
      {
        title: '价格',
        dataIndex: 'price',
      },
      {
        title: '厂家',
        dataIndex: 'firm',
      },
      {
        title: '种类',
        dataIndex: 'category',
      },
      {
        title: '操作',
        dataIndex: '',
        render: (text,record) => {
          return (
            <div>
              <Popconfirm title="确定删除药品？" onConfirm={this.delete.bind(this,record)} okText="是" cancelText="否">
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
      <div className="drug">
        <h2>药品管理</h2>
        <div className="btns">
          <Button onClick={this.toAdd}>手动添加</Button> &nbsp;
          <Button onClick={this.toRFID}>RDID添加</Button> &nbsp;
          
          <Button type="danger" onClick={this.batchUpdate}>批量删除</Button>
          {/* onClick={this.batchDelete} */}
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
          title="新增或修改药品信息"
          visible={this.state.visible}
          onOk={this.handleOk}
          onCancel={this.handleCancel}
        >
        <DrugForm ref={this.saveFormRef} drugs={this.state.drugs} />
        {/* <Button onClick={this.run}>自动添加</Button> */}
        </Modal>
      </div>
    )

   }
}
export default Drug;
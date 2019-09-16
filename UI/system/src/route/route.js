//Loadable插件需使用Loading
import Loadable from 'react-loadable'
//  import Loading from '../components/Loading/index';

//定义路由
global.Main = Loadable({
    loader: () => import('../App.js'),
    //  loading: Loading,
});
//表单
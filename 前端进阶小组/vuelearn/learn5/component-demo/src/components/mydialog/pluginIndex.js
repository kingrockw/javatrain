/**
 * Created by wjl48511 on 2019/8/26.
 */
import alert from './index.js'

export default {
    install(vue) {
        vue.prototype.$myDialog = alert
    }
}
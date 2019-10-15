import _ from 'lodash'
import './style.css'
import Icon from './webpack.png'
import Data from './data.xml'
import testtpl from './test.tpl.html'
function component() {
    var element = document.createElement('div')
    element.innerHTML = _.join(['Hello','webpack'],' ')
    element.classList.add('hello')
    // var myIcon = new Image()
    // myIcon.src = Icon
    //
    // element.appendChild(myIcon)
    var tpldiv = document.createElement('div')
    tpldiv.innerHTML = testtpl
    console.log(Data);
    console.log(testtpl);
    element.appendChild(tpldiv)
    return element
}

document.body.appendChild(component())
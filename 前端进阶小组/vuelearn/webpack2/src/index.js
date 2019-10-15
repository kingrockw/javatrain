import _ from "lodash";
import printMe from "./print.js";
import './styles.css'
function component() {
    var element = document.createElement('div')
    element.innerHTML = _.join(['Hello', 'webpack'], ' ')
    var btn = document.createElement('button');
    btn.innerHTML = 'Click me and check the console! ccc';
    btn.onclick = printMe;
    var btnDiv = document.createElement('div')
    btnDiv.appendChild(btn);
    element.appendChild(btnDiv);
    return element
}
let element = component()
document.body.appendChild(element)

if (module.hot) {
    module.hot.accept();
    // module.hot.accept('./print.js', function () {
    //     console.log('Accepting the updated printMe module!');
    //     document.body.removeChild(element)
    //     element = component()
    //     document.body.appendChild(element)
    //     printMe();
    // })
}
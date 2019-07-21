import _ from "lodash";
import printMe from "./print.js";

function component() {
    var element = document.createElement('div')
    element.innerHTML = _.join(['Hello', 'webpack'], ' ')
    var btn = document.createElement('button');
    btn.innerHTML = 'Click me and check the console!33';
    btn.onclick = printMe;
    element.appendChild(btn);
    return element
}

document.body.appendChild(component())

if (module.hot) {
    module.hot.accept('./print.js', function () {
        console.log('Accepting the updated printMe module!');
        printMe();
    })
}
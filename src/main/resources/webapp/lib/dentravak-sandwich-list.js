class DenTravakSandwichList extends HTMLElement {

    constructor() {
        super();
        this.connectedCallBack();
    }

    connectedCallBack() {
        this.initShadowDom();
    }

    initShadowDom() {
        let shadowRoot = this.attachShadow({mode: 'open'});

        this.setSandwiches(shadowRoot);
    }

    setSandwiches(sr) {
        var lis = document.createElement('ul');
        lis.setAttribute('id', 'sandwichlist');

        fetch("http://localhost:8080/sandwiches", {mode: 'cors'})
            .then(res => res.json())
            .then(function (res) {
            for (var r in res) {
                var lisit = document.createElement('li');
                lisit.innerText = res[r].name + ' (' + res[r].ingredients + ') ' + res[r].price;
                lis.appendChild(lisit);
            }

        });


        sr.appendChild(lis);
    }

}


customElements.define('dt-sandwich-list', DenTravakSandwichList);

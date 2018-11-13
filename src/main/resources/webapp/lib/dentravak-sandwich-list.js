class DenTravakSandwichList extends HTMLElement {

    constructor(){
        super();
        this.connectedCallBack();
    }

    connectedCallBack(){
        this.initShadowDom();
    }
    
    initShadowDom(){
        let shadowRoot = this.attachShadow({mode:'open'});
        shadowRoot.innerHTML = this.template;
        this.setSandwiches();
    }

    setSandwiches(){
        let sandwichlist = document.getElementById('sandwichlist');

        fetch('http://http://localhost:8080/sandwiches')
        .then(res => res.json())
        .then(function(res){
            for(var r in res){
                
            }
            let child = document.createElement('li')
            sandwichlist.appendChild(document.createTextNode('<li>' + res.name + '<li'))
        });

        
    }

    get template(){
        return `<ul id="sandwichlist"></ul>`;
    }

}


customElements.define('dt-sandwich-list', DenTravakSandwichList);
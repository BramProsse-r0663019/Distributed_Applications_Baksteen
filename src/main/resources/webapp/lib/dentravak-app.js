class DenTravakApp extends HTMLElement{
    constructor(){
        super();
        this.initShadowDom;
    }

    connectedCallBack(){
        this.innerHTML = this.template;
        this.initShadowDom();
    }
    
    initShadowDom(){
        let shadowRoot = this.attachShadow({mode:'open'});
        shadowRoot.innerHTML = this.template;
    }

    get template(){
        return `<div>This is a div</div>`;
    }
}


customElements.define('dt-app', DenTravakApp);
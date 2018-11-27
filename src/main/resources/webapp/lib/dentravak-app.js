import DenTravakAbstractElement from './dentravak-abstract-element.js';
import './dentravak-sandwich-list.js';
import './dentravak-sandwich-checkout.js';
import './dentravak-order-confirmation.js';

class DenTravakApp extends DenTravakAbstractElement{

    connectedCallback(){
        super.connectedCallback();
        this.showSandwichList();
        this.initEventListeners();
    }

    initEventListeners() {
        this.addEventListener('checkout', (e) => this.showCheckoutPage(e.detail));
        this.addEventListener('show-sandwich-list', (e) => this.showSandwichList());
        this.addEventListener('order-succeeded', (e) => this.showOrderConfirmationPage(e.detail));
    }
    
    showSandwichList(){
        this.byCss(`dt-sandwich-list`).classList.remove('hidden');
        this.byCss(`dt-sandwich-checkout`).classList.add('hidden');
        this.byCss(`dt-order-confirmation`).classList.add('hidden');
    }

    showCheckoutPage(sandwich) {
        this.byCss(`dt-sandwich-checkout`).init(sandwich);
        this.byCss(`dt-sandwich-list`).classList.add('hidden');
        this.byCss(`dt-sandwich-checkout`).classList.remove('hidden');
        this.byCss(`dt-order-confirmation`).classList.add('hidden');
    }

    showOrderConfirmationPage(sandwich) {
        this.byCss(`dt-order-confirmation`).init(sandwich);
        this.byCss(`dt-sandwich-list`).classList.add('hidden');
        this.byCss(`dt-sandwich-checkout`).classList.add('hidden');
        this.byCss(`dt-order-confirmation`).classList.remove('hidden');
    }

    //Defining a custom element for each function
    get template() {
        return `
            <style>
                .hidden {display: none;}
            </style>
            <dt-sandwich-list></dt-sandwich-list>
            <dt-sandwich-checkout></dt-sandwich-checkout>
            <dt-order-confirmation></dt-order-confirmation>
        `;
    }

}

//Our custom element on index.html is called 'dt-app' -> we define it here
customElements.define('dt-app', DenTravakApp);
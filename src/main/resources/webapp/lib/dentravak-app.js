import DenTravakAbstractElement from './dentravak-abstract-element.js';
import './dentravak-sandwich-list.js';
import './dentravak-sandwich-checkout.js';
import './utils.js';

class DenTravakApp extends DenTravakAbstractElement{

    connectedCallBack(){
        super.connectedCallBack();
        this.showSandwichList();
        this.initEventListeners();
    }

    initEventListeners() {
        this.addEventListener('show-sandwich-list', (e) => this.showSandwichList());
        this.addEventListener('checkout', (e) => this.showCheckoutPage(e.detail));
        this.addEventListener('order-succeeded', (e) => this.showOrderConfirmationPage(e.detail));
    }
    
    showSandwichList(){
        this.byCss(`dt-sandwich-list`).classList.add('hidden');
        this.byCss(`dt-sandwich-checkout`).classList.add('hidden');
        // this.byCss(`travak-sandwiches-order-confirmation`).classList.add('hidden');
    }

    showCheckoutPage(sandwich) {
        this.byCss(`dt-sandwich-checkout`).init(sandwich);
        this.byCss(`dt-sandwich-list`).classList.add('hidden');
        this.byCss(`dt-sandwich-checkout`).classList.remove('hidden');
        // this.byCss(`travak-sandwiches-order-confirmation`).classList.add('hidden');
    }

    showOrderConfirmationPage(sandwich) {
        // this.byCss(`travak-sandwiches-order-confirmation`).init(sandwich);
        // this.byCss(`dt-sandwich-list`).classList.add('hidden');
        // this.byCss(`dt-sandwich-checkout`).classList.add('hidden');
        // this.byCss(`travak-sandwiches-order-confirmation`).classList.remove('hidden');
    }

    get template() {
        return `
            <style>
                .hidden {display: none;}
            </style>
            <dt-sandwich-list></dt-sandwich-list>
            <dt-sandwich-checkout class="hidden"></dt-sandwich-checkout>
        `;
    }

}


customElements.define('dt-app', DenTravakApp);
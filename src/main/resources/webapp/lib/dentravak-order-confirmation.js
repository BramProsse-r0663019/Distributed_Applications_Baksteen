import DenTravakAbstractElement from './dentravak-abstract-element.js';

class DenTravakSandwichOrderConfirmation extends DenTravakAbstractElement {

    connectedCallback() {
        super.connectedCallback();
        this.initEventListeners();
    }

    init(sandwich) {
        this.sandwich = sandwich;
    }

    initEventListeners() {
        this.byId('show-sandwich-list').addEventListener('click', e => this.app().dispatchEvent(new Event('show-sandwich-list')));
    }

    get template() {
        return `
            <style>
            </style>
            <div>
                <div>
                    <h3>Welcome to Den Travak</h3>
                    <button id="show-sandwich-list" type="button">New order</button>
                </div>
                <h4>We just received your order, thanks!</h4>
            </div>
            `;
    }
}

customElements.define('dt-order-confirmation', DenTravakSandwichesOrderConfirmation);
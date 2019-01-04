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
        //show-sandwich-list = id of button -> see template
        this.byId('show-sandwich-list').addEventListener('click', e => this.app().dispatchEvent(new Event('show-sandwich-list')));
    }

    get template() {
        return `
        <style>
            h3 {
                margin-top: 20px;
            }
            h5 {
                margin-top: 50px;
            }
            #show-sandwich-list:hover {
                background-color: #C8ECFD !important;
                transition: background-color 1.5s, color 1.5s;
            }
        </style>
        <div>
            <div>
                <h3>Welcome to Den Travak</h3>
            </div>
            <h5>We just received your order, thanks!</h5>
            <button class="btn btn-info active" id="show-sandwich-list">New order</button>
        </div>
        `;
    }
}

customElements.define('dt-order-confirmation', DenTravakSandwichOrderConfirmation);
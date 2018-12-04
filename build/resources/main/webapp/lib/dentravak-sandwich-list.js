import DenTravakAbstractElement from './dentravak-abstract-element.js';

class DenTravakSandwichList extends DenTravakAbstractElement {

    //Get Sandwiches via REST from backend
    connectedCallback() {
        super.connectedCallback();
        fetch('http://localhost:8080/sandwiches')
            .then(resp => resp.json())
            .then(json => this.updateSandwichesList(json));
    }

    //Show all breads
    updateSandwichesList(sandwiches) {
        let sandwichesList = this.byId('sandwiches');
        sandwiches.forEach(sandwich => {
            //Use template for each bread
            let sandwichEl = htmlToElement(this.getSandwichTemplate(sandwich));
            sandwichEl.addEventListener('click', () => this.app().dispatchEvent(new CustomEvent('checkout', {detail: sandwich})));
            sandwichesList.appendChild(sandwichEl);
        });
    }

    //Base template where all 
    get template() {
        return `
            <style>
                div.dt-sandwich-info {
                    margin-left: auto;
                }
            </style>
            <div class="animate">
                <h3>Welcome at Den Travak</h3>
                <h5>Choose your delicious sandwich</h5>
                <div>
                    <ul id="sandwiches" class="list-group">
                    </ul>
                </div>
            </div>
        `;
    }

    getSandwichTemplate(sandwich) {
        return `
            <a class="list-group-item" id="checkout">
                <div class="bmd-list-group-col">
                    <p class="list-group-item-heading">${sandwich.name}</p>
                    <p class="list-group-item-text">${sandwich.ingredients}</p>
                </div>
                <div class="dt-sandwich-info">
                    <p class="list-group-item-text">€ ${sandwich.price}</p>
                </div>
            </a>
        `;
    }
}

customElements.define('dt-sandwich-list', DenTravakSandwichList);
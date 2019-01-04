import DenTravakAbstractElement from './dentravak-abstract-element.js';

class DenTravakSandwichList extends DenTravakAbstractElement {

    //Get Sandwiches via REST from backend
    connectedCallback() {
        super.connectedCallback();
        fetch('http://193.191.177.8:10468/den-travak/sandwiches')
            .then(resp => resp.json())
            .then(json => this.updateSandwichesList(json));
    }

    //Show all sandwiches
    updateSandwichesList(sandwiches) {
        let sandwichesList = this.byId('sandwiches');
        sandwiches.forEach(sandwich => {
            //Use template for each bread
            let sandwichEl = htmlToElement(this.getSandwichTemplate(sandwich));
            sandwichEl.addEventListener('click', () => this.app().dispatchEvent(new CustomEvent('checkout', {detail: sandwich})));
            sandwichesList.appendChild(sandwichEl);
        });
    }

    //Base template
    get template() {
        return `
            <style>
                div.dt-sandwich-info {
                    margin-left: auto;
                }
                h3 {
                    margin-top: 20px;
                }
                h5 {
                    margin-top: 50px;
                }
                a:hover {
                    color: #19B0F5 !important;
                    background-color: #C8ECFD;
                    transition: background-color 2s, color 1s !important;
                    cursor:url(http://cur.cursors-4u.net/food/foo-2/foo175.cur), auto;
                }
                a:hover div p {
                    color: #19B0F5 !important;
                    transition: color 1s !important;
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
            <a class="list-group-item border-bottom border-info" id="checkout">
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
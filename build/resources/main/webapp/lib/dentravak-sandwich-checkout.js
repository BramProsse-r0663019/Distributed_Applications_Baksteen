import DenTravakAbstractElement from './dentravak-abstract-element.js';

class DenTravakSandwichCheckout extends DenTravakAbstractElement{

    connectedCallback(){
        super.connectedCallback();
        this.initEventListeners();
    }

    init(sandwich){
        this.sandwich = sandwich;
        this.byId('sandwiches').innerHTML = ``;
        this.byId('sandwiches').appendChild(htmlToElement(this.getSandwichTemplate(this.sandwich)));
    }

    initEventListeners(){
        this.byId('order-button').addEventListener('click', e => this.orderSandwich());
        this.byId('back-button').addEventListener('click', e => this.app().showSandwichList());
    }

    //Fetch to back-end
    orderSandwich(){
        //Empty order object
        let order = {};
        order.sandwichId = this.sandwich.id;
        order.name = this.sandwich.name;
        order.breadType = this.byCss('input[name="breadType"]:checked').value;
        order.price = this.sandwich.price;
        order.mobilePhoneNumber = this.byCss('input[id="mobile-phone-number"]').value;

        fetch("http://localhost:8080/orders",
        {
            method: "POST",
            mode: "cors",
            credentials: "same-origin",
            headers: {
                'Content-Type': 'application/json'
            },
            body:JSON.stringify(order)
        })
        .then(this.app().dispatchEvent(new CustomEvent('order-succeeded', {detail: this.sandwich})));
    }

    get template() {
        return `
            <style>
                .form-group {
                    margin-bottom: 2rem;
                }
                .dt-header {
                    display: flex;
                }
                .dt-header button {
                    margin-left: auto;
                }
                div.dt-sandwich-info {
                    margin-left: auto;
                }
            </style>
            <div class="animate">
                <div class="dt-header">
                    <h3>Welkcome at Den Travak</h3>
                    <button id="back-button" class="btn btn-primary">Back</button>
                </div>
                <h4>Your selected delicious sandwich</h4>
                <div>
                    <ul id="sandwiches" class="list-group">
                    </ul>
                </div>
                <div class="form-group">
                    <label for="typeBrood"><h4>Choose your bread type</h4></label>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="breadType" id="radioBoterhammekes" value="BOTERHAMMEKES" checked>
                        <label class="form-check-label" for="radioBoterhammekes">
                            Boterhammekes
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="breadType" id="radioWrap" value="WRAP">
                        <label class="form-check-label" for="radioWrap">
                            Wrap
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="breadType" id="radioTurksBrood" value="TURKISH_BREAD">
                        <label class="form-check-label" for="radioTurksBrood">
                            Turkish bread
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label for="mobile-phone-number"><h4>Phone Number:</h4></label>
                    <input type="text" class="form-control" id="mobile-phone-number" placeholder="0487/12 34 56">
                </div>
                <button id="order-button" class="btn btn-primary active">Order</button>
            </div>
        `;
    }

    getSandwichTemplate(sandwich) {
        return `
            <a class="list-group-item">
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

customElements.define('dt-sandwich-checkout', DenTravakSandwichCheckout);
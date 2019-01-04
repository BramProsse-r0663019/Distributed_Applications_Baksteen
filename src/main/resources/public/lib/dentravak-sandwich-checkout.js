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

        fetch("http://193.191.177.8:10468/den-travak/orders",
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

        //Show sandwich in browserconsole
        console.log("New order: " + order.name + " - " + order.breadType + " - " + order.price);
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
                h3 {
                    margin-top: 20px;
                }
                h5 {
                    margin-top: 50px;
                }
                #back-button {
                    border-radius: 20px;
                }
                #back-button:hover {
                    background-color: #C8ECFD !important;
                    transition: background-color 1.5s;
                }
                #order-button:hover {
                    background-color: #C8ECFD !important;
                    transition: background-color 1.5s, color 1.5s;
                }
            </style>
            <div class="animate">
                <div class="dt-header">
                    <h3>Welcome at Den Travak</h3>
                    <button id="back-button" class="btn btn-info">Back</button>
                </div>
                <h5>Your selected delicious sandwich</h5>
                <div>
                    <ul id="sandwiches" class="list-group">
                    </ul>
                </div>
                <div class="form-group">
                    <label for="typeBrood"><h5>Choose your bread type</h5></label>
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
                    <label for="mobile-phone-number"><h5>Phone Number</h5></label>
                    <input type="text" class="form-control" id="mobile-phone-number" required>
                </div>
                <div class="form-group">
                    <label for="rating"><h5>Rating (0 - 5)</h5></label>
                    <input type="number" class="form-control" id="rating" required min="0" step="1" max="5" value="3">
                </div>
                <button id="order-button" class="btn btn-info active">Order</button>
            </div>
        `;
    }

    getSandwichTemplate(sandwich) {
        return `
            <a class="list-group-item border-bottom border-info">
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
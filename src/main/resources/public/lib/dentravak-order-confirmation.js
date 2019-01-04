import DenTravakAbstractElement from './dentravak-abstract-element.js';

class DenTravakSandwichOrderConfirmation extends DenTravakAbstractElement {

    connectedCallback() {
        super.connectedCallback();
        this.initEventListeners();
    }

    init(order) {
        this.order = order;
    }

    initEventListeners() {
        //show-sandwich-list = id of button -> see template
        this.byId('show-sandwich-list').addEventListener('click', e => this.app().dispatchEvent(new Event('show-sandwich-list')));
        this.shadowRoot.querySelectorAll('button.score')
            .forEach(scoreBtn => scoreBtn.addEventListener('click', e => {

                let recommendedItem = {};
                recommendedItem.emailAddress = this.order.phoneNumber;
                recommendedItem.ratedItem = this.order.sandwichId;
                recommendedItem.rating = scoreBtn.dataset.score;

                fetch('http://193.191.177.8:10468/den-travak/sandwiches/recommend/', {
                    method: "POST", // *GET, POST, PUT, DELETE, etc.
                    mode: "cors", // no-cors, cors, *same-origin
                    cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
                    headers: {
                        "Content-Type": "application/json; charset=utf-8",
                    },
                    body: JSON.stringify(recommendedItem),
                })
                .then(response => response.json())
                .then(resAsJson => alert('Thanks for the rating'));
            }));
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
            <h4>We just received your order, thanks!</h4>
            <button class="btn btn-info active" id="show-sandwich-list">New order</button>

            <h5>Did u like it? Please leave a rating</h5>
            <button type="button" class="btn btn-primary bmd-btn-fab score" data-score="1">1</button>    
            <button type="button" class="btn btn-primary bmd-btn-fab score" data-score="2">2</button>
            <button type="button" class="btn btn-primary bmd-btn-fab score" data-score="3">3</button>    
            <button type="button" class="btn btn-primary bmd-btn-fab score" data-score="4">4</button>    
            <button type="button" class="btn btn-primary bmd-btn-fab score" data-score="5">5</button>    
        </div>
        `;
    }
}

customElements.define('dt-order-confirmation', DenTravakSandwichOrderConfirmation);
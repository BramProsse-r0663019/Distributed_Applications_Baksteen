import DenTravakAbstractElement from '../dentravak-abstract-element.js';
import './dentravak-login.js';
import './dentravak-orders-of-today.js';

class DenTravakAdmin extends DenTravakAbstractElement{

    connectedCallback(){
        super.connectedCallback();
        this.initEventListeners();

        //Check here before showing loginPage
        var cookies = document.cookie;
        //If cookies is empty -> not yet logged in
        if (cookies == "") {
            this.showLoginPage();
        } else {
            this.showOrdersOfToday();
        }
    }

    initEventListeners() {
        this.addEventListener('show-orders-of-today', (e) => this.showOrdersOfToday());
    }

    showLoginPage() {
        this.byCss(`dt-login`).classList.remove('hidden');
        this.byCss(`dt-orders-of-today`).classList.add('hidden');
    }

    showOrdersOfToday() {
        this.byCss(`dt-login`).classList.add('hidden');
        this.byCss(`dt-orders-of-today`).classList.remove('hidden');
    }

    get template() {
        return `
            <style>
                .hidden {display: none;}
                #back-button {
                    border-radius: 50px;
                    height: 50px;
                }
                #back-button:hover {
                    background-color: #C8ECFD !important;
                    transition: background-color 1.5s;
                }
            </style>
            <a id="back-button" class="btn btn-info" href="http://193.191.177.8:10468/den-travak/index.html">Back</a>
            <dt-login></dt-login>
            <dt-orders-of-today></dt-orders-of-today>
        `;
    }
}

customElements.define('dt-admin', DenTravakAdmin);
import DenTravakAbstractElement from '../dentravak-abstract-element.js';

class DenTravakLogin extends DenTravakAbstractElement {

    connectedCallback() {
        super.connectedCallback();
        this.initEventListeners();
    }

    initEventListeners() {
        //Event on loginbutton
        this.byId('login').addEventListener('click', e => this.checkLogin()); 
    }

    checkLogin() {
        let username = this.byCss('input[name="username"]').value;
        let password = this.byCss('input[name="password"]').value;

        if (username == "admin" && password == "t") {
            //Adding cookie for logged in
            document.cookie = "loggedIn=yes";
            //Event in dentravak-admin throwen
            document.querySelector(`dt-admin`).dispatchEvent(new CustomEvent('show-orders-of-today'));
        }
    }

    get template() {
        return `
        <style>
        </style>
        <div>
            <div>
                <h3>Login on Den Travak</h3>
                <label for="username">Username</label>
                <input type="text" id="username" name="username" value="admin">

                <label for="password">Password</label>
                <input type="password" id="password" name="password" value="t">

                <button id="login">Login</button>
            </div>
        </div>
        `;
    }
}

customElements.define('dt-login', DenTravakLogin);
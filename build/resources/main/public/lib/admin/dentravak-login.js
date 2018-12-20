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
            h3 {
                margin-top: 20px;
                margin-bottom: 50px;
            }
            #login:hover {
                background-color: #C8ECFD !important;
                transition: background-color 1.5s, color 1.5s;
            }
        </style>
        <div>
            <div>
                <div class="dt-header">
                    <h3>Login on Den Travak</h3>
                </div>
                <div class="form-group">
                    <label for="username">Username</label>
                    <input class="form-control" type="text" id="username" name="username" value="admin">
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input class="form-control" type="password" id="password" name="password" value="t">
                </div>
                <div class="form-group">
                    <button class="btn btn-info active" id="login">Login</button>
                </div>
            </div>
        </div>
        `;
    }
}

customElements.define('dt-login', DenTravakLogin);
import DenTravakAbstractElement from '../dentravak-abstract-element.js';

class DenTravakOrdersOfToday extends DenTravakAbstractElement {

    connectedCallback() {
        super.connectedCallback();
        this.initEventListeners();
        fetch('http://localhost:8080/orders/today')
            .then(resp => resp.json())
            .then(json => this.updateOrdersOfToday(json));
    }

    initEventListeners() {
        this.byId('printButton').addEventListener('click', e => this.printAllOrders()); 
    }

    updateOrdersOfToday(orders) {
        let ordersOfToday = this.byId('orders');
        orders.forEach(order => {
            let orderEl = htmlToElement(this.getOrderTemplate(order));
            ordersOfToday.appendChild(orderEl);
        });
        console.log("testupdateordersoftoday");
    }

    printAllOrders() {
        fetch('http://localhost:8080/orders/print')
            .then(resp => resp.json())
            .then(json => this.createCSVFile(json));
    }

    createCSVFile(orders){
        var csv = 'id,sandwichId,name,breadType,creationDate,price,mobilePhoneNumber,printed\n';

        orders.forEach(order => {
            csv += order.id + ',' + order.sandwichId + ',' + order.breadType + ',' + order.creationDate + ',' + order.price + ',' + order.mobilePhoneNumber + ',' + order.printed;
            csv += "\n";
        });

        var hiddenElement = document.createElement('a');
        hiddenElement.href = 'data:text/csv;charset=utf-8,' + encodeURI(csv);
        hiddenElement.target = '_blank';
        hiddenElement.download = 'todaysOrders.csv';
        hiddenElement.click();
    }

    get template() {
        return `
            <style>
                div.dt-order-info {
                    margin-left: auto;
                }
                table {
                    width: 100%;
                }
                table th {
                    border-bottom: solid;
                }
            </style>
            <div class="animate">
                <h3>Den Travak</h3>
                <h5>All the orders for today!</h5>
                <div>
                    <table id="orders">
                        <tr>
                            <th>Sandwich name</th>
                            <th>Breadtype</th> 
                            <th>Price</th>
                            <th>Phone number</th>
                            <th>Printed</th>
                        </tr>
                    </table>
                </div>
                <button id="printButton">Print all orders</button>
            </div>
        `;
    }

    getOrderTemplate(order) {
        return `
            <tr>
                <td>${order.name}</td>
                <td>${order.breadType}</td> 
                <td>€ ${order.price}</td>
                <td>${order.mobilePhoneNumber}</td>
                <td>${order.printed}</td>
            </tr>
        `;
    }
}

customElements.define('dt-orders-of-today', DenTravakOrdersOfToday);
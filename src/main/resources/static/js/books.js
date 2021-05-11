const app = {
    data() {
        return{
            products:[],
            book: {
                id: '',
                name: '',
                cost: '',
                img_path: '',
                description: '',
                maker: {
                    id: '',
                    name: ''
                },
                product_type: {
                    id: 0,
                    name: ''
                }
            },
            // purchasedProductId:'',
            productTypeId: 3,
            flagProductsView: true,
            url:{
                products:'http://localhost:8080/products/',
                orderByCostAsc:'http://localhost:8080/products/orderByCostAsc/',
                orderByCostDesc:'http://localhost:8080/products/orderByCostDesc/',
                orderByNameDesc:'http://localhost:8080/products/orderByNameDesc/',
                orderByNameAsc:'http://localhost:8080/products/orderByNameAsc/',
                buyProductLink:'http://localhost:8080/shoppingCart/product/add/'
            }
        }
    },
    methods: {
        getBooks: function (){
            axios.get(this.url.products + this.productTypeId)
                .then((response) => {
                    this.flagProductsView = true;
                    this.products = response.data;
                })
        },
        getBooksOrderByCostAsc: function (){
            axios.get(this.url.orderByCostAsc + this.productTypeId)
                .then((response) => {
                    this.flagProductsView = true;
                    this.products = response.data;
                })
        },
        getBooksOrderByCostDesc: function (){
            axios.get(this.url.orderByCostDesc + this.productTypeId)
                .then((response) => {
                    this.flagProductsView = true;
                    this.products = response.data;
                })
        },
        getBooksOrderByNameDesc: function (){
            axios.get(this.url.orderByNameDesc + this.productTypeId)
                .then((response) => {
                    this.flagProductsView = true;
                    this.products = response.data;
                })
        },
        getBooksOrderByNameAsc: function (){
            axios.get(this.url.orderByNameAsc + this.productTypeId)
                .then((response) => {
                    this.flagProductsView = true;
                    this.products = response.data;
                })
        },
        buyProduct: function (productId){
            axios.get(this.url.buyProductLink + productId)
                .then(function(response) {
                    console.log(response.headers)
                    // if(status === 302)
                    //     window.location = "http://localhost:8080/auth/login"
                })
                .catch(err => {
                    console.log(1234)
                })
        }

    },
    beforeMount() {
        this.getBooks()
    }
}

Vue.createApp(app).mount('#booksView')


const app = {
    data() {
        return{
            products:[],
            product: {
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
            productTypeId: 2,
            flagProductsView: true,
            url:{
                products:'http://localhost:8080/products/',
                orderByCostAsc:'http://localhost:8080/products/orderByCostAsc/',
                orderByCostDesc:'http://localhost:8080/products/orderByCostDesc/',
                orderByNameDesc:'http://localhost:8080/products/orderByNameDesc/',
                orderByNameAsc:'http://localhost:8080/products/orderByNameAsc/',
                buyProductLink:'http://localhost:8080/shoppingCart/product/add/',
                authUrl: 'http://localhost:8080/auth/login'
            }
        }
    },
    methods: {
        getProducts: function (){
            axios.get(this.url.products + this.productTypeId)
                .then((response) => {
                    this.flagProductsView = true;
                    this.products = response.data;
                })
        },
        getProductsOrderByCostAsc: function (){
            axios.get(this.url.orderByCostAsc + this.productTypeId)
                .then((response) => {
                    this.flagProductsView = true;
                    this.products = response.data;
                })
        },
        getProductsOrderByCostDesc: function (){
            axios.get(this.url.orderByCostDesc + this.productTypeId)
                .then((response) => {
                    this.flagProductsView = true;
                    this.products = response.data;
                })
        },
        getProductsOrderByNameDesc: function (){
            axios.get(this.url.orderByNameDesc + this.productTypeId)
                .then((response) => {
                    this.flagProductsView = true;
                    this.products = response.data;
                })
        },
        getProductsOrderByNameAsc: function (){
            axios.get(this.url.orderByNameAsc + this.productTypeId)
                .then((response) => {
                    this.flagProductsView = true;
                    this.products = response.data;
                })
        },
        buyProduct: function (productId){
            axios.get(this.url.buyProductLink + productId)
                .then(function(response) {
                    if(response.request.responseURL === 'http://localhost:8080/auth/login')
                        window.location = response.request.responseURL
                    else{
                        alert('Вы добавили товар в корзину!')
                    }
                })
        }

    },
    beforeMount() {
        this.getProducts()
    }
}

Vue.createApp(app).mount('#exerciseBooksView')
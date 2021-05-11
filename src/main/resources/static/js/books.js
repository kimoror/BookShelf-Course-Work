const app = {
    data() {
        return{
            products:[],
            // book: {
            //     id: '',
            //     name: '',
            //     cost: '',
            //     img_path: '',
            //     description: '',
            //     maker: {
            //         id: '',
            //         name: ''
            //     },
            //     product_type: {
            //         id: 0,
            //         name: ''
            //     }
            // },
            productTypeId: 3,
            flagProductsView: true,
            url:{
                products:'http://localhost:8080/products/',
                orderByCostAsc:'http://localhost:8080/products/orderByCostAsc/',
                orderByCostDesc:'http://localhost:8080/products/orderByCostDesc/',
                orderByNameDesc:'http://localhost:8080/products/orderByNameDesc/',
                orderByNameAsc:'http://localhost:8080/products/orderByNameAsc/'

            }
        }
    },
    methods: {
        getBooks: function (){
            console.log(this.productTypeId)
            axios.get(this.url.products + this.productTypeId)
                .then((response) => {
                    this.flagProductsView = true;
                    this.products = response.data;
                })
        },
        getBooksOrderByCostAsc: function (){
            console.log(this.productTypeId)
            axios.get(this.url.orderByCostAsc + this.productTypeId)
                .then((response) => {
                    this.flagProductsView = true;
                    this.products = response.data;
                })
        },
        getBooksOrderByCostDesc: function (){
            console.log(this.productTypeId)
            axios.get(this.url.orderByCostDesc + this.productTypeId)
                .then((response) => {
                    this.flagProductsView = true;
                    this.products = response.data;
                })
        },
        getBooksOrderByNameDesc: function (){
            console.log(this.productTypeId)
            axios.get(this.url.orderByNameDesc + this.productTypeId)
                .then((response) => {
                    this.flagProductsView = true;
                    this.products = response.data;
                })
        },
        getBooksOrderByNameAsc: function (){
            console.log(this.productTypeId)
            axios.get(this.url.orderByNameAsc + this.productTypeId)
                .then((response) => {
                    this.flagProductsView = true;
                    this.products = response.data;
                })
        }
    },
    beforeMount() {
        this.getBooks()
    }
}

Vue.createApp(app).mount('#booksView')


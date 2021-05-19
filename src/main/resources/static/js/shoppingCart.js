const app = {
    data() {
        return{
            products: [],
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
                },
                num: ''
            },
            purchasedProductId:'',
            flagProductsView: true,
            url:{
                order:'http://localhost:8080/shoppingCart/getOrder',
                ascNumOfProduct:'http://localhost:8080/shoppingCart/product/add/',
                deleteProductFromOrder:'http://localhost:8080/shoppingCart/product/delete/',
                descNumOfProduct:'http://localhost:8080/shoppingCart/product/desc/',
                buyOrder:'http://localhost:8080/shoppingCart/buyOrder',
                cancelOrder:'http://localhost:8080/shoppingCart/cancelOrder',
            }
        }
    },
    methods: {
        orderParser: function(response){
            let productsKey = Object.keys(response.data),
                productsVal = Object.keys(response.data).map(function (key){
                    return response.data[key];
                });
            for(let i = 0; i < productsKey.length; i++){
                this.product.id = productsKey[i].substring(productsKey[i].indexOf('id') + 3, productsKey[i].indexOf('name') - 2);
                productsKey[i] = productsKey[i].substring(productsKey[i].indexOf('name'), productsKey[i].length);
                this.product.name=productsKey[i].substring( 5, productsKey[i].indexOf('cost') - 2);
                productsKey[i] = productsKey[i].substring(productsKey[i].indexOf('cost'), productsKey[i].length);
                this.product.cost=productsKey[i].substring( 5, productsKey[i].indexOf('img_path') - 2);
                productsKey[i] = productsKey[i].substring(productsKey[i].indexOf('img_path'), productsKey[i].length);
                this.product.img_path=productsKey[i].substring( 9, productsKey[i].indexOf('description') - 2);
                productsKey[i] = productsKey[i].substring(productsKey[i].indexOf('description'), productsKey[i].length);
                this.product.description=productsKey[i].substring( 12, productsKey[i].indexOf('short') - 2);
                productsKey[i] = productsKey[i].substring(productsKey[i].indexOf('short'), productsKey[i].length);
                this.product.short_description=productsKey[i].substring( 18, productsKey[i].indexOf('maker') - 2);
                productsKey[i] = productsKey[i].substring(productsKey[i].indexOf('maker'), productsKey[i].length);
                this.product.maker.name=productsKey[i].substring( productsKey[i].indexOf('name') + 5, productsKey[i].indexOf('product_type') - 3);
                productsKey[i] = productsKey[i].substring(productsKey[i].indexOf('product_type'), productsKey[i].length);
                this.product.product_type.name=productsKey[i].substring( productsKey[i].indexOf('name') + 5, productsKey[i].length - 2);
                this.product.num = productsVal[i]
                this.products[i] = Object.assign({}, this.product);
            }
        },
        getOrder: function (){
            this.products = [];
            axios.get(this.url.order)
                .then((response) => {
                    this.flagProductsView = true;
                    this.orderParser(response)
                    console.log(this.products)
                })
        },
        deleteProduct: function(id){
            if(confirm('Вы точно хотите удалить товар из корзины?')){
                axios.get(this.url.deleteProductFromOrder + id);
                setTimeout(this.getOrder,3000);
            }
        },
        ascNumOfProduct: function (id){
            axios.get(this.url.ascNumOfProduct + id);
            setTimeout(this.getOrder,3000);
        },
        descNumOfProduct: function(id){
            if(this.products.find(el => el.id === id).num === 1){
                if(confirm('Вы точно хотите удалить товар из корзины?')){
                    axios.get(this.url.descNumOfProduct + id);
                    setTimeout(this.getOrder,3000);
                }
            }
            else {
                axios.get(this.url.descNumOfProduct + id);
                setTimeout(this.getOrder,3000);
            }
        },
        buyOrder: function (){
            if(this.products.length > 0){
                axios.get(this.url.buyOrder)
                alert('Спасибо за покупку! Подробную информацию о заказе и о месте где вы сможете его забрать ищите на почте, которую вы указывали при регистрации. Предупреждаем, оплата только наличными.');
                setTimeout(this.getOrder,3000);
            }else
                alert('Ваша корзина пуста');
        },
        cancelOrder:function (){
            if(confirm('Вы точно хотите отменить заказ?')) {
                axios.get(this.url.cancelOrder);
                setTimeout(this.getOrder,3000);
            }
        }

    },
    beforeMount() {
        this.getOrder()
    }
}

Vue.createApp(app).mount('#shoppingCart')
var app = new Vue({
    el:"#app",
    data() {
        return {
            item: {
                name: '',
                creation_date: '',
                price: '',
                // order:{
                //     id:'',
                //     order_date:''
                // }
                order_id:''
            },
            filter:{
                name:'',
                creation_date:'',
                price:'',
                order_id:''
            },
            items: [],
            filterItems:[],
            flagItemsView: false,
            flagFilterItemsView:false,
            delete_id:'',
            url: {
                allItems: 'http://localhost:8080/items/getItems',
                postItem: 'http://localhost:8080/items/addItem',
                deleteItem:'http://localhost:8080/items/delete_item',
                filteritem:'http://localhost:8080/items/filter'
            }


        }
    },
    methods: {
        getItems: function () {
            axios.get(this.url.allItems).then((response) => {
                this.flagItemsView = true;
                console.log(this.flagItemsView);
                console.log(response.data);
                this.items = response.data;
            })
        },
        addItem: function (){
            axios.post(this.url.postItem, {
                name: this.item.name,
                creation_date:this.item.creation_date,
                price:this.item.price,
                // order: {
                //     id: this.item.order.id
                //     //TODO Если не сработает добавь order_date
                // }
                order_id: this.item.order_id
            }).then((response) => {
                // console.log(this.item.order.id)
                console.log(response.httpRequestStatusCode);
            })
        },
        deleteItem: function (){
            console.log(this.delete_id)
            axios.post(this.url.deleteItem, {
                delete_id: this.delete_id
            }).then((response) => {
                console.log(this.delete_id)
                console.log(response);
            })
        },
        filterItemsAllProp: function (){
            axios.post(this.url.filteritem,{
                name: this.filter.name,
                creation_date:this.filter.creation_date,
                price: this.filter.price,
                order_id: this.filter.order_id
            }).then((response) => {
                this.flagFilterItemsView = true;
                this.filterItems = response.data;
                console.log(this.filterItems)
                console.log(response);
            })
        }
    }
})
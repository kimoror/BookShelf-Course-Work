const app = {
    data() {
        return{
            order:{
                id:'',
                status:''
            },
            url:{
                changeOrderStatus:'http://localhost:8080/admin/changeOrderStatus/'
            }
        }
    },
    methods: {
        changeOrderStatus: function (){
            axios.post(this.url.changeOrderStatus + this.order.id, {
                status: this.order.status
            })
        }
    }
}

Vue.createApp(app).mount('#admin')
const app = {
    data() {
        return{
            order:{
                id:'',
                status:''
            },
            user:{
                id:'',
                role:''
            },
            url:{
                changeOrderStatus:'http://localhost:8080/admin/changeOrderStatus/',
                changeUserRole:'http://localhost:8080/admin/changeUserRole/'
            }
        }
    },
    methods: {
        changeOrderStatus: function (){
            axios.post(this.url.changeOrderStatus + this.order.id, {
                status: this.order.status
            })
        },
        changeUserRole: function (){
            axios.post(this.url.changeUserRole + this.user.id,{
                role: this.user.role
            })
        }
    }
}

Vue.createApp(app).mount('#admin')
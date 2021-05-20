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
            message: {
                email:'',
                text: ''
            },
            url:{
                changeOrderStatus:'http://localhost:8080/admin/changeOrderStatus/',
                changeUserRole:'http://localhost:8080/admin/changeUserRole/',
                sendMessage:'http://localhost:8080/admin/sendMessage/'
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
        },
        sendMessage: function (){
            axios.post(this.url.sendMessage + this.message.email, {
                message: this.message.text
            })
        }

    }
}

Vue.createApp(app).mount('#admin')
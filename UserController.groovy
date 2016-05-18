package racetrac

class UserController {

    def index() {
        User user=new User(role:"admin" )
        [user:user]
    }
    def save(){
        def user =new User(params)
        user.save()
        render "Sucess"
    }
    def show(){
        def users=User.list()
        [users:users]
    }
    def login={}
    def logout = {
        flash.message = "Goodbye ${session.user.login}"
        session.user = null
        redirect(action:"login")
    }

    def delete ={
        User user = User.findById(params.id)
        if (user){
            user.delete()
            render "Deleted user"
        }
        else {
            flash.message = "User not found"
            redirect(action: "show")
        }
            }

    def authenticate = {
        def user =
                User.findByLoginAndPassword(params.login,
                        params.password)
        if(user){
            session.user = user
            flash.message = "Hello ${user.login}!"
            redirect(controller:"race", action:"index")
        }else{
            flash.message =
                    "Sorry, ${params.login}. Please try again."
            redirect(action:"login")
        }
    }
}

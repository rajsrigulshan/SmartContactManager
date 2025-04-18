
import { Button, Card, Checkbox, Label, TextInput } from "flowbite-react";
import { Link } from "react-router-dom";

function LoginForm() {
    return (
        <div className="flex justify-center items-center mt-2 overflow-auto">
            
            <Card className="max-w-xl">
                <h1 className="text-center font-bold text-2xl">Sign in</h1>
                <form className="flex flex-col gap-4">
                    <div>
                        <div className="mb-2 block">
                            <Label htmlFor="email1">Email</Label>
                        </div>
                        <TextInput id="email1" type="email" placeholder="yourmailid@scm.com" required size={80} />
                    </div>
                    <div>
                        <div className="mb-2 block">
                            <Label htmlFor="password1">Password</Label>
                        </div>
                        <TextInput id="password1" type="password" required />
                    </div>
                    <div className="flex items-center gap-2">
                        <Checkbox id="remember" />
                        <Label htmlFor="remember">Remember me</Label>
                    </div>
                    <Button type="submit">Submit</Button>
                    <p>Don't have accout ?   <Link to="/signup" className="text-blue-600 underline" type="url" >signup</Link></p>
                </form>
            </Card>
        </div>
    );
}

export default LoginForm;

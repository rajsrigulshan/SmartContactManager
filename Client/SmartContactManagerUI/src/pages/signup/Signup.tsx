import { Button, Card, Checkbox, Label, TextInput } from "flowbite-react";
import { Link } from "react-router-dom";

function Signup(){
    return(
        <div className="flex justify-center items-center mt-40 overflow-auto">
            
        <Card className="max-w-sm">
            <h1 className="text-center font-bold text-2xl">Sign Up</h1>
            <form className="flex flex-col gap-4">
                <div>
                    <div className="mb-2 block">
                        <Label htmlFor="email1">Your email</Label>
                    </div>
                    <TextInput id="email1" type="email" placeholder="Enter your email id" required size={70} />
                </div>
                <div>
                    <div className="mb-2 block">
                        <Label htmlFor="password1">Your password</Label>
                    </div>
                    <TextInput id="password1" type="password" required />
                </div>
                <div className="flex items-center gap-2">
                    <Checkbox id="remember" />
                    <Label htmlFor="remember">Remember me</Label>
                </div>
                <Button type="submit">Submit</Button>
                <p>Already have accout ?   <Link to="/login" className="text-blue-600 underline" type="url" >signin</Link></p>
            </form>
        </Card>
    </div>
    );
}
export default Signup;
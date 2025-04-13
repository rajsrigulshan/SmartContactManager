import { Button, Card, Label, Textarea, TextInput } from "flowbite-react";
import { Link } from "react-router-dom";

function Signup() {
    return (
        <div className="flex justify-center items-center mt-2 overflow-auto">
            <Card className="max-w-xl">
            <div className="p-0 gap-0 flex flex-col">
            <h2 className="text-center font-bold text-2xl">Sign Up</h2>
                <form className="flex flex-col gap-3">
                    <div>
                        <div className="mb-2 block">
                            <Label htmlFor="name">Name</Label>
                        </div>
                        <TextInput id="name" type="text" placeholder="Enter your name" required size={80} />
                    </div>
                    <div>
                        <div className="mb-2 block">
                            <Label htmlFor="email">Email</Label>
                        </div>
                        <TextInput id="email" type="email" placeholder="yourmailid@scm.com" required size={80} />
                    </div>
                    <div>
                        <div className="mb-2 block">
                            <Label htmlFor="contact">Contact Number</Label>
                        </div>
                        <TextInput id="contact" type="text" placeholder="+91-9999988888" required size={80} />
                    </div>
                    <div>
                        <div className="mb-2 block">
                            <Label htmlFor="password1">New Password</Label>
                        </div>
                        <TextInput id="password1" type="password" required />
                    </div>
                    <div className="max-w-2xl">
                        <div className="mb-2 block">
                            <Label htmlFor="comment">About Me</Label>
                        </div>
                        <Textarea id="comment" placeholder="write here..." rows={3} />
                    </div>
                   <div className="grid grid-cols-2 gap-2"> 
                    <Button type="submit">Submit</Button>
                    <Button className="bg-gray-300 text-black outline-none hover:bg-slate-300 dark:bg-gray-600  dark:outline-none dark:text-white dark:hover:bg-gray-700 ">Reset</Button>
                   </div>
                    <p>Already have accout ?   <Link to="/signin" className="text-blue-600 underline" type="url" >signin</Link></p>
                </form>
            </div>
            </Card>
        </div>
    );
}
export default Signup;
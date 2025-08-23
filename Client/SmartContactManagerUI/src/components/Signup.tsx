import { Alert, Button, Card, Label, Spinner, Textarea, TextInput } from "flowbite-react";
import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { userSignup } from "../services/authService/signupService";
import { signupSchema, signupSchemaType } from "../schema/signupSchema";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { FormStatus } from "../common/types";


function Signup() {

    const [loading, setLoading] = useState<boolean>(false);
    const [signupStatus, setSignupStatus] = useState<FormStatus>({ type: null, message: "" });
    const navigate = useNavigate();
    const { register, handleSubmit, reset, formState: { errors } } = useForm<signupSchemaType>({
        defaultValues: {
            name: "",
            email: "",
            phoneNumber: "",
            password: "",
            rePassword: "",
            description: ""
        },
        resolver: zodResolver(signupSchema)
    });

    useEffect(() => {
        if (signupStatus.message) {
            const timer = setTimeout(() => {
                setSignupStatus({ type: null, message: "" });
            }, 1000);
            return () => clearTimeout(timer);
        }
    }, [signupStatus.message]);


    const handleReset = () => {
        reset();
    }

    const signup = async (data: signupSchemaType) => {
        setLoading(true);

        try {
            const result = await userSignup(data);
            if (!result.isError) {
                reset();
                setSignupStatus({ type: "success", message: result.message || "Signup Successful!" });
                setTimeout(() => {
                    navigate('/signin');
                }, 200);
            }
            else {
                setSignupStatus({ type: "error", message: result.message || "Signup failed!" });
            }
        } catch (error) {
            setSignupStatus({ type: "error", message: "Unexpecte error occured!" })
        } finally {
            setLoading(false);
        }
    }

    return (
        <div className="flex justify-center relative">
            {signupStatus.type && (
                <div className="absolute top-4 right-4 z-50">
                    <Alert className={`${signupStatus.type === "error" ? 'bg-red-500 text-white  dark:bg-red-500 dark:text-white text-xl' : 'text-xl'}`}>
                        <span className="font-bold">{signupStatus.type === "error" ? 'Error!' : 'Success!'}</span> {signupStatus.message}
                    </Alert>
                </div>
            )}
            <div className="flex justify-center items-center mt-2 overflow-auto">
                <Card className="max-w-xl">
                    <div className="p-0 gap-0 flex flex-col">
                        <h2 className="text-center font-bold text-2xl">Sign Up</h2>
                        <form className="flex flex-col gap-3" onSubmit={handleSubmit(signup)} noValidate>
                            <div>
                                <div className="mb-2 block">
                                    <Label htmlFor="name">Name</Label>
                                </div>
                                <TextInput id="name" type="text" {...register("name")} placeholder="Enter your name" size={80}
                                    className={errors.name ? 'hasError' : ''}
                                />
                                {errors.name && <p className="text-red-500 text-sm">{errors.name.message}</p>}
                            </div>
                            <div>
                                <div className="mb-2 block">
                                    <Label htmlFor="email">Email</Label>
                                </div>
                                <TextInput id="email" type="email" {...register("email")} placeholder="yourmail@scm.com" size={80}
                                    className={errors.email ? 'hasError' : ''}
                                />
                                {errors.email && <p className="text-red-500 text-sm">{errors.email.message}</p>}
                            </div>
                            <div>
                                <div className="mb-2 block">
                                    <Label htmlFor="contact">Contact Number</Label>
                                </div>
                                <TextInput id="contact" type="text" {...register("phoneNumber")} placeholder="+91-9999988888" size={80}
                                    className={errors.phoneNumber ? 'hasError' : ''}
                                />
                                {errors.phoneNumber && <p className="text-red-500 text-sm">{errors.phoneNumber.message}</p>}
                            </div>

                            <div className="flex gap-4 w-full">
                                <div className="w-1/2 flex flex-col">
                                    <div>
                                        <div className="mb-2 block">
                                            <Label htmlFor="password">Password</Label>
                                        </div>
                                        <TextInput id="password" type="password" {...register("password")}
                                            className={errors.password ? 'hasEror' : ''}
                                        />
                                        {errors.password && <p className="text-red-500 text-sm">{errors.password.message}</p>}
                                    </div>

                                </div>
                                <div className="w-1/2 flex flex-col">
                                    <div>
                                        <div className="mb-2 block">
                                            <Label htmlFor="re-enter password">Re-Enter Password</Label>
                                        </div>
                                        <TextInput id="rePassword" type="text" {...register("rePassword")}
                                            className={errors.rePassword ? 'hasError' : ''}
                                        />
                                        {errors.rePassword && <p className="text-red-500 text-sm">{errors.rePassword.message}</p>}
                                    </div>

                                </div>

                            </div>
                            <div className="max-w-2xl">
                                <div className="mb-2 block">
                                    <Label htmlFor="about">About Me</Label>
                                </div>
                                <Textarea id="about"  {...register("description")} placeholder="write here..." rows={3} />
                            </div>
                            <div className="grid grid-cols-2 gap-2">
                                <Button type="submit" disabled={loading}>
                                    {loading ? <Spinner size="sm" /> : 'Submit'}
                                </Button>
                                <Button type="reset" onClick={handleReset} className="bg-gray-300 text-black outline-none hover:bg-slate-400 dark:bg-gray-600  dark:outline-none dark:text-white dark:hover:bg-gray-700 ">Reset</Button>
                            </div>
                            <p>Already have accout ?   <Link to="/signin" className="text-blue-600 underline" type="url" >signin</Link></p>
                        </form>
                    </div>
                </Card>
            </div>
        </div>
    );
}
export default Signup;
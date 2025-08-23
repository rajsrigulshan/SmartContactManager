import {Button, Card, Label, Spinner, TextInput } from "flowbite-react";
import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { userLogin } from "../services/authService/loginService";
import {useForm } from "react-hook-form";
import { loginSchema, LoginSchemaType } from "../schema/loginSchema";
import { zodResolver } from "@hookform/resolvers/zod";
import { FormStatus } from "../common/types";
import FlashMessage from "./FlashMessage";

function LoginForm() {

    const [loading, setLoading] = useState<boolean>(false);
    const [loginStatus, setLoginStatus] = useState<FormStatus>({ type: null, message: "" });
    const navigate = useNavigate();
    const { register, handleSubmit, formState: { errors } } = useForm<LoginSchemaType>({
        defaultValues: {
            email: "",
            password: ""
        },
        resolver: zodResolver(loginSchema)
    });




    useEffect(() => {
        if (loginStatus.message) {
            const timer = setTimeout(() => {
                setLoginStatus({ type: null, message: "" });
            }, 1000);
            return () => clearTimeout(timer);
        }
    }, [loginStatus.message]);



    const login = async (data: LoginSchemaType) => {
        setLoading(true);
        setLoginStatus({ type: null, message: "" });

        try {
            const result = await userLogin(data);
        if (!result.isError) {
            setLoginStatus({ type: "success", message: result.message || "Login Successful!" });
            setTimeout(() => {
                navigate('/user/users');
            }, 200);
        }
        else {
            setLoginStatus({ type: "error", message: result.message || "Login failed!" });
        }
            
        } catch (error) {
            setLoginStatus({type:"error",message:"Unexpecte error occured!"})
        }
        finally{
             setLoading(false);
        }
    }


    return (
        <div className="flex justify-center items-center mt-2 overflow-auto">
            {loginStatus.type && <FlashMessage loginStatus={loginStatus} />}
            <Card className="max-w-xl">
                <h1 className="text-center font-bold text-2xl">Sign in</h1>
                <form onSubmit={handleSubmit(login)} className="flex flex-col gap-4" noValidate>
                    <div>
                        <div className="mb-2 block">
                            <Label htmlFor="email1">Email</Label>
                        </div>
                        <TextInput id="email1"
                            type="email"
                            placeholder="yourmail@scm.com"
                            size={80}
                            className={errors.email ? 'hasError' : ''}
                            {...register("email")}
                        />
                        {errors.email && <p className="text-red-500 text-sm">{errors.email.message}</p>}
                    </div>
                    <div>
                        <div className="mb-2 block">
                            <Label htmlFor="password1">Password</Label>
                        </div>
                        <TextInput id="password1" {...register("password",{ required: true })} type="password" />
                        {errors.password && <p className="text-red-500 text-sm">{errors.password.message}</p>}
                    </div>
                    {/* <div className="flex items-center gap-2">
                        <Checkbox id="remember" />
                        <Label htmlFor="remember">Remember me</Label>
                    </div> */}
                    <Button type="submit" disabled={loading}>
                        {loading ? <Spinner size="sm" /> : 'Submit'}
                    </Button>
                    <p>Don't have accout ?   <Link to="/signup" className="text-blue-600 underline" type="url" >signup</Link></p>
                </form>
            </Card>
        </div>
    );
}

export default LoginForm;

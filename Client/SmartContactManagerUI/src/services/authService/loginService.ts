import axios, { AxiosResponse } from "axios";
import {LoginResponse } from "../../common/interfaces";
import {LoginSchemaType} from "../../schema/loginSchema"

export async function userLogin(loginData: LoginSchemaType): Promise<LoginResponse> {
    try {
        const response: AxiosResponse = await axios.post('http://127.0.0.1:8081/home/login', loginData, {
            validateStatus: (status) => {
                return (status >= 200 && status < 300) || (status >= 400 && status <= 410)
            }


        });
        if (response.status === 200) {
            console.log("JWT_TOKEN: ", response.data.data)
            localStorage.setItem("token", response.data.data);// to be continuied from here...
            return {
                isError: false,
                message: "login successfil",
            }
        } else {
            return {
                isError: true,
                message: "Password wrong! try again"
            }
        }

    } catch (error) {
        console.log("something Went Wrong..");
        return {
            isError: true,
            message: "login failed",
        }
    }
}

import { formDataType, signupResponse } from "../common/interfaces";
import axios, { AxiosResponse } from "axios";



export async function userSignup(userData: formDataType): Promise<signupResponse> {
    if (userData.password != userData.rePassword) {
        return {
            isError:true,
            message:'password not match'
        };
    }
    try {
        const response: AxiosResponse = await axios.post('http://127.0.0.1:8081/signup', userData);
        // await new Promise(resolve => setTimeout(resolve, 2000));
        if (response.status === 201) {
            return {
                isError:false,
                message:'Signup Succesful! '
            }
        }
        else {
           return {
            isError:true,
            message:`Signup failed: ${response.data?.message || ' An unexpected error occured.'}`
           }
        }
    } catch (error) {
        return {
            isError:true,
            message:'Signup failed: Could not send request. ',
            error:'Network Error!!!'
        }
    }

}
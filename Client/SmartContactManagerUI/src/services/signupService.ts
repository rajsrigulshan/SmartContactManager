import { formDataType, signupResponse } from "../common/interfaces";
import axios, { AxiosResponse } from "axios";



export async function userSignup(userData: formDataType): Promise<signupResponse> {
    try {
        const response: AxiosResponse = await axios.post('http://127.0.0.1:8081/user/signup', userData,{
            validateStatus: (status)=>{
                return (status>=200 && status<300) || (status>=400 && status<=410)
            }
        });
        // await new Promise(resolve => setTimeout(resolve, 2000));
        console.log("RESPONSE: "+response);
        if (response.status === 201) {
            return {
                isError:false,
                message:'Signup Succesful! '
            }
        }
        else if(response.status===409){
            return {
                isError:true,
                message:`Signup failed: ${response.data?.message || 'email already exists.'}`
               }
        }
        else if(response.status===400) {
           return {
            isError:true,
            message:`Signup failed: ${response.data?.message || 'Invalid Data'}`
           }
        }
        else{
            return {
                isError:true,
                message:`Signup failed: ${response.data?.message || ' An unexpected error occured.'}`
               }
        }
    } catch (error) {
        console.log("IN catch Block");        
        return {
            isError:true,
            message:'Signup failed: Could not send request. ',
            error:'Network Error!!!'
        }
    }

}
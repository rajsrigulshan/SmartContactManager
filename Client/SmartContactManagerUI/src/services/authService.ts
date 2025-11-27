import { ApiResponse, UserLoginResponseDTO } from "../common/interfaces";
import { LoginSchemaType } from "../schema/loginSchema";
import axiosInstance from "./axiosConf/axiosInstance";

export class AuthService{

      async login(loginData:LoginSchemaType):Promise<ApiResponse<UserLoginResponseDTO>>{
          try {
                const response=await axiosInstance.post<ApiResponse<UserLoginResponseDTO>>("/home/login",loginData);
                console.log("Response:------ ",JSON.stringify(response.data))
                return response.data;
          } catch (error) {
            throw "Something went Wrong!" 
          }

      }
}

const authService=new AuthService();
export default authService;
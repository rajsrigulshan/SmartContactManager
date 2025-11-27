 export interface formDataType{
    name:string,
    email:string,
    phoneNumber:string,
    password:string,
    rePassword:string,
    description:string
}

export interface signupResponse{
    isError:boolean,
    message:string,
    error ?:string
}

export interface SignupFormErrortype{
    name?:string;
    email?:string;
    phoneNumber?:string
    password?:string;
    rePassword?:string;
}
export interface LoginFormErrorType{
    email ?:string
}
export interface UserLoginResponseDTO{
    userId:string,
    name:string,
    userName:string,
    token:string
}

export interface ApiResponse<T>{
    success:boolean,
    message:string,
    data:T
}
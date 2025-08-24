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
export interface LoginResponse{
    isError:boolean,
    message:string,
    error ?:string
}
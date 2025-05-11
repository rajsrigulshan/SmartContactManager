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

export interface formErrortype{
    name?:string;
    email?:string;
    phoneNumber?:string
    password?:string;
    rePassword?:string;
}
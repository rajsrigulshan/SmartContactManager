import { z } from "zod";

export const signupSchema=z.object({
        name:z.string().nonempty("name is required"),
        email:z.email("please enter valid email."),
        phoneNumber:z.string().nonempty("phone number is required.").min(6,"more than 6 digits required").max(15,"less than 15 digits required"),
        password:z.string().min(8,"min 8 characters required"),
        rePassword:z.string(),
        description:z.string()
}).refine((data)=> data.password===data.rePassword,{
        message:"Passwords do not match",
        path:["rePassword"]
});


export type signupSchemaType=z.infer<typeof signupSchema>;
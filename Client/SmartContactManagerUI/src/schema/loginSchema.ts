import { z } from "zod";

export const loginSchema=z.object({
    email:z.email("please enter valid email"),
    password:z.string().nonempty("password is required")
})


export type LoginSchemaType=z.infer<typeof loginSchema>;
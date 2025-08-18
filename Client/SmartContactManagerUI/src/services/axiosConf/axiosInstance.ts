import axios,{InternalAxiosRequestConfig} from "axios";

const axiosInstance=axios.create({
        baseURL: import.meta.env.VITE_BASE_URL as string,
});

axiosInstance.interceptors.request.use(
    (config:InternalAxiosRequestConfig)=>{
        const token=localStorage.getItem('token');
        if(token){
            config.headers=config.headers ?? {};
            config.headers.Authorization=`Bearer ${token}`;
        }
        return config;
    },
    (error)=>{
        return Promise.reject("ERROR: while request setup, "+error);
    }
);

export default axiosInstance;
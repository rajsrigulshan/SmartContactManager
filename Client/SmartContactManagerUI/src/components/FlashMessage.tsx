import { Alert } from "flowbite-react";
import { FormStatus } from "../common/types.tsx";

function FlashMessage({ loginStatus }: { loginStatus: FormStatus }) {
    return (
        <div className="absolute top-20 right-4 z-50">
            <Alert className={`${loginStatus.type === "error" ? 'bg-red-500 text-white  dark:bg-red-500 dark:text-white text-xl' : 'text-xl'}`}>
                <span className="font-bold">{loginStatus.type === "error" ? 'Error!' : 'Success!'}</span> {loginStatus.message}
            </Alert>
        </div>
    );
}

export default FlashMessage;
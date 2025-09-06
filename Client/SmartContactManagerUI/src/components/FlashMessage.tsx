import { Alert } from "flowbite-react";
import { FormStatus } from "../common/types.tsx";


function FlashMessage({ flashMsgProp }: { flashMsgProp: FormStatus }) {
    return (
        <div className="absolute top-20 right-4 z-50">
            <Alert className={`${flashMsgProp.type === "error" ? 'bg-red-500 text-white  dark:bg-red-500 dark:text-white text-xl' : 'text-xl'}`}>
                <span className="font-bold">{flashMsgProp.type === "error" ? 'Error!' : 'Success!'}</span> {flashMsgProp.message}
            </Alert>
        </div>
    );
}

export default FlashMessage;
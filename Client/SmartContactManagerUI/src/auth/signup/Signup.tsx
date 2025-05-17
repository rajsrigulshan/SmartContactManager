import { Alert, Button, Card, Label, Spinner, Textarea, TextInput } from "flowbite-react";
import { FormEvent, useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { formDataType, formErrortype } from "../../common/interfaces";
import { userSignup } from "../../services/signupService";

function Signup() {

    const [formData, setFormData] = useState<formDataType>({
        name: '',
        email: '',
        phoneNumber: '',
        password: '',
        rePassword: '',
        description: ''
    });
    const [loading, setLoading] = useState<boolean>(false);
    const [error, setError] = useState<boolean>(false);
    const [formError, setFormError] = useState<formErrortype>({});
    const [message, setMessage] = useState<string>('');
    const [showToast, setShowToast] = useState<boolean>(false);
    const navigate = useNavigate();

    const resetFormData = () => {
        setFormData({
            name: '',
            email: '',
            phoneNumber: '',
            password: '',
            rePassword: '',
            description: ''
        })
    }
    useEffect(() => {
        if (message) {
            setShowToast(true);
            const timer = setTimeout(() => {
                setShowToast(false);
                setMessage('');
            }, 1000);
            return () => clearTimeout(timer);
        }
    }, [message]);

    const handleChange = (e: any) => {
        const { name, value } = e.target;
        setFormData((prevData) => ({
            ...prevData,
            [name]: value
        }));
        console.log(name,value)
        setFormError(prevErrors => ({ ...prevErrors, [name]: undefined }));
    }
//@raj: client-side  validation.
    const validate = (): boolean => {
        let newErrors: formErrortype = {};
        if (!formData.name.trim()) {
            newErrors.name='Name required!';
        }
        else if (formData.name.length < 3) {
            newErrors.name='Name must be at least 3 characer!'
        }
        if(!formData.email.trim()){
            newErrors.email='Email required!'
        }
        else if(!/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i.test(formData.email)){
            newErrors.email='Invalid email!'
        }
        if(!formData.phoneNumber.trim()){
            newErrors.phoneNumber='Contact number required!'
        }
        else if(formData.phoneNumber.length<6 || formData.phoneNumber.length>16){
            newErrors.phoneNumber='Invalid contact (min:6, max:15 required!)'
        }
        if(!formData.password.trim()){
            newErrors.password='Password required!'
        }
        else if(formData.password.length<8){
            newErrors.password='Password, min 8 characters required!'
        }
        if(formData.password !== formData.rePassword){
            newErrors.rePassword='Password mismatch!'
        }


        setFormError(newErrors);
        return Object.keys(newErrors).length === 0;
    };


    const handleSubmit = async (e: FormEvent) => {
        e.preventDefault();
        setLoading(true);
        setError(false);
        if (validate()) {
            const result = await userSignup(formData);
            setLoading(false);
            setError(result.isError);
            if (!result.isError) {
                setMessage(result.message || 'Signup successful!');
                resetFormData();
                setTimeout(() => {
                    navigate('/signin');
                }, 1200);
            }
            else {
                setMessage(result.message || 'Signup failed.');
                console.log('Signup error: ', result.message);

            }
        }
        setLoading(false);
    }

    return (
        <div className="flex justify-center relative">
            {showToast && (
                <div className="absolute top-4 right-4 z-50">
                    {/* <Alert color={error ? 'failure' : 'success'}> */}
                    <Alert className={`${error ? 'bg-red-500 text-white  dark:bg-red-500 dark:text-white text-xl' : 'text-xl'}`}>
                        <span className="font-bold">{error ? 'Error!' : 'Success!'}</span> {message}
                    </Alert>
                </div>
            )}
            <div className="flex justify-center items-center mt-2 overflow-auto">
                <Card className="max-w-xl">
                    <div className="p-0 gap-0 flex flex-col">
                        <h2 className="text-center font-bold text-2xl">Sign Up</h2>
                        <form className="flex flex-col gap-3" onSubmit={handleSubmit}>
                            <div>
                                <div className="mb-2 block">
                                    <Label htmlFor="name">Name</Label>
                                </div>
                                <TextInput id="name" name="name" type="text" value={formData.name} onChange={handleChange} placeholder="Enter your name"  size={80}
                                   className={formError.name?'hasError':''} 
                                />
                                {formError.name && <p className="text-red-500 text-sm">{formError.name}</p>}
                            </div>
                            <div>
                                <div className="mb-2 block">
                                    <Label htmlFor="email">Email</Label>
                                </div>
                                <TextInput id="email" name="email" type="email" value={formData.email} onChange={handleChange} placeholder="yourmailid@scm.com"  size={80}
                                className={formError.email?'hasError':''}
                                />
                                {formError.email && <p className="text-red-500 text-sm">{formError.email}</p>}
                            </div>
                            <div>
                                <div className="mb-2 block">
                                    <Label htmlFor="contact">Contact Number</Label>
                                </div>
                                <TextInput id="contact" name="phoneNumber" type="text" value={formData.phoneNumber} onChange={handleChange} placeholder="+91-9999988888"  size={80}
                                className={formError.phoneNumber?'hasError':''}
                                />
                                {formError.phoneNumber && <p className="text-red-500 text-sm">{formError.phoneNumber}</p>}
                            </div>

                            <div className="flex gap-4 w-full">
                                <div className="w-1/2 flex flex-col">
                                    <div>
                                        <div className="mb-2 block">
                                            <Label htmlFor="password">Password</Label>
                                        </div>
                                        <TextInput id="password" name="password" type="password" value={formData.password} onChange={handleChange}  
                                        className={formError.password?'hasEror':''}
                                        />
                                        {formError.password && <p className="text-red-500 text-sm">{formError.password}</p>}
                                    </div>

                                </div>
                                <div className="w-1/2 flex flex-col">
                                    <div>
                                        <div className="mb-2 block">
                                            <Label htmlFor="re-enter password">Re-Enter Password</Label>
                                        </div>
                                        <TextInput id="rePassword" name="rePassword" type="text" value={formData.rePassword} onChange={handleChange}  
                                        className={formError.rePassword?'hasError':''}
                                        />
                                        {formError.rePassword && <p className="text-red-500 text-sm">{formError.rePassword}</p>}
                                    </div>

                                </div>

                            </div>
                            <div className="max-w-2xl">
                                <div className="mb-2 block">
                                    <Label htmlFor="about">About Me</Label>
                                </div>
                                <Textarea id="about" name="description" value={formData.description} onChange={handleChange} placeholder="write here..." rows={3} />
                            </div>
                            <div className="grid grid-cols-2 gap-2">
                                <Button type="submit" disabled={loading}>
                                    {loading ? <Spinner size="sm" /> : 'Submit'}
                                </Button>
                                <Button type="reset" onClick={resetFormData} className="bg-gray-300 text-black outline-none hover:bg-slate-400 dark:bg-gray-600  dark:outline-none dark:text-white dark:hover:bg-gray-700 ">Reset</Button>
                            </div>
                            <p>Already have accout ?   <Link to="/signin" className="text-blue-600 underline" type="url" >signin</Link></p>
                        </form>
                    </div>
                </Card>
            </div>
        </div>
    );
}
export default Signup;
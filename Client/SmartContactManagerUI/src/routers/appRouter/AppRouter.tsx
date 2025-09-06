import {Routes,Route} from 'react-router-dom';

import SCMHome from '../../pages/home/SCMHome.tsx';
import SCMAbout from '../../pages/about/SCMAbout';
import LoginForm from '../../components/Signin.tsx';
import Signup from '../../components/Signup.tsx';

function AppRouter(){
    return(
        <Routes>
            <Route path='/' element={<SCMHome/>}/>
            <Route path='/about' element={<SCMAbout/>}/>
            <Route path='/signin' element={<LoginForm/>}/>
            <Route path='/signup' element={<Signup/>}/>
        </Routes>
    );
}

export default AppRouter;
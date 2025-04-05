import {Routes,Route} from 'react-router-dom';

import SCMHome from '../../pages/home/SCMHome.tsx';
import SCMAbout from '../../pages/about/SCMAbout';

function AppRouter(){
    return(
        <Routes>
            <Route path='/' element={<SCMHome/>}/>
            <Route path='/about' element={<SCMAbout/>}/>
        </Routes>
    );
}

export default AppRouter;
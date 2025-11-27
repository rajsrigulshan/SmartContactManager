import { Button, Navbar, NavbarCollapse, NavbarToggle, SunIcon, useThemeMode} from "flowbite-react";
import useThemeToggler from "../../hooks/useThemeToggler";
import { MoonIcon } from "flowbite-react";
import { Link,NavLink, useNavigate } from "react-router-dom";


function NavbarComponent(){
  const {toggleTheme}=useThemeToggler();
  const{mode}=useThemeMode();
  const navigate =useNavigate();

    const getLinkClass = ({ isActive }: { isActive: boolean }) =>
      `mr-3 pb-1 pl-1 hover:text-primary-500 dark:hover:text-blue-500 ${
        isActive ? "text-primary-700 dark:text-blue-500 font-semibold" : "text-gray-700 dark:text-gray-300"
      }`;

    return (
    <Navbar fluid  className="bg-slate-200">
       <div className="flex items-center">
       <Link to="/about"><img src="https://flowbite.com/docs/images/logo.svg" className="mr-3 h-6 sm:h-9" alt="Flowbite React Logo" /></Link>
       <Link to="/"><span className="self-center whitespace-nowrap text-xl font-semibold dark:text-white">SCM_2.0</span></Link>
       </div>
      <div className="flex md:order-2">
       <div className="flex justify-center items-center mr-2">
       {mode === 'dark' ? <SunIcon onClick={toggleTheme} className="h-5 w-5" /> : <MoonIcon onClick={toggleTheme} className="h-5 w-5" />}
       </div>
        <Button onClick={()=>navigate("/login")} className="mr-1" style={{userSelect:'none'}}>Signin</Button>
        <Button onClick={()=>navigate("/signup")} className="mr-2" style={{userSelect:'none'}}>Signup</Button>
        <NavbarToggle />
      </div>
      <NavbarCollapse>

<div className="flex flex-col md:flex-row justify-center">
          <NavLink to="/" className={getLinkClass}>
            Home
          </NavLink>
          <NavLink to="/about" className={getLinkClass}>
            About
          </NavLink>
          <NavLink to="/services" className={getLinkClass}>
            Services
          </NavLink>
          <NavLink to="/contact" className={getLinkClass}>
            Contact
          </NavLink>
        </div>
      </NavbarCollapse>
    </Navbar>


    );
}

export default NavbarComponent;
import { Button, Navbar, NavbarBrand, NavbarCollapse, NavbarLink, NavbarToggle, SunIcon, useThemeMode} from "flowbite-react";
import useThemeToggler from "../../hooks/useThemeToggler";
import { MoonIcon } from "flowbite-react";
import { Link } from "react-router-dom";




function NavbarComponent(){
  const {toggleTheme}=useThemeToggler();
  const{mode}=useThemeMode();
    return (
    <Navbar fluid  className="bg-slate-200">
      <NavbarBrand>
        <Link to="/"><img src="https://flowbite.com/docs/images/logo.svg" className="mr-3 h-6 sm:h-9" alt="Flowbite React Logo" /></Link>
        <Link to="/"><span className="self-center whitespace-nowrap text-xl font-semibold dark:text-white">SCM_2.0</span></Link>
      </NavbarBrand>
      <div className="flex md:order-2">
       <div className="flex justify-center items-center mr-2">
       {mode === 'dark' ? <SunIcon onClick={toggleTheme} className="h-5 w-5" /> : <MoonIcon onClick={toggleTheme} className="h-5 w-5" />}
       </div>
        <Link to="/signin"><Button className="mr-1" style={{userSelect:'none'}}>Signin</Button></Link>
        <Link to="/signup"><Button className="mr-2" style={{userSelect:'none'}}>Signup</Button></Link>
        <NavbarToggle />
      </div>
      <NavbarCollapse>
        <NavbarLink active>
          <Link to="/"> Home </Link>
        </NavbarLink>

        <NavbarLink>
          <Link to="/about">About</Link>
        </NavbarLink>

        <NavbarLink href="#">Services</NavbarLink>
        <NavbarLink href="#">Pricing</NavbarLink>
        <NavbarLink href="#">Contact</NavbarLink>
      </NavbarCollapse>
    </Navbar>


    );
}

export default NavbarComponent;
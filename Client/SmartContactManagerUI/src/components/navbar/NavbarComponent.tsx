import { Button, DarkThemeToggle, Navbar, NavbarBrand, NavbarCollapse, NavbarLink, NavbarToggle, SunIcon, useThemeMode} from "flowbite-react";
import useThemeToggler from "../../hooks/useThemeToggler";
import { MoonIcon } from "flowbite-react";
DarkThemeToggle



function NavbarComponent(){
  const {toggleTheme}=useThemeToggler();
  const{mode}=useThemeMode();
    return (
    <Navbar fluid className="bg-slate-200">
      <NavbarBrand href="/">
        <img src="https://flowbite.com/docs/images/logo.svg" className="mr-3 h-6 sm:h-9" alt="Flowbite React Logo" />
        <span className="self-center whitespace-nowrap text-xl font-semibold dark:text-white">SCM_2.0</span>
      </NavbarBrand>
      <div className="flex md:order-2">
       <div className="flex justify-center items-center mr-2">
       {mode === 'dark' ? <SunIcon onClick={toggleTheme} className="h-5 w-5" /> : <MoonIcon onClick={toggleTheme} className="h-5 w-5" />}
       </div>
        <Button className="mr-1">Login</Button>
        <Button className="mr-2">Signup</Button>
        <NavbarToggle />
      </div>
      <NavbarCollapse>
        <NavbarLink active href="/">
          Home
        </NavbarLink>

        <NavbarLink href="/about">
          About
        </NavbarLink>

        <NavbarLink href="#">Services</NavbarLink>
        <NavbarLink href="#">Pricing</NavbarLink>
        <NavbarLink href="#">Contact</NavbarLink>
      </NavbarCollapse>
    </Navbar>


    );
}

export default NavbarComponent;
import { Button, Navbar, NavbarBrand, NavbarCollapse, NavbarLink, NavbarToggle,DarkThemeToggle } from "flowbite-react";

function NavbarComponent(){
    return (

    <Navbar fluid>
      <NavbarBrand href="/">
        <img src="https://flowbite.com/docs/images/logo.svg" className="mr-3 h-6 sm:h-9" alt="Flowbite React Logo" />
        <span className="self-center whitespace-nowrap text-xl font-semibold dark:text-white">SCM_2.0</span>
      </NavbarBrand>
      <div className="flex md:order-2">
        <Button className="mr-1">Login</Button>
        <Button className="mr-1">Signup</Button>
        <DarkThemeToggle/>
        <NavbarToggle />
      </div>
      <NavbarCollapse>
        <NavbarLink href="#"active>
          Home
        </NavbarLink>
        <NavbarLink href="#">About</NavbarLink>
        <NavbarLink href="#">Services</NavbarLink>
        <NavbarLink href="#">Pricing</NavbarLink>
        <NavbarLink href="#">Contact</NavbarLink>
      </NavbarCollapse>
    </Navbar>


    );
}

export default NavbarComponent;
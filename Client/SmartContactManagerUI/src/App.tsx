import NavbarComponent from "./components/navbar/NavbarComponent.tsx";


import useLoadTheme from "./hooks/useLoadTheme.ts";
import { Outlet } from "react-router-dom";

function App() {
  useLoadTheme();
  return (
    <div className="SCM_2.0 dark:text-white dark:bg-gray-700 flex flex-col h-screen w-screen">
      <div className="SCM_header">
        <NavbarComponent/>
        <Outlet/>
      </div>
    </div>

  )
}

export default App

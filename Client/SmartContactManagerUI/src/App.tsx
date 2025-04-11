import NavbarComponent from "./components/navbar/NavbarComponent.tsx";
import AppRouter from "./routers/appRouter/AppRouter.tsx";
import { BrowserRouter as Router } from "react-router-dom";
import useLoadTheme from "./hooks/useLoadTheme.ts";


function App() {
  useLoadTheme();
  return (
    <Router>
    <div className="SCM_2.0 dark:text-white dark:bg-gray-700 flex flex-col h-screen w-screen">
      <div className="SCM_header">
        <NavbarComponent/>
        <AppRouter/>
      </div>
    </div>
  </Router>

  )
}

export default App

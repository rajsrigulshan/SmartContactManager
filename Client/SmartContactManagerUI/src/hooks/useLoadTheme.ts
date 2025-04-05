import { ThemeMode, useThemeMode } from "flowbite-react";
import { useEffect } from "react";

function useLoadTheme(){
    const {setMode}=useThemeMode();
    useEffect(()=>{
        console.log("useEffect triggered...."+Date.now());
        
        const storedTheme=localStorage.getItem('theme');
        if(storedTheme){
          setMode(storedTheme as ThemeMode)
        }
      },[]);
}
export default useLoadTheme;